package test.leetcode.easy.test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 * 303. 区域和检索 - 数组不可变
 *
 * 给定一个整数数组  nums，处理以下类型的多个查询:
 *
 * 计算索引 left 和 right （包含 left 和 right）之间的 nums 元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 *
 * NumArray(int[] nums) 使用数组 nums 初始化对象
 * int sumRange(int i, int j) 返回数组 nums 中索引 left 和 right 之间的元素的 总和 ，包含 left 和 right 两点（也就是 nums[left] + nums[left + 1] + ... + nums[right] )
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 *
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1))
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= i <= j < nums.length
 * 最多调用 104 次 sumRange 方法
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code303 {
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};

        Code303 code303 = new Code303();
        code303.test(nums);

    }

    public void test(int[] nums) {
        NumArray numArray = new NumArray(nums);

        System.out.println(numArray.sumRange(0, 2));// return 1 ((-2) + 0 + 3)
        System.out.println(numArray.sumRange(2, 5));// return -1 (3 + (-5) + 2 + (-1))
        System.out.println(numArray.sumRange(0, 5));// return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))

    }

    class NumArray {
        int[] num_arr = {};
        public NumArray(int[] nums) {
            num_arr = nums;

            System.out.println(Arrays.toString(num_arr));
        }

        /**
         * 暴力迭代相加
         *
         * // 可以优化到初始化的时候，新建个数组，记录前缀的和
         * @param left
         * @param right
         * @return
         */
        public int sumRange(int left, int right) {
            int sum = 0;

            for (int i = left; i <= right; i++) {
                sum += num_arr[i];
            }


            return sum;
        }

    }

}
