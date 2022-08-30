package tree;

/**
 * leetcode654. 最大二叉树
 * leetcode998. 最大二叉树 II
 *
 * @author dks233
 * @create 2022-08-20-9:14
 */
@SuppressWarnings("ALL")
public class MaximumBinaryTree {
    public static class ProblemOne {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }
            return process(nums, 0, nums.length - 1);
        }

        public TreeNode process(int[] nums, int left, int right) {
            if (left > right) {
                return null;
            }
            if (left == right) {
                return new TreeNode(nums[left]);
            }
            int maxIndex = left;
            for (int i = left; i <= right; i++) {
                maxIndex = nums[i] > nums[maxIndex] ? i : maxIndex;
            }
            TreeNode head = new TreeNode(nums[maxIndex]);
            head.left = process(nums, left, maxIndex - 1);
            head.right = process(nums, maxIndex + 1, right);
            return head;
        }
    }

    public static class ProblemTwo {
        public TreeNode insertIntoMaxTree(TreeNode root, int val) {
            if (root == null) {
                return null;
            }
            return process(root, val);
        }

        public TreeNode process(TreeNode head, int val) {
            if (head == null) {
                return new TreeNode(val);
            }
            // 根节点小于val，替换根节点
            if (head.val < val) {
                TreeNode newHead = new TreeNode(val);
                newHead.left = head;
                return newHead;
            }
            // 根节点大于val，去右子树找（题目要求是在末尾加val节点）
            head.right = process(head.right, val);
            return head;
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
