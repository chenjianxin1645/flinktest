package test.leetcode.easy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * 94. 二叉树的中序遍历
 *
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
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
 * 输出：[2,1]
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
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code94 {
    public static void main(String[] args) {

//        int [] root = {1,null,2,3};

        Code94 code94 = new Code94();
        TreeNode root = code94.init();

        List<Integer> integers = code94.inorderTraversal(root);
        System.out.println(integers);
    }

    public TreeNode init () {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2, new TreeNode(3), null);

        return root;
    }


    /**
     * 递归法
     * 因为每个根节点的遍历，都是从左-根-右，递归相同的操作
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
//        System.out.println(root);
        ArrayList<Integer> integers = new ArrayList<>();


        // 递归操作
        inorder(root, integers);

        return integers;
    }

    // 递归 内部自动维护一个堆栈结构
    public void inorder(TreeNode root, List<Integer> res) {
        // 结束递归操作
        if (root == null) {
            return ;
        }

        // 递归当前节点下的左节点
        inorder(root.left, res);
        // 递归结束，添加当前节点
        res.add(root.val);
        // 继续递归当前节点下的右节点
        inorder(root.right, res);
    }

    /**
     * 模拟迭代法
     *  手动维护一个堆栈结构  先进后出，适合当前节点先进，后继续遍历左节点 当左节点结束，继续遍历右节点
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
//        System.out.println(root);

        ArrayList<Integer> integers = new ArrayList<>();

        // 迭代 -- 模拟堆栈 先进后出
        LinkedList<TreeNode> list = new LinkedList<>();

        while (root != null || !list.isEmpty()) {
            // 不断循环左节点
            while (root != null) {
                list.push(root);
                root = root.left;
            }
//            System.out.println(list);
//            System.exit(0);
            //  左节点结束，输出当前节点
            TreeNode pop = list.pop();
            integers.add(pop.val);

            // 开始遍历当前节点的右侧
            root = pop.right;

            // 右侧节点为空，继续弹出堆栈上次节点
        }

        return integers;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
        }

        @Override
        public String toString() {
            return "val:" + val + " ==== left:" + left + " === right:" + right;
        }
    }

}
