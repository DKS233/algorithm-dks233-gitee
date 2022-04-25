package class11;

import common.TreeUtils;
import common.TreeUtils.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层序列化和反序列化
 *
 * @author dks233
 * @create 2022-04-25-14:29
 */
public class LayerSerializeBinaryTree {

    // 类比：class11.TraverseBinaryTreeByLayer.layerTraverse
    // 队列：每弹一个头对头的左右孩子节点进行序列化
    public static Queue<String> layerSerial(Node head) {
        Queue<String> stringQueue = new LinkedList<>();
        if (head == null) {
            stringQueue.offer(null);
        } else {
            Queue<Node> nodeQueue = new LinkedList<>();
            nodeQueue.offer(head);
            stringQueue.offer(String.valueOf(head.data));
            while (!nodeQueue.isEmpty()) {
                head = nodeQueue.poll();
                if (head.left != null) {
                    // node入队列的时候进行序列化
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
        }
        return stringQueue;
    }

    // 队列；每弹一个头对头的左右孩子节点进行反序列化
    public static Node layerDeserialize(Queue<String> stringQueue) {
        if (stringQueue == null || stringQueue.size() == 0) {
            return null;
        }
        // stringQueue最先弹出根节点的值
        String value = stringQueue.poll();
        Node head = getNode(value);
        if (head == null) {
            return null;
        }
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(head);
        Node cur = null;
        while (!nodeQueue.isEmpty()) {
            // 每弹出一个父节点
            cur = nodeQueue.poll();
            // 反序列化对应的左右子节点
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
        int maxLevel = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = TreeUtils.randomBinaryTree(maxValue, maxLevel);
            Queue<String> stringQueue = layerSerial(head);
            Node comparatorHead = layerDeserialize(stringQueue);
            if (!TreeUtils.binaryTreeEquals(head, comparatorHead)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

}
