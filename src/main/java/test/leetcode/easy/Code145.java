package test.leetcode.easy;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * 145. 二叉树的后序遍历
 *
 *
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[3,2,1]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *  
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code145 {
    public static void main(String[] args) {

        String[] root = {"4","2", null, "1", "3"};
        TreeNode treeNode = TreeNode.getTreeNode(root);

        Code145 code145 = new Code145();
        List<Integer> integers = code145.postorderTraversal(treeNode);
        System.out.println(integers);
    }

    /**
     * 广度优先搜索堆栈法
     *
     *
     * 后序遍历：左右中
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        System.out.println(root);
//        System.exit(0);
        ArrayList<Integer> integers = new ArrayList<>();

        if (root == null) {
            return integers;
        }

        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.push(root);

        // 存放添加过的根节点
        LinkedList<TreeNode> preList = new LinkedList<>();
        while (!linkedList.isEmpty()) {
            // 获取当前的节点
            TreeNode treeNode = linkedList.peek();
            // 获取上次添加过的根节点
            TreeNode pre = preList.peek();

            // 判断当前获取的节点是否已经添加过
            if (treeNode == pre) {
                // 直接弹出
                integers.add(treeNode.val);
                linkedList.pop();

                preList.pop();

                System.out.println(treeNode.val);
                continue;
            }

            // 获取右节点
            if (treeNode.right != null) {
                linkedList.push(treeNode.right);
            }
            // 获取左节点
            if (treeNode.left != null) {
                linkedList.push(treeNode.left);
            }
            // 设置添加过的节点
            if (treeNode.left != null || treeNode.right != null) {
                preList.push(treeNode);
            }

            if (treeNode.left == null && treeNode.right == null) {
                // 叶子节点
                integers.add(treeNode.val);

                linkedList.pop();

                System.out.println(treeNode.val);
            }
        }


        return integers;
    }

    /**
     * 深度优先搜索递归法
     *
     *  后序遍历：左右中
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
//        System.out.println(root);
        ArrayList<Integer> integers = new ArrayList<>();
        postorder(root, integers);

        return integers;
    }

    /**
     * 左右中递归
     *
     * @param root
     * @param integers
     * @return
     */
    public void postorder(TreeNode root,  ArrayList<Integer> integers) {
        // 递归结束
        if (root == null) {
            return ;
        }

        // 递归未结束
        // 先递归左节点
        postorder(root.left, integers);
        // 后递归右节点
        postorder(root.right, integers);

        // 添加当前节点
        integers.add(root.val);
    }
}
