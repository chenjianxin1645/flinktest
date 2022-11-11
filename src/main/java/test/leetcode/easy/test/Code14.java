package test.leetcode.easy.test;

/**
 *https://leetcode-cn.com/problems/longest-common-prefix/
 * 14. 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *  
 *
 * 提示：
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code14 {
    public static void main(String[] args) {
//        String[] strs = {"flower","flow","flight"};
//        String[] strs = {"dog","racecar","car"};
//        String[] strs = {"reflower","flow","flight"};
        String[] strs = {"a","a","b"};

        Code14 code14 = new Code14();
        String s = code14.longestCommonPrefix(strs);
        System.out.println(s);

    }


    public String longestCommonPrefix(String[] strs) {
//        System.out.println(Arrays.toString(strs));

        // 横向判断前后字符的相同
        int idx = 0;
        for (int i = 0; i < strs.length; i++) {
            if (i+1 == strs.length) break;
//            System.out.println(strs[i]);
//            System.out.println(strs[i+1]);

            int min = Math.min(strs[i].length(), strs[i + 1].length());
//            System.out.println(min);

            int idx2 = 0;
            for (int j = 0; j < min; j++) {
//                System.out.println(strs[i].charAt(j));
//                System.out.println(strs[i+1].charAt(j));
                if (strs[i].charAt(j) == strs[i+1].charAt(j)) {
                    idx2++;
                } else {
                    break;
                }
            }
            if (idx == 0) {
                idx = idx2;
            } else {
                idx = Math.min(idx, idx2);
            }
            if (idx == 0) break;

//            System.out.println(idx);
//            System.exit(0);
        }


        return strs.length>1 ? strs[0].substring(0, idx) : strs[0];
    }

}
