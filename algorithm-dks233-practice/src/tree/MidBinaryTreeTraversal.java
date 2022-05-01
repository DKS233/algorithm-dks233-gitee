package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode94：二叉树中序遍历
 *
 * @author dks233
 * @create 2022-05-01-20:03
 */
public class MidBinaryTreeTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private final ArrayList<Integer> nodelist = new ArrayList<>();

    // 递归遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return nodelist;
        }
        process(root);
        return nodelist;
    }

    private void process(TreeNode root) {
        if (root == null) {
            return;
        }
        process(root.left);
        nodelist.add(root.val);
        process(root.right);
    }

    // 非递归遍历
    public List<Integer> inorderTraversalTwo(TreeNode root) {
        if (root == null) {
            return nodelist;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            nodelist.add(cur.val);
            cur = cur.right;
        }
        return nodelist;
    }
}
