package tree;

/**
 * 剑指offer55：二叉树的深度(高度)
 * 分析：用二叉树递归套路做
 * 需要的信息：子树的高度
 *
 * @author dks233
 * @create 2022-05-06-15:57
 */
public class DepthOfBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int maxDepth(TreeNode root) {
        return process(root).depth;
    }

    public Info process(TreeNode head) {
        if (head == null) {
            return new Info(0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int depth = Math.max(leftInfo.depth, rightInfo.depth) + 1;
        return new Info(depth);
    }

    public static class Info {
        int depth;

        public Info(int depth) {
            this.depth = depth;
        }
    }

    public static class Comparator {
        public int maxDepth(TreeNode root) {
            return process(root);
        }

        public int process(TreeNode head) {
            if (head == null) {
                return 0;
            }
            int leftHeight = process(head.left);
            int rightHeight = process(head.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

}
