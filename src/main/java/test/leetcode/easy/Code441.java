package test.leetcode.easy;

/**
 * https://leetcode-cn.com/problems/arranging-coins/
 *
 * 441. 排列硬币
 *
 * 你总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
 *
 * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：n = 5
 * 输出：2
 * 解释：因为第三行不完整，所以返回 2 。
 * 示例 2：
 *
 *
 * 输入：n = 8
 * 输出：3
 * 解释：因为第四行不完整，所以返回 3 。
 *  
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arranging-coins
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code441 {
    public static void main(String[] args) {
        int n = 8;

        Code441 code441 = new Code441();
        int i = code441.arrangeCoins(n);
        System.out.println(i);
    }


    public int arrangeCoins(int n) {
        System.out.println(n);

        /**
         * 暴力迭代
         * 容易造成时间超出
         *
         * 采用数学方法
         *
         * 因为 (x *（x+1）) / 2 = n
         * 分解为 (x *（x+1）) = 2n;
         *
         * // 因此可以采用二分法
         */
        int left = 0;
        int right = n;
        while (left < right) {
            // 获取中间值
            int mid = (right -left)/2 + left;
            System.out.println(mid);

            if (mid * (mid + 1) < 2 * n) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }

        return left;
    }

    public int arrangeCoins2(int n) {
        System.out.println(n);

        /**
         * 暴力迭代
         * 容易造成时间超出
         *
         * 采用数学方法
         *
         * 因为 (x *（x+1）) / 2 = n
         * 因为要满足完整的x次阶梯
         * 根据其一元二次方程式可的
         */

        // 向下取整
        int v = (int)(Math.sqrt(8 * n + 1) - 1) / 2;

        return v;
    }
}
