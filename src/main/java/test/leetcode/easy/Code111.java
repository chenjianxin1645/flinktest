package test.leetcode.easy;


import javax.validation.constraints.Max;

/**
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 * 111. 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明：叶子节点是指没有子节点的节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * 示例 2：
 *
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *  
 *
 * 提示：
 *
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code111 {
    public static void main(String[] args) {
        String[] root = {"3","9","20",null,null,"15","7"};

        TreeNode treeNode = TreeNode.getTreeNode(root);
        Code111 code111 = new Code111();
        int i = code111.minDepth(treeNode);
        System.out.println(i);

    }

    public int minDepth(TreeNode root) {
//        System.out.println(root);

        int hight = getHight(root);
        System.out.println(hight);

        return hight;
    }

    // 获取每个节点的高度
    public int getHight(TreeNode root) {
        // 递归结束
        if (root == null) {
            return  0;
        }
        // 如果都为空，意味着到了叶子节点
        if (root.left == null && root.right == null) {
            return 1;
        }

        // 初始化最小值
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            // 递归获取左子节点的深度获取最小
            min = Math.min(getHight(root.left), min);

            // 每返回一次，层级+1
//            System.out.println("val:" + root.val + " -- min:" + min);
        }
        if (root.right != null) {
            // 递归获取左子节点的深度获取最小
            min = Math.min(getHight(root.right), min);
//            System.out.println("val:" + root.val + " -- min:" + min);
        }

        // 当层结束，返回上一层级即+1
        return min + 1;
    }
}
