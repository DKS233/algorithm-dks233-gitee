package tree;

/**
 * leetcode111：二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 * 分析：用二叉树递归套路做
 * 需要的信息：以head为头节点的二叉树
 * 左树右树如果都非空：最小深度=min(左树的最小深度，右树的最小深度)+1
 * 左树为空：最小深度=右树最小深度
 * 右树为空：最小深度=左树最小深度
 * 需要返回的子树信息：子树的最小深度
 *
 * @author dks233
 * @create 2022-05-11-14:40
 */
public class MinimumDepthOfBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class MethodOne {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return process(root).minDepth;
        }

        public Info process(TreeNode head) {
            if (head == null) {
                return new Info(0);
            }
            Info leftInfo = process(head.left);
            Info rightInfo = process(head.right);
            int minDepth = 0;
            if (leftInfo.minDepth == 0 && rightInfo.minDepth == 0) {
                minDepth = 1;
            } else if (leftInfo.minDepth == 0) {
                minDepth = rightInfo.minDepth + 1;
            } else if (rightInfo.minDepth == 0) {
                minDepth = leftInfo.minDepth + 1;
            } else {
                minDepth = Math.min(leftInfo.minDepth, rightInfo.minDepth) + 1;
            }
            return new Info(minDepth);
        }

        public static class Info {
            int minDepth;

            public Info(int minDepth) {
                this.minDepth = minDepth;
            }
        }
    }

    public static class MethodTwo {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return process(root);
        }

        public int process(TreeNode head) {
            if (head == null) {
                return 0;
            }
            int left = process(head.left);
            int right = process(head.right);
            int minDepth = 0;
            if (left == 0 && right == 0) {
                minDepth = 1;
            } else if (left == 0) {
                minDepth = right + 1;
            } else if (right == 0) {
                minDepth = left + 1;
            } else {
                minDepth = Math.min(left, right) + 1;
            }
            return minDepth;
        }
    }
}
