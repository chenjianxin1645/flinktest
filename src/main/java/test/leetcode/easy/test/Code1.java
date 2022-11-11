package test.leetcode.easy.test;


import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/two-sum/
 *  1. 两数之和
 *  给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *  
 *
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code1 {

    public static void main(String[] args) {
        int [] nums = {2,7,11,15};
        int target = 26;

        Code1 code1 = new Code1();
        int[] twoSum = code1.twoSum(nums, target);
        System.out.println(Arrays.toString(twoSum));
    }

    /**
     * 哈希表
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        System.out.println(Arrays.toString(nums));
        System.out.println(target);

//        System.out.println(twoSum.length);
//        System.out.println(Arrays.toString(twoSum));

        // 哈希存储已经计算过的
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
            if (hm.containsKey(target - nums[i])) {
                return new int[]{hm.get(target - nums[i]), i};
            }
            hm.put(nums[i], i);
        }

        return null;
    }


    /**
     * 暴力枚举
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        System.out.println(Arrays.toString(nums));
        System.out.println(target);

        int[] twoSum = new int[2];
//        System.out.println(twoSum.length);
//        System.out.println(Arrays.toString(twoSum));

        // 双层循环遍历
        for (int i = 0; i < nums.length; i++) {
            System.out.println("外部：" + nums[i]);
            for (int j = i+1; j < nums.length; j++) {
                System.out.println("内部：" + nums[j]);
//                System.exit(0);
                if (nums[i] + nums[j] == target) {
                    twoSum[0] = i;
                    twoSum[1] = j;

                    return twoSum;
                }
            }
//            System.exit(0);
        }

        System.out.println(Arrays.toString(twoSum));
        return twoSum;
    }
}
