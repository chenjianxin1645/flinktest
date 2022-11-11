package test.leetcode.easy;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * 206. 反转链表
 *
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 */
public class Code206 {
    public static void main(String[] args) {
       int[] head = {1,2,3,4,5};
        ListNode listNode = ListNode.generate(head);

        Code206 code206 = new Code206();
        ListNode listNode1 = code206.reverseList(listNode);
        System.out.println(listNode1);

    }


    public ListNode reverseList(ListNode head) {
//        System.out.println(head);

        /**
         * 暴力迭代
         */
        // 初始化前一个节点
        ListNode pre = null;
        // 初始化当前节点
        ListNode curr = head;
        while (curr != null) {
            System.out.println(curr.val);

            ListNode next = curr.next; // 下一个节点
            curr.next = pre; // 设置当前节点为前一个节点
            pre = curr; // 将当前节点保存为前一个节点

            curr = next; // 继续迭代循环
        }


        return pre;
    }

    public ListNode getNewList(ListNode head, ListNode reverse) {
//        System.out.println(head);
//        System.out.println(reverse);

        if (head == null) {
            return null;
        }
        // 递归下个节点
        ListNode newList = getNewList(head.next, reverse);
        head.next = null;
//        System.out.println(head);
        newList.next = head;
        reverse.next = newList;
//        System.out.println(reverse);

        return reverse;
    }
}
