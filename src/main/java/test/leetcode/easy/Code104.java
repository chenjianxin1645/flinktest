package test.leetcode.easy;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * 104. 二叉树的最大深度
 *
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code104 {
    public static void main(String[] args) {
        String[] root = {"3","9","20",null,null,"15","7"};

        TreeNode treeNode = TreeNode.getTreeNode(root);

        Code104 code104 = new Code104();
        int i = code104.maxDepth(treeNode);
        System.out.println(i);
    }


    /**
     *  深度优先搜索法： 即采用递归法
     *
     *   当判断当前节点为空时，直接返回，
     *   否则，判断左右节点的大小，并且当前+1
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
//        System.out.println(root);

        if (root == null) {
            return 0;
        }

        // 获取左节点的层级 每进行一次深度搜索，层级+1
        int left = maxDepth(root.left);
//        System.out.println("left:" + left);

        // 获取右节点的层级， 每进行一次深度虽多，层级+1
        int right = maxDepth(root.right);
//        System.out.println("right:" + right);

        // 层级遍历结束，返回上一层级即+1
//        System.out.println("max:" + Math.max(left, right));
        return Math.max(left, right) + 1;
    }


    /**
     * 采用迭代法：
     *     判断每个根节点是否子节点
     *     用一个队列结构存放每个需要判断的节点
     *
     *    广度优先搜索
     */
    public int maxDepth2(TreeNode root) {
//        System.out.println(root);

        if (root == null) {
            return 0;
        }

        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        int cnt = 0;
        while (!treeNodes.isEmpty()) {
            // 这里可以改造，直接获取队列大小
            // 然后迭代队列大小即可
            LinkedList<TreeNode> newTreeNodes = new LinkedList<>();
            while (!treeNodes.isEmpty()) {
                // 迭代取出旧的队列
                TreeNode node = treeNodes.poll();
                if (node.left != null) {
                    newTreeNodes.add(node.left);
                }
                if (node.right != null) {
                    newTreeNodes.add(node.right);
                }
            }
            cnt++;
            treeNodes = newTreeNodes;
        }

        return cnt;
    }


}
