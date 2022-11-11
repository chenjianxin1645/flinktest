package test.leetcode.easy;

/**
 * https://leetcode-cn.com/problems/power-of-four/
 *
 * 342. 4的幂
 *
 * 给定一个整数，写一个函数来判断它是否是 4 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 整数 n 是 4 的幂次方需满足：存在整数 x 使得 n == 4x
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 16
 * 输出：true
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：false
 * 示例 3：
 *
 * 输入：n = 1
 * 输出：true
 *  
 *
 * 提示：
 *
 * -231 <= n <= 231 - 1
 *  
 *
 * 进阶：你能不使用循环或者递归来完成本题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-four
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code342 {
    public static void main(String[] args) {
        int n = 16;

        Code342 code342 = new Code342();
        boolean powerOfFour = code342.isPowerOfFour(n);
        System.out.println(powerOfFour);
    }

    public boolean isPowerOfFour(int n) {
        System.out.println(n);

        // 判断是否为4的幂值
        // 1 非0
        // 2 同理也是2的幂值  ： 是否为2的幂值，可以根据 x & (x -1) == 0
        //    因为2的幂值特性是最高位为1，其余位为0。 当把高位1置为0后，其值为0
        // 3 如果是4的幂值，那么（4 ^x） = (3 +1)^ x
        //      因此是3的倍数，并且余1

        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }

}
