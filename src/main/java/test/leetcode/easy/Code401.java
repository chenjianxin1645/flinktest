package test.leetcode.easy;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * https://leetcode-cn.com/problems/binary-watch/
 *
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 *
 * 例如，下面的二进制手表读取 "3:25" 。
 *
 *
 * （图源：WikiMedia - Binary clock samui moon.jpg ，许可协议：Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0) ）
 *
 * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
 *
 * 小时不会以零开头：
 *
 * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 * 分钟必须由两位数组成，可能会以零开头：
 *
 * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 *  
 *
 * 示例 1：
 *
 * 输入：turnedOn = 1
 * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * 示例 2：
 *
 * 输入：turnedOn = 9
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 0 <= turnedOn <= 10
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-watch
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code401 {
    public static void main(String[] args) {
        int turnedOn = 1;

        Code401 code401 = new Code401();
        List<String> strings = code401.readBinaryWatch(turnedOn);
        System.out.println(strings);
    }


    public List<String> readBinaryWatch(int turnedOn) {
        System.out.println(turnedOn);
        ArrayList<String> stringArrayList = new ArrayList<>();

        /**
         * 枚举时分
         * 只要满足小时和分钟的bit1数为n即可
         */

        // 小时
        for (int i = 0; i < 12; i++) {
            // 分钟
            for (int j = 0; j < 60; j++) {
//                System.out.println(i + ":" + j);

//                System.out.println(Integer.bitCount(i));
//                System.out.println(Integer.bitCount(j));
//                System.exit(0);
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    String min = String.valueOf(j);
                    if (j <=9) {
                        min = "0" + min;
                    }
                    stringArrayList.add(i + ":" + min);
                }
            }
        }
//        System.out.println(stringArrayList);


        return stringArrayList;
    }

}
