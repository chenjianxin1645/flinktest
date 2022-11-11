package test.leetcode.easy;

import org.apache.commons.lang3.AnnotationUtils;
import test.leetcode.easy.test.Code13;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/single-number/
 * 136. 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code136 {
    public static void main(String[] args) {
        int[] nums = {2,2,1};

        Code136 code136 = new Code136();
        int i = code136.singleNumber(nums);
        System.out.println(i);

    }

    public int singleNumber(int[] nums) {
//        System.out.println(Arrays.toString(nums));

        /**
         *  由于时间复杂度为O(n)，空间复杂度为O(1)
         *
         *  因此不可以采用双重循环的办法：O(n*n)
         *  也不可以采用空间换时间时间的办法，比如集合或者哈希存储计算过的大小: O(n)
         *
         * 可以采用异或算法：
         * 性质如下：
         * 1：a ^ 0 = a
         * 2: a ^ a = 0
         * 3. a ^ b ^ a = (a ^ a) ^ b = 0 ^ b = b (具有传递、结合性质)
         *
         * 因此将所有数字进行计算后，剩下的即为单个数字
         */

        int res = 0; // 默认为0 因为和0异或计算后，仍为当前数
        for (int num: nums) {
//            System.out.println(num);

            res = res ^ num;
        }
//        System.out.println(res);

        return res;
    }
}
