package test.leetcode.easy.test;

import java.util.HashMap;

/**
 *https://leetcode-cn.com/problems/roman-to-integer/
 * 13. 罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "III"
 * 输出: 3
 * 示例 2:
 *
 * 输入: s = "IV"
 * 输出: 4
 * 示例 3:
 *
 * 输入: s = "IX"
 * 输出: 9
 * 示例 4:
 *
 * 输入: s = "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 *
 * 输入: s = "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Code13 {
    public static void main(String[] args) {
//       String s = "III";
//       String s = "IV";
//       String s = "IX";
//       String s = "LVIII";
//       String s = "MCMXCIV";
       String s = "CMXCIX";

        Code13 code13 = new Code13();
        int i = code13.romanToInt(s);
        System.out.println(i);
    }

    /**
     * 模拟法
     * 通常情况下，罗马数字中小的数字在大的数字的右边。若输入的字符串满足该情况，那么可以将每个字符视作一个单独的值，累加每个字符对应的数值即可。
     *
     * 例如 \texttt{XXVII}XXVII 可视作 \texttt{X}+\texttt{X}+\texttt{V}+\texttt{I}+\texttt{I}=10+10+5+1+1=27X+X+V+I+I=10+10+5+1+1=27。
     *
     * 若存在小的数字在大的数字的左边的情况，根据规则需要减去小的数字。对于这种情况，我们也可以将每个字符视作一个单独的值，若一个数字右侧的数字比它大，则将该数字的符号取反。
     *
     * 例如 \texttt{XIV}XIV 可视作 \texttt{X}-\texttt{I}+\texttt{V}=10-1+5=14X−I+V=10−1+5=14。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/roman-to-integer/solution/luo-ma-shu-zi-zhuan-zheng-shu-by-leetcod-w55p/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        // 存储罗马数字对应关系
//        字符          数值
//        I             1
//        V             5
//        X             10
//        L             50
//        C             100
//        D             500
//        M             1000
        HashMap<Character, Integer> hm = new HashMap<>();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);
        System.out.println(hm);

        int sum = 0;
        int l = s.length();
        for (int i = 0; i < l; i++) {
//            System.out.println(s.charAt(i));

            char c = s.charAt(i);
            Integer v = hm.get(c);
            System.out.println(v);
            // 判断当前字符是否大于下个字符
            if (i<l-1 && v < hm.get(s.charAt(i+1))) {
                sum -= v;
            } else {
                sum += v;
            }
        }

//        System.out.println(sum);
        return sum;
    }

    /**
     * 暴力法
     * @param s
     * @return
     */
    public int romanToInt2(String s) {
        char[] chars = s.toCharArray();

        //  获取上一个字符
        char last_c = '0';
        int sum = 0;
        for (char c: chars) {
//            System.out.println(c);

            switch (c) {
                case 'I':
//                    System.out.println(1);
                    sum += 1;
                    break;
                case 'V':
//                    System.out.println(5);
                    if ('I' == last_c) {
                        sum -= 1;
                        sum += 4;
                    } else {
                        sum += 5;
                    }
                    break;
                case 'X':
//                    System.out.println(10);
                    if ('I' == last_c) {
                        sum -= 1;
                        sum += 9;
                    } else {
                        sum += 10;
                    }

                    break;
                case 'L':
//                    System.out.println(50);

                    if ('X' == last_c) {
                        sum -= 10;
                        sum += 40;
                    } else {
                        sum += 50;
                    }
                    break;
                case 'C':
//                    System.out.println(100);

                    if ('X' == last_c) {
                        sum -= 10;
                        sum += 90;
                    } else {
                        sum += 100;
                    }
                    break;
                case 'D':
//                    System.out.println(500);

                    if ('C' == last_c) {
                        sum -= 100;
                        sum += 400;
                    } else {
                        sum += 500;
                    }
                    break;
                case 'M':
//                    System.out.println(1000);

                    if ('C' == last_c) {
                        sum -= 100;
                        sum += 900;
                    } else {
                        sum += 1000;
                    }
                    break;

                default:
                    System.out.println("not found any valid char:" + c);
                    System.exit(1);
            }

            last_c = c;
//            System.out.println(last_c);
        }

//        System.out.println(sum);
        return sum;
    }
}
