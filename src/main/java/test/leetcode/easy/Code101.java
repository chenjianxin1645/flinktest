package test.leetcode.easy;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/symmetric-tree/
 * 101. 对称二叉树
 *
 *
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *  
 *
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code101 {
    public static void main(String[] args) {
        String[] root = {"1", "2", "2", "3", "4", "4", "3"};

        Code101 code101 = new Code101();
        TreeNode treeNode = TreeNode.getTreeNode(root);
        boolean symmetric = code101.isSymmetric(treeNode);
        System.out.println(symmetric);

    }


    public boolean isSymmetric(TreeNode root) {
//        System.out.println(root);
        boolean check = check(root, root);

        return check;
    }

    /**
     *  递归法：
     *      判断数是否对称，可以将数复制两份，并且满足两个要求：
     *      1. 各自的根节点相同
     *      2. 每个树的右子树都与左边镜像数的左子树相同
     */
    public boolean check(TreeNode p, TreeNode q) {
//        System.out.println(p);
//        System.out.println(q);

        // 递归结束
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }

        // 继续递归
        return check(p.left, q.right) && check(p.right, q.left);
    }

    /**
     *  迭代法：
     *      判断数是否对称，可以将数复制两份，并且满足两个要求：
     *      1. 各自的根节点相同
     *      2. 每个树的右子树都与左边镜像数的左子树相同
     *
     *    采用队列结构，存储右子树和左子树的节点，互相比较
     *
     */

    public boolean check2(TreeNode p, TreeNode q) {
//        System.out.println(p);
//        System.out.println(q);

        // 声明个队列
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(p);
        treeNodes.add(q);

        while (!treeNodes.isEmpty()) {
            // 弹出比较 注意顺序相反
            TreeNode node1 = treeNodes.poll();
            TreeNode node2 = treeNodes.poll();

           // 都为空 不进行比较
            if (node1 == null && node2 == null) {
                continue;
            }
            // 不都为空 直接返回 都不为空 判断节点值
            if (node1 ==  null || node2 == null || node1.val != node2.val) {
                return false;
            }

            // 当前根节点相等，开始添加各自节点的子树 注意左右两数顺序相反
            // 左边和右边比较
            treeNodes.add(node1.left);
            treeNodes.add(node2.right);
            // 右边和左边比较
            treeNodes.add(node1.right);
            treeNodes.add(node2.left);
        }

        return true;
    }

}
