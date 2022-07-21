package linkedlist;

/**
 * 剑指offer专项突击版：剑指 Offer II 029. 排序的循环链表
 *
 * @author dks233
 * @create 2022-07-20-22:20
 */
@SuppressWarnings("ALL")
public class SortedCircularLinkedlist {
    public static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node insert = new Node(insertVal);
            insert.next = insert;
            return insert;
        }
        // 遍历链表，找到最小和最大的节点
        Node cur = head;
        Node minNode = head;
        Node maxNode = head;
        do {
            minNode = cur.val < minNode.val ? cur : minNode;
            maxNode = cur.val >= maxNode.val ? cur : maxNode;
            cur = cur.next;
        } while (cur != head);
        // 从最小节点开始，往后遍历，遇到大于等于insertVal的节点就在节点前面插入
        // 特殊情况：没有大于等于insertVal的节点，在最大的节点后面插入
        cur = minNode;
        do {
            if (cur.val <= insertVal && cur.next.val >= insertVal) {
                Node insert = new Node(insertVal);
                Node next = cur.next;
                cur.next = insert;
                insert.next = next;
                return head;
            } else {
                cur = cur.next;
            }
        } while (cur != minNode);
        // 跳出循环说明没有大于等于insertVal的节点，在最大的节点后面插入
        Node maxNext = maxNode.next;
        Node maxInsert = new Node(insertVal);
        maxNode.next = maxInsert;
        maxInsert.next = maxNext;
        return head;
    }
}
