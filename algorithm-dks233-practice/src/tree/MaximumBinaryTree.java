package tree;

/**
 * leetcode654. 最大二叉树
 *
 * @author dks233
 * @create 2022-08-20-9:14
 */
public class MaximumBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int index = 0; index < nums.length; index++) {
            maxIndex = nums[index] > nums[maxIndex] ? index : maxIndex;
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        int[] left = new int[maxIndex];
        for (int index = 0; index < maxIndex; index++) {
            left[index] = nums[index];
        }
        root.left = constructMaximumBinaryTree(left);
        int[] right = new int[nums.length - maxIndex - 1];
        for (int index = maxIndex + 1; index < nums.length; index++) {
            right[index - maxIndex - 1] = nums[index];
        }
        root.right = constructMaximumBinaryTree(right);
        return root;
    }
}
