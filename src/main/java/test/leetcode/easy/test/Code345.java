package test.leetcode.easy.test;


import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/reverse-vowels-of-a-string/
 *
 * 345. 反转字符串中的元音字母
 *
 * 给你一个字符串 s ，仅反转字符串中的所有元音字母，并返回结果字符串。
 *
 * 元音字母包括 'a'、'e'、'i'、'o'、'u'，且可能以大小写两种形式出现。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "hello"
 * 输出："holle"
 * 示例 2：
 *
 * 输入：s = "leetcode"
 * 输出："leotcede"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 3 * 105
 * s 由 可打印的 ASCII 字符组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code345 {
    public static void main(String[] args) {
        String s = "hello";

        Code345 code345 = new Code345();
        String s1 = code345.reverseVowels(s);
        System.out.println(s1);
    }

        public String reverseVowels(String s) {
            System.out.println(s);


            char[] chars = s.toCharArray();
            // 临时变量
            char tmp;
            // 添加个元音字母集合
            HashSet<Character> set = new HashSet<>();

            // 采用双指针法

            for (int left = 0, right = chars.length -1; left < right;) {
    //            System.out.println(s.charAt(i));
    //            System.out.println(s.charAt(left));
    //            System.out.println(s.charAt(right));

                if (isVowel(chars[left]) && isVowel(chars[right])) {
                    // 互换
                    tmp = chars[left];
                    chars[left] = chars[right];
                    chars[right] = tmp;

                    left++;
                    right--;
                } else if (!isVowel(chars[left])) {
                    left++;
                } else {
                    right--;
                }
            }

            return String.valueOf(chars);
        }

        // 判断是否元音字母
        public boolean isVowel(char ch) {
            return "aeiouAEIOU".indexOf(ch) >= 0;
        }

}
