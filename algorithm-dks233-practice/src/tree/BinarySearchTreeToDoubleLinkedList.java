package tree;

/**
 * 剑指Offer36：二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向
 * 左指针指向前驱节点
 * 右指针指向后继节点
 *
 * @author dks233
 * @create 2022-05-08-15:46
 */
public class BinarySearchTreeToDoubleLinkedList {
    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public static class MethodOne {
        Node newHead = null; // 双向链表头节点
        Node pre = null;
        Node cur = null;

        /**
         * 中序遍历过程中，构建每个节点的cur和pre，中序遍历完成后，构建头节点和尾节点的cur和pre
         * 画图模板：二叉搜索树转双向循环链表.drawio
         *
         * @param head 二叉树头节点
         * @return 双向链表头节点
         */
        public Node treeToDoublyList(Node head) {
            if (head == null) {
                return null;
            }
            // 递归结束，pre和cur都指向尾节点，双向链表构建完毕
            process(head);
            // 构建双向循环链表
            newHead.left = cur;
            cur.right = newHead;
            return newHead;
        }

        public void process(Node head) {
            if (head == null) {
                return;
            }
            process(head.left);
            // 中序遍历过程中，每遍历到一个节点，构建pre和cur
            cur = head;
            // pre==null说明当前节点为双向链表头节点
            if (pre == null) {
                newHead = cur;
            }
            // pre!=null时说明当前节点不是双向链表头节点，连接cur和pre
            else {
                cur.left = pre;
                pre.right = cur;
            }
            // 更新pre=cur，后继节点的pre为cur
            pre = cur;
            process(head.right);
        }
    }
}
