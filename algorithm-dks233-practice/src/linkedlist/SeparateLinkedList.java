package linkedlist;

/**
 * leetcode86. 分隔链表
 *
 * @author dks233
 * @create 2022-06-26-21:10
 */
public class SeparateLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode partition(ListNode head, int x) {
        ListNode dummyHeadBigger = new ListNode(-1);
        ListNode dummyHeadSmaller = new ListNode(-1);
        ListNode curBigger = dummyHeadBigger;
        ListNode curSmaller = dummyHeadSmaller;
        while (head != null) {
            if (head.val >= x) {
                curBigger.next = head;
                curBigger = curBigger.next;
            } else {
                curSmaller.next = head;
                curSmaller = curSmaller.next;
            }
            head = head.next;
        }
        // 把两个链表连起来
        curBigger.next = null;
        curSmaller.next = dummyHeadBigger.next;
        return dummyHeadSmaller.next;
    }

    public static void main(String[] args) {
        SeparateLinkedList separateLinkedList = new SeparateLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        System.out.println(separateLinkedList.partition(head, 3));
    }
}
