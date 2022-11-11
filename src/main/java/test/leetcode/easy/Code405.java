package test.leetcode.easy;


/**
 * https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/
 *
 * 405. 数字转换为十六进制数
 *
 * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
 *
 * 注意:
 *
 * 十六进制中所有字母(a-f)都必须是小写。
 * 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
 * 给定的数确保在32位有符号整数范围内。
 * 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。
 * 示例 1：
 *
 * 输入:
 * 26
 *
 * 输出:
 * "1a"
 * 示例 2：
 *
 * 输入:
 * -1
 *
 * 输出:
 * "ffffffff"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code405 {
    public static void main(String[] args) {
        int num = -26;

        Code405 code405 = new Code405();
        String s = code405.toHex(num);
        System.out.println(s);
    }

    public String toHex(int num) {
//        System.out.println(num);

        /**
         *
         * 模拟进制转换
         *
         * 即将一个数不断的 n%k 、n/k 机制k进制的转换
         * 注意：因为是有符号的整数，所以存在负数的问题，需要进行补码运算
         * 负数的补码运算 可以直接
         *
         */

        if (num == 0) {
            return "0";
        }

        StringBuffer sb = new StringBuffer();

        long num2 = num;
        if(num < 0) {
            num2 = (long)(Math.pow(2, 32) + num);
        }
//        System.out.println(num2);
//        System.out.println((long)(Math.pow(2, 32)));

        while (num2 > 0) {
//            System.out.println(num2);
//            System.out.println(num2 % 16);

            long i = num2 % 16;
            System.out.println(i);
            // 因为16进制大于9
            char c = (char) (i + '0');
            if (i > 9) {
                // 用字符表示
                c = (char) ('a' + i - 10);
            }
            System.out.println(c);
            sb.append(c);

            num2 /= 16;
        }
        System.out.println(sb);

        return sb.reverse().toString();
    }


    public String toHex2(int num) {
//        System.out.println(num);

        /**
         *
         * 位运算 + 分组换算
         * （不用考虑负数补码的情况，因为直接对二进制转换，只需要考虑前导0的情况）
         * 因为最大的为有符号的32位整数二进制
         *
         * 转成16进制表示，那么就是每四个位表示一位16进制的 共有八组
         *
         * 负数的话，采用补码的形式，那么就是最高位1
         *
         *
         */

        if (num == 0) {
            return "0";
        }

        StringBuffer sb = new StringBuffer();
        // 避免前导0的做法，从高位开始计算不是0的那一组开始拼接
        for (int i = 7; i >= 0; i--) {
            // 获取每一组的二进制值 有符号的右移 无符号右移是>>>
            int i1 = num >> (4 * i) & 0x0f;
            System.out.println(i1);

            // 避免前导0 以及后面有0的情况
            if (i1 > 0 || sb.length()>0) {
                // 将该组的值转为成相应的16进制
                char c = i1 < 10 ?  (char) ('0' + i1) : (char) ('a' + i1 - 10);
                System.out.println(c);
                sb.append(c);
            }
        }
        System.out.println(sb);

        return sb.toString();
    }
}
