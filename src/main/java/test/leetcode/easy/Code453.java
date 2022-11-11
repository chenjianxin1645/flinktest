package test.leetcode.easy;


import java.util.Arrays;
import java.util.OptionalInt;

/**
 * https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/
 *
 * 453. 最小操作次数使数组元素相等
 *
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * 示例 2：
 *
 * 输入：nums = [1,1,1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 答案保证符合 32-bit 整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code453 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};

        Code453 code453 = new Code453();
        int i = code453.minMoves(nums);
        System.out.println(i);
    }


    public int minMoves(int[] nums) {
        System.out.println(Arrays.toString(nums));

        /**
         * 思路，每次操作，需要给（n-1）给数+1，对于求自增的比较难求
         *
         * 可以考虑换个反向思路，因为自增到最大值比较难确定（需要给n-1个数添加），
         * 但是自减到最小值比较确定（就是说每个数-1）
         */
        // 获取最小值
        int min = Arrays.stream(nums).min().getAsInt();
//        System.out.println(min);
        int res = 0;
        for (int num:nums) {
            res += num - min;
        }

        return res;
    }


}
