package test.leetcode.easy;


import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sum-of-left-leaves/
 *
 * 404. 左叶子之和
 * 给定二叉树的根节点 root ，返回所有左叶子之和。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入: root = [3,9,20,null,null,15,7]
 * 输出: 24
 * 解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 * 示例 2:
 *
 * 输入: root = [1]
 * 输出: 0
 *  
 *
 * 提示:
 *
 * 节点数在 [1, 1000] 范围内
 * -1000 <= Node.val <= 1000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-left-leaves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code404 {
    public static void main(String[] args) {
        String[] root = {"1","2","3","4","5"};

        TreeNode treeNode = TreeNode.getTreeNode(root);


        Code404 code404 = new Code404();
        int i = code404.sumOfLeftLeaves(treeNode);
        System.out.println(i);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 广度优先搜索队列
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        // 添加队首
        treeNodes.add(root);

        int sum = 0;
        while (!treeNodes.isEmpty()) {
            // 获取队首
            TreeNode node = treeNodes.poll();

            if (node.left != null && isLeafNode(node.left)) {
                // 左节点存在 并且是叶子节点
                sum += node.left.val;
            }

            if (node.left != null && !isLeafNode(node.left)) {
                treeNodes.add(node.left);
            }

            if (node.right != null && !isLeafNode(node.right)) {
                treeNodes.add(node.right);
            }
        }

        return sum;
    }

    public int sumOfLeftLeaves2(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int ans = 0;
        if (root.left != null) {
            ans += isLeafNode(root.left) ? root.left.val : sumOfLeftLeaves(root.left);
        }
        if (root.right != null && !isLeafNode(root.right)) {
            ans += sumOfLeftLeaves(root.right);
        }
        return ans;
    }

    public boolean isLeafNode(TreeNode node) {
        // 判断是否叶子节点
        return node.left == null && node.right == null;
    }

}
