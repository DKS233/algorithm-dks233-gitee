package tree;

/**
 * leetcode1302. 层数最深叶子节点的和
 *
 * @author dks233
 * @create 2022-08-30-18:57
 */
public class SumOfTheDeepestLeafNodes {
    int sum = 0;

    public int deepestLeavesSum(TreeNode root) {
        // 求最大深度
        int height = getHeight(root);
        process(root, 1, height);
        return sum;
    }

    public void process(TreeNode head, int depth, int height) {
        if (head == null) {
            return;
        }
        if (depth == height) {
            sum += head.val;
        } else {
            process(head.left, depth + 1, height);
            process(head.right, depth + 1, height);
        }
    }

    public int getHeight(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int leftHeight = getHeight(head.left);
        int rightHeight = getHeight(head.right);
        return Math.max(leftHeight, rightHeight) + 1;
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
