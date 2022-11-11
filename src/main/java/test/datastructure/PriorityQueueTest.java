package test.datastructure;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class PriorityQueueTest {
    public static void main(String[] args) {

        // 采用treeSet和treeSet自带的排序
        // 底层采用的是红黑树结构
        TreeSet<Integer> integers1 = new TreeSet<>();

        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 100; i > 0; i--) {
            integers.add(i);
//            integers.add(i);

        }
        System.out.println(integers);

        integers1.addAll(integers);
        System.out.println(integers1);

        for (int i = 0; i < 10; i++) {
//            System.out.println(integers1.pollFirst());
            System.out.println(integers1.pollLast());
        }
        System.exit(0);



        // 最小堆求topN大
        PriorityQueue<Integer> topMin = getTopMax(integers);
        System.out.println(topMin);


        // 最大堆求topN小
        PriorityQueue<Integer> topMax = getTopMin(integers);
        System.out.println(topMax);





    }


    // 最小堆求topN大
    public static PriorityQueue<Integer> getTopMax(ArrayList<Integer> arr) {
        // 初始化大小
        PriorityQueue<Integer> integers = new PriorityQueue<>(10);

        for (int v: arr) {
//            System.out.println(v);

            if (integers.size() != 10) {
                integers.add(v);
            } else {
//                System.out.println(integers);
//                System.exit(0);

                // 获取头部最小
                Integer peek = integers.peek();
                if (peek.compareTo(v) < 0) {
                    // 小于新增元素
                    // 移除原头部元素并添加新增大的的元素
                    integers.poll();
                    integers.add(v);
                }
            }
        }

        return integers;
    }


    // 最大堆求topN小
    public static PriorityQueue<Integer> getTopMin(ArrayList<Integer> arr) {
        // 初始化优先级队列 底层是数组结构
        PriorityQueue<Integer> integers = new PriorityQueue<>(10);

        for (int v: arr) {
//            System.out.println(v);
//                integers.add(v);
            if (integers.size() != 10) {
                integers.add(v);
            } else {
                // 获取头部队列元素
                Integer peek = integers.peek();
                if (peek.compareTo(v) > 0) {
                    // 头部元素大于新增元素
                    // 移除原来的头部的元素 添加小的新增元素
                    integers.poll();
                    integers.add(v);
                }
            }
        }

        return integers;
    }
}
