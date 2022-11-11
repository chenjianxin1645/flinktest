package test.leetcode.easy.test;

import java.util.Locale;

/**
 * https://leetcode-cn.com/problems/valid-palindrome/
 *
 * 125. 验证回文串
 *
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 2 * 105
 * 字符串 s 由 ASCII 字符组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code125 {
    public static void main(String[] args) {
//        String s = "A man, a plan, a canal: Panama";
        String s = "A man";

        Code125 code125 = new Code125();
        boolean palindrome = code125.isPalindrome(s);
        System.out.println(palindrome);

    }


    public boolean isPalindrome(String s) {
//        System.out.println(s);

        StringBuffer sb = new StringBuffer();
        // 提取出数字和字符 忽略大小写
        for (int i = 0; i < s.length(); i++) {
//            System.out.println(s.charAt(i));
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(String.valueOf(c).toLowerCase(Locale.ROOT));
            }
        }
        System.out.println(sb);

        // 回文字符串 正反一样
        /**
         * 借用双指针法，前后判断是否一致
         */
        int length = sb.length();
        for (int i = 0; i < length; i++) {
            if (i > length /2 -1) {
                break;
            }
            if (sb.charAt(i) != sb.charAt(length -i -1)) {
                System.out.println(i);
                System.out.println(sb.charAt(i));
                System.out.println(sb.charAt(length -i -1));
                return false;
            }
//            System.out.println(sb.charAt(i));
        }

        return true;
    }

    public boolean isPalindrome2(String s) {
//        System.out.println(s);

        StringBuffer sb = new StringBuffer();
        // 提取出数字和字符 忽略大小写
        for (int i = 0; i < s.length(); i++) {
//            System.out.println(s.charAt(i));
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(String.valueOf(c).toLowerCase(Locale.ROOT));
            }
        }
//        System.out.println(sb);

        // 回文字符串 正反一样
        /**
         * 借用字符串反转
         */
        StringBuffer reverse = new StringBuffer(sb).reverse();
        System.out.println(reverse);
        System.out.println(sb);





        return sb.toString().equals(reverse.toString());
    }
}
