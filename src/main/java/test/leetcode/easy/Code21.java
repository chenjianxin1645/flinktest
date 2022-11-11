package test.leetcode.easy;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *21. 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 *
 *
 *
 */

public class Code21 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
//        System.out.println(l1);
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
//        System.out.println(l2);


        Code21 code21 = new Code21();
        ListNode listNode = code21.mergeTwoLists(l1, l2);
        System.out.println(listNode);
    }


    /**
     * review
     *
     */
    public ListNode mergeTwoLists44444(ListNode list1, ListNode list2) {
//        System.out.println(list1);
//        System.out.println(list2);

        /**
         * 递归法
         */

        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return  list1;
        }

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }



    /**
     * review
     *
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
//        System.out.println(list1);
//        System.out.println(list2);

        /**
         * 迭代法
         */
        // 生成新的空节点列表
        ListNode listNode = new ListNode(-1);
        // 设置当前新节点的索引即哨兵
        ListNode pre = listNode;


        while (list1 != null && list2 != null) {
            // 判断两个列表的大小
            if (list1.val <= list2.val) {
                // 添加到当前哨兵下
                pre.next = list1;
                list1 = list1.next;
            } else {
                // 添加到当前哨兵下
                pre.next = list2;
                list2 = list2.next;
            }

            // 更换哨兵
            pre = pre.next;
        }
//        System.out.println(list1);
//        System.out.println(list2);

        // 将剩下的列表更新补充
        pre.next = list1 == null ? list2 : list1;


        return listNode.next;
    }


    /**
     * 采用合并递归法
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists222(ListNode list1, ListNode list2) {
//        System.out.println(list1);
//        System.out.println(list2);
        // 判断结束条件
        if (list1 == null) {
            System.out.println("1" + list2);
            return list2;
        } else if (list2 == null) {
            System.out.println("2" + list1);
            return list1;
        } else if (list1.val >= list2.val) {
            // 开始递归判断下一个节点
            list2.next = mergeTwoLists(list1, list2.next);
            System.out.println("3" + list2);
            return list2;
        } else {
             list1.next = mergeTwoLists(list1.next, list2);
            System.out.println("4" + list1);
             return list1;
        }
    }


    /**
     * 采用迭代法
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {

//        System.out.println(listNode);
//        System.out.println(list1.val);
//        System.out.println(list2.val);
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
//        System.out.println(listNode);

        // 初始化节点
        ListNode listNode = new ListNode(-1);
//        ListNode listNode = new ListNode();
//        System.out.println(listNode);

        // 添加一个哨兵
        ListNode pre = listNode;
//        System.out.println(listNode);
//        System.out.println(pre);
//        System.exit(0);
        while (list1!=null && list2!=null) {
            if (list1.val <= list2.val) {
                pre.next = list1;
                list1 = list1.next;

//                System.out.println(list1);
//                System.out.println(pre);
//                System.out.println(listNode);
//                System.exit(0);
            } else {
                pre.next = list2;
                list2 = list2.next;

//                System.out.println(list2);
//                System.out.println(pre);
//                System.out.println(listNode);
//                System.exit(0);
            }
            System.out.println("===== start ========");
            System.out.println(pre);
            System.out.println(listNode);
            // 添加到后面
            pre = pre.next;
            System.out.println(list1);
            System.out.println(list2);
            System.out.println(pre);
            System.out.println(listNode);
            System.out.println("===== end ========");
//            System.exit(0);
        }

        pre.next = list1 == null ? list2 : list1;

        return listNode.next;
    }

}






