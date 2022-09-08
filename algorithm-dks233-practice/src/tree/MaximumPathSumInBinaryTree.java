package tree;

/**
 * leetcode124. 二叉树中的最大路径和
 *
 * @author dks233
 * @create 2022-09-08-9:35
 */
@SuppressWarnings("ALL")
public class MaximumPathSumInBinaryTree {
    // 设递归过程中头结点是head
    // 情况1：最大路径和经过head
    // 最大路径和可能性：左子树最大分支路径和+右子树最大分支路径和+head.val 当前头节点值 当前最大分支路径
    // 情况2：最大路径和不经过head
    // 最大路径和可能性：Math.max(左子树最大路径和，右子树最大路径和）
    // 需要知道的信息：左右子树最大分支路径，左右子树最大路径和
    public int maxPathSum(TreeNode root) {
        return process(root).maxPathSum;
    }

    // 左右子树可能为空，这时候不能传0，因为树的节点中可能有负值，所以返回空
    public Info process(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int maxBranchSum = head.val;
        int maxPathSum = head.val;
        if (leftInfo != null && rightInfo == null) {
            maxBranchSum = Math.max(maxBranchSum, head.val + leftInfo.maxBranchSum);
            maxPathSum = Math.max(maxPathSum, head.val + leftInfo.maxBranchSum);
            maxPathSum = Math.max(maxBranchSum, maxPathSum);
            maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSum);
        }
        if (leftInfo == null && rightInfo != null) {
            maxBranchSum = Math.max(maxBranchSum, head.val + rightInfo.maxBranchSum);
            maxPathSum = Math.max(maxPathSum, head.val + rightInfo.maxBranchSum);
            maxPathSum = Math.max(maxBranchSum, maxPathSum);
            maxPathSum = Math.max(maxPathSum, rightInfo.maxPathSum);
        }
        if (leftInfo != null && rightInfo != null) {
            maxBranchSum = Math.max(maxBranchSum, Math.max(head.val + rightInfo.maxBranchSum, head.val + leftInfo.maxBranchSum));
            maxPathSum = Math.max(maxPathSum, head.val + leftInfo.maxBranchSum + rightInfo.maxBranchSum);
            maxPathSum = Math.max(maxPathSum, maxBranchSum);
            maxPathSum = Math.max(maxPathSum, Math.max(leftInfo.maxPathSum, rightInfo.maxPathSum));
        }
        return new Info(maxBranchSum, maxPathSum);
    }

    public static class Info {
        int maxBranchSum;
        int maxPathSum;

        public Info(int maxBranchSum, int maxPathSum) {
            this.maxBranchSum = maxBranchSum;
            this.maxPathSum = maxPathSum;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
