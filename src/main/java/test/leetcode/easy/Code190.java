package test.leetcode.easy;

/**
 * https://leetcode-cn.com/problems/reverse-bits/
 * 190. 颠倒二进制位
 *
 * 颠倒给定的 32 位无符号整数的二进制位。
 *
 * 提示：
 *
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 *  
 *
 * 示例 1：
 *
 * 输入：n = 00000010100101000001111010011100
 * 输出：964176192 (00111001011110000010100101000000)
 * 解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 * 示例 2：
 *
 * 输入：n = 11111111111111111111111111111101
 * 输出：3221225471 (10111111111111111111111111111111)
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 *      因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
 *  
 *
 * 提示：
 *
 * 输入是一个长度为 32 的二进制字符串
 *  
 *
 * 进阶: 如果多次调用这个函数，你将如何优化你的算法？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code190 {
    public static void main(String[] args) {
        int n = 43261596;

        Code190 code190 = new Code190();
        int i = code190.reverseBits(n);
        System.out.println(i);
    }


    public int reverseBits(int n) {
//        System.out.println(n);

        // 转换为二进制  00000010100101000001111010011100 无符号
//        String s = Integer.toUnsignedString(n, 2);
//        System.out.println(s);
//        System.out.println(Integer.toBinaryString(n));

        // 初始化反转
        int rev = 0;
        for (int i = 0; i < 32 && n!=0; i++) {
//            System.out.println(Integer.toBinaryString(n));
            // 获取低位值
//            System.out.println((n&1)); // 任何数&1的结果就是取二进制的末位
//            System.out.println((n&1) << (31-i)); // 按照32位进制，将该末位的转为最高位
            // 相加
            rev = rev | (n&1) << (31-i);
//            System.out.println(rev); // 按照32位进制，将该末位的转为最高位




            // 开始下一个低位循环
            n >>>= 1; // 逻辑右移，无符号，高位用0表示
        }

        return rev;
    }
}
