package test.leetcode.easy.test;

import test.leetcode.easy.ListNode;

import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *
 * 83. 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * 示例 2：
 *
 *
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *  
 *
 * 提示：
 *
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Code83 {
    public static void main(String[] args) {
        int[] head_arr = {1,1,2,3,3};

        ListNode generate = ListNode.generate(head_arr);

        Code83 code83 = new Code83();
        ListNode listNode = code83.deleteDuplicates(generate);
        System.out.println(listNode);
    }


    public ListNode deleteDuplicates(ListNode head) {
//        System.out.println(head);

        // 每遍历一个元素 就确认下当前集合是否存在
        // 若存在直接获取到下一个节点
        HashSet<Integer> set = new HashSet<>();

        // 设置双指针
        ListNode curr = head; // 当前节点指针
        ListNode pre = head; // 上一个节点指针
        while (curr != null) {
//            System.out.println(head.val);
            if (!set.add(curr.val)) {
                // 说明已经添加过了
                pre.next = curr.next;
                curr = pre.next;
            } else {
                pre = curr;
                curr = curr.next;
            }
        }

//        System.out.println(curr);
//        System.out.println(pre);
//        System.out.println(head);

        return head;
    }



    public ListNode deleteDuplicates2222(ListNode head) {
//        System.out.println(head);

        if (head == null) {
            return head;
        }

        // 已经升序
        ListNode curr = head;
        /**
         * 迭代遍历当前节点和下一个节点的值
         */
        while (curr.next != null){
            if (curr.next.val == curr.val) {
                curr.next = curr.next.next;
            } else {
                // 不相登，直接下一个
                curr = curr.next;
            }
        }

        return head;
    }


}


