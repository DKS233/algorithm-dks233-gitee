package linkedlist;

import java.util.ArrayList;

/**
 * leetcode234：回文链表
 * 给一个单链表的头结点head，判断该链表是否是回文链表
 * O(N)+O(1)
 *
 * @author dks233
 * @create 2022-04-22-13:57
 */
public class PalindromeLinkedList {
    // 方法1：快慢指针
    // 快慢指针求链表中点，四道题：class01~class10：快慢指针求链表中点.drawio
    // 奇中，偶上中 + 奇中，偶下中 + 奇中上，偶上中 + 奇中上，偶下中
    // 本题：奇数返回中点，偶数返回上中点
    // 后半部分链表反转，遍历比较，然后再将链表还原
    public static boolean isPalindromeLinkedListOne(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 循环跳出时，slow位置就是中点位置（奇数长度中点位置，偶数长度上中点位置）
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 后半部分链表反转，slow==null时没有后半部分，返回null
        ListNode newHead = reverse(slow.next);
        ListNode copyNewHead = newHead;
        ListNode cur = head;
        // 奇数长度时后半部分比前半部分长度短，所以结束条件应该以后半部分为准
        while (newHead != null) {
            if (newHead.val != cur.val) {
                return false;
            }
            newHead = newHead.next;
            cur = cur.next;
        }
        // 将反转的后半部分链表还原
        slow.next = reverse(copyNewHead);
        return true;
    }

    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 方法2：node都存到list中，头和尾遍历比较
    // 时间复杂度：O(N)，额外空间复杂度：O(N)
    public static boolean isisPalindromeLinkedListOne(ListNode head) {
        if (head == null) {
            return true;
        }
        ArrayList<ListNode> nodeList = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            nodeList.add(cur);
            cur = cur.next;
        }
        int start = 0;
        int end = nodeList.size() - 1;
        // 奇数：start和end相遇跳出循环
        // 偶数：start和end错过跳出循环
        while (start < end) {
            if (nodeList.get(start).val != nodeList.get(end).val) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
