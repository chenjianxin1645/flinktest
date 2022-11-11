package test.leetcode.easy;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/range-addition-ii/
 *
 * 598. 范围求和 II
 *
 * 给你一个 m x n 的矩阵 M ，初始化时所有的 0 和一个操作数组 op ，其中 ops[i] = [ai, bi] 意味着当所有的 0 <= x < ai 和 0 <= y < bi 时， M[x][y] 应该加 1。
 *
 * 在 执行完所有操作后 ，计算并返回 矩阵中最大整数的个数 。
 *
 *  
 *
 * 示例 1:
 *
 *
 *
 * 输入: m = 3, n = 3，ops = [[2,2],[3,3]]
 * 输出: 4
 * 解释: M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。
 * 示例 2:
 *
 * 输入: m = 3, n = 3, ops = [[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3],[2,2],[3,3],[3,3],[3,3]]
 * 输出: 4
 * 示例 3:
 *
 * 输入: m = 3, n = 3, ops = []
 * 输出: 9
 *  
 *
 * 提示:
 *
 * 1 <= m, n <= 4 * 104
 * 0 <= ops.length <= 104
 * ops[i].length == 2
 * 1 <= ai <= m
 * 1 <= bi <= n
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-addition-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code598 {
    public static void main(String[] args) {
        int m = 3, n = 3;
        int[][] ops = {{2,2},{3,3},{3,3},{3,3},{2,2},{3,3},{3,3},{3,3},
                {2,2},{3,3},{3,3},{3,3}};

        Code598 code598 = new Code598();
        int i = code598.maxCount(m, n, ops);
        System.out.println(i);
    }

    public int maxCount(int m, int n, int[][] ops) {
        System.out.println(m);
        System.out.println(n);
        System.out.println(Arrays.toString(ops));


        // 因为每次操作都会加1，需要返回最大整数的个数
        // 那么就需要获取每次操作的交集
        int minm = m;
        int minn = n;
        for (int[] op: ops) {
//            System.out.println(Arrays.toString(op));
            minm = Math.min(minm, op[0]);
            minn = Math.min(minn, op[1]);

        }

        return minm * minn;
    }

}
