package linkedlist;

import java.util.HashSet;

/**
 * leetcode142. 环形链表 II
 *
 * @author dks233
 * @create 2022-05-26-11:08
 */
@SuppressWarnings("ALL")
public class CircularLinkedListTwo {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 方法1：set
    public ListNode detectCycleOne(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        // set包含head，说明到了环的第一个节点，跳出循环，返回head
        // head==null说明链表无环，跳出循环，返回head
        while (!set.contains(head) && head != null) {
            set.add(head);
            head = head.next;
        }
        return head;
    }

    // 方法2
    // 图解：环形链表2.drawio
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 求第一次相遇的节点
        while (true) {
            // fast能走到头说明没环
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            // 求快慢指针第一次相遇的节点
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        // 新节点初始值为head，和慢指针都是一次走一步，如果有环，总会相遇
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
