package test.leetcode.easy.test;


import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/longest-palindrome/
 *
 * 409. 最长回文串
 *
 * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。
 *
 * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入:s = "abccccdd"
 * 输出:7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * 示例 2:
 *
 * 输入:s = "a"
 * 输入:1
 * 示例 3:
 *
 * 输入:s = "bb"
 * 输入: 2
 *  
 *
 * 提示:
 *
 * 1 <= s.length <= 2000
 * s 只能由小写和/或大写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code409 {
    public static void main(String[] args) {
        String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";

        Code409 code409 = new Code409();
        int i = code409.longestPalindrome(s);
        System.out.println(i);
    }

    public int longestPalindrome(String s) {
//        System.out.println(s);

        // 统计每个字符的词频
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hm = new HashMap<>();
        for (char c: chars) {
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
        System.out.println(hm);

        int sum = 0;
        int max_odd = 0;
        // 如果存在一个字符
        // 判断偶数的词频
        for (char c: hm.keySet()) {
            if (hm.get(c) % 2 == 0) {
                System.out.println(hm.get(c));
                sum += hm.get(c);
            } else {
                // 奇数 如果有奇数的话，只能放在中间，并且放置最大的奇数
                max_odd = Math.max(max_odd, hm.get(c));

                // 不要求全部字符一定要保留，所以只要能构造就好，构造的条件在于奇数-1为偶数即可
                System.out.println(hm.get(c) + ":" + (hm.get(c) - 1));
                sum += hm.get(c) - 1;
            }
        }
        System.out.println(sum);
        System.out.println(max_odd);



        return max_odd >0 ? sum+1:sum;
    }
}
