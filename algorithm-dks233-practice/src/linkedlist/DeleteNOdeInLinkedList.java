package linkedlist;

/**
 * leetcode237. 删除链表中的节点
 *
 * @author dks233
 * @create 2022-07-09-15:25
 */
public class DeleteNOdeInLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 删除的不是末尾节点，所以肯定有next
    public void deleteNode(ListNode node) {
        ListNode cur = node;
        ListNode next = node.next;
        while (true) {
            cur.val = next.val;
            if (next.next == null) {
                cur.next = null;
                break;
            }
            cur = cur.next;
            next = next.next;
        }
    }

    // 简写
    public static class MethodTwo {
        public void deleteNode(ListNode node) {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}
