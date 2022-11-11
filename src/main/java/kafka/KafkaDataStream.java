package kafka;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.HashMap;

public class KafkaDataStream {

	public static DataStream<String> generateDs(StreamExecutionEnvironment env) {

		// 配置kafka
		HashMap<String, String> properties = new HashMap<>();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("group.id", "test");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("auto.offset.reset", "earliest");
		properties.put("session.timeout.ms", "30000");
		properties.put("topic", "test");
		ParameterTool parameterTool = ParameterTool.fromMap(properties);

		// 添加source
		DataStream<String> dataStream = env.addSource(new FlinkKafkaConsumer<>(
				parameterTool.getRequired("topic"),
				new SimpleStringSchema(),
				parameterTool.getProperties()
		));

		return dataStream;
	}


}
