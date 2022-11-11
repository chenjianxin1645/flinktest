package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/hamming-distance/
 *
 * 461. 汉明距离
 *
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 * 示例 2：
 *
 * 输入：x = 3, y = 1
 * 输出：1
 *  
 *
 * 提示：
 *
 * 0 <= x, y <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hamming-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code461 {
    public static void main(String[] args) {
        int x = 1, y = 4;

        Code461 code461 = new Code461();
        int i = code461.hammingDistance(x, y);
        System.out.println(i);
    }

    public int hammingDistance(int x, int y) {
        System.out.println(x);
        System.out.println(y);
        System.out.println(x ^ y);

        int z = x ^ y;
        // brain kernighan 算法 x&(x-1) 移除最右位1
        int sum = 0;
        while (z != 0) {
            z &= (z-1);
            System.out.println(z);

            sum++;
        }

        return sum;
    }

    public int hammingDistance2(int x, int y) {
        System.out.println(x);
        System.out.println(y);
        System.out.println(x ^ y);

        int z = x ^ y;
        // 获取每个位的值
        int sum = 0;
        while (z != 0) {
            int i = z & 1;
            System.out.println(i);

            if (i == 1) {
                sum++;
            }

            z >>>= 1; // 逻辑右移
        }

        return sum;
    }
}
