package test.leetcode.easy;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * 108. 将有序数组转换为二叉搜索树
 *
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 *
 * 示例 2：
 *
 *
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 按 严格递增 顺序排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code108 {
    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};

        Code108 code108 = new Code108();
        TreeNode node = code108.sortedArrayToBST(nums);
        System.out.println(node);
    }


    /**
     * 要求最后为一颗BST（二叉搜索树），即中序遍历是升序的即可
     * 左子节点不大于父节点  右子节点不小于父节点
     * 如果没有高度高度平衡限制的话，那么可以任意选择节点作为根节点
     *
     * 但是如果有高度平衡限制的话，那么需要保证根节点为数组的中间值（
     *      当数组为奇数时，根节点唯一
     *      当数组为偶数是，根节点可以选择左右两边靠近中间位置的
     * ）
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
//        System.out.println(Arrays.toString(nums));

        TreeNode bst = Bst(nums, 0, nums.length - 1);
        return bst;
    }

    /**
     *  当选择好中间位置式，
     *  采用递归法来构造中间的位置
     *  数组已经排好序了
     */
    public TreeNode Bst(int[] nums, int left, int right) {
        // 获取中间位置，选择中间的节点为根节点
        // 因此可以递归的构造
        // 递归结束标志是 左边大于右边的索引，表示数组搜索为空找不到结束了
        if (left > right) {
            return null;
        }
        // 搜索未结束，开始获取中间位置
        int mid = (left+right)/2; // 不管数组奇偶大小，总是靠近左边
        // 生成根节点或者父子节点
        TreeNode node = new TreeNode(nums[mid]);
        // 开始递归获取当前根节点的子节点
        // 左子节点的值不大于根节点值
        node.left = Bst(nums, left, mid -1);
        //  右子节点的值不小于根节点的值
        node.right = Bst(nums, mid+1, right);

//        System.out.println("node:" + node);
        return node;
    }

}
