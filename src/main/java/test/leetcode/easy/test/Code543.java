package test.leetcode.easy.test;


import test.leetcode.easy.TreeNode;

/**
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 *
 * 543. 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *  
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *  
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code543 {
    public static void main(String[] args) {
        String[] root = {"4","-7","-3",null,null,
                "-9","-3","9","-7","-4",null,"6",null,
                "-6","-6",null,null,"0","6","5",null,
                "9",null,null,"-1","-4",null,null,null,"-2"};


        Code543 code543 = new Code543();
        int i = code543.diameterOfBinaryTree(TreeNode.getTreeNode(root));
        System.out.println(i);

    }

    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
//        System.out.println(root);

        /**
         * 获取最长的直径
         *
         * 就是要从最深的左叶子节点到最深的右叶子节点上去
         *
         */
        /**
         * 深度优先递归
         */

        dfs(root);
//        System.out.println(max);

        return  max;
    }

    public int dfs(TreeNode root) {
//        System.out.println(root);

        /**
         * 获取最长的直径
         *
         * 就是要从最深的左叶子节点到最深的右叶子节点上去
         *
         */
        /**
         * 深度优先递归
         */
        if (root == null) {
            return 0;
        }
        // 递归左节点
        int i1 = dfs(root.left);
        // 递归右节点
        int i2 = dfs(root.right);
        System.out.println("i1：" + i1 + "== i2：" + i2 + "==" + (Math.max(i1, i2) + 1));
        System.out.println(root.val);

        // 因为求路径的话，不一定要经过根节点
        // 当前节点遍历结束后，判断当前节点的左右层级之和，更新最大值
        max = Math.max(i1 + i2, max);
        System.out.println(max);

        // 层级加1
        return Math.max(i1, i2) + 1;
    }

}

