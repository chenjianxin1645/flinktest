package test.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Iterator;

public class WordCount {
    public static void main(String[] args) throws Exception{
        // 获取配置信息
        Configuration configuration = new Configuration();

        Path outpath = new Path("mr");
        FileSystem fs = FileSystem.get(configuration);
        if (fs.exists(outpath)) {
            System.out.println("exists:" + outpath);
            fs.delete(outpath, true);
        }
//        System.exit(0);

        Job job = Job.getInstance(configuration);

        // 加载当前jar路径，即当前class对象
        job.setJarByClass(WordCount.class);

        // 加载mapper
        job.setMapperClass(WordCountMapper.class);
        // 设置输入对象key/value格式
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);


        // 加载reducer
        job.setReducerClass(WordCountReducer.class);
        // 设置输出对象key/value格式
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path("input.txt"));
        FileOutputFormat.setOutputPath(job, outpath);


        // 提交任务
        boolean result = job.waitForCompletion(true);
        System.exit( result ? 0 : 1);
    }
}



//Map阶段：输入的行号作为key,每行读取的值作为value
class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text k = new Text();
    IntWritable v = new IntWritable(1); // 固定每次map词频为1


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        super.map(key, value, context);
        // 读取一行
        String str = value.toString();

        // 切分行内容
        String[] words = str.split(" ");

        // 按照键值对输出
        for (String word: words) {
            k.set(word);

            context.write(k, v);
        }

    }
}



//同一个key有且仅只执行一次reduce方法
class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//        super.reduce(key, values, context);
        // 将map阶段的key值累计求和
        int sum = 0;
        Iterator<IntWritable> iterator = values.iterator();
        while (iterator.hasNext()) {
            sum += iterator.next().get();
     }
        // 将结果输出
        context.write(key, new IntWritable(sum));
    }
}


