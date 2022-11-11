package test.leetcode.easy;


/**
 * https://leetcode-cn.com/problems/is-subsequence/
 *
 * 392. 判断子序列
 *
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢：
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * 两个字符串都只由小写字符组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code392 {
    public static void main(String[] args) {
        String s = "leeeeetcodel", t = "leeee1eetco12aade";

        Code392 code392 = new Code392();
        boolean subsequence = code392.isSubsequence(s, t);
        System.out.println(subsequence);
    }


    public boolean isSubsequence(String s, String t) {
//        System.out.println(s);
//        System.out.println(t);

        // 将t的其他字符剔除
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
//            System.out.println();
            if (s.indexOf(c) != -1) {
                sb.append(c);
            }
        }
        String s1 = sb.toString();
        System.out.println(s1);
        System.out.println(s);

        /**
         * 采用双指针法
         * 每个字符一个指针，如果有指针结束的话，判断是否比较完成
         */
        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < s.length() && idx2 < s1.length()) {
            if (s.charAt(idx1) == s1.charAt(idx2)) {
                idx1++;
                idx2++;
            } else {
                idx2++;
            }
        }
        System.out.println(idx1);
        System.out.println(idx2);




        return idx1 == s.length();
    }
}
