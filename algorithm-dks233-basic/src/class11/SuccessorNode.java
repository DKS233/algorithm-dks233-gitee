package class11;

import java.util.ArrayList;

/**
 * 找到中序遍历某个节点的后继节点
 *
 * @author dks233
 * @create 2022-04-27-22:04
 */
public class SuccessorNode {
    public static class Node {
        int data;
        Node left;
        Node right;
        Node parent;

        public Node(int data) {
            this.data = data;
        }
    }

    public static ArrayList<Node> nodeList = new ArrayList<>();

    // 方法1，传统中序遍历
    public static Node getSuccessorNodeOne(Node head, Node node) {
        if (head == null || node == null) {
            return null;
        }
        midOrder(head);
        if (nodeList.indexOf(node) == nodeList.size() - 1) {
            return null;
        }
        return nodeList.get(nodeList.indexOf(node) + 1);
    }

    private static void midOrder(Node head) {
        if (head == null) {
            return;
        }
        midOrder(head.left);
        nodeList.add(head);
        midOrder(head.right);
    }


    // 方法2
    // O(K)+O(1) K:到后继节点的距离
    public static Node getSuccessorNodeTwo(Node node) {
        if (node == null) {
            return null;
        }
        // 判断node节点是否有右子树，如果有，后继节点是右子树的最左节点
        if (node.right != null) {
            return getLeftestNode(node.right);
        }
        // 无右子树
        else {
            Node parent = node.parent;
            // 判断当前节点是父节点的右孩子节点还是左孩子节点，或者当前节点是无右孩子节点的根节点
            // 如果是左孩子节点，跳出循环，parent是后继节点
            // 如果是根节点，跳出循环，返回null
            // 如果是右孩子节点，进入循环，一直往上走，直到走到根节点或走到当前节点是父节点的左孩子节点，跳出循环
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private static Node getLeftestNode(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.data + " next: " + getSuccessorNodeOne(head, test).data + "--->" + getSuccessorNodeTwo(test).data);
        test = head.left.left.right;
        System.out.println(test.data + " next: " + getSuccessorNodeOne(head, test).data + "--->" + getSuccessorNodeTwo(test).data);
        test = head.left;
        System.out.println(test.data + " next: " + getSuccessorNodeOne(head, test).data + "--->" + getSuccessorNodeTwo(test).data);
        test = head.left.right;
        System.out.println(test.data + " next: " + getSuccessorNodeOne(head, test).data + "--->" + getSuccessorNodeTwo(test).data);
        test = head.left.right.right;
        System.out.println(test.data + " next: " + getSuccessorNodeOne(head, test).data + "--->" + getSuccessorNodeTwo(test).data);
        test = head;
        System.out.println(test.data + " next: " + getSuccessorNodeOne(head, test).data + "--->" + getSuccessorNodeTwo(test).data);
        test = head.right.left.left;
        System.out.println(test.data + " next: " + getSuccessorNodeOne(head, test).data + "--->" + getSuccessorNodeTwo(test).data);
        test = head.right.left;
        System.out.println(test.data + " next: " + getSuccessorNodeOne(head, test).data + "--->" + getSuccessorNodeTwo(test).data);
        test = head.right;
        System.out.println(test.data + " next: " + getSuccessorNodeOne(head, test).data + "--->" + getSuccessorNodeTwo(test).data);
        test = head.right.right; // 10's next is null
        System.out.println(test.data + " next: " + getSuccessorNodeOne(head, test).data + "--->" + getSuccessorNodeTwo(test).data);
    }

}
