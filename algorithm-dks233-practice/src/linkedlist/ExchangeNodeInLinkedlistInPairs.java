package linkedlist;

/**
 * leetcode24. 两两交换链表中的节点
 *
 * @author dks233
 * @create 2022-06-30-22:31
 */
public class ExchangeNodeInLinkedlistInPairs {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 时间复杂度：O(N)
    // 空间复杂度：O(1)
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        while (cur.next != null && cur.next.next != null) {
            ListNode nextNode = cur.next;
            ListNode nextNextNode = cur.next.next;
            nextNode.next = nextNextNode.next;
            nextNextNode.next = nextNode;
            cur.next = nextNextNode;
            cur = nextNode;
        }
        return dummyHead.next;
    }
}
