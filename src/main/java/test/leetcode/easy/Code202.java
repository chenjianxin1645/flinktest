package test.leetcode.easy;

/**
 * https://leetcode-cn.com/problems/happy-number/
 *
 * 202. 快乐数
 *
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」 定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：false
 *  
 *
 * 提示：
 *
 * 1 <= n <= 231 - 1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code202 {
    public static void main(String[] args) {
        int n = 19;

        Code202 code202 = new Code202();
        boolean happy = code202.isHappy(n);
        System.out.println(happy);
    }


    /**
     * 判断快乐数，需要了解到：
     *  总共会有三种情况：
     *      1。找到1
     *      2。无限循环
     *      3。值会越来越大
     *  根据以上的情况，考虑如下
     *  最大有13位数字，从4～13，会递减
     *  然后会在1～3位里面，开始循环或者找到1
     *  也就只会出现到前面两种情况
     *
     *  采用以下方法可以解决：
     *      1。 暴力法，将无限重复循环的添加到集合，如果集合存在代表无限循环永远找不到1
     *          不建议使用，因为集合可能无限大
     *      2。 采用快慢指针法
     *          原理：当不循环时，快的指针会优先找到1
     *              当循环时，快的指针会和慢的指针相遇
     *
     * @param
     * @return
     */
    public boolean isHappy(int n) {
//        System.out.println(n);

        // 初始化快慢指针
        int slow = n;
        int fast = getNext(n);
//        System.out.println(slow);
        System.out.println(fast);

        // 不循环时，快的优先到1
        // 循环时，快的和慢的相遇
        while (slow != fast && fast !=1) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));


//            System.out.println(slow);
            System.out.println(fast);
        }

        return fast == 1;
    }

    public int getNext(int n) {
//        System.out.println(n);

        // 获取各个数字平方和
        // 10进制向右移
        int sum = 0;
        while (n>0) {
            // 获取各位数字
            int i = n % 10;
            sum += i * i;

            n /= 10;
        }
        return sum;
    }

}
