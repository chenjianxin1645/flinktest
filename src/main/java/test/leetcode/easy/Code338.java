package test.leetcode.easy;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/counting-bits/
 *
 * 338. 比特位计数
 *
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *  
 *
 * 提示：
 *
 * 0 <= n <= 105
 *  
 *
 * 进阶：
 *
 * 很容易就能实现时间复杂度为 O(n log n) 的解决方案，你可以在线性时间复杂度 O(n) 内用一趟扫描解决此问题吗？
 * 你能不使用任何内置函数解决此问题吗？（如，C++ 中的 __builtin_popcount ）
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/counting-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code338 {
    public static void main(String[] args) {
        int n = 5;

        Code338 code338 = new Code338();
        int[] ints = code338.countBits(n);
        System.out.println(Arrays.toString(ints));

    }

    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {

            // 右移1位，表示将最低位的数字直接抹除
            // 因为是从小到大遍历的，i >> 1的值是确定的
            // 那么还需要判断被抹除的数字的奇偶情况
            // (i & 1) == 1 表示奇数为1

            bits[i] = bits[i >> 1] + (i & 1);
        }
        return bits;
    }


    public int[] countBits2(int n) {
        System.out.println(n);

        int[] ints = new int[n+1];

        // 总体复杂度为nlogn
        //
        for (int i = 0; i <= n; i++) {
//            System.out.println(i);

            //  获取二进制的数字
            // 采用brain kernighan算法
            // 根据 x & (x -1)性质，可以将右边的数字1转为0
            // 只要不断进行迭代，知道为0
            int x = i;
            int cnt = 0;

            // 时间复杂度 logn
            while (x > 0) {
                x = x & (x - 1);
                cnt++;
            }
            ints[i] = cnt;
        }


        return ints;
    }
}
