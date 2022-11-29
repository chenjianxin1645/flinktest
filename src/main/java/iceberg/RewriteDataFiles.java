package iceberg;


import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.iceberg.Snapshot;
import org.apache.iceberg.actions.RewriteDataFilesActionResult;
import org.apache.iceberg.flink.actions.Actions;
import org.apache.iceberg.hive.HiveCatalog;
import org.apache.iceberg.Table;
import org.apache.iceberg.catalog.TableIdentifier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 *  可以通过提交Flink批量任务来合并Data Files 文件。
 */
public class RewriteDataFiles {

    public static void main(String[] args) {
        // 初始化环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        System.setProperty("HADOOP_USER_NAME", "hadoop"); // 设置当前用户


        // 加载hadoop、hive配置
        Configuration conf = new Configuration();
        conf.addResource("hadoop/core-site.xml");
        conf.addResource("hadoop/hdfs-site.xml");
        conf.addResource("hive/hive-site.xml");
//        conf.addResource("hive/");
//        conf.addResource("hadoop");
        System.out.println(conf.get("dfs.namenode.http-address.emr-cluster.nn2"));
        System.exit(0);

        // Using a Hadoop catalog
//        String warehousePath = "hdfs://emr-cluster/iceberg/warehouse";
////        String warehousePath = "hdfs://172.16.220.100:8020/iceberg/warehouse";
//        HadoopCatalog catalog = new HadoopCatalog(conf, warehousePath);

        // Using a Hive catalog
        HiveCatalog catalog = new HiveCatalog();
        catalog.setConf(conf);
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("type", "iceberg");
        properties.put("catalog-type", "hive");
        properties.put("uri", "thrift://172.16.220.100:9083,thrift://172.16.220.102:9083");
        properties.put("warehouse", "hdfs://emr-cluster/iceberg/warehouse");
        properties.put("clients", "5");
        properties.put("property-version", "1");
        catalog.initialize("hive", properties);

        // 加载表
        TableIdentifier name = TableIdentifier.of("iceberg_db", "table_v2");
        Table table = catalog.loadTable(name);
        long l = table.currentSnapshot().snapshotId();
        System.out.println(l);

        // 合并小文件
        RewriteDataFilesActionResult result = Actions.forTable(table)
                .rewriteDataFiles()
                .targetSizeInBytes(128 * 1024 * 1024L)
                .execute();
        long l2 = table.currentSnapshot().snapshotId();
        System.out.println(l2);

        // 清除历史快照数据
        Snapshot snapshot = table.currentSnapshot();
        if (snapshot != null) {
            System.out.println(snapshot.snapshotId());
            long current_time = snapshot.timestampMillis();
            System.out.println(current_time);
            long old_time = current_time - TimeUnit.MINUTES.toMillis(5);
            System.out.println(old_time);

            table.expireSnapshots().expireOlderThan(old_time).commit();

            long l3 = table.currentSnapshot().snapshotId();
            System.out.println(l3);
        }

    }
}
