package linkedlist;

/**
 * 剑指offer25：合并排序链表
 * 输入两个递增排序的链表，合并这两个链表，并且新链表中的节点仍然是递增排序的
 *
 * @author dks233
 * @create 2022-04-21-15:55
 */
public class MergeLinkedList {

    // 方法1：准备虚拟头节点，按照归并排序的方法依次将两个链表的节点接到虚拟头节点后面
    // 时间复杂度：O(m+n)，即俩链表长度之和，额外空间复杂度：O(1)
    public static ListNode mergeLinkedListOne(ListNode headOne, ListNode headTwo) {
        if (headOne == null && headTwo == null) {
            return null;
        }
        if (headOne == null) {
            return headTwo;
        }
        if (headTwo == null) {
            return headOne;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        // headOne和headTwo都未越界
        while (headOne != null && headTwo != null) {
            if (headOne.val <= headTwo.val) {
                cur.next = headOne;
                headOne = headOne.next;
            } else {
                cur.next = headTwo;
                headTwo = headTwo.next;
            }
            cur = cur.next;
        }
        // headTwo越界
        while (headOne != null) {
            cur.next = headOne;
            cur = cur.next;
            headOne = headOne.next;
        }
        // headOne越界
        while (headTwo != null) {
            cur.next = headTwo;
            cur = cur.next;
            headTwo = headTwo.next;
        }
        return dummyHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

}
