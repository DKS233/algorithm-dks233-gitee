package linkedlist;

/**
 * 剑指offer22：输入一个链表，输出该链表中倒数第k个节点
 *
 * @author dks233
 * @create 2022-04-20-10:22
 */
public class LastKthNode {

    // 方法1：先遍历获取长度，然后再遍历输出节点
    // 时间复杂度：O(N)，额外空间复杂度：O(1)
    public static ListNode getKthNodeOne(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        int length = 0;
        ListNode cur = head;
        // 计算链表长度
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        if (length < k) {
            return null;
        }
        cur = head;
        int index = 0;
        while (index < length - k) {
            index++;
            cur = cur.next;
        }
        return cur;
    }

    // 方法2：快慢指针
    // fast初始位置为k位置，slow初始位置为0位置（head），然后fast和slow都往后走，fast==null时slow即为需要求的节点
    // 时间复杂度：O(N)，额外空间复杂度：O(1)
    public static ListNode getKthNodeTwo(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 快指针先走到k位置
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        // fast==null 说明链表长度等于k，返回倒数第k个节点就是head（到这一步slow=head）
        if (fast == null) {
            return slow;
        }
        // 快指针和慢指针同步移动，fast==null时slow即为需要求的节点
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
