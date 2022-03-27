package class03;

/**
 * 反转单链表
 *
 * @author dks233
 * @create 2022-03-21-21:06
 */
public class ReverseSingleLinkedList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    // 给头结点head,返回新的头结点
    // 该写法适用于head本身为null和单链表中只有一个结点的情况
    // 记作写法1，写法2为完整写法
    public static Node reverseSingleLinkedListOne(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 写法2
    public static Node reverseSinegleLinkedListTwo(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
