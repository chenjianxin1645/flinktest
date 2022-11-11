package test.leetcode.easy;


import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Integers;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/path-sum/
 *
 * 112. 路径总和
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 解释：等于目标和的根节点到叶节点路径如上图所示。
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * 解释：树中存在两条根节点到叶子节点的路径：
 * (1 --> 2): 和为 3
 * (1 --> 3): 和为 4
 * 不存在 sum = 5 的根节点到叶子节点的路径。
 * 示例 3：
 *
 * 输入：root = [], targetSum = 0
 * 输出：false
 * 解释：由于树是空的，所以不存在根节点到叶子节点的路径。
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code112 {
    public static void main(String[] args) {
        String[] root = {"5","4","8","11",null,"13","4","7","2",null,null,null,"1"};
//        String[] root = {"1","2"};
        int targetSum = 18;
//        int targetSum = 0;

        TreeNode treeNode = TreeNode.getTreeNode(root);

        Code112 code112 = new Code112();
        boolean b = code112.hasPathSum(treeNode, targetSum);
        System.out.println(b);
    }

    /**
     * 考虑采用广度优先迭代，需要自己维护个队列结构
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        // 声明个队列存放当前经过的节点
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
//        // 声明个队列存单当前经过的节点的的累加值
        LinkedList<Integer> integers = new LinkedList<>();
        // 声明当前路径的和
        integers.add(root.val);
        while (!treeNodes.isEmpty()) {
            // 获取当前经过的节点
            TreeNode treeNode = treeNodes.poll();
            // 获取经过该路径的之和
            Integer sum = integers.poll();
            System.out.println("val:" + treeNode.val + " == sum:" + sum);

            // 判断节点是否到达叶子节点
            if (treeNode.left == null && treeNode.right == null) {
                if (sum == targetSum) {
                    return true;
                }
                continue;
            }

            // 还未达到叶子节点
            if (treeNode.left != null) {
                // 还未达到叶子节点 继续添加队列
                treeNodes.add(treeNode.left);
                //记录当前节点的累加值
                integers.add(sum + treeNode.left.val);
            }
            if (treeNode.right != null) {
                // 还未达到叶子节点 继续添加队列
                treeNodes.add(treeNode.right);
                //记录当前节点的累加值
                integers.add(sum + treeNode.right.val);
            }

        }

        return false;
    }


    /**
     * 考虑采用深度优先迭代，递归获取每个叶子节点的路径的之和
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        return getSum(root, targetSum);
    }

    /**
     * 考虑采用深度优先迭代，递归获取每个叶子节点的路径的之和
     */

    public boolean getSum(TreeNode root, int targetSum) {
        // 初始化
        if (root == null) {
            // 空节点
            return false;
        }

        System.out.println("val:" + root.val + " == targetSum:" + targetSum);

        // 递归结束 即到了叶子节点
        if (root.left == null && root.right == null) {
            // 判断当前节点的后的目标值是否满足条件
            return targetSum == root.val;
        }

        // 还未到叶子节点，继续往下递归减少目标值
        return getSum(root.left, targetSum - root.val) || getSum(root.right, targetSum - root.val);
    }



}
