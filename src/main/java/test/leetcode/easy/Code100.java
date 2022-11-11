package test.leetcode.easy;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/same-tree/
 * 100. 相同的树
 *
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：p = [1,2,3], q = [1,2,3]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：p = [1,2], q = [1,null,2]
 * 输出：false
 * 示例 3：
 *
 *
 * 输入：p = [1,2,1], q = [1,1,2]
 * 输出：false
 *  
 *
 * 提示：
 *
 * 两棵树上的节点数目都在范围 [0, 100] 内
 * -104 <= Node.val <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/same-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code100 {
    public static void main(String[] args) {
//        String[] p = {"1", "2", "3", null, null, "4", "5"}, q = {"1", "2", "3"};
        String[] p = {}, q = {};

        Code100 code100 = new Code100();
        TreeNode pTreeNode = code100.getTreeNode(p);
        TreeNode qTreeNode1 = code100.getTreeNode(q);

        boolean sameTree = code100.isSameTree(pTreeNode, qTreeNode1);
        System.out.println(sameTree);

    }

    /**
     * 迭代法：为每一个节点添加左右节点信息
     * @param arr
     * @return
     */
    public TreeNode getTreeNode(String[] arr) {
        if (arr.length == 0) {
            return null;
        }

        // 声明一个队列存放每个节点的当前信息 先进先出设置左右节点信息
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        // 创建跟节点信息
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        // 声明当前头部节点信息
        TreeNode head = root;
//        System.out.println(head);
//        System.exit(0);
        Boolean left = false;
        Boolean right = false;
        for (int i = 1; i < arr.length; i++) {
//            System.out.println(arr[i]);

            // 生成当前节点
            TreeNode treeNode = null;
            if (arr[i] != null) {
                int num_int = Integer.parseInt(arr[i]);
                treeNode = new TreeNode(num_int);

                // 添加到队列，留着下一层节点
                linkedList.add(treeNode);
            }
//
            // 判断当前头部节点左右节点信息
           if (right) {
                // 当前头节点的左右节点已经补充，重新从队列拿最新的
                head = linkedList.poll();

                // 重置当前头部节点信息
                left = false;
                right = false;
           }

            // 设置左右节点
            if (!left) {
                // 补充左节点信息
                head.left = treeNode;
                left = true;
            } else {
                // 补充右节点信息
                head.right = treeNode;
                right = true;
            }
        }
//        System.out.println(linkedList);


//        System.out.println(root);
//        System.exit(0);
        return root;
    }



    /**
     * 判断两个数是否一致，包括结构和对应上的值
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        System.out.println(p);
        System.out.println(q);
        System.out.println("================");

        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        /**
         * 广度判断两个节点的相同
         * 分别采取两个队列来存储节点（先进先出）
         * 如果节点的结构和值不想等，直接退出
         *
         * 如果队列有不为空的，则说明不想等
         *
         */
        LinkedList<TreeNode> treeNodes1 = new LinkedList<>();
        LinkedList<TreeNode> treeNodes2 = new LinkedList<>();
        // 首先先判断根节点
        treeNodes1.add(p);
        treeNodes2.add(q);
        System.out.println();

        // 队列都不为空，需要判断
        while (!treeNodes1.isEmpty() && !treeNodes2.isEmpty()) {
            // 弹出需要比较的节点
            TreeNode treeNode1 = treeNodes1.poll();
            TreeNode treeNode2 = treeNodes2.poll();

            // 节点值相等
            if (treeNode1.val != treeNode2.val) {
                return false;
            }
            // 判断左右节点结构是否相同
            if (treeNode1.left == null ^ treeNode2.left == null) {
                // 不相同
                return false;
            }
            if (treeNode1.right == null ^ treeNode2.right == null) {
                // 不相同
                return false;
            }

            // 左右节点都相同
            // 添加到队列，继续迭代
            // 注意添加的顺序。 先左后右
            if (treeNode1.left != null) {
                treeNodes1.add(treeNode1.left);
            }
            if (treeNode1.right != null) {
                treeNodes1.add(treeNode1.right);
            }

            if (treeNode2.left != null) {
                treeNodes2.add(treeNode2.left);
            }
            if (treeNode2.right != null) {
                treeNodes2.add(treeNode2.right);
            }
        }

        // 队列都为空 说明已经遍历完 没有不想等，直接返回真
        return treeNodes1.isEmpty() && treeNodes2.isEmpty();
    }


    /**
     * 判断两个数是否一致，包括结构和对应上的值
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        System.out.println(p);
        System.out.println(q);
        System.out.println("================");


        /**
         * 深度优先搜索，本质上就是递归
         * 如果都为空相等
         * 如果一个为空 一个不为空，不想等
         * 如果都不为空，则从根节点和左右子树一起判断是否相等
         *
         * 以上的操作，都可以看作是递归的过程
         */

        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else {
            // 都不为空
            // 判断当前的根节点值是否相等
            if (p.val != q.val) {
                return false;
            }

            // 继续上述的递归过程 左右节点需同时相等
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
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
            return "val:" + val + " == left:" + left + " == right:" + right;
        }
    }
}
