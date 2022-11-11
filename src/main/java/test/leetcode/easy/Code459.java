package test.leetcode.easy;


/**
 * https://leetcode-cn.com/problems/repeated-substring-pattern/
 *
 * 459. 重复的子字符串
 *
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "abab"
 * 输出: true
 * 解释: 可由子串 "ab" 重复两次构成。
 * 示例 2:
 *
 * 输入: s = "aba"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "abcabcabcabc"
 * 输出: true
 * 解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code459 {
    public static void main(String[] args) {
        String s = "abcabcabcabc";

        Code459 code459 = new Code459();
        boolean b = code459.repeatedSubstringPattern(s);
        System.out.println(b);
    }

    public boolean repeatedSubstringPattern(String s) {
        System.out.println(s);

        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
    
}
