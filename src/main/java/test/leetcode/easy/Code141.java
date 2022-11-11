package test.leetcode.easy;

import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 141. 环形链表
 *
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * 示例 3：
 *
 *
 *
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *  
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 104]
 * -105 <= Node.val <= 105
 * pos 为 -1 或者链表中的一个 有效索引 。
 *  
 *
 * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code141 {
    public static void main(String[] args) {
        int[] head = {3,2,0,-4};
        int pos = 1;
//        int pos = -1;

        ListNode listNode = ListNode.generate(head, pos);
        Code141 code141 = new Code141();
        boolean b = code141.hasCycle(listNode);
        System.out.println(b);

    }

    /**
     * 要求空间复杂度为O(1) 即用时间换取空间
     * 即要求时间多一点
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
//        System.out.println(head.val);
//        System.out.println(head.next);

        // 空节点
        if (head == null) {
            return false;
        }

        /**
         * 模拟龟兔赛跑
         * 即采用快慢指针法，快的是兔子（走两步），慢的是乌龟（走一步）
         * 因为当节点有闭环时，兔子会优先在闭环圈子里循环，
         * 当乌龟入圈的时候，兔子会和乌龟碰上
         */
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (fast == slow) {
                // 闭环时，快的碰上慢的
                return true;
            }
            // 还未碰上
            // 慢的的走一步
            slow = slow.next;
            // 快的走两步
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                fast = fast.next;
            }
        }

        return false;
    }


    /**
     * 暴力迭代
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
//        System.out.println(head.val);
//        System.out.println(head.next);

        // 要求空间复杂度为O(1) 即用时间换取空间
        HashSet<ListNode> listNodes = new HashSet<>();
        // 获取当前判断的节点
        while (head != null) {
//            System.out.println(head.val);
            if (!listNodes.add(head)) {
//                System.out.println(head.val);
                return true;
            }

            head = head.next;
        }

        return false;
    }
}
