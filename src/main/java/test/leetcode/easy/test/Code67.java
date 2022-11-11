package test.leetcode.easy.test;

/**
 * https://leetcode-cn.com/problems/add-binary/
 *
 * 67. 二进制求和
 *
 *
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code67 {
    public static void main(String[] args) {
        String a = "11", b = "1";

        Code67 code67 = new Code67();
        String s = code67.addBinary(a, b);
        System.out.println(s);
    }

    /**
     * 模拟10进制的思路，对于二进制的加法，满二进一
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        /**
         * 思路
         * 模拟法，对于每个字符串，从低位开始相加，逢2进一
         * 对于没有的字符串，直接为0相加，避免直接构造补齐字符'0'
         */

        StringBuffer sB = new StringBuffer();

        // 声明下一位进制变量
        int idx = 0;
        for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
            // 获取低位
            idx += i < a.length() ? a.charAt(a.length() - 1 - i) - '0' : 0;
            idx += i < b.length() ? b.charAt(b.length() - 1 - i) - '0' : 0;

            sB.append(idx % 2);
            idx = idx / 2;
//            System.out.println(idx);
        }
        if (idx == 1)  sB.append(idx);

        return sB.reverse().toString();
    }

    /**
     * 直接计算10进制相加减
     *
     * 局限性：如果字符串超过 3333 位，不能转化为 Integer
     * 如果字符串超过 6565 位，不能转化为 Long
     * 如果字符串超过 500000001500000001 位，不能转化为 BigInteger
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/add-binary/solution/er-jin-zhi-qiu-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param a
     * @param b
     * @return
     */
    public String addBinary2(String a, String b) {
//        System.out.println(a);
//        System.out.println(b);

        int i = Integer.parseInt(a, 2);
        System.out.println(i);
        int i2 = Integer.parseInt(b, 2);
        System.out.println(i2);
        int i1 = i + i2;


        return Integer.toBinaryString(i1);
    }
}
