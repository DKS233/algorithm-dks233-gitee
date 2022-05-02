package tree.serialize;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 后序遍历：序列化和反序列化
 *
 * @author dks233
 * @create 2022-05-02-8:48
 */
public class PostSerializeAndDeserialize {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // 后序遍历：序列化
    public static Queue<String> postSerialize(Node head) {
        Queue<String> queue = new LinkedList<>();
        process(head, queue);
        return queue;
    }

    public static void process(Node head, Queue<String> queue) {
        if (head == null) {
            queue.offer(null);
        } else {
            process(head.left, queue);
            process(head.right, queue);
            queue.offer(String.valueOf(head.data));
        }
    }

    // 后序遍历：反序列化
    // 前序遍历：头左右  后序遍历：左右头
    // 头左右->头右左->左右头
    // 将后序遍历得到的queue元素依次弹出加入到栈中，入栈顺序是左右头，出栈顺序就是头右左
    public static Node postDeserialize(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return process(stack);
    }

    public static Node process(Stack<String> stack) {
        String pop = stack.pop();
        if (pop == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(pop));
        head.right = process(stack);
        head.left = process(stack);
        return head;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLevel = 20;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            Node node = postDeserialize(postSerialize(head));
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
     * 判断两棵二叉树是否相等
     *
     * @param headOne 二叉树1的头节点
     * @param headTwo 二叉树2的头节点
     * @return 两棵二叉树是否相等
     */
    public static boolean isEquals(Node headOne, Node headTwo) {
        if (headOne != null && headTwo == null || headOne == null && headTwo != null) {
            return false;
        }
        if (headOne == null) {
            return true;
        }
        if (headOne.data != headTwo.data) {
            return false;
        }
        return isEquals(headOne.left, headTwo.left) && isEquals(headOne.right, headTwo.right);
    }
}
