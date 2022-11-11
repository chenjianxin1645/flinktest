package test.leetcode.easy.test;

/**
 * https://leetcode-cn.com/problems/number-complement/
 * 476. 数字的补数
 *
 * 对整数的二进制表示取反（0 变 1 ，1 变 0）后，再转换为十进制表示，可以得到这个整数的补数。
 *
 * 例如，整数 5 的二进制表示是 "101" ，取反后得到 "010" ，再转回十进制表示得到补数 2 。
 * 给你一个整数 num ，输出它的补数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = 5
 * 输出：2
 * 解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
 * 示例 2：
 *
 * 输入：num = 1
 * 输出：0
 * 解释：1 的二进制表示为 1（没有前导零位），其补数为 0。所以你需要输出 0 。
 *  
 *
 * 提示：
 *
 * 1 <= num < 231
 *  
 *
 * 注意：本题与 1009 https://leetcode-cn.com/problems/complement-of-base-10-integer/ 相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-complement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code476 {
    public static void main(String[] args) {
        int num = 5;

        Code476 code476 = new Code476();
        int complement = code476.findComplement(num);
        System.out.println(complement);
    }

        public int findComplement(int num) {
            System.out.println(num);


            /**
             * 获取该数的补数
             */
            StringBuffer sb = new StringBuffer();
            while (num !=0) {
                if ((num & 1) == 1) {
                    sb.append('0');
                } else {
                    sb.append('1');
                }

                // num不为负数
                num >>= 1;
            }
    //        System.out.println(sb.toString());
    //        System.out.println(sb.reverse().toString());

            Integer integer = Integer.parseInt(sb.reverse().toString(), 2);
            System.out.println(integer);


            return integer;
        }

}
