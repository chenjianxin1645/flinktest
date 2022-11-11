package test.leetcode;

import java.util.Arrays;

/**
 * 121. 买卖股票的最佳时机
 *
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 *
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 *
 * 提示：
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code121 {

    public int maxProfit(int[] prices) {
        System.out.println(Arrays.toString(prices));

        int max = 0;

//        // 暴力法 每次和后面的依次比较差值最大
//        for (int i = 0; i < prices.length; i++) {
////            cur = prices[i];
//            for (int j = i+1; j < prices.length; j++) {
//                System.out.println(prices[i] + "==" + prices[j]);
//
//                if (prices[j] - prices[i] > max) {
//                    max = prices[j] - prices[i];
//                }
//            }
//        }


//        // 差值最大，即只需要从最小价格买起，后面比较差值最大即可
//        int min = 0; // 初始值
//        for (int i = 0; i < prices.length; i++) {
//            if (prices[i] < min || min == 0) {
//                // 获取最小值
//                min = prices[i];
//            } else {
//                // 不是最小值，对比最小值的差值
//                if (prices[i] - min > max) {
//                    max =  prices[i] - min;
//                }
//            }
//        }

        // 采取单调栈方法



        return max;
    }


    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};


        Code121 code121 = new Code121();
        System.out.println(code121.maxProfit(prices));
    }
}
