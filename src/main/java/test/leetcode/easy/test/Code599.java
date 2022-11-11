package test.leetcode.easy.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/
 *
 * 599. 两个列表的最小索引总和
 *
 * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 *
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 * 示例 2:
 *
 * 输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 *  
 *
 * 提示:
 *
 * 1 <= list1.length, list2.length <= 1000
 * 1 <= list1[i].length, list2[i].length <= 30 
 * list1[i] 和 list2[i] 由空格 ' ' 和英文字母组成。
 * list1 的所有字符串都是 唯一 的。
 * list2 中的所有字符串都是 唯一 的。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 */
public class Code599 {
    public static void main(String[] args) {
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"KFC", "Shogun", "Burger King"};

        Code599 code599 = new Code599();
        String[] restaurant = code599.findRestaurant(list1, list2);
        System.out.println(Arrays.toString(restaurant));

    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        System.out.println(Arrays.toString(list1));
        System.out.println(Arrays.toString(list2));

        if (list1.length > list2.length) {
            return findRestaurant(list2, list1);
        }

        HashMap<String, Integer> hm1 = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            hm1.put(list1[i], i);
        }
        System.out.println(hm1);

        HashMap<String, Integer> hm2 = new HashMap<>();
        for (int i = 0; i < list2.length; i++) {
            hm2.put(list2[i], i);
        }
        System.out.println(hm2);


        ArrayList<String> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (String s: hm1.keySet()) {
            if (hm2.containsKey(s)) {
                if (hm1.get(s) + hm2.get(s) ==  min) {
                    res.add(s);
                }
                if (hm1.get(s) + hm2.get(s) <  min) {
                    res.clear();
                    res.add(s);
                }
                min = Math.min(hm1.get(s) + hm2.get(s), min);
            }
        }
        return res.toArray(new String[res.size()]);
    }


}
