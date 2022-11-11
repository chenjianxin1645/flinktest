package test.leetcode.easy.test;


import java.util.HashMap;
import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/contains-duplicate-ii/
 *
 * 219. 存在重复元素 II
 *
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 * 示例 3：
 *
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 *  
 *
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code219 {
    public static void main(String[] args) {
       int[] nums = {1,2,3,1};
       int k = 3;

        Code219 code219 = new Code219();
        boolean b = code219.containsNearbyDuplicate(nums, k);
        System.out.println(b);

    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
//        System.out.println(Arrays.toString(nums));
//        System.out.println(k);


        /**
         * 采取滑动窗口来滑动k大小的窗口元素，如果有相等的，则存在
         * 采用集合表示大小为k的滑动窗口
         */
        // 采用哈希集合表是滑动窗口的大小
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {

            if (i > k) {
                // 移除最早进入的窗口的原属
                set.remove(nums[i-k-1]);
            }

            // 判断是否存在窗口中
            if (!set.add(nums[i])) {
                // 已经添加过
                return true;
            }
        }

        return false;
    }


    public boolean containsNearbyDuplicate2(int[] nums, int k) {
//        System.out.println(Arrays.toString(nums));
//        System.out.println(k);

        // 采用哈希存储value的索引
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hm.containsKey(nums[i])) {
                if (i - hm.get(nums[i]) <=k) {
                    return true;
                }
            }
            hm.put(nums[i], i);
        }

        return false;
    }

//    public boolean containsNearbyDuplicate(int[] nums, int k) {
////        System.out.println(Arrays.toString(nums));
////        System.out.println(k);
//
//        // 暴力迭代
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i+1; j < nums.length; j++) {
//                if (nums[i] == nums[j] && j-i<=k) {
//                    return true;
//                }
//            }
//        }
//
//
//        return false;
//    }
}
