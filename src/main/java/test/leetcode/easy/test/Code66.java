package test.leetcode.easy.test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/plus-one/
 * 66. 加一
 *
 *
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * 示例 2：
 *
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * 示例 3：
 *
 * 输入：digits = [0]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code66 {
    public static void main(String[] args) {

        int[] digits = {9, 9, 9};
        Code66 code66 = new Code66();
        int[] ints = code66.plusOne(digits);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 反向迭代
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        System.out.println(Arrays.toString(digits));
        // 反向迭代添加1
        int n = digits.length - 1;
        while (n>=0) {
//            System.out.println(digits[n]);
//            return digits;
            // 首次
            if (digits[n] != 9) {
                digits[n] = digits[n] + 1;
                break;
            }

            // 仍然等于最大值
            if (digits[n] == 9) {
                // 循环迭代加1
                digits[n] = 0;
                n--;
            }
        }

        if (digits[0] == 0) {
            int[] ints = new int[digits.length + 1];
            ints[0] = 1;

            return ints;
        }

        return digits;
    }

    /**
     * 迭代还原法
     * 受限于数字类型范围表示
     * short：16位，最大数据存储量是65536，数据范围是-32768~32767之间。
     *
     * int：32位，最大数据存储容量是2的32次方减1，数据范围是负的2的31次方到正的2的31次方减1。
     *
     * long：64位，最大数据存储容量是2的64次方减1，数据范围为负的2的63次方到正的2的63次方减1。
     * @param digits
     * @return
     */
    public int[] plusOne2(int[] digits) {
        System.out.println(Arrays.toString(digits));

        // 首先还原成完整数字
        int length = digits.length;
        long sum = 0;
        for (int i = length-1; i >=0 ; i--) {
//            System.out.println(digits[i]);
            sum += digits[i] * Math.pow(10, length-i-1);
        }
        System.out.println(sum);
        long new_sum = sum + 1;
//        System.out.println(new_sum);
        // 拆分成数组
        String s = String.valueOf(new_sum);
        System.out.println(s);
        int[] ints = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
//            System.out.println(s.charAt(i));
            ints[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
        }

        return ints;
    }
}
