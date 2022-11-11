package test;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;


public class TableTestJob {


    public static void main(String[] args) throws Exception {
        // 创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings bsSettings = EnvironmentSettings.newInstance().inStreamingMode().build();
        StreamTableEnvironment tEnv = StreamTableEnvironment.create(env, bsSettings);
        env.setParallelism(1);


//		 添加source
        tEnv.executeSql(
            "CREATE TABLE datagen (\n" +
                    " f_sequence INT,\n" +
                    " f_random INT,\n" +
                    " f_random_str STRING,\n" +
                    " ts AS localtimestamp,\n" +
                    " WATERMARK FOR ts AS ts\n" +
                    ") WITH (\n" +
                    " 'connector' = 'datagen',\n" +
                    " 'rows-per-second'='5',\n" +
                    " 'fields.f_sequence.kind'='sequence',\n" +
                    " 'fields.f_sequence.start'='1',\n" +
                    " 'fields.f_sequence.end'='1000',\n" +
                    " 'fields.f_random.min'='1',\n" +
                    " 'fields.f_random.max'='1000',\n" +
                    " 'fields.f_random_str.length'='10'\n" +
                    ")"
        );

        Table table = tEnv.from("datagen");
        table.printSchema();
        tEnv.createTemporaryView("datagenView", table);
//        tEnv.executeSql("select * from " + table).print();
//        tEnv.executeSql("select * from datagenView").print();

        String sql = "SELECT window_time, window_start, window_end, count(1) AS cnt \n" +
//                "FROM TABLE(CUMULATE(TABLE datagenView, DESCRIPTOR(ts), INTERVAL '10' seconds, INTERVAL '60' seconds))\n" +
//                "FROM TABLE(tumble(TABLE datagenView, DESCRIPTOR(ts), INTERVAL '10' seconds))\n" +
                "FROM TABLE(HOP(TABLE datagenView, DESCRIPTOR(ts), INTERVAL '5' seconds, INTERVAL '10' seconds))\n" +
                "GROUP BY window_time, window_start, window_end";
        tEnv.executeSql(sql).print();

        // 执行任务
//        env.execute("DataStreamTestJob");
    }

}
