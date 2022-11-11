package test.leetcode.easy.test;

import test.leetcode.easy.ListNode;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 * 234. 回文链表
 *
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：false
 *  
 *
 * 提示：
 *
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code234 {
    public static void main(String[] args) {
        int[] head = {1,2,2,1};

        ListNode listNode = ListNode.generate(head);
        Code234 code234 = new Code234();
        boolean palindrome = code234.isPalindrome(listNode);
        System.out.println(palindrome);
    }


    public boolean isPalindrome(ListNode head) {
//        System.out.println(head);

//        ListNode listNode = new ListNode(-1);
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            System.out.println(cur.val);

            ListNode listNode = new ListNode(cur.val);
            listNode.next = pre;
            pre = listNode;

            cur = cur.next;
        }
//        System.out.println(pre);
//        System.out.println(head);
//        System.out.println(pre == head);


        while (head != null || pre != null) {
            if (head == null || pre == null || head.val != pre.val) {
                return false;
            }

            head = head.next;
            pre = pre.next;
        }

        return true;
    }
}
