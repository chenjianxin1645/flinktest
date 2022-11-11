package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/excel-sheet-column-number/
 * 171. Excel 表列序号
 *
 * 给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
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
 * 示例 1:
 *
 * 输入: columnTitle = "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: columnTitle = "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: columnTitle = "ZY"
 * 输出: 701
 *  
 *
 * 提示：
 *
 * 1 <= columnTitle.length <= 7
 * columnTitle 仅由大写英文组成
 * columnTitle 在范围 ["A", "FXSHRXW"] 内
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code171 {
    public static void main(String[] args) {
        String columnTitle = "ZY";

        Code171 code171 = new Code171();
        int i = code171.titleToNumber(columnTitle);
        System.out.println(i);
    }

    public int titleToNumber(String columnTitle) {
//        System.out.println(columnTitle);

        int length = columnTitle.length();
        int sum = 0;
        int mult = 1; // 从低位进制倍数1开始
        for (int i = length -1; i >=0; i--) {
            char c = columnTitle.charAt(i);
//            System.out.println(c);
//            System.out.println(c - 'A' + 1);

            // 当前位的数字
            int i1 = c - 'A' + 1;
            System.out.println(i1);
            sum += i1 * mult ;
            System.out.println(sum);

            // 往高位+1
            mult *= 26; // 26进制
        }

        return sum;

    }
}
