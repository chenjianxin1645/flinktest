package test.leetcode.easy;


import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/construct-the-rectangle/
 *
 * 492. 构造矩形
 *
 * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。 所以，现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。要求：
 *
 * 你设计的矩形页面必须等于给定的目标面积。
 * 宽度 W 不应大于长度 L ，换言之，要求 L >= W 。
 * 长度 L 和宽度 W 之间的差距应当尽可能小。
 * 返回一个 数组 [L, W]，其中 L 和 W 是你按照顺序设计的网页的长度和宽度。
 *  
 *
 * 示例1：
 *
 * 输入: 4
 * 输出: [2, 2]
 * 解释: 目标面积是 4， 所有可能的构造方案有 [1,4], [2,2], [4,1]。
 * 但是根据要求2，[1,4] 不符合要求; 根据要求3，[2,2] 比 [4,1] 更能符合要求. 所以输出长度 L 为 2， 宽度 W 为 2。
 * 示例 2:
 *
 * 输入: area = 37
 * 输出: [37,1]
 * 示例 3:
 *
 * 输入: area = 122122
 * 输出: [427,286]
 *  
 *
 * 提示:
 *
 * 1 <= area <= 107
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-the-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code492 {
    public static void main(String[] args) {
        int area = 122122;

        Code492 code492 = new Code492();
        int[] ints = code492.constructRectangle(area);
        System.out.println(Arrays.toString(ints));
    }


    public int[] constructRectangle(int area) {
        System.out.println(area);

        /**
         * 采用数学方法
         * 根据题意可得：
         * 1. l * w = area
         * 2. w * w <= l * w = area
         *
         * 3. w<=area的开方
         */

        int w = (int) Math.sqrt(area);
        System.out.println(w);
        while (area % w != 0) {
            --w;

            System.out.println(w);
        }



        return new int[] {area/w, w};
    }

}
