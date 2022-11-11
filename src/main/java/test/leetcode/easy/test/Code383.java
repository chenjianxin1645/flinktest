package test.leetcode.easy.test;


import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/ransom-note/
 *
 * 383. 赎金信
 *
 * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
 *
 * 如果可以，返回 true ；否则返回 false 。
 *
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：ransomNote = "a", magazine = "b"
 * 输出：false
 * 示例 2：
 *
 * 输入：ransomNote = "aa", magazine = "ab"
 * 输出：false
 * 示例 3：
 *
 * 输入：ransomNote = "aa", magazine = "aab"
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote 和 magazine 由小写英文字母组成
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ransom-note
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code383 {
    public static void main(String[] args) {
        String ransomNote = "aa", magazine = "ab";

        Code383 code383 = new Code383();
        boolean b = code383.canConstruct(ransomNote, magazine);
        System.out.println(b);

    }

    public boolean canConstruct(String ransomNote, String magazine) {
//        System.out.println(ransomNote);
//        System.out.println(magazine);

        // 判断字符长度
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // 将ransomNote记数
        HashMap<Character, Integer> hm = new HashMap<>();
        char[] chars = ransomNote.toCharArray();
        for (char c : chars) {
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
//        System.out.println(hm);

        // 判断magazine
        char[] chars1 = magazine.toCharArray();
        for (char c: chars1) {
            if (hm.containsKey(c)) {
                Integer val = hm.get(c);
                if (val == 1) {
                    // 移除
                    hm.remove(c);
                } else {
                    // 更新计数
                    hm.put(c, val - 1);
                }
            }
        }
        System.out.println(hm);


        return hm.size() == 0;
    }


}
