package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/license-key-formatting/
 *
 * 482. 密钥格式化
 *
 * 给定一个许可密钥字符串 s，仅由字母、数字字符和破折号组成。字符串由 n 个破折号分成 n + 1 组。你也会得到一个整数 k 。
 *
 * 我们想要重新格式化字符串 s，使每一组包含 k 个字符，除了第一组，它可以比 k 短，但仍然必须包含至少一个字符。此外，两组之间必须插入破折号，并且应该将所有小写字母转换为大写字母。
 *
 * 返回 重新格式化的许可密钥 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：S = "5F3Z-2e-9-w", k = 4
 * 输出："5F3Z-2E9W"
 * 解释：字符串 S 被分成了两个部分，每部分 4 个字符；
 *      注意，两个额外的破折号需要删掉。
 * 示例 2：
 *
 * 输入：S = "2-5g-3-J", k = 2
 * 输出："2-5G-3J"
 * 解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
 *  
 *
 * 提示:
 *
 * 1 <= s.length <= 105
 * s 只包含字母、数字和破折号 '-'.
 * 1 <= k <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/license-key-formatting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code482 {
    public static void main(String[] args) {
        String  S = "5F3Z-2e-9-w";
        int k = 4;


        Code482 code482 = new Code482();
        String s = code482.licenseKeyFormatting(S, k);
        System.out.println(s);
    }


    public String licenseKeyFormatting(String s, int k) {
        System.out.println(s);
        System.out.println(k);

        char[] chars = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char c: chars) {
            if (c == '-') {
                continue;
            }
            sb.append(c);
        }
        System.out.println(sb.toString());

        StringBuffer sb2 = new StringBuffer();
        int num = 0;
        for (int i = sb.length() -1; i >=0 ; i--) {
            System.out.println(i + "==" + sb.charAt(i));
            sb2.append(sb.charAt(i));
            num++;
            if (num == k && i != 0) {
                // 满足k位 判断是否补充'-'
                sb2.append('-');
                num = 0;
            }
        }
//        System.out.println(sb2);


        return sb2.reverse().toString().toUpperCase();
    }
}
