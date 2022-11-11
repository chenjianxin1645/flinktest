package test.leetcode.easy;


import org.drools.core.common.AgendaFactory;
import test.leetcode.easy.test.Code26;

/**
 * https://leetcode-cn.com/problems/ugly-number/
 *
 * 263. 丑数
 *
 * 丑数 就是只包含质因数 2、3 和 5 的正整数。
 *
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 6
 * 输出：true
 * 解释：6 = 2 × 3
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：true
 * 解释：1 没有质因数，因此它的全部质因数是 {2, 3, 5} 的空集。习惯上将其视作第一个丑数。
 * 示例 3：
 *
 * 输入：n = 14
 * 输出：false
 * 解释：14 不是丑数，因为它包含了另外一个质因数 7 。
 *  
 *
 * 提示：
 *
 * -231 <= n <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code263 {
    public static void main(String[] args) {
        int n = 6;

        Code263 code263 = new Code263();
        boolean ugly = code263.isUgly(n);
        System.out.println(ugly);
    }


    public boolean isUgly(int n) {
//        System.out.println(n);

        /**
         * 根据丑数的定义
         * 丑数的质因数为{2, 3, 5}
         * 那么当num为丑数时，满足 2^a * 3^b * 5^c = num
         * 即num>=1
         *
         * 所以反复除以质因数的话，如果最终结果为1，那么为丑数。反之则不是。
         */

        if (n <=0) {
            return false;
        }

        int[] factors = {2, 3, 5};
        for (int factor: factors) {
//            System.out.println(factor);

            // 用取模判断是否整除
            while (n % factor == 0) {
                // 能够整除
                n /= factor;
            }
        }

        return n==1;
    }
}
