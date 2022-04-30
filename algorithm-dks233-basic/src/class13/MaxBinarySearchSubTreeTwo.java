package class13;


import java.util.ArrayList;

/**
 * 给定一棵二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的头节点
 * 设每轮递归的头节点是head
 * 情况1：最大二叉搜索子树经过head，head是最大二叉搜索子树的头节点
 * 左子树是二叉搜索树，左子树的最大值小于head.data
 * 右子树是二叉搜索树，右子树的最小值大于head.data
 * 最大二叉搜索子树的头节点就是head
 * 情况2：最大二叉搜索子树不经过head，head不是最大二叉搜索子树的头节点
 * 最大二叉搜索子树是左子树的最大二叉搜索子树和右子树的最大二叉搜索子树中的较大值
 * 最大二叉搜索子树的头节点是左右子树中最大二叉搜索子树的头节点
 * 注：整个左/右子树可能是最大二叉搜索树
 * 需要的信息：左右子树的最大值，最小值，左右子树是否是二叉搜索树，左右子树最大二叉搜索子树的头节点，为了比较左右子树
 * 中哪个子树的最大二叉搜索树大，需要知道左右子树中最大二叉搜索子树的大小
 * 化简：减掉左右子树是否是二叉搜索树，因为左右子树最大二叉搜索树的头节点是head.left，左右子树肯定是二叉搜索树
 * 左右子树最大二叉搜索树的头节点不是head.right，左右子树肯定不是二叉搜索树
 *
 * @author dks233
 * @create 2022-04-29-21:26
 */
public class MaxBinarySearchSubTreeTwo {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static Node getMaxBinarySearchSubTreeHead(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).maxHead;
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        // maxValue,minValue
        int maxValue = head.data;
        int minValue = head.data;
        if (leftInfo != null) {
            maxValue = Math.max(maxValue, leftInfo.maxValue);
            minValue = Math.min(minValue, leftInfo.minValue);
        }
        if (rightInfo != null) {
            maxValue = Math.max(maxValue, rightInfo.maxValue);
            minValue = Math.min(minValue, rightInfo.minValue);
        }
        // maxSize,maxHead
        int maxSize = 0;
        Node maxHead = null;
        // 情况1：最大二叉搜索子树的节点是head
        // 左子树是二叉搜索树，右子树是二叉搜索树
        // 左子树最大值小于head.data，右子树最大值大于head.data
        // 这种情况下左树的最大二叉搜索树就是左树，右树的最大二叉搜索树就是右树
        boolean leftIsBinarySearchTree = (leftInfo == null || leftInfo.maxHead == head.left);
        boolean rightIsBinarySearchTree = (rightInfo == null || rightInfo.maxHead == head.right);
        if (leftIsBinarySearchTree && rightIsBinarySearchTree) {
            int leftMaxValue = (leftInfo == null ? head.data : leftInfo.maxValue);
            int rightMinValue = (rightInfo == null ? head.data : rightInfo.minValue);
            boolean leftMaxSmallerThanHead = leftInfo == null || leftMaxValue < head.data;
            boolean rightMinLessThanHead = rightInfo == null || rightMinValue > head.data;
            if (leftMaxSmallerThanHead && rightMinLessThanHead) {
                int leftMaxSize = leftInfo == null ? 0 : leftInfo.maxSize;
                int rightMaxSize = rightInfo == null ? 0 : rightInfo.maxSize;
                maxHead = head;
                maxSize = leftMaxSize + rightMaxSize + 1;
                return new Info(maxValue, minValue, maxHead, maxSize);
            }
        }
        // 情况2：最大二叉搜索子树的头节点不是head
        int leftMaxSize = (leftInfo == null ? 0 : leftInfo.maxSize);
        int rightMaxSize = (rightInfo == null ? 0 : rightInfo.maxSize);
        if (leftMaxSize >= rightMaxSize) {
            maxSize = leftMaxSize;
            maxHead = (leftInfo == null ? null : leftInfo.maxHead);
        } else {
            maxSize = rightMaxSize;
            maxHead = (rightInfo == null ? null : rightInfo.maxHead);
        }
        return new Info(maxValue, minValue, maxHead, maxSize);
    }

    public static class Info {
        int maxValue;
        int minValue;
        Node maxHead;
        int maxSize;

        public Info(int maxValue, int minValue, Node maxHead, int maxSize) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.maxHead = maxHead; // 最大二叉搜索子树的头节点
            this.maxSize = maxSize; // 最大二叉搜索子树的大小
        }
    }

    // comparator
    public static Node comparator(Node head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        Node leftAns = comparator(head.left);
        Node rightAns = comparator(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).data <= arr.get(i - 1).data) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static void main(String[] args) {
        // 满二叉树测试
        Node testNode = new Node(1);
        testNode.left = new Node(2);
        testNode.right = new Node(3);
        Node nodeOne = getMaxBinarySearchSubTreeHead(testNode);
        Node nodeTwo = comparator(testNode);
        // 正式测试
        int testTimes = 100000;
        int maxLevel = 20;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            Node myMethod = getMaxBinarySearchSubTreeHead(head);
            Node comparator = comparator(head);
            if (comparator != myMethod) {
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

}
