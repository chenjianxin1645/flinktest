package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/valid-perfect-square/
 *
 * 367. 有效的完全平方数
 *
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 *
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = 16
 * 输出：true
 * 示例 2：
 *
 * 输入：num = 14
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-perfect-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code367 {
    public static void main(String[] args) {
        int num = 808201;

        Code367 code367 = new Code367();
        boolean perfectSquare = code367.isPerfectSquare(num);
        System.out.println(perfectSquare);
    }


    public boolean isPerfectSquare(int num) {
//        System.out.println(num);

        /**
         * 采用二分法试试
         */
        int left = 0, right = num;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            System.out.println(mid);
            long square = (long) mid * mid;
            System.out.println(square);
            if (square < num) {
                left = mid + 1;
            } else if (square > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }


}
