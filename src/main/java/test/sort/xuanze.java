package test.sort;


import java.util.Arrays;

/**
 * 选择排序
 * 1、将第一个看成最小值
 * 2、与后续的值比较，交换本次遍历的初始值和下标
 *
 * 算法时间复杂度 O(n2) ~ O(n2) 不稳定
 *
 */
public class xuanze {
    public static void main(String[] args) {
        int arr[] = {8, 5, 3, 2, 4};
        System.out.println(Arrays.toString(arr));

        // 外层遍历次数
        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
            int min = arr[i]; // 记录最小值
            int idx = i; // 记录最小值下标
            System.out.println(min);
            // 内层找最小值交换, 并互相交换下标
            for (int j = i + 1; j < arr.length; j++) {
//                System.out.println(arr[j]);
                if (min > arr[j]) {
                    min = arr[j];
                    idx = j;
                }
            }
//            System.out.println(min);
//            System.out.println(idx);
            // 将获得的最小值和下标，与本次循环交换
            int temp = arr[i];
            arr[i] = min;
            arr[idx] = temp;

//            System.out.println(Arrays.toString(arr));
//            System.exit(0);
        }


        System.out.println(Arrays.toString(arr));
    }
}
