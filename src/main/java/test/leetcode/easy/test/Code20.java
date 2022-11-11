package test.leetcode.easy.test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *  
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 示例 4：
 *
 * 输入：s = "([)]"
 * 输出：false
 * 示例 5：
 *
 * 输入：s = "{[]}"
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code20 {
    public static void main(String[] args) {

//        String s = "()[]{}";
//        String s = "([)]";
//        String s = "()[]{}";
//        String s = "{[]}";
//        String s = "([)]";
//        String s = "]";
//        String s = "(){}}{";
//        String s = "(}{)";
//        String s = "()";
        String s = "([]){";


        Code20 code20 = new Code20();
        boolean valid = code20.isValid(s);
        System.out.println(valid);
    }


    public boolean isValid(String s) {
        System.out.println(s);
        boolean flag = false;

//        只包括 '('，')'，'{'，'}'，'['，']'

        HashMap<Character, Character> hm = new HashMap<>();
        hm.put('(', ')');
        hm.put('{', '}');
        hm.put('[', ']');
        System.out.println(hm);
//        System.exit(0);

        // 声明一个堆栈结构存放左边符号，先进后出
        ArrayDeque<Character> ad = new ArrayDeque<>(); // 堆栈效率高
        // 声明一个队列结构存放右边的符号，先进先出
        LinkedList<Character> ll = new LinkedList<>(); // 队列效率高

        for (int i = 0; i < s.length(); i++) {
//            System.out.println(s.charAt(i));
            char c = s.charAt(i);
//            if (c == '(' || c == '{' || c == '[') {
            if (hm.keySet().contains(c)) {
                ad.add(c);
            } else {
//                ll.add(c);

                // 判断是否可以提前闭合
//                System.out.println(ad);
                Character character = ad.peekLast();
                if (character == null) return false;
//                System.out.println(character);
//                System.out.println(ad);
//                System.exit(0);
                if (hm.get(character) == c) {
                    // 当前不添加，弹出其他的
                    ad.removeLast();
                    flag = true;
                } else {
                    return false;
//                    ll.add(c);
                }
            }
        }
        System.out.println(ad);
        System.out.println(ll);
        if (ad.size() !=0 || ll.size()!=0) {
            flag = false;
        }

        // 判断两者是否一一对应
//        if (ad.size() == ll.size()) {
////            System.out.println(ad.element());
////            System.out.println(ad);
////            System.out.println(ad.size());
//            int size = ad.size();
//            System.out.println(size);
//            if (size == 0) return true;
////            Iterator<Character> iterator = ad.iterator();
////            while (iterator.hasNext()) {
////                System.out.println(iterator.next());
////            }
//            for (int i = 0; i < size; i++) {
////                System.out.println(i);
////                Character peek = ad.peek();
////                System.out.println(peek);
//                Character pop = ad.removeLast();
////                System.out.println(pop);
//                Character pop1 = ll.pop();
////                System.out.println(pop1);
//                if (hm.get(pop) == pop1) {
//                    flag = true;
//                } else {
//                    System.out.println(pop);
//                    System.out.println(pop1);
//                    flag = false;
//                    break;
//                }
//            }
//        }
//        System.out.println(ad);
//        System.out.println(ll);

        return flag;
    }
}
