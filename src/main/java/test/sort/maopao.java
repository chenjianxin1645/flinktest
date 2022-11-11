package test.sort;


import java.util.Arrays;

/**
 * 冒泡排序
 * 1、通过每次遍历最大值/最小值
 * 2、将最大值/最小值放在尾部/头部
 * 3、剩下的值继续每次遍历最大值/最小值并放在尾部/头部
 * 算法时间复杂度O(n)~O(n2) 稳定
 *
 */
public class maopao {
    public static void main(String[] args) {
        int arr[] = {8, 5, 3, 2, 4};
        System.out.println(Arrays.toString(arr));

        // 外层循环，遍历次数
        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
            // 内层循环，升序，最大值放尾部
            for (int j = 0; j < arr.length - i -1; j++) {
//                System.out.println(arr[j]);
//                System.exit(0);
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tmp;
                }
//                System.out.println(Arrays.toString(arr));
//                System.exit(0);
            }
//            System.out.println(Arrays.toString(arr));
//            System.exit(0);
        }


        System.out.println(Arrays.toString(arr));
    }
}
