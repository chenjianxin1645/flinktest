package test.leetcode.easy;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 *
 * 572. 另一棵树的子树
 *
 *
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 *
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 * 输出：false
 *  
 *
 * 提示：
 *
 * root 树上的节点数量范围是 [1, 2000]
 * subRoot 树上的节点数量范围是 [1, 1000]
 * -104 <= root.val <= 104
 * -104 <= subRoot.val <= 104
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 */
public class Code572 {
    public static void main(String[] args) {
        String[] root = {"3","4","5","1","2"};
        String[] subRoot = {"4","1","2"};

        Code572 code572 = new Code572();
        boolean subtree = code572.isSubtree(TreeNode.getTreeNode(root), TreeNode.getTreeNode(subRoot));
        System.out.println(subtree);
    }


    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        System.out.println(root);
        System.out.println(subRoot);

        /**
         * 判断是否含有子树
         * 暴力迭代树搜索思路如下：
         * 1、递归母树，递归每一个节点
         * 2、当母树递归每一个节点时，判断当前节点树和子树是否相等
         *      2.1 、 这里如何判断相等，也是需要通过互相递归两棵树来一致判断是否相等
         *          1、 树相等的条件，根节点值相等并且左右子树分别相等
         */
//        // 双重深度优先搜索树
//        boolean dfs = dfs(root, subRoot);
//        return dfs;

        /**
         * 思路2：
         * 了解到每一个树的深度优先搜索的前序遍历顺序是连续的，
         * 即根节点遍历完，轮到左右子节点顺序遍历
         *
         * 因此判断是否含有子树的话，可以看子树的前序遍历顺序是否在母树的前序遍历当中。
         */
        // 获取树的前序遍历顺序，采用深度优先递归获取
        String s = dfs(root);
        System.out.println(s);
        String s2 = dfs(subRoot);
        System.out.println(s2);


        // 判断字符串是否子串的，可以考虑采用kmp算法
        return kmp(s, s2);
//        return s.contains(s2);
    }


    public boolean kmp(String s1, String s2) {
//        System.out.println(s1);
//        System.out.println(s2);

        // 初始化子串前后公共子串信息
        // k为索引，v为长度
        int[] fail = new int[s2.length()];
        fail[0] = -1;
        for (int i = 1, j = -1; i < s2.length(); i++) {
            while (j != -1 && s2.charAt(i) != s2.charAt(j+1)) {
                j = fail[j];
            }

            if (s2.charAt(i) == s2.charAt(j+1)) {
                j++;
            }
            fail[i] = j;
        }
        System.out.println(Arrays.toString(fail));


        // 获取s1和s2的公共前后缀
        for (int i = 0, j = -1; i < s1.length(); i++) {
            while (j != -1 && s1.charAt(i) != s2.charAt(j+1)) {
                j = fail[j];
            }
            if (s1.charAt(i) == s2.charAt(j+1)) {
                j++;
            }

            // 判断相等的条件
            // 最终最大的j前后公共缀等于s2的长度
            if (j == s2.length() -1) {
                return true;
            }
        }

        return false;
    }

    // 前序遍历
    public String dfs(TreeNode root) {
        if (root == null) {
            // 需要保持前序遍历的完整性，需要把当前的空值补全
            return "null";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("'" + root.val + "'");
        System.out.println(root.val + "===" + sb.toString());

        sb.append(dfs(root.left));
        sb.append(dfs(root.right));

        return sb.toString();
    }

    // 深度优先搜索树
    public boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        // 检测当前节点是否和子树相等
        // 不相等的话，持续递归判断左右子树
        return check(root, subRoot) || dfs(root.left, subRoot) || dfs(root.right, subRoot);
    }

    // 判断两棵树是否相等
    public boolean check(TreeNode root, TreeNode subRoot) {
        /**
         * 两棵树相等的条件：
         * 1、当前节点值相等
         * 2、左右子树分别相等
         */
        // 判断边界和递归结束的标志
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null || root.val != subRoot.val) {
            return false;
        }

        // 继续递归两棵树的左右子树
        return check(root.left, subRoot.left) && check(root.right, subRoot.right);
    }


}
