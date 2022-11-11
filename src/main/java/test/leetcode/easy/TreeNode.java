package test.leetcode.easy;

import java.util.LinkedList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
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

    /**
     * 迭代法：为每一个节点添加左右节点信息
     *      采用一个队列结构村根节点信息
     * @param arr
     * @return
     */
    public static TreeNode getTreeNode(String[] arr) {
        if (arr.length == 0) return null;

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

}
