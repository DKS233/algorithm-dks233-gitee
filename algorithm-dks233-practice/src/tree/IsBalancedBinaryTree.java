package tree;

/**
 * 剑指offer55：平衡二叉树
 * 是否是平衡二叉树
 * 平衡二叉树：在一棵二叉树中，每一棵子树，左树和右树的高度差不超过1
 * 分析：用二叉树递归套路做
 * 分析：整棵树是平衡二叉树时左树右树的情况
 * 情况1：左树高度=右树高度 左树是平衡二叉树 右树是平衡二叉树
 * 情况2：左树高度=右树高度+1 左树是平衡二叉树 右树是平衡二叉树
 * 情况3：左树高度=右树高度-1 左树是平衡二叉树 右树是平衡二叉树
 * 左树右树需要的信息：左树高度，右树高度，左树是否是平衡二叉树，右树是平衡二叉树
 * 子树需要返回的信息：高度，是否是平衡二叉树
 *
 * @author dks233
 * @create 2022-04-28-14:51
 */
public class IsBalancedBinaryTree {

    public static boolean isBalancedBinaryTree(Node head) {
        return process(head).isBalanced;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        boolean isBalanced = true;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 如果左子树不是平衡二叉树
        if (!leftInfo.isBalanced) {
            isBalanced = false;
        }
        // 如果右子树不是平衡二叉树
        if (!rightInfo.isBalanced) {
            isBalanced = false;
        }
        // 如果左右子树高度差超过1
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            isBalanced = false;
        }
        return new Info(isBalanced, height);
    }

    public static class Info {
        boolean isBalanced;
        int height;

        public Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }

    }

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
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
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    // comparator
    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLevel = 30;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            if (isBalancedBinaryTree(head) != comparator(head)) {
                isSuccess = false;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

}
