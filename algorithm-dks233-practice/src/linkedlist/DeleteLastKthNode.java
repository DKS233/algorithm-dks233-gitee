package linkedlist;

/**
 * leetcode19. 删除链表的倒数第 N 个结点
 *
 * @author dks233
 * @create 2022-05-26-10:12
 */
public class DeleteLastKthNode {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 总共有多少节点
        int count = getCount(head);
        if (head == null || n > count) {
            return null;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        // end=count-n+1 需要删除的节点位置，索引从1开始
        int index = 0;
        // cur=head index=1
        // cur=head.next index=2
        // cur=head.next.next index=3
        while (index < count - n) {
            cur = cur.next;
            index++;
        }
        cur.next = cur.next != null ? cur.next.next : null;
        return dummyHead.next;
    }

    public int getCount(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
