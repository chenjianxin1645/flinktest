package test.leetcode.easy.test;

import test.leetcode.easy.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * 144. 二叉树的前序遍历
 *
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 *
 * 输入：root = [1,2]
 * 输出：[1,2]
 * 示例 5：
 *
 *
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code144 {
    public static void main(String[] args) {
        String[] root = {"1", null, "2", "3"};
//        String[] root = {};
        TreeNode treeNode = TreeNode.getTreeNode(root);
//        System.out.println(treeNode);

        Code144 code144 = new Code144();
        List<Integer> integers = code144.preorderTraversal(treeNode);
        System.out.println(integers);
    }


    public List<Integer> preorderTraversal2(TreeNode root) {
//        System.out.println(root);

        ArrayList<Integer> integers = new ArrayList<>();
        if (root == null) {
            return integers;
        }

        /**
         * 前序遍历
         * 根-》左-》右
         *
         * 广度迭代，采用堆栈结构，前进后出
         */
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.push(root);
        while (!linkedList.isEmpty()) {
            TreeNode poll = linkedList.pop();
            integers.add(poll.val);

            if (poll.right != null) {
                linkedList.push(poll.right);
            }

            // 优先输出左节点
            if (poll.left != null) {
                linkedList.push(poll.left);
            }
        }


        return integers;
    }

    /**
     * 深度优先搜索，即递归迭代
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
//        System.out.println(root);

        ArrayList<Integer> integers = new ArrayList<>();
        if (root == null) {
            return integers;
        }

        /**
         * 前序遍历
         * 根-》左-》右
         *
         * 广度迭代，采用堆栈结构，前进后出
         */
        preorder(root, integers);

        return integers;
    }

    public void preorder(TreeNode root, List<Integer> integers) {

        if (root == null) {
            return ;
        }

        // 节点不为空，添加
        integers.add(root.val);
        // 递归左节点
        preorder(root.left, integers);
        // 递归右节点
        preorder(root.right, integers);

    }
}
