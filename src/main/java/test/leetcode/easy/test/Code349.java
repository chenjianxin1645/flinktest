package test.leetcode.easy.test;


import java.util.Arrays;
import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 *
 * 349. 两个数组的交集
 *
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 解释：[4,9] 也是可通过的
 *  
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code349 {
    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};

        Code349 code349 = new Code349();
        int[] intersection = code349.intersection(nums1, nums2);
        System.out.println(Arrays.toString(intersection));
    }

    public int[] intersection(int[] nums1, int[] nums2) {
//        System.out.println(Arrays.toString(nums1));
//        System.out.println(Arrays.toString(nums2));

        // 判断来给你个集合的交集

        // 哈希集合判断
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
//            System.out.println(nums1[i]);
//            System.out.println(nums2[i]);
            set.add(nums1[i]);
        }
//        System.out.println(set);

        HashSet<Integer> res = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
//            System.out.println(nums1[i]);
//            System.out.println(nums2[i]);
            if (set.contains(nums2[i])) {
                res.add(nums2[i]);
            }
        }

        return res.stream().mapToInt(num -> num).toArray();
    }


}
