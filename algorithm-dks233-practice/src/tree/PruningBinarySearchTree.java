package tree;

/**
 * leetcode669. 修剪二叉搜索树
 *
 * @author dks233
 * @create 2022-09-10-8:09
 */
@SuppressWarnings("ALL")
public class PruningBinarySearchTree {
    // 头节点位于[low,high] 左右子树根节点哪个不在范围内哪个置null
    // 头节点不在[low,high]
    // 情况1：左子节点在[low,high] 右子节点在[low,high] 右.left=左
    // 情况2：左子节点在[low,high] 右子节点不在[low,high] head=left
    // 情况3：左子节点不在[low,high] 右子节点在[low,high] head=right
    // 情况4：左子节点不在[low,high] 右子节点不在[low,high] head=null
    public TreeNode trimBST(TreeNode root, int low, int high) {
        return process(root, low, high);
    }

    public TreeNode process(TreeNode head, int low, int high) {
        if (head == null) {
            return null;
        }
        TreeNode leftNode = process(head.left, low, high);
        TreeNode rightNode = process(head.right, low, high);
        boolean headIsMid = isMid(head, low, high);
        boolean leftIsMid = isMid(leftNode, low, high);
        boolean rightIsMid = isMid(rightNode, low, high);
        if (headIsMid) {
            head.left = leftNode;
            head.right = rightNode;
        } else {
            if (leftIsMid && rightIsMid) {
                rightNode.left = leftNode;
                head = rightNode;
                head.left = null;
            }
            if (leftIsMid && !rightIsMid) {
                head = leftNode;
            }
            if (!leftIsMid && rightIsMid) {
                head = rightNode;
            }
            if (!leftIsMid && !rightIsMid) {
                head = null;
            }
        }
        return head;
    }

    public boolean isMid(TreeNode node, int low, int high) {
        return node == null ? false : node.val >= low && node.val <= high;
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
