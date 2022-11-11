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
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class TopKjava {
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
        job.setJarByClass(TopKjava.class);

        // 加载mapper
        job.setMapperClass(TopKjavaMapper.class);
        // 设置输入对象key/value格式
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);


        // 加载reducer
        job.setReducerClass(TopKjavaReducer.class);
        // 设置输出对象key/value格式
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path("topk_input.txt"));
        FileOutputFormat.setOutputPath(job, outpath);


        // 提交任务
        boolean result = job.waitForCompletion(true);
        System.exit( result ? 0 : 1);
    }
}


//Map阶段：输入的行号作为key,每行读取的值作为value
class TopKjavaMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    // 因需要topK的话，可以考虑在map端过滤部分数据
    // 使用treeMap（红黑数）数据结构比haspMap有排序功能
//    private TreeMap<Integer, Integer> topM = new TreeMap<>();
    private HashMap<String, TreeMap<Integer, Integer>> topMK = new HashMap<>();
    int topS = 3;


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        super.map(key, value, context);
        // 读取一行
        String str = value.toString();
//        System.out.println(str);
//        System.exit(0);

        // 切分行内容
        String[] words = str.split("-");
        if (words.length!=2) return ;
//        System.out.println(Arrays.toString(words));
//        System.exit(0);
//        System.out.println(topMK);
//        System.exit(0);
        String k = words[0];
        int v = Integer.parseInt(words[1]);
        if (topMK.containsKey(words[0])) {
//            System.out.println(k);
//            System.out.println(v);
//            System.out.println(topMK.get(k));
//            System.exit(0);
            TreeMap<Integer, Integer> treeMap = topMK.get(k);
            treeMap.put(v, v);

            if (treeMap.size() > topS) {
                treeMap.remove(treeMap.firstKey());
            }
            topMK.put(k, treeMap);
        } else {
            TreeMap<Integer, Integer> vs = new TreeMap<>();
            vs.put(v, v);
            topMK.put(k, vs);
        }
//        System.exit(0);
//        // 默认按照key排序
//        topM.put(Integer.parseInt(words[1]), Integer.parseInt(words[1]));
//        topMK.put(words[0], topM);
//        if (topM.size() > topS) {
////            System.out.println(topM);
//            // 移除最小的key
//            topM.remove(topM.firstKey());
//        }
    }

    /**
     * 最后输出map端topK数据
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
//        super.cleanup(context);
//        System.out.println(111111);
        System.out.println(topMK);
//        System.out.println(context.getCurrentKey());
//        System.exit(0);
        for (String k: topMK.keySet()) {
//            System.out.println(k);
//            System.out.println(topMK.get(k));
//            System.exit(0);
            TreeMap<Integer, Integer> treeMap = topMK.get(k);
            for (Integer value: treeMap.values()) {
                context.write(new Text(k), new IntWritable(value));
            }
        }
//        System.exit(0);
    }
}


//同一个key有且仅只执行一次reduce方法

/**
 * 注意：需要重新排序 涉及shuffle操作
 */
class TopKjavaReducer extends Reducer<Text, IntWritable, Text, Text> {

    private HashMap<String, TreeMap<Integer, String>> hashMap = new HashMap<>();

    private int topS = 2;

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//        super.reduce(key, values, context);
//        System.out.println(key);
//        System.out.println(values.toString());
//        System.exit(0);

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        if (hashMap.containsKey(key.toString())) {
            treeMap = hashMap.get(key.toString());
        }
        // 将map阶段的key值累计求和
        Iterator<IntWritable> iterator = values.iterator();
        while (iterator.hasNext()) {
            IntWritable v = iterator.next();
//            System.out.println(key + "-" + iterator.next());
//            context.write(key, new Text(key + "-" + iterator.next()));
            treeMap.put(v.get(), key + "-" + v.get());

            if (treeMap.size()> topS) {
                treeMap.remove(treeMap.firstKey());
            }
        }
        hashMap.put(key.toString(), treeMap);
//        System.exit(0);
        // 将结果输出
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
//        super.cleanup(context);
//        System.out.println(context.getCurrentKey());
//        System.out.println(hashMap);
//        System.exit(0);
        for (String key:
             hashMap.keySet()) {
//            System.out.println(key);
//            System.out.println(hashMap.get(key));
            for (String value:
                hashMap.get(key).values()) {
//                System.out.println(value);
                context.write(new Text(key), new Text(value));
            }
//            System.exit(0);
        }
    }
}


