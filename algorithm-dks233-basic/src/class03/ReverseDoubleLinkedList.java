package class03;

/**
 * 反转双链表
 *
 * @author dks233
 * @create 2022-03-25-22:24
 */
public class ReverseDoubleLinkedList {
    public static class DoubleNode {
        public DoubleNode pre;
        public DoubleNode next;
        public int value;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    /**
     * 反转双链表，给定头结点，返回新链表的头结点
     *
     * @param head 原始链表的头结点
     * @return 新链表的头结点
     */
    public static DoubleNode reverseDoubleLinkedList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.pre = next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
