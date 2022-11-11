package test.leetcode.easy;


/**
 * https://leetcode-cn.com/problems/fibonacci-number/
 *
 * 509. 斐波那契数
 *
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 *
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给定 n ，请计算 F(n) 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 示例 3：
 *
 * 输入：n = 4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 *  
 *
 * 提示：
 *
 * 0 <= n <= 30
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fibonacci-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code509 {
    public static void main(String[] args) {
        int n = 4;


        Code509 code509 = new Code509();
        int fib = code509.fib(n);
        System.out.println(fib);
    }


    public int fib(int n) {
//        System.out.println(n);

        /**
         * 采用动态规划思想 dp
         * 即：
         * F(0) = 0，F(1) = 1
         * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
         *
         * 1. 初始化状态
         * 2. 状态转移
         */

        if (n == 0 || n ==1) {
            return n;
        }
        // 初始化状态 初始化0和1的边界
        int p = 0, q = 1, r = 0;
        // 状态转移
        for (int i = 2; i <= n; i++) {
            r = p + q;

            p = q;
            q = r;
        }



        return r;
    }

    public int fib2(int n) {
//        System.out.println(n);


        /**
         * 递归：
         * F(0) = 0，F(1) = 1
         * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
         */
        // 递归结束的标志
        if (n == 0 || n ==1) {
            return n;
        }

        return fib(n-1) + fib(n-2);
    }
}
