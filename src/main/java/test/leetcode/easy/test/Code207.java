package test.leetcode.easy.test;


/**
 * https://leetcode-cn.com/problems/contains-duplicate/
 *
 * 217. 存在重复元素
 *
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true ；如果数组中每个元素互不相同，返回 false 。
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,1]
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 示例 3：
 *
 * 输入：nums = [1,1,1,3,3,4,3,2,4,2]
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code207 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
//        int[] nums = {1,1};

        Code207 code207 = new Code207();
        boolean b = code207.containsDuplicate(nums);
        System.out.println(b);
    }


    public boolean containsDuplicate(int[] nums) {
//        System.out.println(Arrays.toString(nums));


        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
//                System.out.println(nums[i] + "===" + nums[j]);
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        return false;
    }
}
