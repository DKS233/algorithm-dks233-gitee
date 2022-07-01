package linkedlist;

/**
 * leetcode83. 删除排序链表中的重复元素
 *
 * @author dks233
 * @create 2022-07-01-23:59
 */
public class DeleteDuplicateElement {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(head.val + 1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode deleteDuplicatesTwo(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
