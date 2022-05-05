package class12;

import java.util.ArrayList;

/**
 * leetcode98:验证二叉搜索树
 * 判断二叉树是不是搜索二叉树
 * 搜索二叉树：整棵树没有重复值，在一棵二叉树中，每一棵子树的头节点，左树上的值都比它小，右树上的值都比它大
 * 有重复值的需求：相同的放一起，不是在树上挂很多重复节点
 * 分析：用二叉树递归套路做
 * 分析：整棵树是搜索二叉树的情况
 * 左树是搜索二叉树，右树是搜索二叉树，左树的最大值小于头节点的值，右树的最小值大于头节点的值
 * 需要的左树右树信息：左树右树是不是搜索二叉树，左树右树最大值，子树最小值
 * 需要返回的子树信息：是不是搜索二叉树，子树最大值，子树最小值
 *
 * @author dks233
 * @create 2022-04-28-15:41
 */
public class IsSearchBinaryTree {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // 需要的信息
    // 原则：左树是搜索二叉树、右树是搜索二叉树、左树的最大值小于head.data，右树的最小值大于head.data
    // 需要的信息，左树/右树是否是搜索二叉树、最大值、最小值
    public static boolean isSearchBinaryTree(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isSearchBinaryTree;
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        boolean isSearchBinaryTree = true;
        int max = head.data;
        int min = head.data;
        // 计算head的最大值和最小值
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
        }
        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
        }
        // 计算head是否是搜索二叉树
        if (leftInfo != null && !leftInfo.isSearchBinaryTree) {
            isSearchBinaryTree = false;
        }
        if (rightInfo != null && !rightInfo.isSearchBinaryTree) {
            isSearchBinaryTree = false;
        }
        if (leftInfo != null && leftInfo.max >= head.data) {
            isSearchBinaryTree = false;
        }
        if (rightInfo != null && rightInfo.min <= head.data) {
            isSearchBinaryTree = false;
        }
        return new Info(isSearchBinaryTree, max, min);
    }

    public static class Info {
        boolean isSearchBinaryTree;
        int max;
        int min;

        public Info(boolean isSearchBinaryTree, int max, int min) {
            this.isSearchBinaryTree = isSearchBinaryTree;
            this.max = max;
            this.min = min;
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLevel = 30;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            if (isSearchBinaryTree(head) != comparator(head)) {
                isSuccess = false;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    /**
     * 生成随机二叉树
     *
     * @param maxLevel 最大层级
     * @param maxValue 最大值
     * @return 随机二叉树
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

    // comparator
    public static boolean comparator(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).data <= arr.get(i - 1).data) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }
}
