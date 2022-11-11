package test.tree;


import com.alibaba.druid.sql.visitor.functions.Bin;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树遍历
 */
public class BinaryTreeNode {

    // 定义二叉树的结构体
    private int value;
    private BinaryTreeNode leftNode; // 左结点
    private BinaryTreeNode rightNode; // 右结点

    public static void main(String[] args) {

        // 生成节点
        BinaryTreeNode root = new BinaryTreeNode(1, null, null);
        BinaryTreeNode node2 = new BinaryTreeNode(2, null, null);
        BinaryTreeNode node3 = new BinaryTreeNode(3, null, null);
        BinaryTreeNode node4 = new BinaryTreeNode(4, null, null);
        BinaryTreeNode node5 = new BinaryTreeNode(5, null, null);
        BinaryTreeNode node6 = new BinaryTreeNode(6, null, null);
        BinaryTreeNode node7 = new BinaryTreeNode(7, null, null);
        // 生成节点数
        System.out.println(root);

        root.setLeftNode(node2);
        root.setRightNode(node3);
        System.out.println(root);

        node2.setLeftNode(node4);
        node2.setRightNode(node5);
        System.out.println(node2);

        node3.setLeftNode(node6);
        node3.setRightNode(node7);
        System.out.println(node3);
//        System.out.println(root);



        root.print();
    }

    private void print() {
        // 生成一个队列存储当前节点
        LinkedList<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.add(this);
        // 生成两个指针  last:上一行的最后一个节点 cur:当前节点
        BinaryTreeNode last = this;
        BinaryTreeNode cur = this;

        // 遍历队列
        while (!queue.isEmpty()) {
            BinaryTreeNode poll = queue.poll();
//            System.out.println(poll.value);

            if (poll.leftNode != null) {
                queue.add(poll.leftNode);
                cur = poll.leftNode;
            }

            if (poll.rightNode != null) {
                queue.add(poll.rightNode);
                cur = poll.rightNode;
            }
//            System.out.println(cur);
//            System.exit(0);

            // 判断上一个节点是否当前节点
            if (last == poll) {
                System.out.println(poll.value);

                last = cur;
            } else {
                System.out.print(poll.value + " ");

            }
        }




    }


    // 初始化节点
    public BinaryTreeNode(int value, BinaryTreeNode leftNode, BinaryTreeNode rightNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public BinaryTreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(BinaryTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public BinaryTreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(BinaryTreeNode rightNode) {
        this.rightNode = rightNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        int leftValue = 0;
        if (leftNode != null) {
            leftValue = leftNode.value;
        }

        int rightValue = 0;
        if (rightNode != null) {
            rightValue = rightNode.value;
        }

        return "value:" + value + "-left:" + leftValue + "-righr:" + rightValue;
    }
}
