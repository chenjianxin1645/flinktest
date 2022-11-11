package test.leetcode.easy.test;


import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pascals-triangle/
 *
 * 118. 杨辉三角
 *
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 *
 *
 *  
 *
 * 示例 1:
 *
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 示例 2:
 *
 * 输入: numRows = 1
 * 输出: [[1]]
 *  
 *
 * 提示:
 *
 * 1 <= numRows <= 30
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code118 {
    public static void main(String[] args) {
        int numRows = 5;

        Code118 code118 = new Code118();
        List<List<Integer>> generate = code118.generate(numRows);
        System.out.println(generate);
    }


    public List<List<Integer>> generate(int numRows) {
        System.out.println(numRows);
        List<List<Integer>> arrayLists = new ArrayList<>();

        /**
         * 生成杨辉三角，即每个数是它左上方和右上方之和
         *
         * 广度迭代法
         */
        for (int i = 0; i < numRows; i++) {
            // 初始化当前层级列表
            ArrayList<Integer> curr = new ArrayList<>();

            if (i == 0) {
                curr.add(1);
            } else {
                // 获取上一层级的列表
                List<Integer> pre = arrayLists.get(i - 1);
//                System.out.println(pre);
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        // 前后位置都为1
                        curr.add(1);
                    } else {
                        // 其他位置为上一行的左右位置相加
                        curr.add(pre.get(j-1) + pre.get(j));
                    }
                }
            }
            // 添加到整体列表
            arrayLists.add(curr);
//            System.out.println(arrayLists);
        }

        return arrayLists;
    }
}
