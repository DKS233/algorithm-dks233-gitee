package linkedlist;

/**
 * leetcode328. 奇偶链表
 *
 * @author dks233
 * @create 2022-08-26-22:48
 */
@SuppressWarnings("ALL")
public class OddEvenLinkedlist {
    // odd:奇数 even:偶数
    // left:奇数 right:偶数
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = head;
        ListNode right = head.next;
        while (right != null) {
            if (right.next == null) {
                break;
            }
            // 下一个需要连接的奇数节点
            ListNode leftNext = right.next;
            // 偶数节点链表头节点
            ListNode rightHead = left.next;
            if (leftNext.next == null) {
                left.next = leftNext;
                leftNext.next = rightHead;
                right.next = null;
            } else {
                ListNode rightNext = leftNext.next;
                left.next = leftNext;
                leftNext.next = rightHead;
                right.next = rightNext;
            }
            left = left.next;
            right = right.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
