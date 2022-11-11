package test.tree;


import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class minHeap {
    public static void main(String[] args) {
//        // 初始化一个数组
        int[] nums = {1, 2, 10, 6, 7, 9, 19, 11, 20};
        System.out.println(Arrays.toString(nums));
//
//        // 生成优先队列，模式实现采用最小堆 默认自然元素排序
//        PriorityQueue<Object> priorityQueue = new PriorityQueue<>();
//        for (int num: nums) {
////            System.out.println(num);
//            priorityQueue.add(num);
//        }
//        System.out.println(priorityQueue);
//        while (!priorityQueue.isEmpty()) {
//            // 获取的时候最小堆顶获取
//            Object poll = priorityQueue.poll();
//            System.out.println(poll);
//    }


        // 添加比较器实现最大堆
        PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                System.out.println(o1); // 下一个
//                System.out.println(o2); // 上一个
//                System.exit(0);
//                return o1.compareTo(o2);
                return o2.compareTo(o1);
            }
        });

        for (int num: nums) {
//            System.out.println(num);
            priorityQueue1.add(num);
        }
        System.out.println(priorityQueue1);
        while (!priorityQueue1.isEmpty()) {
            // 获取的时候最小堆顶获取
            Object poll = priorityQueue1.poll();
            System.out.println(poll);
        }

    }
}


