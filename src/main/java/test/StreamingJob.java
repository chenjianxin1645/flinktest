/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test;

import org.apache.flink.api.common.functions.*;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="https://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {

	public static void main(String[] args) throws Exception {
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		// 设置时间特征
//		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
//		// 设置checkpoint
//		// 时间1000ms
//		env.enableCheckpointing(1000);
//		// 精确一次
//		env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
//		// 最小时间间隔
//		env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500);
//		// 超时
//		env.getCheckpointConfig().setCheckpointTimeout(60000);
//		// 并发数目
//		env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
//		// 外部存储保留
//		env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
//		// checkpoint优先级
//		env.getCheckpointConfig().setPreferCheckpointForRecovery(true);


//		DataStream<Tuple2<String, Integer>> dataStream = env
//				.socketTextStream("localhost", 9000)
//				.flatMap(new Splitter())
//				.keyBy(0)
//				.timeWindow(Time.seconds(5))
//				.sum(1);
//
////		dataStream.print();
//		env.setParallelism(1);
//		dataStream.writeAsCsv("out2", FileSystem.WriteMode.OVERWRITE);
//		dataStream.writeAsText("out", FileSystem.WriteMode.OVERWRITE);

		// 添加source
//		DataStream<String> ds = env.readTextFile("input.txt");
		DataStream<String> ds = env.socketTextStream("localhost", 9000);

		// 算子操作
//		DataStream<Tuple2<String, Integer>> mapDs = ds.map(new MapFunction<String, Tuple2<String, Integer>>() {
//			@Override
//			public Tuple2<String, Integer> map(String value) throws Exception {
//				return new Tuple2<String, Integer>(value, 1);
//			}
//		});

		DataStream<String> words = ds.flatMap(new FlatMapFunction<String, String>() {
			@Override
			public void flatMap(String value, Collector<String> out) throws Exception {
				for (String word : value.split("\\s")) {
					out.collect(word);
				}
			}
		});

//		DataStream<String> res = words.filter(new FilterFunction<String>() {
//			@Override
//			public boolean filter(String value) throws Exception {
//				return !value.equals("A");
//			}
//		});

		/*DataStream<Tuple2<String, Integer>> res = words.map(new MapFunction<String, Tuple2<String, Integer>>() {
			@Override
			public Tuple2<String, Integer> map(String value) throws Exception {
				return new Tuple2<String, Integer>(value, 1);
			}
		}).keyBy(0).reduce(new ReduceFunction<Tuple2<String, Integer>>() {
			@Override
			public Tuple2<String, Integer> reduce(Tuple2<String, Integer> value1, Tuple2<String, Integer> value2) throws Exception {
				return new Tuple2<String, Integer>(value1.f0, value1.f1 + value2.f1);
			}
		});*/

		/*DataStream<Tuple2<String, Integer>> res = words.map(new MapFunction<String, Tuple2<String, Integer>>() {
			@Override
			public Tuple2<String, Integer> map(String value) throws Exception {
				return new Tuple2<String, Integer>(value, 1);
			}
		}).keyBy(0).fold(new Tuple2<String, Integer>("X", 1), new FoldFunction<Tuple2<String, Integer>, Tuple2<String, Integer>>() {
			@Override
			public Tuple2<String, Integer> fold(Tuple2<String, Integer> accumulator, Tuple2<String, Integer> value) throws Exception {
				return new Tuple2<String, Integer>(value.f0, accumulator.f1 + value.f1);
			}
		});*/

//		DataStream<Tuple2<String, Integer>> res = words.map(new MapFunction<String, Tuple2<String, Integer>>() {
//			@Override
//			public Tuple2<String, Integer> map(String value) throws Exception {
//				return new Tuple2<String, Integer>(value, 1);
//			}
//		}).keyBy(0).minBy(1);

		DataStream<Tuple2<String, Integer>> mds = words.map(new MapFunction<String, Tuple2<String, Integer>>() {
			@Override
			public Tuple2<String, Integer> map(String value) throws Exception {
				return new Tuple2<String, Integer>(value, 1);
			}
		});

		DataStream<Tuple2<String, Integer>> res = mds.keyBy(0)
				.windowAll(TumblingProcessingTimeWindows.of(Time.seconds(3)))
				.sum(1);

//		// 输出
		res.print();





		// 执行任务
		env.execute("Window WordCount");
	}


	public static class Splitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
		@Override
		public void flatMap(String sentence, Collector<Tuple2<String, Integer>> out) throws Exception {
			for (String word: sentence.split(" ")) {
				out.collect(new Tuple2<String, Integer>(word, 1));
			}
		}
	}
}
