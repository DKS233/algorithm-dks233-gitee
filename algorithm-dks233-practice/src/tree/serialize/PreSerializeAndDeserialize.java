package tree.serialize;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 前序遍历：序列化和反序列化
 *
 * @author dks233
 * @create 2022-05-02-8:48
 */
public class PreSerializeAndDeserialize {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // 前序遍历：序列化
    public static Queue<String> preSerialize(Node head) {
        Queue<String> queue = new LinkedList<>();
        process(head, queue);
        return queue;
    }

    public static void process(Node head, Queue<String> queue) {
        if (head == null) {
            queue.offer(null);
        } else {
            queue.offer(String.valueOf(head.data));
            process(head.left, queue);
            process(head.right, queue);
        }
    }

    // 前序遍历：反序列化
    public static Node preDeserialize(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        return process(queue);
    }

    public static Node process(Queue<String> queue) {
        String headValue = queue.poll();
        if (headValue == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(headValue));
        head.left = process(queue);
        head.right = process(queue);
        return head;
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLevel = 20;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            Node node = preDeserialize(preSerialize(head));
            if (!isEquals(head, node)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    /**
     * 获取随机二叉树
     *
     * @param maxLevel 最大层级
     * @param maxValue 最大值
     * @return 随机二叉树头节点
     */
    public static Node randomBinaryTree(int maxLevel, int maxValue) {
        return process(1, maxLevel, maxValue);
    }

    private static Node process(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * (maxValue + 1)));
        head.left = process(level + 1, maxLevel, maxValue);
        head.right = process(level + 1, maxLevel, maxValue);
        return head;
    }

    /**
     * 判断两棵二叉树是否相等
     *
     * @param headOne 二叉树1的头节点
     * @param headTwo 二叉树2的头节点
     * @return 两棵二叉树是否相等
     */
    public static boolean isEquals(Node headOne, Node headTwo) {
        if (headOne == null && headTwo == null) {
            return true;
        }
        // headOne == null && headTwo != null
        // headOne != null && headTwo == null
        if (headOne == null || headTwo == null) {
            return false;
        }
        if (headOne.data != headTwo.data) {
            return false;
        }
        return isEquals(headOne.left, headTwo.left) && isEquals(headOne.right, headTwo.right);
    }
}
