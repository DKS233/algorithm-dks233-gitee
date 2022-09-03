package tree;

/**
 * leetcode687. 最长同值路径
 *
 * @author dks233
 * @create 2022-09-03-18:45
 */
@SuppressWarnings("ALL")
public class LongestEquivalentPath {
    // 理解题目：求两点之间的最大路径长度，要求：路径上的数都必须相同，路径不能走分支，可以一条路走到黑
    // 二叉树递归套路
    // 设head为递归过程中的头节点
    // 如果最长路径经过head
    // 情况1：最长路径=左子树符合条件的最长分支（包括左子树头节点）+1  不一定是左子树最长路径，因为左子树最长路径可能拐弯
    // 情况2：最长路径=右子树符合条件的最长分支（包括右子树头节点）+1 不一定是右子树最长路径，因为右子树最长路径可能拐弯
    // 情况3：最长路径=左子树符合条件的最长分支(包括左子树头节点）+右子树最长路径的最长分支（包括右子树头节点）+2
    // 如果最长路径不经过head，最大路径=Math.max(左数最长路径，右树最长路径）
    // 需要知道的子树信息：根节点值，最长路径，左右子树的最长分支（包括子树头节点）
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxPath;
    }

    public Info process(TreeNode head) {
        if (head == null) {
            return new Info(-1001, 0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int headValue = head.val;
        int maxBranch = 0;
        int maxPath = 0;
        // 最长路径经过head
        int p1 = 0;
        if (head.val == leftInfo.headValue && head.val != rightInfo.headValue) {
            p1 = 1 + leftInfo.maxBranch;
            maxBranch = 1 + leftInfo.maxBranch;
        } else if (head.val == rightInfo.headValue && head.val != leftInfo.headValue) {
            p1 = 1 + rightInfo.maxBranch;
            maxBranch = 1 + rightInfo.maxBranch;
        } else if (head.val == leftInfo.headValue && head.val == rightInfo.headValue) {
            p1 = 2 + leftInfo.maxBranch + rightInfo.maxBranch;
            maxBranch = Math.max(leftInfo.maxBranch, rightInfo.maxBranch) + 1;
        }
        // 最长路径不经过head
        int p2 = Math.max(leftInfo.maxPath, rightInfo.maxPath);
        maxPath = Math.max(p1, p2);
        return new Info(headValue, maxPath, maxBranch);
    }

    public static class Info {
        int headValue;
        int maxPath;
        int maxBranch;

        public Info(int headValue, int maxPath, int maxBranch) {
            this.headValue = headValue;
            this.maxPath = maxPath;
            this.maxBranch = maxBranch;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
