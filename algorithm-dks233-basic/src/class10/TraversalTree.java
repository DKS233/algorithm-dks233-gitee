package class10;

/**
 * 递归遍历二叉树
 * 遍历打印树：先序，中序，后序
 *
 * @author dks233
 * @create 2022-04-20-20:40
 */
public class TraversalTree {
    public static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // 递归序
    public static void recursion(Node head) {
        if (head == null) {
            return;
        }
        recursion(head.left);
        recursion(head.right);
    }

    // 先序：头 左 右
    public static void preOrder(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.data + "  ");
        preOrder(head.left);
        preOrder(head.right);
    }

    // 中序：左 头 右
    public static void middleOrder(Node head) {
        if (head == null) {
            return;
        }
        middleOrder(head.left);
        System.out.print(head.data + "  ");
        middleOrder(head.right);
    }

    // 后序：左 右 头
    public static void postOrder(Node head) {
        if (head == null) {
            return;
        }
        postOrder(head.left);
        postOrder(head.right);
        System.out.print(head.data + "  ");
    }

    public static void main(String[] args) {
        Node zero = new Node(0);
        zero.left = new Node(1);
        zero.right = new Node(2);
        zero.left.left = new Node(3);
        zero.left.right = new Node(4);
        zero.right.left = new Node(5);
        zero.right.right = new Node(6);
        // 先序：头左右 0134256
        System.out.print("先序--->");
        preOrder(zero);
        System.out.println();
        // 中序：左头右 3140526
        System.out.print("中序--->");
        middleOrder(zero);
        System.out.println();
        // 后序：左右头 3415620
        System.out.print("后序--->");
        postOrder(zero);
        System.out.println();
    }
}
