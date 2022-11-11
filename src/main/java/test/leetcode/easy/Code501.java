package test.leetcode.easy;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
 *
 * 501. 二叉搜索树中的众数
 *
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 *
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 *
 * 假定 BST 满足如下定义：
 *
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,null,2,2]
 * 输出：[2]
 * 示例 2：
 *
 * 输入：root = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 104] 内
 * -105 <= Node.val <= 105
 *  
 *
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code501 {
    public static void main(String[] args) {
        String [] root = {"1",null,"2","2"};



        Code501 code501 = new Code501();
        int[] mode = code501.findMode(TreeNode.getTreeNode(root));
        System.out.println(Arrays.toString(mode));
    }


    public int[] findMode(TreeNode root) {
        System.out.println(root);

        /**
         * 深度优先递归法
         *
         * 二叉搜索树的特质是左子树不大于根节点、右子树不小于根节点
         * 那么其中序遍历是有序的。
         */
        // 先遍历其中序
        getMidSortVal(root);

        System.out.println(curr);
        System.out.println(cnt);
        System.out.println(maxCnt);
        System.out.println(res);


        return res.stream().mapToInt(i -> i).toArray();
    }

    public void getMidSortVal(TreeNode root) {
        if (root == null) {
            return;
        }

        // 先遍历左子树
        getMidSortVal(root.left);

        /**
         * 再遍历根节点
         * 重点在于这里：
         *  计算当前出现的众树
         *  判断众树：
         *          1、记录当前的众数
         *          2、记录当前众数次数
         *          2、 记录最大次数
         *
         */
//        System.out.println(root.val);
        update(root.val);

        // 后遍历右子树
        getMidSortVal(root.right);
    }


    int curr, cnt, maxCnt;
    ArrayList<Integer> res = new ArrayList<>();
    // 注意 中序遍历是有序的
    public void update(int val) {
//        System.out.println(val);

        if (val == curr) {
            cnt++;
        } else {
            curr = val;
            cnt = 1;
        }

        // 判断当前的最大次数
        if (maxCnt == cnt) {
            // 跟最大次数一致，添加到队列中
            res.add(val);
        }
        if (cnt > maxCnt) {
            // 当前次数大于最大次数
            // 重置
            maxCnt = cnt;
            res.clear();
            res.add(val);
        }
    }


}
