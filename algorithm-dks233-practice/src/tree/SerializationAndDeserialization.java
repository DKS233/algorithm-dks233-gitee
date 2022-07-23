package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 剑指offer专项突击版：剑指 Offer II 048. 序列化与反序列化二叉树
 *
 * @author dks233
 * @create 2022-07-23-18:46
 */
public class SerializationAndDeserialization {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 前序遍历
    public Queue<String> serialize(TreeNode root) {
        Queue<String> queue = new LinkedList<>();
        preOrder(root, queue);
        return queue;
    }

    public void preOrder(TreeNode head, Queue<String> queue) {
        if (head == null) {
            queue.offer(null);
            return;
        }
        queue.offer(String.valueOf(head.val));
        preOrder(head.left, queue);
        preOrder(head.right, queue);
    }

    public TreeNode deserialize(Queue<String> queue) {
        return process(queue);
    }

    public TreeNode process(Queue<String> queue) {
        String headValue = queue.poll();
        if (headValue == null) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.parseInt(headValue));
        head.left = process(queue);
        head.right = process(queue);
        return head;
    }
}
