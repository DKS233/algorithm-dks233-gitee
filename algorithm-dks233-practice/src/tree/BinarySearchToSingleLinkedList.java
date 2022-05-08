package tree;

/**
 * leetcode114：二叉树展开为链表
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中right子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同
 *
 * @author dks233
 * @create 2022-05-08-16:56
 */
public class BinarySearchToSingleLinkedList {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 三步走
    // (1)根节点的左子树变成链表
    // (2)根节点的右子树变成链表
    // (3)根据根节点-左子树变成的链表-右子树变成的链表拼接链表
    public static class MethodOne {
        TreeNode cur = null;
        TreeNode pre = null;

        public void flatten(TreeNode head) {
            if (head == null) {
                return;
            }
            // 左子树变成链表
            flatten(head.left);
            // 右子树变成链表
            flatten(head.right);
            // 将左子树变成的链表拼接到head.right
            TreeNode temp = head.right;
            head.right = head.left;
            // 根据题目要求，将左子树置空
            head.left = null;
            // 将右子树变成的链表拼接到左子树变成的链表末尾
            while (head.right != null) {
                head = head.right;
            }
            head.right = temp;
        }
    }

}
