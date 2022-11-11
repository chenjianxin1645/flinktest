package test.leetcode.easy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 *
 * 350. 两个数组的交集 II
 *
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 *
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *  
 *
 * 提示：
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *  
 *
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code350 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};

        Code350 code350 = new Code350();
        int[] intersect = code350.intersect(nums1, nums2);
        System.out.println(Arrays.toString(intersect));
    }


    public int[] intersect(int[] nums1, int[] nums2) {
//        System.out.println(Arrays.toString(nums1));
//        System.out.println(Arrays.toString(nums2));

        // 分别将两个数组添加到hash表中存储
        HashMap<Integer, Integer> hm1 = new HashMap<>();
        HashMap<Integer, Integer> hm2 = new HashMap<>();
        for (int num: nums1) {
            if (hm1.containsKey(num)) {
                hm1.put(num, hm1.get(num) + 1);
            } else {
                hm1.put(num, 1);
            }
        }
//        System.out.println(hm1);
        for (int num: nums2) {
            if (hm2.containsKey(num)) {
                hm2.put(num, hm2.get(num) + 1);
            } else {
                hm2.put(num, 1);
            }
        }
//        System.out.println(hm2);

        // 比较两个hash表
        ArrayList<Integer> res = new ArrayList<>();
        for (int num: hm1.keySet()) {
            if (hm2.containsKey(num)) {
                System.out.println(num);
                int min = Math.min(hm1.get(num), hm2.get(num));

                for (int i = 0; i < min; i++) {
                    res.add(num);
                }
            }
        }
//        System.out.println(res);



        return res.stream().mapToInt(num -> num).toArray();
    }
}
