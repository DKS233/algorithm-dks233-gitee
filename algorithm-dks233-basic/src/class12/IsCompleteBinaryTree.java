package class12;

import java.time.Year;
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

    // 方法2：按照二叉树递归套路做
    public static boolean isCompleteBinaryTreeTwo(Node head) {
        return process(head).isCompleteBinaryTree;
    }

    public static class Info {
        boolean isFullBinaryTree;
        int height;
        boolean isCompleteBinaryTree;

        public Info(boolean isFullBinaryTree, int height, boolean isCompleteBinaryTree) {
            this.isFullBinaryTree = isFullBinaryTree;
            this.height = height;
            this.isCompleteBinaryTree = isCompleteBinaryTree;
        }
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(true, 0, true);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFullBinaryTree = leftInfo.isFullBinaryTree && rightInfo.isFullBinaryTree
                && (leftInfo.height == rightInfo.height);
        boolean isCompleteBinaryTree = false;
        if (leftInfo.isFullBinaryTree && rightInfo.isFullBinaryTree && leftInfo.height == rightInfo.height) {
            isCompleteBinaryTree = true;
        }
        if (leftInfo.isCompleteBinaryTree && rightInfo.isFullBinaryTree && leftInfo.height == (rightInfo.height + 1)) {
            isCompleteBinaryTree = true;
        }
        if (leftInfo.isFullBinaryTree && rightInfo.isFullBinaryTree && leftInfo.height == (rightInfo.height + 1)) {
            isCompleteBinaryTree = true;
        }
        if (leftInfo.isFullBinaryTree && rightInfo.isCompleteBinaryTree && leftInfo.height == rightInfo.height) {
            isCompleteBinaryTree = true;
        }
        return new Info(isFullBinaryTree, height, isCompleteBinaryTree);
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLevel = 20;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            boolean one = isCompleteBinaryTree(head);
            boolean two = isCompleteBinaryTreeTwo(head);
            if (one != two) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    /**
     * 生成随机二叉树
     *
     * @param maxLevel 最大层数
     * @param maxValue 最大值
     * @return 随机二叉树
     */
    public static Node randomBinaryTree(int maxLevel, int maxValue) {
        return process(1, maxLevel, maxValue);
    }

    public static Node process(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.25) {
            return null;
        }
        Node head = new Node((int) (Math.random() * (maxValue + 1)));
        head.left = process(level + 1, maxLevel, maxValue);
        head.right = process(level + 1, maxLevel, maxValue);
        return head;
    }
}
