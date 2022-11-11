package test.leetcode.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 * 590. N 叉树的后序遍历
 *
 * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
 *
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[5,6,3,2,4,1]
 * 示例 2：
 *
 *
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 *  
 *
 * 提示：
 *
 * 节点总数在范围 [0, 104] 内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 *  
 *
 * 进阶：递归法很简单，你可以使用迭代法完成此题吗?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code590 {

    public static void main(String[] args) {
        Code590 code590 = new Code590();
        List<Integer> postorder = code590.postorder(new Node());
        System.out.println(postorder);
    }

    public List<Integer> postorder(Node root) {

        /**
         * 广度优先迭代
         */
        ArrayList<Integer> integers = new ArrayList<>();

        if (root == null) {
            return  integers;
        }

        // 手动声明堆栈结构存储节点
        ArrayDeque<Node> nodes = new ArrayDeque<>();
        // 声明个集合存放已经遍历过子节点列表的节点
        HashSet<Node> set = new HashSet<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            // 获取当前栈顶节点
            Node node = nodes.peek();

            // 判断是否有子节点列表
            // 注意，这里需要判断当前节点下的子节点是否已经遍历过。。。。 不然死循环
            if (node.children != null && !set.contains(node)) {
                // 添加子节点列表入栈
                // 根据栈的先进后出
                for (int i = node.children.size()-1; i >=0 ; i--) {
                    nodes.push(node.children.get(i));
                }
                set.add(node);
            } else {
                // 子节点为空
                // 出栈
                nodes.pop();
                integers.add(node.val);
            }
        }


        return integers;
    }



    public List<Integer> postorder2(Node root) {

        /**
         * 深度优先搜索递归
         */
        dfs(root);
        return list;
    }

    ArrayList<Integer> list = new ArrayList<>();
    public void dfs(Node node) {
        if (node == null) {
            return;
        }

        // N叉树后续遍历 左右根
        for (Node node2 : node.children) {
            dfs(node2);
        }
        list.add(node.val);
    }
}
