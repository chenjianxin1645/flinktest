package test.leetcode.easy.test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 * 9. 回文数
 *
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 *
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 *
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * 示例 4：
 *
 * 输入：x = -101
 * 输出：false
 *  
 *
 * 提示：
 *
 * -231 <= x <= 231 - 1
 *  
 *
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code9 {
    public static void main(String[] args) {
        int x = 121;
//        int x = -1;
//        int x = 456;

        Code9 code9 = new Code9();
        boolean palindrome = code9.isPalindrome(x);
        System.out.println(palindrome);
    }


    /**
     * 采用字符串来比较
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        System.out.println(x);
        if (x < 0) return false;

        String s = String.valueOf(x);
        System.out.println(s);
        char[] chars = s.toCharArray();
        System.out.println(Arrays.toString(chars));

        String n = "";
        for (int i = chars.length - 1; i >=0; i--) {
//            System.out.println(chars[i]);
            String s1 = String.valueOf(chars[i]);
            System.out.println(s1);
//            n = n.concat(s1);
            n += s1;
        }
        System.out.println(n);

        return s.equals(n);
    }


    public boolean isPalindrome2(int x) {
        System.out.println(x);
        String s = String.valueOf(x);
        System.out.println(s);

        if (x <0) {
            return false;
        }
        int o = x;

//        String res = "";
        int res = 0; // 存储反转的数字
        // 数字反转输出，就是10进制降位
        while (true) {
            int i = x % 10; // 拿到个位数字
            System.out.println(i);
//            res = res.concat(String.valueOf(i));
            res = res * 10 + i; // 升位存储
            x = x / 10; // 拿到剩下的数字 降位
//            System.out.println(j);
            if (x == 0) {
                break;
            }
        }
        System.out.println(res);


        return o == res;

//        return res.equals(s);
    }
}
