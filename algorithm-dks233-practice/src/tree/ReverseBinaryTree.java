package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指Offer27：二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像（反转二叉树）
 * 例子：按层遍历的满二叉树：4271369，反转后为4729631
 * 时间复杂度：O(N)
 *
 * @author dks233
 * @create 2022-05-07-15:54
 */
public class ReverseBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 递归的方式反转二叉树
    public static class ReverseOne {
        public TreeNode mirrorTree(TreeNode head) {
            if (head == null) {
                return null;
            }
            // 交换head为头节点的左右子树
            TreeNode temp = head.left;
            head.left = head.right;
            head.right = temp;
            // 反转左右子树
            mirrorTree(head.left);
            mirrorTree(head.right);
            return head;
        }
    }

    // 前序遍历的方式反转二叉树
    // 遍历到一个头节点，交换其左右节点
    public static class ReverseTwo {
        public TreeNode mirrorTree(TreeNode head) {
            if (head == null) {
                return null;
            }
            process(head);
            return head;
        }

        private void process(TreeNode head) {
            if (head == null) {
                return;
            }
            TreeNode temp = head.left;
            head.left = head.right;
            head.right = temp;
            process(head.left);
            process(head.right);
        }
    }

    // 后序遍历的方式反转二叉树
    // 遍历到头节点，交换左右节点
    public static class ReverseThree {
        public TreeNode mirrorTree(TreeNode head) {
            if (head == null) {
                return null;
            }
            process(head);
            return head;
        }

        private void process(TreeNode head) {
            if (head == null) {
                return;
            }
            process(head.left);
            process(head.right);
            TreeNode temp = head.left;
            head.left = head.right;
            head.right = temp;
        }
    }

    // 中序遍历方式反转二叉树
    // 遍历到头节点，交换左右子树
    public static class ReverseFour {
        public TreeNode mirrorTree(TreeNode head) {
            if (head == null) {
                return null;
            }
            process(head);
            return head;
        }

        private void process(TreeNode head) {
            if (head == null) {
                return;
            }
            process(head.left);
            TreeNode temp = head.left;
            head.left = head.right;
            head.right = temp;
            process(head.left); // 此时左右子树已经发生交换
        }
    }

    // 层序遍历方式反转二叉树
    // 遍历到头节点，交换左右子树
    public static class ReverseFive {
        public TreeNode mirrorTree(TreeNode head) {
            if (head == null) {
                return null;
            }
            TreeNode cur = head;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(cur);
            while (!queue.isEmpty()) {
                cur = queue.poll();
                TreeNode temp = cur.left;
                cur.left = cur.right;
                cur.right = temp;
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            return head;
        }
    }
}
