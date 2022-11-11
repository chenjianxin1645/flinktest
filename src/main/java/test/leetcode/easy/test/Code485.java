package test.leetcode.easy.test;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/max-consecutive-ones/
 * 485. 最大连续 1 的个数
 *
 * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * 示例 2:
 *
 * 输入：nums = [1,0,1,1,0,1]
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1.
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-consecutive-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code485 {
    public static void main(String[] args) {
        int[] nums = {1,1,0,1,1,1};

        Code485 code485 = new Code485();

        int maxConsecutiveOnes = code485.findMaxConsecutiveOnes(nums);
        System.out.println(maxConsecutiveOnes);

    }


    public int findMaxConsecutiveOnes(int[] nums) {
        System.out.println(Arrays.toString(nums));

        /**
         * 迭代记录连续最长
         */
        int cnt = 0;
        int res = 0;
        for (int num: nums) {
            cnt = num == 1 ? ++cnt : 0;
            res = Math.max(res, cnt);
            System.out.println(num + "==" + cnt + "==" + res);
        }


        return res;
    }


}
