package test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.tuple.Tuple6;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.StateBackend;
import org.apache.flink.runtime.state.filesystem.FsStateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.*;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Author: Wang Pei
 * Summary:
 *   基于Broadcast State 动态更新配置以实现实时过滤数据并增加字段
 */
@Slf4j
public class TestBroadcastState {
    public static void main(String[] args) throws Exception{

//        //1、解析命令行参数
//        ParameterTool fromArgs = ParameterTool.fromArgs(args);
//        ParameterTool parameterTool = ParameterTool.fromPropertiesFile(fromArgs.getRequired("applicationProperties"));
//
//        //checkpoint配置
//        String checkpointDirectory = parameterTool.getRequired("checkpointDirectory");
//        long checkpointSecondInterval = parameterTool.getLong("checkpointSecondInterval");
//
//        //事件流配置
//        String fromKafkaBootstrapServers = parameterTool.getRequired("fromKafka.bootstrap.servers");
//        String fromKafkaGroupID = parameterTool.getRequired("fromKafka.group.id");
//        String fromKafkaTopic = parameterTool.getRequired("fromKafka.topic");
//
//        //配置流配置
//        String fromMysqlHost = parameterTool.getRequired("fromMysql.host");
//        int fromMysqlPort = parameterTool.getInt("fromMysql.port");
//        String fromMysqlDB = parameterTool.getRequired("fromMysql.db");
//        String fromMysqlUser = parameterTool.getRequired("fromMysql.user");
//        String fromMysqlPasswd = parameterTool.getRequired("fromMysql.passwd");
//        int fromMysqlSecondInterval = parameterTool.getInt("fromMysql.secondInterval");

        //2、配置运行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setParallelism(1);
        //设置StateBackend
//        env.setStateBackend((StateBackend) new FsStateBackend(checkpointDirectory, true));
//        //设置Checkpoint
//        CheckpointConfig checkpointConfig = env.getCheckpointConfig();
//        checkpointConfig.setCheckpointInterval(checkpointSecondInterval * 1000);
//        checkpointConfig.setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
//        checkpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

        //3、Kafka事件流
        //从Kafka中获取事件数据
        //数据：某个用户在某个时刻浏览或点击了某个商品,如
//        //{"userID": "user_3", "eventTime": "2019-08-17 12:19:47", "eventType": "browse", "productID": 1}
//        Properties kafkaProperties = new Properties();
//        kafkaProperties.put("bootstrap.servers",fromKafkaBootstrapServers);
//        kafkaProperties.put("group.id",fromKafkaGroupID);
//
//        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(fromKafkaTopic, new SimpleStringSchema(), kafkaProperties);
//        kafkaConsumer.setStartFromLatest();
//        DataStream<String> kafkaSource = env.addSource(kafkaConsumer).name("KafkaSource").uid("source-id-kafka-source");
//
//        SingleOutputStreamOperator<Tuple4<String, String, String, Integer>> eventStream = kafkaSource.process(new ProcessFunction<String, Tuple4<String, String, String, Integer>>() {
//            @Override
//            public void processElement(String value, Context ctx, Collector<Tuple4<String, String, String, Integer>> out){
//                try {
//                    JSONObject obj = JSON.parseObject(value);
//                    String userID = obj.getString("userID");
//                    String eventTime = obj.getString("eventTime");
//                    String eventType = obj.getString("eventType");
//                    int productID = obj.getIntValue("productID");
//                    out.collect(new Tuple4<>(userID, eventTime, eventType, productID));
//                }catch (Exception ex){
////                    log.warn("异常数据:{}",value,ex);
//                }
//            }
//        });
//        eventStream.print();

        //4、Mysql配置流
        //自定义Mysql Source，周期性地从Mysql中获取配置，并广播出去
        //数据: 用户ID,用户姓名，用户年龄
        DataStreamSource<HashMap<String, Tuple2<String, String>>> configStream = env.addSource(new MysqlSource());
//        configStream.print();

        DataStreamSource<HashMap<String, Tuple2<String, String>>> eventStream = env.addSource(new MysqlSource(1));
//        eventStream.print();


        /*
          (1) 先建立MapStateDescriptor
          MapStateDescriptor定义了状态的名称、Key和Value的类型。
          这里，MapStateDescriptor中，key是Void类型，value是Map<String, Tuple2<String,Int>>类型。
         */
        MapStateDescriptor<Void, Map<String, Tuple2<String,String>>> configDescriptor = new MapStateDescriptor<>("config", Types.VOID, Types.MAP(Types.STRING, Types.TUPLE(Types.STRING, Types.STRING)));
        /*
          (2) 将配置流广播，形成BroadcastStream
         */
        BroadcastStream<HashMap<String, Tuple2<String, String>>> broadcastConfigStream = configStream.broadcast(configDescriptor);

        //5、事件流和广播的配置流连接，形成BroadcastConnectedStream
        BroadcastConnectedStream<HashMap<String, Tuple2<String, String>>, HashMap<String, Tuple2<String, String>>> connectedStream = eventStream.connect(broadcastConfigStream);
        //6、对BroadcastConnectedStream应用process方法，根据配置(规则)处理事件
        SingleOutputStreamOperator<Tuple6<String, String, String, String, String, String>> resultStream = connectedStream.process(new CustomBroadcastProcessFunction());

        //7、输出结果
        resultStream.print();

        //8、生成JobGraph，并开始执行
        env.execute();

    }

    /**
     * 自定义BroadcastProcessFunction
     * 当事件流中的用户ID在配置中出现时，才对该事件处理, 并在事件中补全用户的基础信息
     * Tuple4<String, String, String, Integer>: 第一个流(事件流)的数据类型
     * HashMap<String, Tuple2<String, Integer>>: 第二个流(配置流)的数据类型
     * Tuple6<String, String, String, Integer,String, Integer>: 返回的数据类型
     */
    static class CustomBroadcastProcessFunction extends BroadcastProcessFunction<HashMap<String, Tuple2<String, String>>, HashMap<String, Tuple2<String, String>>, Tuple6<String, String, String, String, String, String>>{

        /**定义MapStateDescriptor*/
        MapStateDescriptor<Void, Map<String, Tuple2<String,String>>> configDescriptor = new MapStateDescriptor<>("config", Types.VOID, Types.MAP(Types.STRING, Types.TUPLE(Types.STRING, Types.STRING)));

        /**
         * 读取状态，并基于状态，处理事件流中的数据
         * 在这里，从上下文中获取状态，基于获取的状态，对事件流中的数据进行处理
         * @param value 事件流中的数据
         * @param ctx 上下文
         * @param out 输出零条或多条数据
         * @throws Exception
         */
        @Override
        public void processElement(HashMap<String, Tuple2<String, String>> value, ReadOnlyContext ctx, Collector<Tuple6<String, String, String, String, String, String>> out) throws Exception {

            //获取状态
            ReadOnlyBroadcastState<Void, Map<String, Tuple2<String, String>>> broadcastState = ctx.getBroadcastState(configDescriptor);
            Map<String, Tuple2<String, String>> broadcastStateUserInfo = broadcastState.get(null);
            if (null == broadcastStateUserInfo) {
                System.out.println("wait broadcastStateUserInfo init");

//                Thread.sleep(3000);
//                broadcastStateUserInfo = broadcastState.get(null);
                return ;
            }
            System.out.println("broadcastStateUserInfo:" + broadcastStateUserInfo);

            //事件流中的用户ID
            System.out.println("value:" + value);
//            System.exit(0);
            String teminal = "000006099999";
            Tuple2<String, String> stringStringTuple2 = value.get(teminal);
            System.out.println(stringStringTuple2);
//            System.exit(0);

            Tuple2<String, String> stringStringTuple21 = broadcastStateUserInfo.get(teminal);
            System.out.println(stringStringTuple21);
//            System.exit(0);

            if (stringStringTuple2.equals(stringStringTuple21)) {
                out.collect((new Tuple6<>(teminal, stringStringTuple2.f0, stringStringTuple2.f1, teminal, stringStringTuple21.f0, stringStringTuple21.f1)));
            }

//
//            //配置中有此用户，则在该事件中添加用户的userName、userAge字段。
//            //配置中没有此用户，则丢弃
//            Tuple2<String, String> userInfo = broadcastStateUserInfo.get(userID);
//            if(userInfo!=null){
////                out.collect(new Tuple6<>(value.f0,value.f1,value.f2,value.f3,userInfo.f0,userInfo.f1));
//            }

        }

        /**
         * 处理广播流中的每一条数据，并更新状态
         * @param value 广播流中的数据
         * @param ctx 上下文
         * @param out 输出零条或多条数据
         * @throws Exception
         */
        @Override
        public void processBroadcastElement(HashMap<String, Tuple2<String, String>> value, Context ctx, Collector<Tuple6<String, String, String, String, String, String>> out) throws Exception {

            //获取状态
            BroadcastState<Void, Map<String, Tuple2<String, String>>> broadcastState = ctx.getBroadcastState(configDescriptor);
//            System.out.println("broadcastState2：" + broadcastState);

            //清空状态
            broadcastState.clear();

            //更新状态
            broadcastState.put(null, value);

//            System.out.println("broadcastState3：" + broadcastState);

        }
    }



    /**
     * 自定义Mysql Source，每隔 secondInterval 秒从Mysql中获取一次配置
     */
    static class MysqlSource extends RichSourceFunction<HashMap<String, Tuple2<String, String>>> {

        private volatile boolean isRunning = true;

        private DruidDataSource dataSource ;

        private Connection connection;
        private PreparedStatement preparedStatement;

        private int secondInterval = 5;


        public MysqlSource(int secondInterval) {
            this.secondInterval = secondInterval;
        }

        public MysqlSource() {
        }

        /**
         * 开始时, 在open()方法中建立连接
         * @param parameters
         * @throws Exception
         */
        @Override
        public void open(Configuration parameters) throws Exception {
            super.open(parameters);

            dataSource = new DruidDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl("jdbc:postgresql://172.16.220.102:8000/ld_holo");
            dataSource.setUsername("LTAI4G3rrMuahiuon1qsanyf");
            dataSource.setPassword("49b359mzNyqwjyb3ZMQ4X0eFGh9BDq");

            connection = dataSource.getConnection();

            String sql="select terminal,updatetime,area_name from public.cdt_device_dim where terminal in ('000006099999', '000006000961')";
            preparedStatement=connection.prepareStatement(sql);
        }

        /**
         * 执行完，调用close()方法关系连接，释放资源
         * @throws Exception
         */
        @Override
        public void close() throws Exception {
            super.close();

            if(preparedStatement !=null){
                preparedStatement.close();
            }
            if(connection!=null){
                connection.close();
            }
            if(preparedStatement !=null){
                preparedStatement.close();
            }
        }

        /**
         * 调用run()方法获取数据
         * @param ctx
         */
        @Override
        public void run(SourceContext<HashMap<String, Tuple2<String, String>>> ctx) {
            try {
                while (isRunning){
                    HashMap<String, Tuple2<String, String>> output = new HashMap<>();
                    ResultSet resultSet = preparedStatement.executeQuery();
//                    System.out.println(resultSet);
//                    System.exit(0);
                    while (resultSet.next()){
//                        System.out.println(resultSet);
//                        System.exit(0);
                        String terminal = resultSet.getString("terminal");
                        String updatetime = resultSet.getString("updatetime");
                        String  area_name = resultSet.getString("area_name");
                        output.put(terminal, new Tuple2<>(updatetime,area_name));
                    }
                    ctx.collect(output);
                    //每隔多少秒执行一次查询
                    Thread.sleep(1000*secondInterval);
                }
            } catch (Exception ex){
//                log.error("从Mysql获取配置异常...",ex);
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

        }

        /**
         * 取消时，会调用此方法
         */
        @Override
        public void cancel() {
            isRunning = false;
        }
    }
}
