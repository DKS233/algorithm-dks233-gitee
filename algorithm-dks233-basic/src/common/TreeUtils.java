package common;

/**
 * @author dks233
 * @create 2022-04-25-15:32
 */
public class TreeUtils {
    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 根据最大层级和最大值随机生成二叉树
     *
     * @param maxValue 最大值
     * @param maxLevel 最大层级
     * @return 随机二叉树
     */
    public static Node randomBinaryTree(int maxValue, int maxLevel) {
        return getBinaryTree(1, maxLevel, maxValue);
    }

    /**
     * 从第一层开始生成二叉树
     *
     * @param level    层数，初始值为1
     * @param maxLevel 最大层数
     * @param maxValue 最大值
     * @return 随机二叉树
     */
    private static Node getBinaryTree(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node(ArrayUtils.randomNumber(maxValue));
        head.left = getBinaryTree(level + 1, maxLevel, maxValue);
        head.right = getBinaryTree(level + 1, maxLevel, maxValue);
        return head;
    }

    /**
     * 根据头节点判断两个二叉树节点值是否相等
     *
     * @param headOne 头节点1
     * @param headTwo 头节点2
     * @return 两个二叉树对应节点值是否相等
     */
    public static boolean binaryTreeEquals(Node headOne, Node headTwo) {
        if (headOne == null && headTwo == null) {
            return true;
        }
        if (headOne != null && headTwo == null) {
            return false;
        }
        // headOne == null && headTwo != null
        if (headOne == null) {
            return false;
        }
        if (headOne.data != headTwo.data) {
            return false;
        }
        return binaryTreeEquals(headOne.left, headTwo.left) && binaryTreeEquals(headOne.right, headTwo.right);
    }

    /**
     * 打印二叉树（逆时针旋转90度看）
     *
     * @param head 根节点
     */
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.data + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuilder buf = new StringBuilder("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }
}
