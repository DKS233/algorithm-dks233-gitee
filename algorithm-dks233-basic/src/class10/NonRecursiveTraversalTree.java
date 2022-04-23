package class10;

import class10.TraversalTree.Node;

import java.util.Stack;

/**
 * 非递归遍历二叉树
 * 先序，中序，后序
 *
 * @author dks233
 * @create 2022-04-23-10:54
 */
public class NonRecursiveTraversalTree {

    // 先序遍历：头左右
    // 准备栈，头节点压入栈，弹出节点的时候打印
    // 如果有右孩子，就先压右孩子。如果有左孩子再压左孩子
    // 弹出顺序为：头 左 左的左孩子 左的右孩子 右 右的左孩子 右的右孩子
    public static void pre(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            // 1.根节点入栈
            stack.push(head);
            while (!stack.isEmpty()) {
                // 2.弹出当前头节点，打印
                head = stack.pop();
                System.out.print(head.data + "  ");
                // 3.根节点如果有右孩子，右孩子入栈
                if (head.right != null) {
                    stack.push(head.right);
                }
                // 4.根节点如果有左孩子，左孩子入栈
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }

    public static void preComparator(Node head) {
        TraversalTree.preOrder(head);
    }

    // 中序遍历 左头右
    public static void mid(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            // stack为空，但是head！=null说明二叉树没遍历完 eg:根节点弹出后，栈为空，但需要继续遍历根节点的右子树
            while (!stack.isEmpty() || head != null) {
                // 当前节点的左边界入栈
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    // head==null时，说明当前头节点左边界已到头
                    // 按照左头右  head位置为最底层的头节点（左为null）
                    head = stack.pop();
                    System.out.print(head.data + "  ");
                    // 左子节点打印，定位到当前头节点的右子节点位置
                    head = head.right;
                }
            }
        }
    }

    public static void midComparator(Node head) {
        TraversalTree.middleOrder(head);
    }

    // 后序遍历：左右头
    // 注：前序是头左右，将前序变为头右左，然后弹出顺序和头右左相反
    public static void post(Node head) {
        if (head != null) {
            Stack<Node> stackOne = new Stack<>();
            Stack<Node> stackTwo = new Stack<>();
            stackOne.push(head);
            while (!stackOne.isEmpty()) {
                head = stackOne.pop();
                stackTwo.push(head);
                if (head.left != null) {
                    stackOne.push(head.left);
                }
                if (head.right != null) {
                    stackOne.push(head.right);
                }
            }
            while (!stackTwo.isEmpty()) {
                System.out.print(stackTwo.pop().data + "  ");
            }
        }
    }

    public static void postComparator(Node head) {
        TraversalTree.postOrder(head);
    }

    public static void main(String[] args) {
        Node zero = new Node(0);
        zero.left = new Node(1);
        zero.right = new Node(2);
        zero.left.left = new Node(3);
        zero.left.right = new Node(4);
        zero.right.left = new Node(5);
        zero.right.right = new Node(6);
        preComparator(zero);
        System.out.println();
        pre(zero);
        System.out.println();
        midComparator(zero);
        System.out.println();
        mid(zero);
        System.out.println();
        postComparator(zero);
        System.out.println();
        post(zero);
        System.out.println();
    }
}
