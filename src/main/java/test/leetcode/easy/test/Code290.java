package test.leetcode.easy.test;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/word-pattern/
 * 290. 单词规律
 *
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 *  
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 *  
 *
 * 提示:
 *
 * 1 <= pattern.length <= 300
 * pattern 只包含小写英文字母
 * 1 <= s.length <= 3000
 * s 只包含小写英文字母和 ' '
 * s 不包含 任何前导或尾随对空格
 * s 中每个单词都被 单个空格 分隔
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code290 {
    public static void main(String[] args) {
        String pattern = "abba", str = "dog cat cat dog1";

        Code290 code290 = new Code290();
        boolean b = code290.wordPattern(pattern, str);
        System.out.println(b);
    }


    public boolean wordPattern(String pattern, String s) {
//        System.out.println(pattern);
//        System.out.println(s);

        // 拆分s
        String[] strings = s.split(" ");
        if (strings.length != pattern.length()) {
            return false;
        }

        // 获取pattern规律
        HashMap<Character, String> hm1 = new HashMap<>();
        HashMap<String, Character> hm2 = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
//            System.out.println(strings[i]);

            char c = pattern.charAt(i);
            String string = strings[i];

            if (!hm1.containsKey(c)) {
                hm1.put(c, string);
            }

            if (!hm2.containsKey(string)) {
                hm2.put(string, c);
            }

            // 判断当前字符对应的字符串
            if (!hm1.get(c).equals(string)) {
                return false;
            }
            // 判断当前字符串对应的字符
            if (hm2.get(string) != c) {
                return false;
            }
        }

        return true;
    }

}
