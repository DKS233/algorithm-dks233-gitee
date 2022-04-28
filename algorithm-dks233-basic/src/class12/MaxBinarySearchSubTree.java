package class12;

/**
 * 二叉树中最大二叉搜索子树的大小
 * head：每轮递归的头节点
 * 情况1：head是最大搜索二叉树的头节点
 * 要求：左树是搜索二叉树，右树是搜索二叉树，左树最大值小于head.data，
 * 右树最小值大于head.data，左树size+右树size+1=最大搜索子树的大小
 * 需要的信息：左右子树是否是搜索二叉树，左右子树的最大值，最小值，左右子树的size
 * 情况2：head不是最大搜索二叉树的头节点
 * 要求：求左树中最大二叉搜索树的size和右树中最大二叉搜索树的size的较大值（可能是整个左树或右树）
 * 需要的信息：左右子树的最大二叉搜索树的size
 * 总共需要的信息：左右树是否是搜索二叉树、左右子树最大值、最小值、左右子树size，左右子树最大二叉搜索子树的大小
 * 化简：左右子树最大值、最小值、左右子树size，左右子树最大二叉搜索子树的大小
 * 原因：左右子树最大二叉搜索树的大小如果等于左右子树的size，左右子树一定是搜索二叉树
 * 原因：左右子树最大二叉搜索树的大小如果不等于左右子树的size，左右子树不是搜索二叉树
 *
 * @author dks233
 * @create 2022-04-28-21:40
 */
public class MaxBinarySearchSubTree {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static int getMaxSize(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxBinarySubTreeSize;
    }

    public static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int maxValue = head.data;
        int minValue = head.data;
        int size = 1;
        // 先求head的maxValue和minValue和size
        if (leftInfo != null) {
            maxValue = Math.max(maxValue, leftInfo.maxValue);
            minValue = Math.min(minValue, leftInfo.minValue);
            size += leftInfo.size;
        }
        if (rightInfo != null) {
            maxValue = Math.max(maxValue, rightInfo.maxValue);
            minValue = Math.min(minValue, rightInfo.minValue);
            size += rightInfo.size;
        }
        int maxBinarySubTreeSize = 0;
        // 再求maxBinarySubTreeSize
        // 情况一：head是最大二叉搜索子树的头节点
        int p1 = 0;
        @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
        boolean leftIsBST = leftInfo == null || leftInfo.maxBinarySubTreeSize == leftInfo.size;
        @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
        boolean rightIsBST = rightInfo == null || rightInfo.maxBinarySubTreeSize == rightInfo.size;
        if (leftIsBST && rightIsBST) {
            boolean leftMaxIsSmallerThanHead = leftInfo == null || leftInfo.maxValue < head.data;
            boolean rightMinIsBiggerThanHead = rightInfo == null || rightInfo.minValue > head.data;
            if (leftMaxIsSmallerThanHead && rightMinIsBiggerThanHead) {
                int leftSize = leftInfo == null ? 0 : leftInfo.size;
                int rightSize = rightInfo == null ? 0 : rightInfo.size;
                p1 = leftSize + rightSize + 1;
            }
        }
        // 情况二：head不是最大二叉搜索子树的头节点，求左右子树的最大二叉搜索子树
        int p2 = 0;
        if (leftInfo != null) {
            p2 = leftInfo.maxBinarySubTreeSize;
        }
        int p3 = 0;
        if (rightInfo != null) {
            p3 = rightInfo.maxBinarySubTreeSize;
        }
        maxBinarySubTreeSize = Math.max(Math.max(p1, p2), p3);
        return new Info(maxBinarySubTreeSize, maxValue, minValue, size);
    }

    public static class Info {
        int maxBinarySubTreeSize;
        int maxValue;
        int minValue;
        int size;

        public Info(int maxBinarySubTreeSize, int maxValue, int minValue, int size) {
            this.maxBinarySubTreeSize = maxBinarySubTreeSize;
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.size = size;
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLevel = 100;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node node = randomBinaryTree(maxLevel, maxValue);
            int maxSize = getMaxSize(node);
            int comparator = ZuoMaxBinarySearchSubTree.largestBSTSubtree(node);
            if (maxSize != comparator) {
                isSuccess = false;
                break;
            }
        }
        maxLevel = 10;
        maxValue = 2333;
        for (int i = 0; i < testTimes; i++) {
            Node node = randomBinaryTree(maxLevel, maxValue);
            int maxSize = getMaxSize(node);
            int comparator = ZuoMaxBinarySearchSubTree.largestBSTSubtree(node);
            if (maxSize != comparator) {
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
        head.right = process(level + 1, maxLevel + 1, maxValue);
        return head;
    }
}
