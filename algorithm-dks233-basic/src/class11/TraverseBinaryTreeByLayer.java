package class11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层遍历二叉树
 *
 * @author dks233
 * @create 2022-04-24-21:26
 */
public class TraverseBinaryTreeByLayer {

    public static void layerTraverse(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        Node cur = head;
        while (!queue.isEmpty()) {
            // 弹出一个父节点
            cur = queue.poll();
            System.out.print(cur.data + "  ");
            // 该节点有左孩子节点，左孩子节点入队列
            // 该节点有右孩子节点，右孩子节点入队列
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node zero = new Node(0);
        zero.left = new Node(1);
        zero.right = new Node(2);
        zero.left.left = new Node(3);
        zero.left.right = new Node(4);
        zero.right.left = new Node(5);
        zero.right.right = new Node(6);
        layerTraverse(zero);
    }
}
