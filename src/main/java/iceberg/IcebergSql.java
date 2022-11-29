package iceberg;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.data.RowData;
import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.flink.CatalogLoader;
import org.apache.iceberg.flink.TableLoader;
import org.apache.iceberg.flink.source.IcebergSource;
import org.apache.iceberg.flink.source.assigner.SimpleSplitAssignerFactory;
import org.apache.iceberg.hive.HiveCatalog;

import java.util.HashMap;
import java.util.Map;

/**
 * Iceberg Streaming Read
 */
public class IcebergSql {

    public static void main(String[] args) throws Exception {
        // 初始化环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 创建table env
        EnvironmentSettings settings = EnvironmentSettings.newInstance().inStreamingMode().build();
        StreamTableEnvironment tenv = StreamTableEnvironment.create(env, settings);
        // 设置
        System.setProperty("HADOOP_USER_NAME", "hadoop"); // 设置当前用户
        env.setParallelism(1);
        env.enableCheckpointing(1000, CheckpointingMode.EXACTLY_ONCE);


        // 创建catalog
        // 注意本地需要指定hadoop和hive配置文件
        tenv.executeSql("CREATE CATALOG iceberg_hive_catalog WITH (\n" +
                "  'type'='iceberg',\n" +
                "  'catalog-type'='hive',\n" +
                "  'uri'='thrift://172.16.220.100:9083,thrift://172.16.220.102:9083',\n" +
                "  'clients'='5',\n" +
                "  'property-version'='1',\n" +
                "  'hive-conf-dir'='/private/var/www/project/xander/flink/test/flinktest/src/main/resources/hive',\n" +
                "  'hadoop-conf-dir'='/private/var/www/project/xander/flink/test/flinktest/src/main/resources/hadoop',\n" +
                "  'warehouse'='hdfs://emr-cluster/iceberg/warehouse'\n" +
                ");");
        TableResult show_catalogs = tenv.executeSql("show catalogs");
        show_catalogs.print();
        tenv.executeSql("use catalog iceberg_hive_catalog");
        TableResult show_databases = tenv.executeSql("show databases");
        show_databases.print();
        tenv.executeSql("use iceberg_db");
        TableResult show_tables = tenv.executeSql("show tables");
        show_tables.print();
        TableResult tableResult = tenv.executeSql("select * from table_v2 limit 10");
        tableResult.print();

        // Submit and execute this streaming read job.
//        env.execute("IcebergSql");
    }

}
