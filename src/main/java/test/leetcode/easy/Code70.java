package test.leetcode.easy;

import java.math.BigInteger;
import java.util.*;

/**
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2：
 *
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *  
 *
 * 提示：
 *
 * 1 <= n <= 45
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code70 {
    public static void main(String[] args) {
        int n = 5;

        Code70 code70 = new Code70();
        int i = code70.climbStairs(n);
        System.out.println(i);
    }


    public int climbStairs(int n) {
//        System.out.println(n);

        /**
         * 采用动态规划
         * 即f(x) = f(x-1) + f(x-2)
         *
         * 分两种情况，要么爬一阶到达，要么就是爬两阶到达
         *
         */
        if (n <3) {
            return n;
        }

        // 定义动态规划数组
        int[] dp = new int[n+1];
        // 初始化状态
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }


    public int climbStairs2(int n) {
//        System.out.println(n);

        if (n == 1 || n == 2) {
            return n;
        }

        int idx = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        while (idx <= n /2) {
//            System.out.println(idx);
            hm.put(idx, n - (2*idx));
            idx++;
        }
        System.out.println(hm);

        int sum = 0;
        Set<Map.Entry<Integer, Integer>> entries = hm.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
            Map.Entry<Integer, Integer> entry = iterator.next();
            int k = entry.getKey();
            int v = entry.getValue();

            if (k == 0 || v == 0) {
                sum += 1;
            } else {
                // 总共需要走的次数, 然后任意组合，不要求顺序排列
                int cnt = k + v;
                BigInteger curr_1 = new BigInteger("1");
                BigInteger curr_2 = new BigInteger("1");
                for (int i = 0; i < k ; i++) {
                    curr_1 = curr_1.multiply(new BigInteger(String.valueOf(cnt -i)));
                    curr_2 = curr_2.multiply(new BigInteger(String.valueOf(k -i)));
                }
                System.out.println(curr_1);
                System.out.println(curr_2);
                sum += curr_1.divide(curr_2).intValue();
            }
        }

        return sum;
    }

    /**
     * 动态规划
     * dp[n]=dp[n−1]+dp[n−2]
     * @param n
     * @return
     */
    public int climbStairs3(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    /**
     * 动态规划
     * 进行空间优化
     * dp[n]=dp[n−1]+dp[n−2]
     * @param n
     * @return
     */
    public int climbStairs33333(int n) {
//        int[] dp = new int[n + 1];
//        dp[0] = 1;
//        dp[1] = 1;

        // 声明初始状态
        int a = 0; // f(x-1)
        int b = 0; // f(x-2)
        int c = 1; // f(x) = f(x-1) + f(x-2) 采用滚动数组的方式
        // 初筛 0 0 1
        for(int i = 1; i <= n; i++) {
            System.out.println(i + "=====" + a + "==" + b + "==" + c);
            // 往前滚动
            a = b;
            b = c;

            // 最后状态值相加
            c = a + b;
            System.out.println(i + "=====" + a + "==" + b + "==" + c);
        }
        System.out.println(c);
        return c;
    }

}
