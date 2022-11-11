package test.leetcode.easy;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-paths/
 * 257. 二叉树的所有路径
 *
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *  
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,null,5]
 * 输出：["1->2->5","1->3"]
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：["1"]
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 100] 内
 * -100 <= Node.val <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code257 {
    public static void main(String[] args) {
        String[] root = {"1","2","3",null,"5"};

        TreeNode treeNode = TreeNode.getTreeNode(root);
        Code257 code257 = new Code257();
        List<String> strings = code257.binaryTreePaths(treeNode);
        System.out.println(strings);
    }

    public List<String> binaryTreePaths(TreeNode root) {
//        System.out.println(root);

        ArrayList<String> stringArrayList = new ArrayList<>();

        /**
         * 获取树路径
         * 采用广度优先搜索队列迭代当前节点方式
         */
        // 设置当前迭代的节点
        LinkedList<TreeNode> treeNodeLinkedList = new LinkedList<>();
        treeNodeLinkedList.add(root);
        // 设置当前迭代节点的路径
        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.add("");

        while (!treeNodeLinkedList.isEmpty()) {
            // 队列不为空
            // 获取队列头部
            TreeNode treeNode = treeNodeLinkedList.poll();
//            System.out.println(treeNode);
            // 获取当前节点的路径
            String s = stringLinkedList.poll();
//            System.out.println(s);

            if (treeNode == null) {
                // 节点为空
                break;
            }
            if (treeNode.left == null && treeNode.right == null) {
                // 叶子节点

                // 添加当前路径之和
                StringBuffer sb = new StringBuffer(s);
                sb.append(treeNode.val);

                // 添加路径输出
                stringArrayList.add(sb.toString());
            }

            // 不是叶子节点
            // 继续向下迭代
            if (treeNode.left != null) {
                // 添加下个迭代的节点
                treeNodeLinkedList.add(treeNode.left);

                // 记录当前节点的路径
                StringBuffer sb = new StringBuffer(s);
                sb.append(treeNode.val);
                sb.append("->");
                stringLinkedList.add(sb.toString());
            }
            if (treeNode.right != null) {
                // 添加下个迭代的节点
                treeNodeLinkedList.add(treeNode.right);

                // 记录当前节点的路径
                StringBuffer sb = new StringBuffer(s);
                sb.append(treeNode.val);
                sb.append("->");
                stringLinkedList.add(sb.toString());
            }

        }


        return stringArrayList;
    }



    public List<String> binaryTreePaths2(TreeNode root) {
//        System.out.println(root);

        ArrayList<String> stringArrayList = new ArrayList<>();

        /**
         * 获取树路径
         * 采用深度优先搜索递归方式
         * 1。递归结束标志
         *      即到了叶子节点的话，将该节点添加到队列
         * 2 . 不是叶子节点的话，将该路径添加到队列，继续递归
         */

        getPath(root, new StringBuffer(""), stringArrayList);


        return stringArrayList;
    }


    public void getPath(TreeNode root, StringBuffer path, List<String> paths) {

        // 判断边界条件
        if (root == null) {
            return;
        }

        // 判断递归结束标志
        if (root.left == null && root.right == null) {
            // 叶子节点 到了最后了

            // 添加当前叶子节点的路径
            path.append(root.val);

            // 添加到输出队列  多少个叶子节点，就输出多少个路径
            paths.add(path.toString());
        }


        // 不是叶子节点，继续递归
        StringBuffer sb = new StringBuffer(path);
        sb.append(root.val);
        sb.append("->");

        getPath(root.left, sb, paths);
        getPath(root.right, sb, paths);

    }

}
