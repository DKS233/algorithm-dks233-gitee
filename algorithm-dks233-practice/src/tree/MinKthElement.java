package tree;

import sun.reflect.generics.tree.Tree;

/**
 * leetcode230. 二叉搜索树中第K小的元素
 *
 * @author dks233
 * @create 2022-07-09-10:16
 */
public class MinKthElement {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    int count = 0;
    TreeNode result = null;

    public int kthSmallest(TreeNode root, int k) {
        process(root, k);
        return result == null ? -1 : result.val;
    }

    public void process(TreeNode head, int k) {
        if (head == null) {
            return;
        }
        process(head.left, k);
        if (++count == k) {
            result = head;
            return;
        }
        process(head.right, k);
    }
}
