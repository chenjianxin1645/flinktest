package test.leetcode.easy.test;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/pascals-triangle-ii/
 * 119. 杨辉三角 II
 *
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 *
 *
 *  
 *
 * 示例 1:
 *
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * 示例 2:
 *
 * 输入: rowIndex = 0
 * 输出: [1]
 * 示例 3:
 *
 * 输入: rowIndex = 1
 * 输出: [1,1]
 *  
 *
 * 提示:
 *
 * 0 <= rowIndex <= 33
 *  
 *
 * 进阶：
 *
 * 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code119 {
    public static void main(String[] args) {
        int rowIndex = 3;

        Code119 code119 = new Code119();
        List<Integer> row = code119.getRow(rowIndex);
        System.out.println(row);

    }

    public List<Integer> getRow(int rowIndex) {
        Code118 code118 = new Code118();
        List<List<Integer>> rows = code118.generate(rowIndex + 1);
        System.out.println(rows);


        return rows.get(rowIndex);
    }
}
