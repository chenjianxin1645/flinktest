package test.leetcode.easy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * 448. 找到所有数组中消失的数字
 *
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[5,6]
 * 示例 2：
 *
 * 输入：nums = [1,1]
 * 输出：[2]
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code448 {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};

        Code448 code448 = new Code448();
        List<Integer> disappearedNumbers = code448.findDisappearedNumbers(nums);
        System.out.println(disappearedNumbers);
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        System.out.println(Arrays.toString(nums));
        ArrayList<Integer> integers = new ArrayList<>();

        /**
         * 思路：
         * 1～n的数字，并且nums[i]都在
         * 将nums[i]的值取模后，可以得到其所在的下标，注意：有可能已经增加过，所以需要对增加的值取模，还原
         * 然后给相应的索引，增加n
         *
         *
         */
        int n = nums.length;
        for (int num : nums) {
//            System.out.println(num);

            // 获取索引
            int idx = (num - 1) % n;
//            System.out.println(idx);
            // 给相应的索引增加n
            nums[idx] += n; // 不用担心会影响后面的值，因为增加的n，会重新取模还原回来
        }
        System.out.println(Arrays.toString(nums));

        // 重新判断当前的索引，是否都增加了
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <=n) {
                integers.add(i+1);
            }
        }



        return integers;
    }

}
