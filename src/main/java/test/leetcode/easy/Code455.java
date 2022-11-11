package test.leetcode.easy;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/assign-cookies/
 *
 * 455. 分发饼干
 *
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 *
 *  
 * 示例 1:
 *
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 * 示例 2:
 *
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *  
 *
 * 提示：
 *
 * 1 <= g.length <= 3 * 104
 * 0 <= s.length <= 3 * 104
 * 1 <= g[i], s[j] <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/assign-cookies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code455 {
    public static void main(String[] args) {
        int[] g = {10,9,8,7};
        int[] s = {5,6,7,8};

        Code455 code455 = new Code455();
        int contentChildren = code455.findContentChildren(g, s);
        System.out.println(contentChildren);

    }

    public int findContentChildren(int[] g, int[] s) {
        System.out.println(Arrays.toString(g));
        System.out.println(Arrays.toString(s));

        /**
         * 采用排序算法
         * 为了满足更多的人吃到，将他们从小到大排序
         */
        Arrays.sort(g);
        Arrays.sort(s);
        System.out.println(Arrays.toString(g));
        System.out.println(Arrays.toString(s));

        int res = 0;
        for (int i = 0, j = 0; i < g.length && j <s.length;) {
//            System.out.println(g[i]);
//            System.out.println(s[i]);

            // 尽可能的满足
            if (g[i] <= s[j]) {
                System.out.println(g[i] + "==" + s[j]);
                // 能满足
                res++;
                i++;
                j++;
            } else {
                // 当前的饼干不满足，往下一个饼干确认
                j++;
            }
        }



        return res;
    }
}
