package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer专项突击版:剑指 Offer II 055. 二叉搜索树迭代器
 *
 * @author dks233
 * @create 2022-07-23-21:42
 */
@SuppressWarnings("ALL")
public class BstIterator {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class BSTIterator {
        LinkedList<TreeNode> queue;

        public BSTIterator(TreeNode root) {
            queue = new LinkedList<>();
            midOrder(root);
        }

        public void midOrder(TreeNode head) {
            if (head == null) {
                return;
            }
            midOrder(head.left);
            queue.addLast(head);
            midOrder(head.right);
        }

        public int next() {
            if (queue.isEmpty()) {
                return -1;
            } else {
                return queue.pollFirst().val;
            }
        }

        public boolean hasNext() {
            if (queue.isEmpty()) {
                return false;
            } else {
                return true;
            }
        }
    }
}
