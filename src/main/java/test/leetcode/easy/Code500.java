package test.leetcode.easy;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/keyboard-row/
 *
 * 500. 键盘行
 *
 * 给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。
 *
 * 美式键盘 中：
 *
 * 第一行由字符 "qwertyuiop" 组成。
 * 第二行由字符 "asdfghjkl" 组成。
 * 第三行由字符 "zxcvbnm" 组成。
 *
 *
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["Hello","Alaska","Dad","Peace"]
 * 输出：["Alaska","Dad"]
 * 示例 2：
 *
 * 输入：words = ["omk"]
 * 输出：[]
 * 示例 3：
 *
 * 输入：words = ["adsdf","sfd"]
 * 输出：["adsdf","sfd"]
 *  
 *
 * 提示：
 *
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 100
 * words[i] 由英文字母（小写和大写字母）组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/keyboard-row
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code500 {
    public static void main(String[] args) {
       String[] words = {"Hello","Alaska","Dad","Peace"};

        Code500 code500 = new Code500();
        String[] words1 = code500.findWords(words);
        System.out.println(Arrays.toString(words1));

    }

    public String[] findWords(String[] words) {
        System.out.println(Arrays.toString(words));
        ArrayList<String> stringArrayList = new ArrayList<>();

        /**
         * 思路：最终还是要转换成字符
         * 1 由于每个字符被分成了三组，因此可以将26个字符从小到大按行号拼接起来
         * 2 根据字符与'a'的相减，可以获取该字符的相对位置，即获取1对应位置所在的行号
         *
         * 最糊只要比较行号不相同的话，则不是
         */
        // 思路1
        String row = "12210111011122000010020202";

        for (String word: words) {
            // 转为小写
            char[] chars = word.toLowerCase().toCharArray();


            // 设置初始字符
            boolean flag = true;
            char last = ' ';
            for (int i = 0; i < chars.length; i++) {
//                System.out.println(chars[i]);

                // 获取当前相对位置索引
//                System.out.println(row.charAt(idx));
                char c = row.charAt(chars[i] - 'a');
                if (last == ' ') {
                    last = c;
                } else {
                    if (last != c) {
                        flag = false;
                        break;
                    }
                }

            }
            if (flag) stringArrayList.add(word);
         }


        return (String[]) stringArrayList.toArray(new String[stringArrayList.size()]);
    }
}
