package test.leetcode.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * 235. 二叉搜索树的最近公共祖先
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 *
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 *  
 *
 * 说明:
 *
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code235 {
    public static void main(String[] args) {
        String[] root = {"6","2","8","0","4","7","9",null,null,"3","5"};
//        String[] root = {"6","2","8","0","4","7","9",null,null,"3","5"};
        int p = 3, q = 5;

        TreeNode treeNode = TreeNode.getTreeNode(root);
        TreeNode treeNode1 = new TreeNode(p);
        TreeNode treeNode2 = new TreeNode(q);

        Code235 code235 = new Code235();
        TreeNode treeNode3 = code235.lowestCommonAncestor(treeNode, treeNode1, treeNode2);
        System.out.println(treeNode3);
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
//        System.out.println(root);
//        System.out.println(p);
//        System.out.println(q);


        // 获取p节点的路径
        List<Integer> pathp = getPath2(root, p);
        System.out.println(pathp);
        List<Integer> pathq = getPath2(root, q);
        System.out.println(pathq);

        TreeNode ans = new TreeNode();
        for (int i = 0; i < pathp.size() && i< pathq.size(); i++) {
//            System.out.println(pathp.get(i));
//            System.out.println(pathq.get(i));

            if (pathp.get(i) == pathq.get(i)) {
                ans.val = pathp.get(i);
            } else {
                break;
            }
        }
//        System.out.println(idx);
//        System.out.println(pathp.get(idx));

        return ans;
    }


    /**
     * 根据二叉搜索树的特点
     *
     *  左子树的值不大于根节点的值
     *  右子树的值不小于根节点的值
     *
     * @param root
     * @param target
     * @return
     */
    public List<Integer> getPath2(TreeNode root, TreeNode target) {
        List<Integer> path = new ArrayList<Integer>();
        TreeNode node = root;
        while (node.val != target.val) {
//            System.out.println(node);
            path.add(node.val);
            if (target.val < node.val) {
                // 左边查找
                node = node.left;
            } else {
                // 右边查找
                node = node.right;
            }
        }
        path.add(node.val);
        return path;
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        System.out.println(root);
//        System.out.println(p);
//        System.out.println(q);

        // 判断最近公共祖先节点
        // 根据二叉搜索树的性质，左子树的所有节点值不大于根节点值 右子树的所有节点值不小于根节点值
        // 1、两个节点都小于当前根节点，在左子树，继续往下判断
        // 2、两个节点都大于当前根节点，在右子树，继续往下判断
        // 3、两个节点在当前根节点分叉，那么当前根节点即为最近公共祖先
        // 同时判断两个节点
        // 默认当前的根节点
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                // 都在左子树
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                // 都在右子树
                ancestor = ancestor.right;
            } else {
                //  开始分叉 一个左子树 一个右子树
                // 直接返回该节点
                break;
            }
        }
//        System.out.println(ancestor);

        return ancestor;
    }



    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        System.out.println(root);
        System.out.println(p);
        System.out.println(q);

        List<TreeNode> path_p = getPath(root, p);
        List<TreeNode> path_q = getPath(root, q);
        TreeNode ancestor = null;
        for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
            if (path_p.get(i) == path_q.get(i)) {
                ancestor = path_p.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    public List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new ArrayList<TreeNode>();
        TreeNode node = root;
        while (node.val != target.val) {
            path.add(node);
            if (target.val < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }

//            System.out.println(node);
        }
        path.add(node);
        return path;
    }

}
