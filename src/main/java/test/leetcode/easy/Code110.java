package test.leetcode.easy;


/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 *
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * 示例 3：
 *
 * 输入：root = []
 * 输出：true
 *  
 *
 * 提示：
 *
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code110 {
    public static void main(String[] args) {

//        String[] root = {"3", "9", "20", null, null, "15", "7"};
        String[] root = {"1", "2", "2", "3", "3", null, null, "4", "4"};

        TreeNode treeNode = TreeNode.getTreeNode(root);

        Code110 code110 = new Code110();
        boolean balanced = code110.isBalanced(treeNode);
        System.out.println(balanced);
    }


    /**
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
//        System.out.println(root);

        /**
         *  高度平衡条件在于左右子树的高度差不能大于1
         *
         *  采用深度优先搜索法：即递归获取左右子树的高度值
         *  最后判断是否大于1
         */

        int diff = getDiff(root);
        System.out.println(diff);
        return diff >=0;
    }

    /**
     * 获取子节点高度差
     *
     * 自下而上的判断每个节点的左右子树高度差
     * 如果绝对值高度差大于1的话，说明已经不是二叉树了，直接返回
     *
     * @param root
     * @return
     */
    public int getDiff(TreeNode root){
        // 递归结束标志
        if (root == null) {
            return 0;
        }

        // 递归左子节点 每递归一层，层级+1
        int left = getDiff(root.left);
        // 递归右子节点 每递归一层，层级+1
        int right = getDiff(root.right);

        System.out.println("left:" + left + "=== val:" + root.val);
        System.out.println("right:" + right + "=== val:" + root.val);

        // 只要绝对值高度差大于1，表示已经
        if (Math.abs(left -right)>1 || left == -1 || right == -1) {
            return -1;
        }

//        return left;
        // 每返回一层，层级+1
        return Math.max(left, right) + 1;
    }


}
