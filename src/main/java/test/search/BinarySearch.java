package test.search;

import java.util.Arrays;

/**
 * 二分查找
 * 左右递归查询
 */
public class BinarySearch {
    public static void main(String[] args) {
        // 需全局有序
        int[] arr = {1, 3, 7, 9, 21, 35, 39, 45, 79, 80};
        System.out.println(Arrays.toString(arr));
//        System.out.println(arr);

        int i = find(arr, 0, arr.length - 1, 40);
        System.out.println(i);
//        find(arr, 0, arr.length - 1, 9);
    }


    public static int find(int[] arr, int leftIdx, int rightIdx, int val) {
        // 获取中间索引
        int idx = (leftIdx + rightIdx)/2;
//        System.out.println(idx);
        System.out.println(leftIdx + "===" + rightIdx + "===" + idx + "===" + arr[idx] + "===" + val);

        // 超出查找范围
        if (leftIdx > rightIdx || idx == leftIdx || idx == rightIdx) {
            return -1;
        }

        // 判断左右两边递归寻找
        if (arr[idx] > val) {
            // 向左
            return find(arr, leftIdx, idx, val);
        } else if (arr[idx] < val) {
            // 向右
            return find(arr, idx, rightIdx, val);
        } else {
            System.out.println(idx + "===" + val);
            return idx;
        }
    }
}
