package test.leetcode.easy.test;


import test.leetcode.easy.TreeNode;

/**
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 *
 * 530. 二叉搜索树的最小绝对差
 *
 *
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *
 * 差值是一个正数，其数值等于两值之差的绝对值。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [4,2,6,1,3]
 * 输出：1
 * 示例 2：
 *
 *
 * 输入：root = [1,0,48,null,null,12,49]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 树中节点的数目范围是 [2, 104]
 * 0 <= Node.val <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code530 {
    public static void main(String[] args) {
        String[] root = {"543","384","652",null,"445",null,"699"};

        Code530 code530 = new Code530();
        int minimumDifference = code530.getMinimumDifference(TreeNode.getTreeNode(root));
        System.out.println(minimumDifference);
    }


    public int getMinimumDifference(TreeNode root) {
        System.out.println(root);

        /**
         *
         * 二叉搜索树；
         *  1。 中序遍历是有序的
         * 获取任意两个节点的之间的最小差值：
         *  根据中序遍历是有序，判断差值的最小值
         *  那么必须是相邻的最近节点，那么获取前后的差值即可
         *
         */
        // 首先获取中序
        getSortVal(root);



        return min;
    }

    /**
     * 采用递归遍历
     * @param root
     */
    public void getSortVal(TreeNode root) {
        if (root == null) {
            return;
        }
        // 递归左子树
        getSortVal(root.left);
        // 打印中序值
//        System.out.println(root.val);
        // 计算当前中序值的前后差值
        getMinVal(root.val);
        // 递归右子树
        getSortVal(root.right);
    }

    public int pre = -1;
    public int min = Integer.MAX_VALUE;
    public void getMinVal(int val) {
//        System.out.println(val);

        // 判断是否第一个值
        if (pre == -1) {
            pre = val;
        } else {
            // 获取最小值
            min = Math.min(val - pre, min);
            System.out.println(min + "==" + (val - pre));
            pre = val;
        }
    }


}
