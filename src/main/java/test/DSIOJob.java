package test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.AsyncDataStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.functions.async.ResultFuture;
import org.apache.flink.streaming.api.functions.async.RichAsyncFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Schema;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;
import org.apache.flink.util.Collector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;


public class DSIOJob {

	public static void main(String[] args)throws Exception {
		// 环境设置
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//		env.setParallelism(1);


		// 添加source
//		DataStream<String> dataStream = env.addSource(new MySource());
		DataStreamSource<Tuple2<String, Integer>> dataStream = env.addSource(new MySource());
		dataStream.print();


		// 应用异步 I/O 转换操作  无序模式
		DataStream<Tuple2<String,String>> async = AsyncDataStream.unorderedWait(dataStream,
				new AsyncDatabaseRequest() ,
				1,
				TimeUnit.SECONDS,
				10);
		async.print();


		// 执行任务
		env.execute("asyncForMysql");

	}


	/**
	 * 实现 'AsyncFunction' 用于发送请求和设置回调。
	 */
	static class AsyncDatabaseRequest extends RichAsyncFunction<Tuple2<String, Integer>, Tuple2<String,String>> {

		private DruidDataSource dataSource ;

		@Override
		public void open(Configuration parameters) throws Exception {
//			System.out.println(parameters);

			super.open(parameters);
			dataSource = new DruidDataSource();
			dataSource.setDriverClassName("org.postgresql.Driver");
			dataSource.setUrl("jdbc:postgresql://172.16.220.102:8000/ld_holo");
			dataSource.setUsername("LTAI4G3rrMuahiuon1qsanyf");
			dataSource.setPassword("49b359mzNyqwjyb3ZMQ4X0eFGh9BDq");
		}

		@Override
		public void close() throws Exception {
			super.close();
			dataSource.close();
		}

		@Override
		public void asyncInvoke(Tuple2<String, Integer> key_id, ResultFuture<Tuple2<String,String>> resultFuture) throws Exception {
//			System.out.println(key_id);
//			System.out.println(key_id.f0);
//			System.out.println(key_id.f1);
//			System.exit(0);

			String chapter_name = queryFormMysql(key_id.f0);
//			System.out.println(chapter_name);

			CompletableFuture.supplyAsync(new Supplier<Tuple2<String,String>>() {
				@Override
				public Tuple2<String,String> get() {
					return new Tuple2<>(key_id.f0 + "--" + key_id.f1, chapter_name);
				}
			}).thenAccept(dbResult -> {
				resultFuture.complete(Collections.singleton(dbResult));
			});
		}

		@Override
		public void timeout(Tuple2<String, Integer> input, ResultFuture<Tuple2<String, String>> resultFuture) throws Exception {
			System.out.println(input);
			System.out.println(resultFuture);
		}


		// 查询结果
		private String queryFormMysql(String id) throws SQLException {
			String sql = "select terminal, updatetime from public.cdt_device_dim where terminal =? ";
//			System.out.println(sql);

			Connection connection = null ;
			PreparedStatement stmt = null ;
			ResultSet rs = null ;
			String result_name = null ;
			try{
				connection = dataSource.getConnection();
				stmt = connection.prepareStatement(sql);
				stmt.setString(1,id);
//				System.out.println(stmt);

				rs = stmt.executeQuery() ;
				if (rs.next()){
					result_name = rs.getString("updatetime") ;
				}

			} catch (SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (Exception e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}finally {
				if(rs != null){
					rs.close();
				}
				if (stmt != null){
					stmt.close();
				}
				if (connection != null){
					connection.close();
				}
			}
//			System.out.println(result_name);
			return result_name;
		}
	}


	static class MySource implements SourceFunction<Tuple2<String, Integer>> {
		@Override
		public void cancel() {
		}

		@Override
		public void run(SourceContext<Tuple2<String, Integer>> ctx) throws Exception {
			Tuple2<String, Integer>[] datas = new Tuple2[]{
					new Tuple2<>("000113000044", 1),
					new Tuple2<>("000006000800", 2),
					new Tuple2<>("000113000012", 3),
			};
			for (int k = 0; k < datas.length; k++) {
				Thread.sleep(100);
//				System.out.println(datas[k]);
//				System.exit(0);
				ctx.collect(datas[k]);
			}
		}
	}

}
