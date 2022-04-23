package linkedlist;

import java.util.HashSet;
import java.util.Objects;

/**
 * leetcode141：给一个链表的头节点，判断链表中是否有环
 *
 * @author dks233
 * @create 2022-04-22-16:44
 */
public class CircularLinkedList {
    // 方法1:哈希表
    // 时间复杂度：O(N)，空间复杂度：O(N)
    public static boolean isCircularLinkedListOne(ListNode head) {
        if (head == null) {
            return false;
        }
        HashSet<ListNode> set = new HashSet<>();
        // 如果有环，head一直不为null，直到返回true
        // 如果没环，head=null的时候结束循环，返回false
        while (head != null) {
            // 如果已经添加过，说明有环
            if (!set.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }

    // 方法2：快慢指针
    // 只要有环，一定能追上
    public static boolean isCircularLinkedListTwo(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    // 方法3：链表反转  链表反转判断环形链表.drawio
    // 如果有环，链表反转后头节点肯定是head
    public static boolean isCircularLinkedListThree(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode reverseHead = reverse(head);
        return reverseHead == head;
    }

    public static ListNode reverse(ListNode head) {
        ListNode next = null;
        ListNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ListNode listNode = (ListNode) o;
            return val == listNode.val &&
                    Objects.equals(next, listNode.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, next);
        }
    }
}
