package linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode61. 旋转链表
 *
 * @author dks233
 * @create 2022-07-06-17:21
 */
@SuppressWarnings("ALL")
public class RotateLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 0 1 2 3 4
    // 向右移动两个位置  3 4 0 1 2
    // 将链表每个节点向右移动k个位置，相当于把最后number个节点移动到链表前面，number = k % length，length为链表长度
    // 注：设定倒数第几个节点是从1开始计数，正数第几个节点是从0开始计数
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        // 先求链表长度，然后定位到倒数第(k%length+1)节点位置
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        if (k % length == 0) {
            return head;
        }
        // 接下来需要定位到倒数第(k % length + 1)节点位置和末尾节点位置，将链表分成两部分，然后后半部分拼接到前半部分前面
        // 准备快慢指针，慢指针初始位置在head，快指针初始位置在第(k % length)个节点位置
        // 然后同步移动，快指针到达末尾节点时慢指针位于倒数第(k % length + 1)个节点位置
        ListNode fast = head;
        ListNode slow = head;
        int count = 0;
        while (count < (k % length)) {
            fast = fast.next;
            count++;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }
}
