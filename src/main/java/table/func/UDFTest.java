package table.func;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.annotation.DataTypeHint;
import org.apache.flink.table.annotation.FunctionHint;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.functions.ScalarFunction;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.call;

public class UDFTest {

    public static void main(String[] args) {
        // 环境变量
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings envSettings = EnvironmentSettings.newInstance().build();
        StreamTableEnvironment stenv = StreamTableEnvironment.create(env, envSettings);


        // 添加source
        // 创建表
        String sql = "CREATE TABLE datagen (\n" +
                " f_sequence INT,\n" +
                " f_random INT,\n" +
                " f_random_str STRING,\n" +
                " ts AS localtimestamp,\n" +
                " WATERMARK FOR ts AS ts\n" +
                ") WITH (\n" +
                " 'connector' = 'datagen',\n" +
                "\n" +
                " -- optional options --\n" +
                "\n" +
                " 'rows-per-second'='5',\n" +
                "\n" +
                " 'fields.f_sequence.kind'='sequence',\n" +
                " 'fields.f_sequence.start'='1',\n" +
                " 'fields.f_sequence.end'='1000',\n" +
                "\n" +
                " 'fields.f_random.min'='1',\n" +
                " 'fields.f_random.max'='1000',\n" +
                "\n" +
                " 'fields.f_random_str.length'='10'\n" +
                ")";
        System.out.println(sql);
        stenv.executeSql(sql).print();
        // 查询表
//        stenv.executeSql("select * from datagen").print();
        Table table = stenv.sqlQuery("select * from datagen");
//        stenv.executeSql("select * from " + table).print();
//        table.select($("*"), $("f_sequence").as("b").plus($("f_random").as("a"))).execute().print();

        // 在 Table API 里不经注册直接“内联”调用函数
//        stenv.from("datagen")
//                .select(
//                        $("*"),
//                        call(SubstringFunction.class, $("f_random_str"), 5, $("f_random_str").charLength()))
//                .execute().print();

        // 注册函数
//        stenv.createTemporarySystemFunction("SubstringFunction", SubstringFunction.class);
//        stenv.createTemporarySystemFunction("SubstringFunction", new SubstringFunction(false));
        // 在 Table API 里调用注册好的函数
//        stenv.from("datagen").
//                select(
//                        $("*"),
//                        call("SubstringFunction", $("f_random_str"), 5, $("f_random_str").charLength()))
//                .execute().print();
        // 在 SQL 里调用注册好的函数
//        stenv.sqlQuery("SELECT *, SubstringFunction(f_random_str, 2, CHAR_LENGTH(f_random_str)) FROM datagen")
//            .execute().print();


        // 在 Table API 里不经注册直接“内联”调用函数
//        stenv.from("datagen")
//                .joinLateral(call(SplitFunction.class, $("f_random_str")))
//                .select(
//                        $("f_random_str"),
//                        $("word"), $("length"))
//            .execute().print();

//        stenv.from("datagen")
//                .leftOuterJoinLateral(call(SplitFunction.class, $("f_random_str")))
//                .select(
//                        $("f_random_str"),
//                        $("word"), $("length"))
//                .execute().print();

        // 在 SQL 里调用注册好的函数
        stenv.createTemporarySystemFunction("SplitFunction", SplitFunction.class);;
//        stenv.sqlQuery(
//                "SELECT f_random_str, word, length " +
//                        "FROM datagen, LATERAL TABLE(SplitFunction(f_random_str))")
//            .execute().print();
//        stenv.sqlQuery(
//                "SELECT f_random_str, word, length " +
//                        "FROM datagen " +
//                        "LEFT JOIN LATERAL TABLE(SplitFunction(f_random_str)) ON TRUE")
//            .execute().print();

        // 在 SQL 里重命名函数字段
        stenv.sqlQuery(
                "SELECT f_random_str, newWord, newLength " +
                        "FROM datagen " +
                        "LEFT JOIN LATERAL TABLE(SplitFunction(f_random_str)) AS T(newWord, newLength) ON TRUE")
            .execute().print();

    }


//    // 定义函数逻辑
//    public static class SubstringFunction extends ScalarFunction {
//        public String eval(String s, Integer begin, Integer end) {
//            return s.substring(begin, end);
//        }
//    }

    // 定义可参数化的函数逻辑
    public static class SubstringFunction extends ScalarFunction {

        private boolean endInclusive;

        public SubstringFunction(boolean endInclusive) {
            this.endInclusive = endInclusive;
        }

        public String eval(String s, Integer begin, Integer end) {
            return s.substring(begin, endInclusive ? end + 1 : end);
        }
    }

    @FunctionHint(output = @DataTypeHint("ROW<word STRING, length INT>"))
    public static class SplitFunction extends TableFunction<Row> {

        public void eval(String str) {
            for (String s : str.split("a")) {
                // use collect(...) to emit a row
                collect(Row.of(s, s.length()));
            }
        }
    }


}
