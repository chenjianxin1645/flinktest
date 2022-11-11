package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/add-digits/
 * 258. 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于 2 是一位数，所以返回 2。
 * 示例 1:
 *
 * 输入: num = 0
 * 输出: 0
 *  
 *
 * 提示：
 *
 * 0 <= num <= 231 - 1
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code258 {
    public static void main(String[] args) {
        int num = 10;

        Code258 code258 = new Code258();
        int i = code258.addDigits(num);
        System.out.println(i);

    }


    public int addDigits(int num) {
        System.out.println(num);


        /**
         * 9的倍数，各位相加还是9
         * 不是9的倍数，最终结果为取模
         */
//        if (num % 9 == 0) {
//            // 9的倍数
//            return 9;
//        } else {
//            return num % 9;
//        }

        // 整合在一起的话
        return (num-1) % 9 + 1;
    }


    public int addDigits2(int num) {
        System.out.println(num);


        while (num >9) {
            int div = num;
            int mod = 0;
            while (div>9) {
                // 不断获取各位数字并相加
                mod += div % 10;
                div /= 10;
            }
            // 将相加的数字进行下次迭代
            num = div + mod;

            System.out.println(num);
        }

        return num;
    }
}
