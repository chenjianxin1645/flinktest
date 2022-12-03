FROM apache/flink:1.16.0-scala_2.12
USER flink
RUN mkdir -p $FLINK_HOME/hadoopconf
ENV HADOOP_CONF_DIR=$FLINK_HOME/hadoopconf/
COPY --chown=9999:9999 ./conf/hadoop/*.xml $HADOOP_CONF_DIR
COPY --chown=9999:9999 ./conf/hive/*.xml $HADOOP_CONF_DIR
COPY --chown=9999:9999 ./jar/common/*.jar $FLINK_HOME/lib/
RUN mkdir -p $FLINK_HOME/usrlib
COPY --chown=9999:9999 ./jar/TopSpeedWindowing.jar $FLINK_HOME/usrlib/TopSpeedWindowing.jar
