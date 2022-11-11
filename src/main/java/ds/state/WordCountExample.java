package ds.state;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.accumulators.IntCounter;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.RichFilterFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.FilterOperator;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.configuration.ConfigOptions;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.util.Collector;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class WordCountExample {

    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings est = EnvironmentSettings.newInstance().build();
        TableEnvironment tenv = TableEnvironment.create(est);

//        DataSet<String> text = env.fromElements(
//                "Who's there?",
//                "I think I hear them. Stand, ho! Who's there?");
//
//        DataSet<Tuple2<String, Integer>> wordCounts = text
//                .flatMap(new LineSplitter())
//                .groupBy(0)
////                .sum(1);
//                .first(2);
//
//        // read a CSV file with five fields, taking only two of them
//        DataSet<Tuple2<String, Double>> csvInput = env.readCsvFile("hdfs:///the/CSV/file")
//                .includeFields("10010")  // take the first and the fourth field
//                .types(String.class, Double.class);
//
//        wordCounts.print();


//        // 1. The DataSet to be broadcast
//        DataSet<Integer> toBroadcast = env.fromElements(1, 2, 3);
//
//        DataSet<String> data = env.fromElements("a", "b");
//
//        MapOperator<String, String> map = data.map(new RichMapFunction<String, String>() {
//            Collection<Integer> broadcastSet;
//
//            @Override
//            public void open(Configuration parameters) throws Exception {
//                // 3. Access the broadcast DataSet as a Collection
//                this.broadcastSet = getRuntimeContext().getBroadcastVariable("broadcastSetName");
//            }
//
//
//            @Override
//            public String map(String value) throws Exception {
//                System.out.println(value);
//                System.out.println(broadcastSet);
//
//                return value + broadcastSet.toString();
//            }
//        }).withBroadcastSet(toBroadcast, "broadcastSetName");// 2. Broadcast the DataSet
//        map.print();


        // register a file from HDFS
//        env.registerCachedFile("hdfs://emr-cluster/tmp/xander/flink/data/20200408/12.txt", "hdfsFile");
//
//        DataSet<String> input = env.fromElements("a", "b");
//        DataSet<String> result = input.map(new MyMapper());
//        result.print();


//        DataSet<Integer> toFilter = env.fromElements(1, 2, 3);
//
//        Configuration config = new Configuration();
//        config.setInteger("limit", 1);
//
//        FilterOperator<Integer> filter = toFilter.filter(new RichFilterFunction<Integer>() {
//            private int limit;
//
//            // 定义累加器
//            private IntCounter numLines = new IntCounter();
//
//
//            @Override
//            public void open(Configuration parameters) throws Exception {
//                ConfigOption<Integer> limit = ConfigOptions.key("limit").intType().defaultValue(1);
//
//                this.limit = parameters.getInteger(limit);
////                System.out.println(limit);
////                System.out.println(this.limit);
////                System.exit(0);
//
//                // 注册累加器
//                getRuntimeContext().addAccumulator("num-lines", this.numLines);
//            }
//
//            @Override
//            public boolean filter(Integer value) throws Exception {
////                System.out.println(value + "-" + limit);
//
//                // 累加器计数
//                this.numLines.add(1);
//
//                return value > limit;
//            }
//        }).withParameters(config);
//        filter.print();
//
//        filter.writeAsText("test.txt", FileSystem.WriteMode.OVERWRITE);
//        JobExecutionResult execute = env.execute("ewwqwq");
//        Object accumulatorResult = execute.getAccumulatorResult("num-lines");
//        System.out.println(accumulatorResult);


        DataSet<Integer> ds = env.fromElements(1, 2, 3);


    }

    public static class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        @Override
        public void flatMap(String line, Collector<Tuple2<String, Integer>> out) {
            for (String word : line.split(" ")) {
                out.collect(new Tuple2<String, Integer>(word, 1));
            }
        }
    }


    // extend a RichFunction to have access to the RuntimeContext
    public static class MyMapper extends RichMapFunction<String, String> {
        private File myFile;

        @Override
        public void open(Configuration config) {

            // access cached file via RuntimeContext and DistributedCache
            this.myFile = getRuntimeContext().getDistributedCache().getFile("hdfsFile");
            // read the file (or navigate the directory)

            System.out.println(myFile);


        }

        @Override
        public String map(String value) throws Exception {
            // use content of cached file
//            System.out.println(value);
//            System.out.println(myFile);

            List<String> strings = FileUtils.readLines(myFile, "UTF-8");
            Iterator<String> iterator = strings.iterator();
            while (iterator.hasNext()) {
                String text = iterator.next();
                System.out.println(text);
                JSONObject jsonObject = JSON.parseObject(text);

                System.out.println(jsonObject.getJSONObject("data").getString("terminal"));
//                System.exit(0);
            }

            return value;
        }
    }

}
