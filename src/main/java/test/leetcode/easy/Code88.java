package test.leetcode.easy;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 *
 * 88. 合并两个有序数组
 *
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * 示例 2：
 *
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * 示例 3：
 *
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *  
 *
 * 提示：
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 *  
 *
 * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code88 {
    public static void main(String[] args) {

        int[] nums1 = {1,2,4,0,0,0,0,0};
        int m = 3;
        int[] nums2 = {1,2,3,5,6};
        int n = 5;

        Code88 code88 = new Code88();
        code88.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        System.out.println(Arrays.toString(nums1));
        System.out.println(m);
        System.out.println(Arrays.toString(nums2));
        System.out.println(n);


        /**
         * 时间复杂度为 O(m + n)
         * 递增合并
         */
        // 双指针
        int idx_1 = 0;
        int idx_2 = 0;

        for (int i = 0; i < m+n; i++) {
            System.out.println(Arrays.toString(nums1));
            System.out.println(i + ":" + nums1[i] + "==" + idx_1 + ":" + nums1[idx_1] + "==" + idx_2 + ":" + nums2[idx_2]);

            if (idx_2 >=n || idx_1>= i && nums1[idx_1] <= nums2[idx_2]) {
                // 小于/另外数组去满 当前数组上取数
                // 判断另存
                int tmp = nums1[i];
                if (i != idx_1) {
                    idx_1++;
                } else {
                    nums1[idx_1+1] = tmp;

                    // 插入
                    nums1[i] = nums1[idx_1];
                    idx_1++;
                }
            } else {
                // 大于 另外数组上取数
                // 判断另存
                if (nums1[i] != 0) {
                    int tmp = nums1[i];
                    nums1[idx_1+1] = tmp;
                }

                // 插入
                nums1[i] = nums2[idx_2];
                idx_2++;
            }
        }


    }


    public void merge2222(int[] nums1, int m, int[] nums2, int n) {
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));

        if (m == 0) {
            for (int i = 0; i < n; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        if (n == 0) {
            return;
        }


        int[] ints = new int[m + n];
        // 采用双指针法
        int idx = 0; // 数组1的指针
        int idx2 = 0; // 数组2的指针
        for (int i = 0; i < m+n; i++) {
//            System.out.println(nums1[i]);
//            System.out.println(idx);
//            System.out.println(idx2);
            // 将两个数组进行遍历
            if (idx2+1 >n || (idx <m  && nums1[idx] <= nums2[idx2]))  {
                ints[i] = nums1[idx];
                idx++;
            } else {
                ints[i] = nums2[idx2];
                idx2++;
            }
        }

        // 待优化
        for (int i = 0; i < n+m; i++) {
            nums1[i] = ints[i];
        }
//        System.out.println(Arrays.toString(nums1));
//        System.out.println(Arrays.toString(nums2));

        return;
    }
}
