package test.leetcode.easy;

import org.checkerframework.checker.units.qual.A;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/excel-sheet-column-title/
 * 168. Excel表列名称
 *
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 *
 * 例如：
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *  
 *
 * 示例 1：
 *
 * 输入：columnNumber = 1
 * 输出："A"
 * 示例 2：
 *
 * 输入：columnNumber = 28
 * 输出："AB"
 * 示例 3：
 *
 * 输入：columnNumber = 701
 * 输出："ZY"
 * 示例 4：
 *
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 *  
 *
 * 提示：
 *
 * 1 <= columnNumber <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code168 {
    public static void main(String[] args) {

        int columnNumber = 2147483647;
//        int columnNumber = 701;
//        int columnNumber = 25;

        Code168 code168 = new Code168();
        String s = code168.convertToTitle(columnNumber);
        System.out.println(s);
    }

    public String convertToTitle(int columnNumber) {
//        System.out.println(columnNumber);

        // 暴力法，直接转为26进制 除法和取模
//        int i = columnNumber / 26;
//        int i1 = columnNumber % 26;
//        System.out.println(i);
//        System.out.println(i1);
         // 然后直接映射字符列表，Z

        // 采用ascii码计算 A-Z = 65-90
        // 循环递减
        // 26进制问题，从1开始递增，满26进一
        StringBuffer sb = new StringBuffer();
        while (columnNumber>0) {
            columnNumber--; //先减一


            // 获取低位取模
//            System.out.println(columnNumber % 26);
            // 该模即为低位所在数字
            sb.append((char)(columnNumber % 26 + 'A'));

            // 每过26，向高位进1
            columnNumber = columnNumber / 26;
        }
        System.out.println(sb);


//        FXSHRXW

        return sb.reverse().toString();
    }
}
