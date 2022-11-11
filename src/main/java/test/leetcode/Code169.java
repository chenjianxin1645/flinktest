package test.leetcode;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[3,2,3]
 * 输出：3
 * 示例 2：
 *
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *  
 *
 * 进阶：
 *
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code169 {
    public static void main(String[] args) {
        int[] nums = new int[]{3,3,4,5,3,7,3,2,3};

        majorityElement(nums);
    }

    public static int majorityElement(int[] nums) {
        // hash
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int num : nums) {
//            System.out.println(num);
            if (!hash.containsKey(num)) {
                hash.put(num, 1);
            } else {
                hash.put(num, hash.get(num) + 1);
                if (hash.get(num) > nums.length /2) {
                    System.out.println("hash:" + num);
                    break;
                }
            }
        }
        System.out.println(hash);
        Set<Map.Entry<Integer, Integer>> entries = hash.entrySet();
        for (Map.Entry<Integer, Integer> entry: entries) {
            System.out.println(entry);
            System.out.println(entry.getValue());
            if (entry.getValue() > nums.length/2) {
                System.out.println(entry.getKey());
                break;
            }
        }
//        System.exit(0);


        // 排序 因为是众数，所以排好序的数组的arr[arr.length/2]一定是众数
        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(nums.length/2);
        System.out.println(nums[nums.length/2]);



        // 摩尔投票法  因为是众数，最后投票肯定大于0
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                // 选取第一个为众数
                candidate = num;
            }
            // 投票数
            count += (num == candidate) ? 1 : -1;
        }
        System.out.println(candidate);



        return 0;
    }
}
