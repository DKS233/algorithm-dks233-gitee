package class11;

import common.TreeUtils;
import common.TreeUtils.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树先序方式序列化和反序列化
 *
 * @author dks233
 * @create 2022-04-25-10:52
 */
public class PreSerializeBinaryTree {
    /**
     * 按照先序方式序列化
     * 将序列化结果放到队列里
     *
     * @param head 根节点
     * @return 序列化结果
     */
    public static Queue<String> preSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        preOrder(head, queue);
        return queue;
    }

    // 对比TraversalTree.java里的preOrder
    public static void preOrder(Node head, Queue<String> queue) {
        if (head == null) {
            // 规定用什么占位
            queue.offer(null);
        } else {
            queue.offer(String.valueOf(head.data));
            preOrder(head.left, queue);
            preOrder(head.right, queue);
        }
    }

    public static Node preDeserialize(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        return preReduction(queue);
    }


    public static Node preReduction(Queue<String> queue) {
        String headValue = queue.poll();
        if (headValue == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(headValue));
        head.left = preReduction(queue);
        head.right = preReduction(queue);
        return head;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxValue = 2333;
        int maxLevel = 30;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = TreeUtils.randomBinaryTree(maxValue, maxLevel);
            Queue<String> queue = preSerial(head);
            Node ans = preDeserialize(queue);
            if (!TreeUtils.binaryTreeEquals(head, ans)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}


