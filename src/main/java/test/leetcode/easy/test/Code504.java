package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/base-7/
 *
 * 504. 七进制数
 *
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 *
 * 输入: num = -7
 * 输出: "-10"
 *  
 *
 * 提示：
 *
 * -107 <= num <= 107
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/base-7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code504 {
    public static void main(String[] args) {
        int num = 0;

        Code504 code504 = new Code504();
        String s = code504.convertToBase7(num);
        System.out.println(s);
    }

    public String convertToBase7(int num) {
//        System.out.println(num);

        if (num == 0) {
            return "0";
        }

        boolean negative = num < 0;
        if (negative) {
            num = Math.abs(num);
        }
        System.out.println(num);

        // 模拟进制
        StringBuffer sb = new StringBuffer();
        while (num >0) {

            sb.append(num % 7);
            num = num /7;
        }
        System.out.println(sb);
        if (negative) {
            sb.append('-');
        }
        System.out.println(sb);


        return sb.reverse().toString();
    }
}
