package test.leetcode.easy.test;

/**
 * https://leetcode-cn.com/problems/implement-strstr/
 *
 * 28. 实现 strStr()
 *
 *
 * 实现 strStr() 函数。
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 *
 *  
 *
 * 说明：
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 *
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 *
 * 输入：haystack = "", needle = ""
 * 输出：0
 *  
 *
 * 提示：
 *
 * 0 <= haystack.length, needle.length <= 5 * 104
 * haystack 和 needle 仅由小写英文字符组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code28 {

    public static void main(String[] args) {
        String haystack = "hello", needle = "ll";
//        String haystack = "aaa", needle = "aaaa";
//        String haystack = "", needle = "a";
//        String haystack = "mississippi", needle = "sippia";

        System.out.println(haystack.indexOf(needle));
//        System.out.println(haystack.charAt(6));
//        System.out.println(haystack.charAt(7));
//        System.out.println(needle.charAt(5));
//        System.out.println(haystack.charAt(5+6));

        Code28 code28 = new Code28();
//        int i = code28.strStr(haystack, needle);
        int i = code28.strStr2(haystack, needle);
        System.out.println(i);
    }


    public int strStr(String haystack, String needle) {
        System.out.println(haystack);
        System.out.println(needle);


        // 迭代法
        if (haystack.length() == 0 && needle.length() > 0) {
            return -1;
        } else if (needle.length() == 0) {
            return 0;
        } else if (needle.length()> haystack.length()) {
            return -1;
        }


        // 设置索引位置
        int idx = -1;
        for (int i = 0; i < needle.length(); i++) {
//            System.out.println(needle.charAt(i));
            System.out.println(idx+ " :" + needle.charAt(i));

            // 设置查找位置
            int k =  idx == -1 ? 0 : idx+i ;
            System.out.println("k:" + k);
            for (int j = k; j < haystack.length(); j++) {
                System.out.println(i + "===" + j);
                System.out.println(needle.charAt(i) + "===" + haystack.charAt(j));
//                System.exit(0);

                if (needle.charAt(i) == haystack.charAt(j)) {
//                    System.out.println(needle.charAt(i));
//                    System.out.println(j);
//                    System.exit(0);
                    // 首次找到位置
                    if (idx == -1) idx = j;
                    break;
                } else {
                    // 判断是否首次找到
                    if (idx != -1) {
                        // 已经找到，但是下个匹配不完整，取消首次无效
                        // 开始重置寻找
                        i = 0;
                        j = idx;
                        idx = -1;
                    }
                }
            }

            // 第一个字符都没查询到，直接返回
            if (idx == -1) break;


            if (k >= haystack.length()) {
                System.out.println(k + "===" + haystack.length());
                // 超出目标字符串范围了，无需查询
                idx = -1;
                break;
            }

        }


        return idx;
    }

    public int strStr2(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    System.out.println(i + "==" + j);
                    System.out.println(haystack.charAt(i + j) + "==" + needle.charAt(j));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }


}
