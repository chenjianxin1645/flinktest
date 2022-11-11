package test.leetcode.easy.test;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/valid-anagram/
 * 242. 有效的字母异位词
 *
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 *  
 *
 * 提示:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t 仅包含小写字母
 *  
 *
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code242 {
    public static void main(String[] args) {
        String s = "aacc", t = "ccac";


        Code242 code242 = new Code242();
        boolean anagram = code242.isAnagram(s, t);
        System.out.println(anagram);
    }

    public boolean isAnagram(String s, String t) {
//        System.out.println(s);
//        System.out.println(t);

        if (s.length() != t.length()) {
            return false;
        }

        // 采用hash存放每个字符的个数
        HashMap<Character, Integer> hm1 = new HashMap<>();
        HashMap<Character, Integer> hm2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
//            System.out.println(c1);
            char c2 = t.charAt(i);
//            System.out.println(s.charAt(i));

//            System.out.println(hm1);
            if (hm1.containsKey(c1)) {
                hm1.put(c1, hm1.get(c1) + 1);
            } else {
                hm1.put(c1, 1);
            }

            if (hm2.containsKey(c2)) {
                hm2.put(c2, hm2.get(c2) + 1);
            } else {
                hm2.put(c2, 1);
            }
        }
        System.out.println(hm1);
        System.out.println(hm2);
        for (char c: hm1.keySet()) {
//            System.out.println(c);


            if (!hm1.getOrDefault(c, 0).equals(hm2.getOrDefault(c, 0))) {
                System.out.println(c);
                System.out.println(hm1.get(c));
                System.out.println(hm2.get(c));
                return false;
            }
        }

        return true;
    }
}
