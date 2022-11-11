package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/add-strings/
 *
 * 415. 字符串相加
 *
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 * 示例 2：
 *
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 * 示例 3：
 *
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 *  
 *
 *  
 *
 * 提示：
 *
 * 1 <= num1.length, num2.length <= 104
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code415 {
    public static void main(String[] args) {
        String num1 = "1", num2 = "9";

        Code415 code415 = new Code415();
        String s = code415.addStrings(num1, num2);
        System.out.println(s);
    }


    public String addStrings(String num1, String num2) {
        System.out.println(num1);
        System.out.println(num2);
//        System.out.println('0');
//        System.out.println('1');
//        System.out.println('1' - '0');
//        System.out.println('2' - '0');
//        System.out.println('3' - '0');
//        System.out.println('9' - '0');

        if (num1.length() < num2.length()) {
            return addStrings(num2, num1);
        }
        // 将num2的字符补全
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num1.length() - num2.length(); i++) {
            sb.append('0');
        }
//        System.out.println(sb.toString());
        num2 = sb.toString() + num2;
        System.out.println(num2);


        StringBuffer sb2 = new StringBuffer();
        int next = 0;
        for (int i = num1.length() -1; i >=0 ; i--) {
//            System.out.println(num1.charAt(i));
//            System.out.println(num2.charAt(i));

            int i1 = num1.charAt(i) + num2.charAt(i) -96 + next;
            System.out.println(i1);

            if (i1 <10) {
                sb2.append(i1);
                next = 0;
            } else {
                sb2.append(i1 % 10);
                next = i1 / 10;
            }
        }
        System.out.println(next);
        if (next !=0) sb2.append(next);
        System.out.println(sb2.toString());


        return sb2.reverse().toString();
    }

}
