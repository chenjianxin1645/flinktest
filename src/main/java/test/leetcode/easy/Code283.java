package test.leetcode.easy;


import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/move-zeroes/
 * 283. 移动零
 *
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *  
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code283 {
    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};

        Code283 code283 = new Code283();
        code283.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }


    public void moveZeroes(int[] nums) {
//        System.out.println(Arrays.toString(nums));

        // 采用双指针方法
        // 左指针负责处理尾部0的位置
        // 右指针负责处理头部每个元素
        int left = 0 , right = 0;
        while (right < nums.length) {
//            System.out.println(nums[right]);

            // 判断是否为0
            if (nums[right] != 0) {
                // 替换左右指针
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;

                left++;
            }
            right++;
        }

    }


    public void moveZeroes2(int[] nums) {
//        System.out.println(Arrays.toString(nums));

        // 记录非0的索引
        LinkedList<Integer> idxs = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
//            System.out.println(nums[i]);
            if (nums[i] != 0) {
                idxs.add(i);
            }
        }
//        System.out.println(idxs);

        // 重新排序当前的非0索引
        for (int i = 0; i < nums.length; i++) {
            if (!idxs.isEmpty()) {
                Integer cur = idxs.poll();

                nums[i] = nums[cur];
            } else {
                nums[i] = 0;
            }
        }


    }
}
