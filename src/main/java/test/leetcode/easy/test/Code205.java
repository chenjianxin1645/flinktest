package test.leetcode.easy.test;


import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/isomorphic-strings/
 * 205. 同构字符串
 *
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 *
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：s = "egg", t = "add"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "foo", t = "bar"
 * 输出：false
 * 示例 3：
 *
 * 输入：s = "paper", t = "title"
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s 和 t 由任意有效的 ASCII 字符组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/isomorphic-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code205 {
    public static void main(String[] args) {
        String s = "paper", t = "title";

        Code205 code205 = new Code205();
        boolean isomorphic = code205.isIsomorphic(s, t);
        System.out.println(isomorphic);
    }


    public boolean isIsomorphic(String s, String t) {
        System.out.println(s);
        System.out.println(t);

        // 获取每个字符的排列顺序
        String s1 = getSortNum(s);
        System.out.println(s1);

        String t1 = getSortNum(t);
        System.out.println(t1);

        return s1.equals(t1);
    }


    //获取字符的排列顺序
    public String getSortNum(String s) {
        // 设置哈希映射字符对应的顺序
        HashMap<Character, Integer> hms = new HashMap<>();
        // 初始化当前的
        StringBuffer sb = new StringBuffer();
        int idx = 1;
        for (int i = 0; i < s.length(); i++) {
//            System.out.println(s.charAt(i));
            char c = s.charAt(i);
            // 判断字符是否存在
            if (hms.containsKey(c)) {
                sb.append(hms.get(c));
            } else {
                hms.put(c, idx);
                sb.append(idx);

                idx++;
            }
        }
        System.out.println(hms);

        return sb.toString();
    }

}
