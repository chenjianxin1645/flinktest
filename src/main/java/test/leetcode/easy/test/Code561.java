package test.leetcode.easy.test;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/array-partition-i/
 *
 * 561. 数组拆分 I
 *
 * 给定长度为 2n 的整数数组 nums ，你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从 1 到 n 的 min(ai, bi) 总和最大。
 *
 * 返回该 最大总和 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,4,3,2]
 * 输出：4
 * 解释：所有可能的分法（忽略元素顺序）为：
 * 1. (1, 4), (2, 3) -> min(1, 4) + min(2, 3) = 1 + 2 = 3
 * 2. (1, 3), (2, 4) -> min(1, 3) + min(2, 4) = 1 + 2 = 3
 * 3. (1, 2), (3, 4) -> min(1, 2) + min(3, 4) = 1 + 3 = 4
 * 所以最大总和为 4
 * 示例 2：
 *
 * 输入：nums = [6,2,6,5,1,2]
 * 输出：9
 * 解释：最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
 *  
 *
 * 提示：
 *
 * 1 <= n <= 104
 * nums.length == 2 * n
 * -104 <= nums[i] <= 104
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/array-partition-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code561 {
    public static void main(String[] args) {
        int[] nums = {6,2,6,5,1,2};

        Code561 code561 = new Code561();
        int i = code561.arrayPairSum(nums);
        System.out.println(i);
    }


    public int arrayPairSum(int[] nums) {
        System.out.println(Arrays.toString(nums));

        /**
         * 1 到 n 的 min(ai, bi) 总和最
         *
         * 暂时考虑情况是保证
         * 1、排序
         * 2、从小到大拆分N队，以此满足最小的最大之和
         */
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        // 奇数位置相加
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
//            System.out.println(nums[i]);
            sum += nums[i];
        }

        return sum;
    }


}
