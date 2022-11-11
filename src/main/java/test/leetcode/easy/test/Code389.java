package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/find-the-difference/
 *
 * 389. 找不同
 *
 * 给定两个字符串 s 和 t ，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * 示例 2：
 *
 * 输入：s = "", t = "y"
 * 输出："y"
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 1000
 * t.length == s.length + 1
 * s 和 t 只包含小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code389 {
    public static void main(String[] args) {
        String s = "abcd", t = "abcde";

        Code389 code389 = new Code389();
        char theDifference = code389.findTheDifference(s, t);
        System.out.println(theDifference);
    }


    public char findTheDifference(String s, String t) {
//        System.out.println(s);
//        System.out.println(t);

        // 将t的字符-'a'相加
        // 将s的字符-'a'相加
        // 剩下的值表示剩下的

        int sum = 0;
        for (int i = 0; i < t.length(); i++) {
//            System.out.println(t.charAt(i) - 'a');
            sum += t.charAt(i) - 'a';
        }
        System.out.println(sum);

        for (int i = 0; i < s.length(); i++) {
//            System.out.println(t.charAt(i) - 'a');
            sum -= s.charAt(i) - 'a';
        }
        System.out.println(sum);

        return (char)(sum + 'a');
    }

}
