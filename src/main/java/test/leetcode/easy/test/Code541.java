package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/reverse-string-ii/
 *
 * 541. 反转字符串 II
 *
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 *
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "abcdefg", k = 2
 * 输出："bacdfeg"
 * 示例 2：
 *
 * 输入：s = "abcd", k = 2
 * 输出："bacd"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由小写英文组成
 * 1 <= k <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-string-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code541 {
    public static void main(String[] args) {
        String s = "abcdefghjkl";
        int k = 2;

        Code541 code541 = new Code541();
        String s1 = code541.reverseStr(s, k);
        System.out.println(s1);
    }

    public String reverseStr(String s, int k) {
        System.out.println(s);
        System.out.println(k);

        int n = s.length();
        System.out.println(n);
        int i = n % (2 * k);
        System.out.println(i);

        // 获取前2k个字符
        String l_s = "";
        if (n - i > 0) {
            l_s = s.substring(0, n - i);
        }
        System.out.println(l_s);
        // 获取剩下的字符
        String r_s = s;
        if (n - i > 0) {
            r_s = s.substring(n - i, n);
        }
        System.out.println(r_s);


        StringBuffer sb = new StringBuffer();
        // 反转前2k个字符
        if (l_s.length() > 0) {
            // 反转奇数k字符
            for (int j = 1; j <= l_s.length() / (2 *k); j++) {
                String s1 = l_s.substring(j * (2 * k) - 2 * k, j * (2 * k) - k);
                String s2 = l_s.substring(j * (2 * k) - k, j * (2 * k));
                System.out.println(s1 + "==" + s2);

                sb.append(new StringBuffer(s1).reverse());
                sb.append(s2);
            }
        }
        System.out.println(sb);

        // 反转剩下的字符
        if (r_s.length() < k) {
            sb.append(new StringBuffer(r_s).reverse());
        } else {
            sb.append(new StringBuffer(r_s.substring(0, k)).reverse());
            sb.append(r_s.substring(k, r_s.length()));
        }
        System.out.println(sb);


        return sb.toString();
    }



}
