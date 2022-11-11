package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/sqrtx/
 *
 * 69. x 的平方根
 *
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 *
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 *
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 4
 * 输出：2
 * 示例 2：
 *
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *  
 *
 * 提示：
 *
 * 0 <= x <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code69 {
    public static void main(String[] args) {
//        int x = 4;
//        int x = 8;
//        int x = 2147395599;
        int x = 2147483647;

        Code69 code69 = new Code69();
        int i = code69.mySqrt(x);
        System.out.println(i);


    }

    public int mySqrt(int x) {
//        System.out.println(x);


        /**
         * 采用二分法
         *
         */
        int left = 1;
        int right = x;
        while (left<right) {
            int mid = (right-left)/2 + left;
            if ((long) mid * mid > x) {
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
//        System.out.println(left);
//        System.out.println(right);

        return (long)left * left > x ? left -1 : left;
    }



    public int mySqrt222(int x) {
//        System.out.println(x);

        /**
         * 二分查找
         *
         */
        int left = 1;
        int right = x / 2;
        // 在区间 [left..right] 查找目标元素
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            // 注意：这里为了避免乘法溢出，改用除法
            if (mid > x / mid) {
                // 下一轮搜索区间是 [left..mid - 1]
                right = mid - 1;
            } else {
                // 下一轮搜索区间是 [mid..right]
                left = mid;
            }
        }
        return left;
    }
}
