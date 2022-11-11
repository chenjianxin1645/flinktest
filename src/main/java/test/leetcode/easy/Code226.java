package test.leetcode.easy;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 *226. 翻转二叉树
 *
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * 示例 2：
 *
 *
 *
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 * 示例 3：
 *
 * 输入：root = []
 * 输出：[]
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code226 {

    public static void main(String[] args) {
       String[] root = {"4", "2", "7","1","3","6","9"};

        TreeNode treeNode = TreeNode.getTreeNode(root);
        Code226 code226 = new Code226();
        TreeNode treeNode1 = code226.invertTree(treeNode);
        System.out.println(treeNode1);
    }


    /**
     * 深度优先搜索 -- 采用递归
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
//        System.out.println(root);
        /**
         * 递归思路
         *
         * 先从根节点开始递归往下遍历
         * 如果到达叶子节点的话，记录当前节点的左右位置
         * 然后互换即可
         *
         */

        // 递归到叶子节点的标志
        if (root == null) {
            return null;
        }
        
        // 获取当前节点的左节点
        TreeNode left = invertTree(root.left);
        // 获取当前节点的右节点
        TreeNode right = invertTree(root.right);
        // 当前节点左右位置互换
        root.left = right;
        root.right = left;



        return root;
    }


    /**
     * 广度优先搜索 -- 采用队列结构存储节点
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
//        System.out.println(root);

        if (root == null) {
            return null;
        }

        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.push(root);
        while (!treeNodes.isEmpty()) {
            TreeNode treeNode = treeNodes.pop();


            TreeNode temp = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = temp;

            if (treeNode.left != null) {
                treeNodes.push(treeNode.left);
            }
            if (treeNode.right != null) {
                treeNodes.push(treeNode.right);
            }

        }


        return root;
    }


}
