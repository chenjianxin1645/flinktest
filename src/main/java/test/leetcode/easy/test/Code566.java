package test.leetcode.easy.test;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/reshape-the-matrix/
 *
 * 566. 重塑矩阵
 *
 * 在 MATLAB 中，有一个非常有用的函数 reshape ，它可以将一个 m x n 矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
 *
 * 给你一个由二维数组 mat 表示的 m x n 矩阵，以及两个正整数 r 和 c ，分别表示想要的重构的矩阵的行数和列数。
 *
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的 行遍历顺序 填充。
 *
 * 如果具有给定参数的 reshape 操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：mat = [[1,2],[3,4]], r = 1, c = 4
 * 输出：[[1,2,3,4]]
 * 示例 2：
 *
 *
 * 输入：mat = [[1,2],[3,4]], r = 2, c = 4
 * 输出：[[1,2],[3,4]]
 *  
 *
 * 提示：
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * -1000 <= mat[i][j] <= 1000
 * 1 <= r, c <= 300
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reshape-the-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code566 {
    public static void main(String[] args) {
        int[][] mat = {{1,2},{3,4}};
        int r = 1, c = 4;


        Code566 code566 = new Code566();
        int[][] ints = code566.matrixReshape(mat, r, c);
        for (int [] i : ints) {
            System.out.println(Arrays.toString(i));
        }
    }

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        System.out.println(Arrays.toString(mat));
        for (int [] m : mat) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println(r);
        System.out.println(c);

        int n = mat.length > 0 ? mat.length * mat[0].length : 0;
        System.out.println(n);
        if (n != r * c) {
            return mat;
        }

        // 开始重塑矩阵
        int[][] ints = new int[r][c];
        int m = mat.length;
        int n1 = mat[0].length;
        for (int x = 0; x < m * n1; ++x) {
            ints[x / c][x % c] = mat[x / n1][x % n1];
        }

        return ints;
    }


}
