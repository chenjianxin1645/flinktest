package iceberg;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.data.RowData;
import org.apache.hadoop.conf.Configuration;
import org.apache.iceberg.Snapshot;
import org.apache.iceberg.Table;
import org.apache.iceberg.TableScan;
import org.apache.iceberg.actions.RewriteDataFilesActionResult;
import org.apache.iceberg.catalog.Catalog;
import org.apache.iceberg.catalog.TableIdentifier;
import org.apache.iceberg.flink.CatalogLoader;
import org.apache.iceberg.flink.TableLoader;
import org.apache.iceberg.flink.actions.Actions;
import org.apache.iceberg.flink.source.FlinkSource;
import org.apache.iceberg.flink.source.IcebergSource;
import org.apache.iceberg.flink.source.StreamingStartingStrategy;
import org.apache.iceberg.flink.source.assigner.SimpleSplitAssignerFactory;
import org.apache.iceberg.hive.HiveCatalog;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Iceberg Streaming Read
 */
public class IcebergStreamingRead {

    public static void main(String[] args) throws Exception {
        // 初始化环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        System.setProperty("HADOOP_USER_NAME", "hadoop"); // 设置当前用户
        // 设置
        env.setParallelism(1);
        env.enableCheckpointing(1000, CheckpointingMode.EXACTLY_ONCE);


        // 加载hadoop、hive配置
        Configuration conf = new Configuration();
        conf.addResource("hadoop/core-site.xml");
        conf.addResource("hadoop/hdfs-site.xml");
        conf.addResource("hive/hive-site.xml");
        System.out.println(conf.get("dfs.namenode.http-address.emr-cluster.nn2"));
//        System.exit(0);
        // 设置iceberg hive属性
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("type", "iceberg");
        properties.put("catalog-type", "hive");
        properties.put("uri", "thrift://172.16.220.100:9083,thrift://172.16.220.102:9083");
        properties.put("warehouse", "hdfs://emr-cluster/iceberg/warehouse");
        properties.put("clients", "5");
        properties.put("property-version", "1");

//        // Using a Hive catalog
        HiveCatalog catalog = new HiveCatalog();
//        catalog.setConf(conf);
//        catalog.initialize("iceberg_hive_catalog", properties);

        // 加载表
        TableIdentifier name = TableIdentifier.of("iceberg_db", "table_v2");
//        Table table = catalog.loadTable(name);
//        long l = table.currentSnapshot().snapshotId();
//        System.out.println(l);
        CatalogLoader iceberg_hive_catalog = CatalogLoader.hive("iceberg_hive_catalog", conf, properties);
//        Catalog catalog = iceberg_hive_catalog.loadCatalog();
//        System.out.println(catalog);
//        Table table = catalog.loadTable(name);
//        long l = table.currentSnapshot().snapshotId();
//        System.out.println(l);
//        TableScan select = table.newScan().select();

        TableLoader tableLoader = TableLoader.fromCatalog(iceberg_hive_catalog, name);
//        System.out.println(tableLoader);
//        tableLoader.open();
//        Table table1 = tableLoader.loadTable();
//        long l = table1.currentSnapshot().snapshotId();
//        System.out.println(l);
//        System.exit(0);

        // 添加source
//        DataStream<RowData> ds = FlinkSource.forRowData()
//                .env(env)
//                .tableLoader(hive)
//                .streaming(true)
//                .startSnapshotId(9194766911045559845L)
//                .build();
//        ds.print();
        IcebergSource<RowData> source = IcebergSource.forRowData()
                .tableLoader(tableLoader)
                .assignerFactory(new SimpleSplitAssignerFactory())
                .streaming(false)
//                .streamingStartingStrategy(StreamingStartingStrategy.TABLE_SCAN_THEN_INCREMENTAL)
//                .monitorInterval(Duration.ofSeconds(10))
                .build();
//        System.out.println(source);
//        System.exit(0);
        DataStream<RowData> stream = env.fromSource(
                source,
                WatermarkStrategy.noWatermarks(),
                "My Iceberg Source",
                TypeInformation.of(RowData.class)
        );
        stream.print();


        // Submit and execute this streaming read job.
        env.execute("IcebergStreamingRead");
    }

}
