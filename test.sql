
./bin/sql-client.sh embedded -j  ./lib/iceberg-flink-1.2.0-SNAPSHOT.jar -j ./lib/flink-sql-connector-hive-3.1.2_2.12-1.16.0.jar shell

mvn install:install-file -DgroupId=org.apache.iceberg -DartifactId=iceberg-flink-runtime -Dversion=1.16-1.2.0-SNAPSHOT -Dpackaging=jar -Dfile=/private/var/www/project/xander/flink/iceberg/flink/v1.16/flink-runtime/build/libs/iceberg-flink-runtime-1.16-1.2.0-SNAPSHOT.jar


catalogs:
  - name: iceberg_hive_catalog
    type: iceberg
    catalog-type: hive
    uri: thrift://172.16.220.100:9083,thrift://172.16.220.102:9083
    property-version: 1
    warehouse: hdfs://emr-cluster/iceberg/warehouse


CREATE CATALOG iceberg_hive_catalog WITH (
  'type'='iceberg',
  'catalog-type'='hive',
  'uri'='thrift://172.16.220.100:9083,thrift://172.16.220.102:9083',
  'clients'='5',
  'property-version'='1',
  'warehouse'='hdfs://emr-cluster/iceberg/warehouse'
);
use catalog iceberg_hive_catalog;
use database iceberg_db;

CREATE TEMPORARY TABLE datagen_source(
    id BIGINT,
    data STRING,
    ts AS localtimestamp,

    WATERMARK FOR ts AS ts
) WITH (
  'connector' = 'datagen',
  'rows-per-second' = '100'
);

add jar hdfs://emr-cluster/iceberg/jars/iceberg-hive-runtime-1.2.0-SNAPSHOT.jar;



CREATE TABLE `iceberg_hive_catalog`.`iceberg_db`.`table_v1` (
    `id` bigINT NOT NULL,
    `data` string NOT NULL
) comment '普通表v1'
WITH (
  'engine.hive.enabled' = 'true',
  'write.metadata.delete-after-commit.enabled'='true',
  'write.metadata.previous-versions-max'='5'
);


CREATE TABLE `iceberg_hive_catalog`.`iceberg_db`.`table_v1_pb` (
   `id` bigINT NOT NULL,
   `data` string NOT NULL,
   statdate string
) comment '分区表v1'
PARTITIONED by (statdate)
WITH (
  'engine.hive.enabled' = 'true',
  'write.metadata.delete-after-commit.enabled'='true',
  'write.metadata.previous-versions-max'='2'
);


CREATE TABLE `iceberg_hive_catalog`.`iceberg_db`.`table_v2` (
    `id` bigINT NOT NULL,
    `data` string NOT NULL,
    PRIMARY KEY (`id`) NOT ENFORCED
) comment '更新表'
WITH (
    'engine.hive.enabled' = 'true',
    'write.metadata.delete-after-commit.enabled'='true',
    'write.metadata.previous-versions-max'='5',
    'format-version'='2',
    'write.upsert.enabled' = 'true'
);


set execution.checkpointing.interval = '30s';


insert into table_v2 /*+ OPTIONS('upsert-enabled'='true') */
select id, data from datagen_source;



CREATE temporary TABLE temp_table_v2_2 (
    id   BIGINT,
    data STRING
) WITH (
    'connector'='iceberg',
    'catalog-type'='hive',
    'catalog-name'='iceberg_hive_catalog',
    'catalog-database'='iceberg_db',
    'catalog-table'='table_v2',
    'uri'='thrift://172.16.220.100:9083,thrift://172.16.220.102:9083',
    'warehouse'='hdfs://emr-cluster/iceberg/warehouse'
);



spark.sql.catalog.iceberg_hive_catalog = org.apache.iceberg.spark.SparkCatalog
spark.sql.catalog.iceberg_hive_catalog.type = hive
spark.sql.catalog.iceberg_hive_catalog.uri = thrift://172.16.220.100:9083,thrift://172.16.220.102:9083
# omit uri to use the same URI as Spark: hive.metastore.uris in hive-site.xml


add jar hdfs://emr-cluster/iceberg/jars/iceberg-spark-runtime-2.4-1.2.0-SNAPSHOT.jar;
add jar hdfs://emr-cluster/iceberg/jars/iceberg-hive-runtime-1.2.0-SNAPSHOT.jar;

spark-sql --jars /home/hadoop/xander/bigdata/iceberg/iceberg-hive-runtime-1.2.0-SNAPSHOT.jar --driver-class-path /home/hadoop/xander/bigdata/iceberg/iceberg-hive-runtime-1.2.0-SNAPSHOT.jar

spark-sql --jars /home/hadoop/xander/bigdata/iceberg/iceberg-spark-runtime-2.4-1.2.0-SNAPSHOT.jar --driver-class-path /home/hadoop/xander/bigdata/iceberg/iceberg-spark-runtime-2.4-1.2.0-SNAPSHOT.jar

