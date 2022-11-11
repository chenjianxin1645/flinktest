package test.tree;


import java.util.Arrays;
import java.util.BitSet;

/**
 * 例如：5,12,26这三个数字，将它用位图法表示的话，那么就是
 * bit[5]=1;bit[12]=1;bit[26]=1;其他为0；
 * 判断a是否存在，可以看bit[a]是否为1。为1则表示存在。
 */
public class BItMap {
    public static void main(String[] args) {
//        // 初始化数字数组
//        int[] nums = {5, 12, 26, 2};
//        System.out.println(Arrays.toString(nums));
//        // 初始化bit数组
//        BitSet bitSet = new BitSet();
//
//        for (int num: nums) {
////            System.out.println(num);
//            // 将对应数字的位的状态设置位1
//            bitSet.set(num);
//        }
////        System.out.println(bitSet);
//
//        // 判断某个数字是否在其中
//        if (bitSet.get(2)) {
//            System.out.println("exists");
//        } else {
//            System.out.println("not exists");
//        }


        // 初始化数字数组
        int[] nums = {5, 12, 26, 2, 5, 26};
        System.out.println(Arrays.toString(nums));
        // 初始化bit数组
        BitSet bitSet = new BitSet();

        for (int num: nums) {
//            System.out.println(num);
//            // 将对应数字的位的状态设置位1
//            if (bitSet.get(num)) {
//                System.out.println("exists");
//
//                bitSet.set(num, false);
//            } else {
//                bitSet.set(num, true);
//            }
            bitSet.set(num, true);
        }
        // 输出不重复的数
        System.out.println(bitSet);

//        // 判断某个数字是否在其中
//        if (bitSet.get(2)) {
//            System.out.println("exists");
//        } else {
//            System.out.println("not exists");
//        }


//        int[] array = new int[] { 423, 423, 700, 9999, 2323, 356, 6400, 1, 2, 3, 2, 2, 2, 2};
//        System.out.println(Arrays.toString(array));
//        BitSet bitSet = new BitSet(2 << 13);
//        // 虽然可以自动扩容，但尽量在构造时指定估算大小，
//        // 我们要看一下数组中的最大值，然后指定大致的容量，默认为64
//        System.out.println("BitSet size: " + bitSet.size());
//        for (int i = 0; i < array.length; i++) {
//            bitSet.set(array[i]);
//        }
//        System.out.println(bitSet);
//        //剔除重复数字后的元素个数
//        int bitLen=bitSet.cardinality();
//        System.out.println("要排序的数组容量：" + array.length);
//        System.out.println("剔除重复数字后的元素个数：" + bitLen);
//        System.out.println(bitSet);

    }
}
