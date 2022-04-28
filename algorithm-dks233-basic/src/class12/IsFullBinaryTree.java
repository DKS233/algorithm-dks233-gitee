package class12;

/**
 * 判断二叉树是不是满二叉树
 *
 * @author dks233
 * @create 2022-04-28-20:05
 */
public class IsFullBinaryTree {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // 满二叉树：高度为h，节点数=2^h-1
    // 方法1  需要的信息，左右子树的高度，左右子树的节点数
    public static boolean isFullBinaryTree(Node head) {
        Info info = process(head);
        return (Math.pow(2, info.height) - 1) == info.nodeCount;
    }

    public static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        // 获得head的高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodeCount = leftInfo.nodeCount + rightInfo.nodeCount + 1;
        // 获得head的节点数
        return new Info(height, nodeCount);
    }

    public static class Info {
        int height;
        int nodeCount;

        public Info(int height, int nodeCount) {
            this.height = height;
            this.nodeCount = nodeCount;
        }
    }

    // 方法2：左树满&&右树满&&左右数高度一样----->满二叉树
    // 需要的信息：左右树是否是满的，左右数高度
    public static boolean isFullBinaryTreeTwo(Node head) {
        return processTwo(head).isFull;
    }

    public static InfoTwo processTwo(Node head) {
        if (head == null) {
            return new InfoTwo(0, true);
        }
        InfoTwo leftInfo = processTwo(head.left);
        InfoTwo rightInfo = processTwo(head.right);
        // 求head的isFull和height
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        return new InfoTwo(height, isFull);
    }

    public static class InfoTwo {
        int height;
        boolean isFull;

        public InfoTwo(int height, boolean isFull) {
            this.height = height;
            this.isFull = isFull;
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLevel = 20;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Node node = randomBinaryTree(maxLevel, maxValue);
            boolean one = isFullBinaryTree(node);
            boolean two = isFullBinaryTreeTwo(node);
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
