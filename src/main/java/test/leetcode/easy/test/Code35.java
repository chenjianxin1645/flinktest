package test.leetcode.easy.test;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/search-insert-position/
 *
 * 35. 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例 2:
 *
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 *
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 示例 4:
 *
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 * 示例 5:
 *
 * 输入: nums = [1], target = 0
 * 输出: 0
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为无重复元素的升序排列数组
 * -104 <= target <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code35 {
    public static void main(String[] args) {
//        int[] nums = {1,3,5,6};
//        int target = 5;

        int[] nums = {1,2,4,6,7};
        int target = 3;

        Code35 code35 = new Code35();
        int i = code35.searchInsert(nums, target);
        System.out.println(i);

    }

    public int searchInsert(int[] nums, int target) {
        System.out.println(Arrays.toString(nums));
        System.out.println(target);
        
        // 改进二分法
        int left = 0;
        int right = nums.length -1;
//        System.out.println(left);
//        System.out.println(right);

        while (left<= right) {
            int mid = (left + right) /2;

            if (target <= nums[mid]) {
                right = mid -1;
            } else {
                left = mid + 1;
            }

//            System.out.println(left);
//            System.out.println(right);
//            System.out.println(mid);
//            System.exit(0);
        }

        return left;
    }

    public int searchInsert2(int[] nums, int target) {
        System.out.println(Arrays.toString(nums));
        System.out.println(target);

        // 空数组直接返回
        if (nums == null) {
            return 0;
        }
        // 只有一个
        if (nums.length == 1) {
            return nums[0] >= target  ? 0 : 1;
        }

        // 采用二分法
        int start = 0;
        int end = nums.length -1;
        int idx =  (start + end)/2;
        System.out.println(idx);
        // 循环判断
        while (idx>=0) {
//            System.out.println(nums[idx]);
            if (nums[idx] == target) {
                return idx;
            }

            // 不相等，继续二分
            if (nums[idx] > target) {
                end = idx;
            } else {
                start = idx;
            }
            idx = (start + end)/2;
            System.out.println(start + "====" + end + "===" + idx);
//            System.exit(0);

            if (idx <= start || idx == end) {
                if (nums[end] < target) {
                    return end+1;
                } else if (nums[start] >= target) {
                    return start>0 ? start-1 : start;
                } else {
                    return start + 1;
                }
            }
        }

        return 0;
    }

}
