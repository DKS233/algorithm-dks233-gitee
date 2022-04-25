package class11;

import common.TreeUtils;
import common.TreeUtils.Node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 后序方式序列化和反序列化
 *
 * @author dks233
 * @create 2022-04-25-14:26
 */
public class PostSerializeBinaryTree {

    public static Queue<String> postSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        postOrder(head, queue);
        return queue;
    }

    public static void postOrder(Node head, Queue<String> queue) {
        if (head == null) {
            queue.offer(null);
        } else {
            postOrder(head.left, queue);
            postOrder(head.right, queue);
            queue.offer(String.valueOf(head.data));
        }
    }

    // 按照后序方式反序列化
    // 后序：左右头 反过来就是头右左
    // 将queue元素依次添加到栈中，弹出的顺序就是头右左
    // 左右头遍历：4 5 2 6 7 3 1
    // 头右左遍历：1 3 7 6 2 5 4
    public static Node postDeserialize(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return postReduction(stack);
    }

    public static Node postReduction(Stack<String> stack) {
        String headValue = stack.pop();
        if (headValue == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(headValue));
        head.right = postReduction(stack);
        head.left = postReduction(stack);
        return head;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxValue = 2333;
        int maxLevel = 100;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = TreeUtils.randomBinaryTree(maxValue, maxLevel);
            Queue<String> queue = postSerial(head);
            Node ans = postDeserialize(queue);
            if (!TreeUtils.binaryTreeEquals(head, ans)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
