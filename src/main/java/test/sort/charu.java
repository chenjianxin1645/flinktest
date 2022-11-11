package test.sort;


import java.util.Arrays;

/**
 * 插入排序
 * 1、将第一个看成最小值
 * 2、从第二开始比较，如果更小则交换，再依次从第三个开始比较，直到找到更小的插入
 * 3、剩下的值继续每次遍历最大值/最小值并放在尾部/头部
 * 算法时间复杂度O(n)~O(n2) 稳定
 *
 */
public class charu {
    public static void main(String[] args) {
        int arr[] = {8, 5, 3, 2, 4};
        System.out.println(Arrays.toString(arr));

        // 外层循环，从第二个开始比较，前面开始当成有序列表
        for (int i = 1; i < arr.length; i++) {
            System.out.println(arr[i]);

            // 内层循环，依次与前面排好序的数据进行对比交换
            for (int j = i; j >0; j--) {
//                System.out.println(arr[j]);
//                System.exit(0);
                if (arr[j] < arr[j-1]) {
                    int tmp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = tmp;
                } else {
                    // 没有小的，直接退出循环
                    break;
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
