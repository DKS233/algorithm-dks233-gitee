package tree;

/**
 * leetcode108. 将有序数组转换为二叉搜索树
 *
 * @author dks233
 * @create 2022-08-25-10:29
 */
@SuppressWarnings("ALL")
public class SortToBst {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 不断寻找中位数，将中位数当成头节点
    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    public TreeNode process(int[] nums, int left, int right) {
        if (left == right) {
            return new TreeNode(nums[left]);
        }
        if (left > right) {
            return null;
        }
        int midIndex = left + ((right - left) >> 1);
        int midNum = nums[midIndex];
        TreeNode head = new TreeNode(midNum);
        head.left = process(nums, left, midIndex - 1);
        head.right = process(nums, midIndex + 1, right);
        return head;
    }
}
