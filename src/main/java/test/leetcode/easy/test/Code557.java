package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
 *
 * 557. 反转字符串中的单词 III
 *
 * 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * 示例 2:
 *
 * 输入： s = "God Ding"
 * 输出："doG gniD"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 5 * 104
 * s 包含可打印的 ASCII 字符。
 * s 不包含任何开头或结尾空格。
 * s 里 至少 有一个词。
 * s 中的所有单词都用一个空格隔开。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code557 {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";

        Code557 code557 = new Code557();
        String s1 = code557.reverseWords(s);
        System.out.println(s1);
    }



    public String reverseWords(String s) {
        System.out.println(s);

        StringBuffer res = new StringBuffer();
        String[] s1 = s.split(" ");
        for (String s2: s1) {
//            System.out.println(s2);


            StringBuilder reverse = new StringBuilder(s2).reverse();
//            System.out.println(s3);
            if (res.length() > 0) {
                res.append(" ");
            }
            res.append(reverse);
        }


        return res.toString();
    }

}
