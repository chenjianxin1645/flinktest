package test.leetcode.easy;

/**
 * https://leetcode-cn.com/problems/power-of-two/
 *
 * 231. 2 的幂
 *
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 *
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 1
 * 输出：true
 * 解释：20 = 1
 * 示例 2：
 *
 * 输入：n = 16
 * 输出：true
 * 解释：24 = 16
 * 示例 3：
 *
 * 输入：n = 3
 * 输出：false
 * 示例 4：
 *
 * 输入：n = 4
 * 输出：true
 * 示例 5：
 *
 * 输入：n = 5
 * 输出：false
 *  
 *
 * 提示：
 *
 * -231 <= n <= 231 - 1
 *  
 *
 * 进阶：你能够不使用循环/递归解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-two
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code231 {
    public static void main(String[] args) {
        int n = 10;


        Code231 code231 = new Code231();
        boolean powerOfTwo = code231.isPowerOfTwo(n);
        System.out.println(powerOfTwo);


    }

    /**
     * 若n是2的x次方的幂值 可以根据位运算进行确认
     *
     *  因为其二进制的表示中，只有一个1，根据其&计算可以获得
     *
     *  有两种计算方式：
     *  1、 n & (n-1) = 0
     *  2、 n & (-n) = n
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        System.out.println(n);
        System.out.println(n & (n-1));
        System.out.println(n & (-n));

        if (n <= 0) {
            return false;
        }

//        return (n & (n-1)) == 0;
        return (n & (-n)) == n;
    }

    public boolean isPowerOfTwo2(int n) {
        System.out.println(n);

        if (n == 1) {
            return true;
        }

        int mod = n % 2;
        while (mod == 0 && n >=2) {
            if (n == 2) {
                return true;
            }

            n >>>= 1;
            mod = n%2;
            System.out.println(n);
        }


        return false;
    }

}
