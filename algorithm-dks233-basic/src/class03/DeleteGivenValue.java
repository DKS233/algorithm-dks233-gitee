package class03;

/**
 * 删除链表中指定的值
 *
 * @author dks233
 * @create 2022-03-26-15:10
 */
public class DeleteGivenValue {
    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 删除链表中指定的值
     * 2->3->1->3->2->4->null
     * 2->1->2->4->null
     * 思路：定位第一个不需要删除的节点，遍历
     *
     * @param head  头结点
     * @param value 指定的值
     * @return 新链表头结点
     */
    public static Node deleteGivenValue(Node head, int value) {
        // 定位到第一个不需要删除的结点位置
        while (head != null) {
            if (head.value != value) {
                break;
            }
            head = head.next;
        }
        // 执行到这里两种情况：head==null  head!=null
        // head为第一个不需要删除的结点位置，即新链表的头结点
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            // 当前结点是需要删除的
            if (cur.value == value) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
