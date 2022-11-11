package test.leetcode.easy;


import java.util.Arrays;

/**
 * https://leetcode.cn/problems/implement-strstr/
 *
 *28. 实现 strStr()
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
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code28 {
    public static void main(String[] args) {

       String haystack = "mississippi", needle = "issip";
//       String haystack = "hello", needle = "ll";

        Code28 code28 = new Code28();
        int i = code28.strStr(haystack, needle);
        System.out.println(i);

    }

    public int strStr(String haystack, String needle) {
        System.out.println(haystack);
        System.out.println(needle);

        if (needle == null) {
            return -1;
        }

        /**
         * 采用kmp算法
         */
        int[] ints = kmpNext(needle);
        System.out.println(Arrays.toString(ints));

        int i = kmpSearch(haystack, needle, ints);
        System.out.println(i);

        return i;
    }


    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历str1
        for (int i = 0,j= 0; i < str1.length(); i++) {
            //如果不匹配，则采用部分匹配表，重置i的位置，避免暴力匹配
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;//如果str1和 str2匹配，则str2的下拨啊加1
            }
            if(j == str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }


    //编写一个获取字串部分匹配表的方法
    public static int[] kmpNext(String str){
        //创建一个和字符串等长度的数组，用来存放部分匹配表
        int[] next = new int[str.length()];
        next[0] = 0;//因为只有一个元素时，前缀为空，后缀也为空，所以长度为0
        //循环寻找前缀和后缀匹配的字符下标
        for (int i = 1,j = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i) + ":" + i + "===" + str.charAt(j) + ":" + j);

            //当str.charAt(i) != str.charAt(j),并且j > 0时，j = next[j-1];,直到str.charAt(i) == str.charAt(j)
            while (j > 0 && str.charAt(i) != str.charAt(j)){
                j = next[j-1];
            }
            //假如只有两个元素，i=1，j=0，第一个元素和第二个元素匹配，则匹配表为[0,1],比如AA
            /*假如有三个元素ABA，
            从A开始考虑，A的前后缀都为空，所以next[0] = 0;
            考虑AB，前缀A，后缀B，没有共同字串，所以next[1] = 0;
            考虑ABA，前缀A，AB，后缀BA，A，有一个共同字串，并且长度为1，所以next[2] = 1;
            所以ABA的部分匹配表next={0,0,1}
             */
            if (str.charAt(i) == str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }


    public int strStr2(String haystack, String needle) {
        System.out.println(haystack);
        System.out.println(needle);

        if (needle == null) {
            return -1;
        }

        // 双指针迭代法
        int idx = 0, idx2 = 0;
        for (int i = idx, i2 = idx2; i < haystack.length() && i2 < needle.length();) {
            System.out.println(i);
            System.out.println(i2);

            if (haystack.charAt(i) == needle.charAt(i2)) {
                i++;
                i2++;

                if (i2 >= needle.length()) {
                    System.out.println(i);
                    System.out.println(i2);
                    System.out.println("=========");


                    return i - i2 + 1;
                }
            } else {
                i2 = 0;

                idx++;
                i = idx;
            }

        }

        return -1;
    }

}
