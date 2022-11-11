package test.leetcode.easy;

import java.util.Arrays;

public class ListNode {
    public int val;
    public ListNode next;
    ListNode() {}
    public ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public static ListNode generate(int[] nums, int pos) {
//        System.out.println(Arrays.toString(nums));

        if (nums == null || nums.length == 0 || pos == -1) {
            return null;
        }

        // 初始化头部节点
        ListNode head = new ListNode(nums[0]);
        // 初始化当前节点
        ListNode curr = head;
        ListNode cycle = null;
        for (int i = 1; i < nums.length; i++) {
            ListNode next = new ListNode(nums[i]);
            if (i == pos) {
                cycle = next;
            }

            // 设置下个节点
            curr.next = next;
            curr = curr.next;
        }
        // 设置闭环节点，默认最后一个节点
        if (cycle != null) {
            curr.next = cycle;
        } else {
            curr.next = head;
        }

        return head;
    }

    public static ListNode generate(int[] nums) {
//        System.out.println(Arrays.toString(nums));

        if (nums == null || nums.length == 0) {
            return null;
        }

        // 初始化头部节点
        ListNode head = new ListNode(nums[0]);
        // 初始化当前节点
        ListNode curr = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode next = new ListNode(nums[i]);

            // 设置下个节点
            curr.next = next;
            curr = curr.next;
        }

        return head;
    }
}
