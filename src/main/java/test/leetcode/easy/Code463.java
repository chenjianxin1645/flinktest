package test.leetcode.easy;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/island-perimeter/
 *
 * 463. 岛屿的周长
 *
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 *
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 *
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * 输出：16
 * 解释：它的周长是上面图片中的 16 个黄色的边
 * 示例 2：
 *
 * 输入：grid = [[1]]
 * 输出：4
 * 示例 3：
 *
 * 输入：grid = [[1,0]]
 * 输出：4
 *  
 *
 * 提示：
 *
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] 为 0 或 1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/island-perimeter
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code463 {

    // 设置网格的四周坐标
    // 顺序从上右下左开始
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) {
        int[][] nums = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};

        Code463 code463 = new Code463();
        int i = code463.islandPerimeter(nums);
        System.out.println(i);
    }

    public int islandPerimeter2(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    // 题目限制只有一个岛屿，计算一个即可
                    return dfs2(grid, r, c);
                }
            }
        }
        return 0;
    }

    /**
     * 递归获取相邻四个边框的网格
     * // 只有一个岛屿，只要递归遍历有1的网格即可
     * // 如果有多个岛屿，可能没有相邻的陆地网格，需要全部遍历相加
     * @param grid
     * @param r
     * @param c
     * @return
     */
    int dfs2(int[][] grid, int r, int c) {
        if (!(0 <= r && r < grid.length && 0 <= c && c < grid[0].length)) {
            return 1;
        }
        if (grid[r][c] == 0) {
            return 1;
        }
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;

        // 递归
        return dfs2(grid, r - 1, c) // 上边相邻
                + dfs2(grid, r + 1, c) // 下边相邻
                + dfs2(grid, r, c - 1) // 左边相邻
                + dfs2(grid, r, c + 1); // 右边相邻
    }


    public int islandPerimeter(int[][] grid) {

        /**
         * 网格类问题，考虑采用dfs（深度优先搜法）
         */
        // 迭代网格
        int n = grid.length; // 获取深度
        int m = grid[0].length; // 获取宽度
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
//                System.out.println(grid[i][j]);
                if (grid[i][j] == 1) {
                    // 获取岛屿
//                    System.out.println(grid[i][j]);
                    // 获取当前岛屿的周长
                    // 如何酸岛屿的周长
                    // 1、 边界  2、 水域相邻
                    // 不采用递归的方式
                    // 每次迭代判断当前网格
                    System.out.println(i + "=====" + j);
                    ans += dfs(i, j, grid, n, m);
                    System.out.println("ans:" + ans);
                }
            }
        }

        return ans;
    }


    // 获取岛屿的周长
    // 每次判断当前网格周边的情况
    // 最后返回当前网格的周长
    public int dfs(int x, int y, int[][] grid, int n, int m) {
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println(grid);
//        System.out.println(n);
//        System.out.println(m);
//        System.exit(0);

        // 判断当前边界溢出 以及当前网格为水域
        if (x<0 || x>= n || y<0 || y>=m || grid[x][y] == 0) {
            return 1;
        }

        // 判段当前网格是否已经遍历过
        if (grid[x][y] == 2) {
            return 0;
        }
        // 设置当前网格已经遍历过
        grid[x][y] = 2;

        // 获取当前网格的周边情况
        // 从上右下左开始依次判断
        int res = 0;
        System.out.println(x + "==" + y);
        for (int i = 0; i < 4; i++) {
            int x_2 = x + dx[i];
            int y_2 = y + dy[i];
//            System.out.println(x_2);
//            System.out.println(y_2);
//            System.out.println(grid[x_2][y_2]);

            // 获取周边网格的周长
            res += dfs(x_2, y_2, grid, n, m);
        }
        System.out.println(res);
//        System.exit(0);

        // 返回当前网格陆地的周边长度之和
        return res;
    }
}
