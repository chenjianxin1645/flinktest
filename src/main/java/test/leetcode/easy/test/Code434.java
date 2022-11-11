package test.leetcode.easy.test;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/number-of-segments-in-a-string/
 *
 * 434. 字符串中的单词数
 *
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 *
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 * 示例:
 *
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-segments-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code434 {
    public static void main(String[] args) {
        String s = ", , , ,        a, eaefa";

        Code434 code434 = new Code434();
        int i = code434.countSegments(s);
        System.out.println(i);

    }

    public int countSegments(String s) {
        System.out.println(s);


        String[] s1 = s.split(" ");
        System.out.println(Arrays.toString(s1));
        int sum = 0;
        for (String s2: s1) {
            if (!s2.equals("")) {
                System.out.println(s2);
                sum++;
            }
        }

        return sum;
    }

}
