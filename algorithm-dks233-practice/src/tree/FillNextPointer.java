package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode116. 填充每个节点的下一个右侧节点指针
 * 参考文档：https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/solution/dong-hua-yan-shi-san-chong-shi-xian-116-tian-chong/
 *
 * @author dks233
 * @create 2022-08-25-10:48
 */
@SuppressWarnings("ALL")
public class FillNextPointer {
    public static class MethodOne {
        public Node connect(Node head) {
            if (head == null) {
                return null;
            }
            Node result = head;
            Queue<Node> queue = new LinkedList<>();
            queue.offer(head);
            Node curEnd = head;
            Node nextEnd = null;
            while (!queue.isEmpty()) {
                head = queue.poll();
                if (head.left != null) {
                    queue.offer(head.left);
                    nextEnd = head.left;
                }
                if (head.right != null) {
                    queue.offer(head.right);
                    nextEnd = head.right;
                }
                if (head == curEnd) {
                    curEnd = nextEnd;
                    nextEnd = null;
                    Node cur = queue.peek();
                    for (Node node : queue) {
                        cur.next = node;
                        cur = node;
                    }
                }
            }
            return result;
        }
    }

    public static class MethodTwo {
        public Node connect(Node head) {
            if (head == null) {
                return null;
            }
            if (head.left != null) {
                head.left.next = head.right == null ? null : head.right;
                head.right.next = head.next != null ? head.next.left : null;
                head.left = connect(head.left);
                head.right = connect(head.right);
            }
            return head;
        }
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int _val) {
            val = _val;
        }
    }
}
