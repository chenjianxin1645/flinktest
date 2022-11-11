package test.leetcode.easy;


/**
 * https://leetcode-cn.com/problems/perfect-number/
 *
 * 507. 完美数
 *
 * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 *
 * 给定一个 整数 n， 如果是完美数，返回 true；否则返回 false。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = 28
 * 输出：true
 * 解释：28 = 1 + 2 + 4 + 7 + 14
 * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
 * 示例 2：
 *
 * 输入：num = 7
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= num <= 108
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code507 {
    public static void main(String[] args) {
        int num = 28;

        Code507 code507 = new Code507();
        boolean b = code507.checkPerfectNumber(num);
        System.out.println(b);
    }


    public boolean checkPerfectNumber(int num) {
        System.out.println(num);

        if (num == 1) {
            return false;
        }
        /**
         * 
         * 枚举法：枚举所有的正因子
         *  只需要枚举不大于num的开方即可，因为如果有大于的大于num的开方的因数，那么必然有小于的num的开方的因数
         */
        int sum = 1;
        for (int i = 2; i * i < num; i++) {
            if (num % i == 0) {
                System.out.println(i);
                sum += i;

                // 获取大于num开方的因数
                if (i * i < num) {
                    sum += num /i;
                }
            }
        }
        System.out.println(sum);

        return sum == num;
    }


}
