package test.leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/summary-ranges/
 * 228. 汇总区间
 *
 * 给定一个  无重复元素 的 有序 整数数组 nums 。
 *
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 *
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 *
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * 示例 2：
 *
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/summary-ranges
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code228 {

    public static void main(String[] args) {
        int[] nums = {0,2,3,4,6,8,9};

        Code228 code228 = new Code228();
        List<String> stringList = code228.summaryRanges(nums);
        System.out.println(stringList);
    }


    public List<String> summaryRanges(int[] nums) {
        System.out.println(Arrays.toString(nums));

        ArrayList<String> stringArrayList = new ArrayList<>();

        if (nums.length == 0) {
            return stringArrayList;
        }

        int start = nums[0];
        int end = nums[0];
        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i] + "==" + nums[i+1]);


            if (end + 1 == nums[i]) {
                // 表示连续
                end = nums[i];
            } else if (end != nums[i]) {
                // 表示不连续

                if (start == end) {
                    stringArrayList.add(String.valueOf(start));
                } else {
                    stringArrayList.add(start +"->" + end);
                }

                // 初始化区间
                start = nums[i];
                end = nums[i];
            }
        }
//        System.out.println(start);
//        System.out.println(end);

        if (start == end) {
            stringArrayList.add(String.valueOf(start));
        } else {
            stringArrayList.add(start +"->" + end);
        }


        return stringArrayList;
    }
}
