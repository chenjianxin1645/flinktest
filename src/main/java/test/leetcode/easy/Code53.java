package test.leetcode.easy;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/maximum-subarray/
 * 53. 最大子数组和
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *  
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code53 {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        Code53 code53 = new Code53();
        int i = code53.maxSubArray(nums);
        System.out.println(i);
    }

    public int maxSubArray(int[] nums) {
//        System.out.println(Arrays.toString(nums));

        /**
         * 采取动态规划思想
         * 因为要连续性的最大和
         * 只要前面的f(x) + f(x+1) <0 则抛弃
         *
         * 优化空间
         */
        // 定义上次最大的值
        int pre = 0;
        int max = nums[0];
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            if (pre >0) {
                pre += nums[i];
            } else {
                pre = nums[i];
            }
            max = Math.max(max, pre);
        }

        return max;
    }

    public int maxSubArray4444(int[] nums) {
//        System.out.println(Arrays.toString(nums));

        /**
         * 采取动态规划思想
         * 因为要连续性的最大和
         * 只要前面的f(x) + f(x+1) <0 则抛弃
         */
        // 定义下动态方程数组
        int[] dp = new int[nums.length];
        // 初始化首次状态
        dp[0] = nums[0];
        // 遍历数组
        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] >0) {
                dp[i] = dp[i-1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            System.out.println(nums[i] + "===" + dp[i]);
        }
//        System.out.println(Arrays.toString(dp));

        int max = dp[0];
        for (int i:
             dp) {
            max = Math.max(max, i);
        }

        return max;
    }


    public int maxSubArray3333(int[] nums) {
//        System.out.println(Arrays.toString(nums));

        /**
         * 采取动态规划思想
         * 因为要连续性的最大和
         * 只要前面的f(x) + f(x+1) <0 则抛弃
         */

        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
//            System.out.println(i);
            // 初始化当前序列值
            int sum = nums[i];
            max = Math.max(max, sum);

            int j = i + 1;
            while (j < nums.length) {
                sum += nums[j];
                // 更新当前最大值
                max = Math.max(max, sum);
                j++;
            }


        }
        return max;
    }


    /**
     * 采取分治法
     * 不断分段递归
     * 对于一个区间 [l,r][l,r]，我们取 m = \lfloor \frac{l + r}{2} \rfloorm=⌊
     * 2
     * l+r
     * ​
     *  ⌋，对区间 [l,m][l,m] 和 [m+1,r][m+1,r] 分治求解。当递归逐层深入直到区间长度缩小为 11 的时候，递归「开始回升」。这个时候我们考虑如何通过 [l,m][l,m] 区间的信息和 [m+1,r][m+1,r] 区间的信息合并成区间 [l,r][l,r] 的信息
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        System.out.println(Arrays.toString(nums));

        Status info = getInfo(nums, 0, nums.length - 1);
        System.out.println(info);
        return info.mSum;
    }

    // 分治递归获取状态下的信息
    public Status getInfo(int[] nums, int left, int right) {
        // 判断结束条件
        if (left == right) {
            return new Status(nums[left], nums[left], nums[left], nums[left]);
        }

        // 如果还未结束，继续递归
        int mid = (left+right) >> 1; // 右移
        // 获取左右区间的信息
        Status lStatus = getInfo(nums, left, mid);
        Status rStatus = getInfo(nums, mid+1, right);
        return pushUp(lStatus, rStatus);
    }

    /**
     * 维护分治区间下的状态信息
     *      *         int lSum = 0; // 左区间的最大和
     *      *         int rSum = 0; // 右区间的最大和
     *      *         int iSum = lSum + rSum; // 左右区间最大和
     *      *         int mSum = Math.max(lSum, rSum); // 左右区间最大子段和
     * @param lStatus
     * @param rStatus
     * @return
     */
    public Status pushUp(Status lStatus, Status rStatus) {
        System.out.println(lStatus);
        System.out.println(rStatus);
        System.out.println( " ================ ");
        int iSum = lStatus.iSum + rStatus.iSum;
        int lSum = Math.max(lStatus.lSum, lStatus.iSum + rStatus.lSum);
        int rSum = Math.max(rStatus.rSum, rStatus.iSum + lStatus.rSum);
        int mSum = Math.max(Math.max(lStatus.mSum, rStatus.mSum), lStatus.rSum + rStatus.lSum);

        return new Status(lSum, rSum, mSum,iSum);
    }

    /**
     * 初始化分治下区间的状态信息
     *         int lSum = 0; // 左区间的最大和
     *         int rSum = 0; // 右区间的最大和
     *         int iSum = lSum + rSum; // 左右区间最大和
     *         int mSum = Math.max(lSum, rSum); // 左右区间最大子段和
     */
    public class Status {
        public int lSum, rSum, iSum, mSum;
        // 初始化区间状态信息
        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }

        @Override
        public String toString() {
            return lSum + "===" + rSum + "===" + iSum + "===" + mSum;
        }
    }

    /**
     * 动态规划，求最大子段和
     * 这个是动态规划的的升级版 --- 贪心法
     */
//    public int maxSubArray2(int[] nums) {
//        System.out.println(Arrays.toString(nums));
//
//        int max = nums[0];
//        int pre = 0;
//        for (int num: nums) {
////            System.out.println(num);
//            pre = Math.max(pre + num, num);
////            System.out.println(pre);
//
//            max = Math.max(pre, max);
//        }
//
//        return max;
//    }
}
