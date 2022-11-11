package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/detect-capital/
 *
 * 520. 检测大写字母
 *
 *
 * 我们定义，在以下情况时，单词的大写用法是正确的：
 *
 * 全部字母都是大写，比如 "USA" 。
 * 单词中所有字母都不是大写，比如 "leetcode" 。
 * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google" 。
 * 给你一个字符串 word 。如果大写用法正确，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：word = "USA"
 * 输出：true
 * 示例 2：
 *
 * 输入：word = "FlaG"
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= word.length <= 100
 * word 由小写和大写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/detect-capital
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code520 {
    public static void main(String[] args) {
        String word = "ffffffffffffffffffffF";

        Code520 code520 = new Code520();
        boolean b = code520.detectCapitalUse(word);
        System.out.println(b);
    }


    public boolean detectCapitalUse(String word) {
        System.out.println(word);


        boolean first_upper = false;
        boolean lower = false;
        boolean upper = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
//            System.out.println(c);

//            System.out.println(c - 'A');

            int i1 = c - 'A';
            // 判断首字符大写
            if (i == 0 && i1 <=25) {
                first_upper = true;
            } else if (i > 0 && i1 <=25) {
                first_upper = false;
            }
            // 判断是否有小写
            if (i1 > 25) {
                lower = true;
            }
            // 判断是否有大写
            if (i1 <= 25) {
                upper = true;
            }
        }
        System.out.println(first_upper);
        System.out.println(lower);
        System.out.println(upper);
        System.out.println(word.length() * 25);


        if (first_upper) {
            // 首字符大写
            return true;
        } else if (!upper && lower) {
            // 全小写
            return true;
        } else if (upper && !lower) {
            // 全是大写
            return true;
        }

        return false;
    }


}
