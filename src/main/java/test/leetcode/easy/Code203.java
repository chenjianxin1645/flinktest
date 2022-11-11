package test.leetcode.easy;

import test.leetcode.easy.test.Code20;

/**
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 203. 移除链表元素
 *
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 *
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code203 {
    public static void main(String[] args) {
        int[] head = {1,2,6,3,4,5,6};
        int val = 6;

        ListNode listNode = ListNode.generate(head);

        Code203 code203 = new Code203();
        ListNode listNode1 = code203.removeElements(listNode, val);

        System.out.println(listNode1);
    }


    // 递归迭代
    // 从下至上返回判断
    public ListNode removeElements(ListNode head, int val) {
//        System.out.println(head);
//        System.out.println(val);

        if (head == null) {
            return head;
        }

        head.next = removeElements(head.next, val);
        System.out.println(head);
//        System.out.println(listNode);
        if (head.val == val) {
            // 如果下一个节点相等，移除
            head = head.next;
        }

        return head;
    }


    public ListNode removeElements3(ListNode head, int val) {
//        System.out.println(head);
//        System.out.println(val);

        if (head == null) {
            return null;
        }

        // 暴力迭代
        // 添加个空节点
        ListNode dumpNode = new ListNode(0);
        dumpNode.next = head;
        ListNode curr = dumpNode; // 初始化当前的指针

        while (curr.next != null) {
            System.out.println(curr.val);
//
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        // 不管根节点是否相等于val，都直接移除
        return dumpNode.next;
    }
}
