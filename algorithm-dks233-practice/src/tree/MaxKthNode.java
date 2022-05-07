package tree;

import java.util.Stack;

/**
 * 剑指Offer54：二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点的值
 *
 * @author dks233
 * @create 2022-05-07-21:18
 */
public class MaxKthNode {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 二叉搜索树
    // 左树二叉搜索树，右树是二叉搜索树，左树最大值比head.val小，右树最大值比head.val大
    // 每棵子树大小顺序：右节点大于头节点大于左节点，所以根据右头左的顺序遍历二叉树
    // 时间复杂度：O(N)
    // 空间复杂度：O(N)
    public static class MethodOne {
        public int kthLargest(TreeNode root, int k) {
            if (root == null) {
                return -1;
            }
            // 中序遍历入栈顺序：左头右，出栈顺序：右头左，也就是从大到小顺序
            Stack<Integer> stack = new Stack<>();
            process(root, stack);
            int result = Integer.MIN_VALUE;
            for (int i = 0; i < k; i++) {
                result = stack.pop();
            }
            return result;
        }

        public void process(TreeNode head, Stack<Integer> stack) {
            if (head == null) {
                return;
            }
            process(head.left, stack);
            stack.push(head.val);
            process(head.right, stack);
        }
    }
}
