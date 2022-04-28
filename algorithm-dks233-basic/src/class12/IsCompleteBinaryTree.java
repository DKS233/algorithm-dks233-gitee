package class12;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是否是完全二叉树
 *
 * @author dks233
 * @create 2022-04-28-10:35
 */
public class IsCompleteBinaryTree {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // 方法1：改造按层遍历
    // 如果当前节点有右孩子没左孩子，不是二叉树
    // 如果存在左右孩子不全的节点，该节点之后的节点必须都是叶节点，这棵树才是完全二叉树
    public static boolean isCompleteBinaryTree(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        // 当前节点的左右孩子全不全，当前节点只有一个节点，flag设置为false
        boolean flag = false;
        while (!queue.isEmpty()) {
            head = queue.poll();
            // 如果存在左右孩子不全的节点，flag变成true
            // 该节点之后的节点必须都是叶节点，这棵树才是完全二叉树
            // 如果该节点之后的节点有一个孩子，说明不是叶节点，返回false
            if (flag && (head.left != null || head.right != null)) {
                return false;
            }
            // 当前节点有右孩子没左孩子，返回false
            if (head.left == null && head.right != null) {
                return false;
            }
            // 当前节点左右孩子不全
            // 没左右孩子或者只有左孩子
            if (head.left == null || head.right == null) {
                flag = true;
            }
            if (head.left != null) {
                queue.offer(head.left);
            }
            if (head.right != null) {
                queue.offer(head.right);
            }
        }
        return true;
    }
}
