package test.leetcode.easy;


import org.apache.commons.collections.map.HashedMap;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/longest-harmonious-subsequence/
 *
 * 594. 最长和谐子序列
 *
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
 *
 * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,2,2,5,2,3,7]
 * 输出：5
 * 解释：最长的和谐子序列是 [3,2,2,2,3]
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：2
 * 示例 3：
 *
 * 输入：nums = [1,1,1,1]
 * 输出：0
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 2 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-harmonious-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code594 {

    public static void main(String[] args) {
        int[] nums = {1,3,2,2,5,2,3,7};

        Code594 code594 = new Code594();
        int lhs = code594.findLHS(nums);
        System.out.println(lhs);
    }


    public int findLHS(int[] nums) {
        System.out.println(Arrays.toString(nums));

        /**
         * 考虑采用利用哈希记录value的次数
         */
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int num: nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        System.out.println(hm);

        // 遍历当前的hash keys
        int res = 0;
        for (int key: hm.keySet()) {
//            System.out.println(key);
            // 判断是否有key的值相差一
            if (hm.containsKey(key -1)) {
                res = Math.max(res, hm.get(key) + hm.get(key - 1));
            }

        }

        return res;

    }

}
