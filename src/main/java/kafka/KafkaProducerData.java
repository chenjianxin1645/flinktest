package kafka;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.HashMap;
import java.util.Random;

public class KafkaProducerData {
    public static void main(String[] args) throws Exception {
        // 初始化环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(4);

        // 配置Kafka
        HashMap<String, String> properties = new HashMap<>();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("topic", "test");
        ParameterTool parameterTool  = ParameterTool.fromMap(properties);
        // 添加source
        DataStream<String> messageStream = env.addSource(new SourceFunction<String>() {
            private Random r = new Random();
            boolean running = true;

            @Override
            public void run(SourceContext<String> ctx) throws Exception {
                while(running) {
                    Thread.sleep(r.nextInt(3000));
                    ctx.collect(String.format("%d,%d", System.currentTimeMillis(), r.nextInt(1000)));
                }
            }

            @Override
            public void cancel() {
                running = false;
            }
        });
        messageStream.print();

        // 输出sink
        FlinkKafkaProducer<String> producer = new FlinkKafkaProducer<>(
                parameterTool.getRequired("topic"),
                new SimpleStringSchema(),
                parameterTool.getProperties()
        );
        messageStream.addSink(producer);

        // 启动任务
        env.execute("ProducerData execute");
    }
}
