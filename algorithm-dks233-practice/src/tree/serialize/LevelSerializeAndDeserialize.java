package tree.serialize;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层遍历：序列化和反序列化
 *
 * @author dks233
 * @create 2022-05-02-9:51
 */
public class LevelSerializeAndDeserialize {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // 按层遍历：序列化
    // 每弹一个头对头的左右节点进行序列化
    // nodeQueue：出入节点的队列
    // stringQueue：出入字符串的队列
    public static Queue<String> levelSerialize(Node head) {
        Queue<Node> nodeQueue = new LinkedList<>();
        Queue<String> stringQueue = new LinkedList<>();
        if (head == null) {
            stringQueue.offer(null);
            return stringQueue;
        }
        nodeQueue.offer(head);
        stringQueue.offer(String.valueOf(head.data));
        while (!nodeQueue.isEmpty()) {
            head = nodeQueue.poll();
            // 左右子节点入nodeQueue时对左右子节点进行序列化
            if (head.left != null) {
                nodeQueue.offer(head.left);
                stringQueue.offer(String.valueOf(head.left.data));
            } else {
                stringQueue.offer(null);
            }
            if (head.right != null) {
                nodeQueue.offer(head.right);
                stringQueue.offer(String.valueOf(head.right.data));
            } else {
                stringQueue.offer(null);
            }
        }
        return stringQueue;
    }

    // 按层遍历：反序列化
    // 每弹一个头对头的左右子节点进行反序列化
    // nodeQueue：出入节点的队列
    // stringQueue：出入字符串的队列
    public static Node levelDeserialize(Queue<String> stringQueue) {
        if (stringQueue == null || stringQueue.size() == 0) {
            return null;
        }
        // 从stringQueue中最先弹出头节点的data
        Node head = getNode(stringQueue.poll());
        if (head == null) {
            return null;
        }
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(head);
        Node cur = null;
        while (!nodeQueue.isEmpty()) {
            // 弹出一个头节点，对左右子节点进行反序列化
            cur = nodeQueue.poll();
            cur.left = getNode(stringQueue.poll());
            cur.right = getNode(stringQueue.poll());
            if (cur.left != null) {
                nodeQueue.offer(cur.left);
            }
            if (cur.right != null) {
                nodeQueue.offer(cur.right);
            }
        }
        return head;
    }

    public static Node getNode(String value) {
        if (value == null) {
            return null;
        } else {
            return new Node(Integer.parseInt(value));
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLevel = 20;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            Node node = levelDeserialize(levelSerialize(head));
            if (!isEquals(head, node)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    /**
     * 生成随机二叉树
     *
     * @param maxLevel 最大层级
     * @param maxValue 最大值
     * @return 随机二叉树头节点
     */
    public static Node randomBinaryTree(int maxLevel, int maxValue) {
        return process(1, maxLevel, maxValue);
    }

    public static Node process(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * (maxValue + 1)));
        head.left = process(level + 1, maxLevel, maxValue);
        head.right = process(level + 1, maxLevel, maxValue);
        return head;
    }

    /**
     * 判断两个二叉树是否相等
     *
     * @param headOne 二叉树1的头节点
     * @param headTwo 二叉树2的头节点
     * @return 两棵二叉树是否相等
     */
    public static boolean isEquals(Node headOne, Node headTwo) {
        if (headOne == null && headTwo == null) {
            return true;
        }
        if (headOne != null && headTwo == null) {
            return false;
        }
        if (headOne == null) {
            return false;
        }
        if (headOne.data != headTwo.data) {
            return false;
        }
        return isEquals(headOne.left, headTwo.left) && isEquals(headOne.right, headTwo.right);
    }
}
