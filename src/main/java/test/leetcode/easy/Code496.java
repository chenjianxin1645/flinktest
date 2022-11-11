package test.leetcode.easy;


import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/next-greater-element-i/
 *
 * 496. 下一个更大元素 I
 *
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 *
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 *
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 *
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出：[-1,3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
 * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * 示例 2：
 *
 * 输入：nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出：[3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
 * - 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
 *  
 *
 * 提示：
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 *  
 *
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code496 {
    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4,2};

        Code496 code496 = new Code496();
        int[] ints = code496.nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(ints));

    }


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));


        /**
         * 拆解为两个问题
         * 1。 如何找到当前元素的右边元素的第一个大值 -- 采用堆栈结构，村粗当前右边元素的大值
         * 2。 如何存储当前元素信息 -- 采用哈希表存储当前元素的大值信息
         */
        HashMap<Integer, Integer> hm = new HashMap<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        // 从右边开始遍历
        for (int i = nums2.length - 1; i >=0; i--) {
            int num = nums2[i];
//            System.out.println(num);

            // 判断堆栈结构里的值
            // 注意该堆栈为单调栈
            while (!linkedList.isEmpty() && num >= linkedList.peek()) {
                // 大于栈顶元素，弹出
                linkedList.pop();
            }
            // 设置当前元素的第一个右边大值
            hm.put(num, linkedList.isEmpty() ? -1 : linkedList.peek());

            // 添加当前元素到堆栈中
            linkedList.push(num);
        }
        System.out.println(hm);

        int[] ints = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ints[i] = hm.get(nums1[i]);
        }

        return ints;
    }


    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));


        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
//            System.out.println(nums2[i]);
            hm.put(nums2[i], i);
        }
        System.out.println(hm);

        int[] ints = new int[nums1.length];
//        System.out.println(Arrays.toString(ints));
        for (int i = 0; i < nums1.length; i++) {
            // 获取下标值
            if (hm.containsKey(nums1[i])) {
                int idx = hm.get(nums1[i]);
                if (idx < nums2.length) {
                    int val = nums2[idx];
                    for (int j = idx + 1; j < nums2.length; j++) {
                        if (nums2[j] > val) {
                            ints[i] = nums2[j];
                            break;
                        }
                    }
                }
            }

            if (ints[i] == 0) {
                ints[i] = -1;
            }
        }


        return ints;
    }


}
