package tree;

/**
 * leetcode236. 二叉树的最近公共祖先
 *
 * @author dks233
 * @create 2022-07-09-11:03
 */
@SuppressWarnings("ALL")
public class MyLowestCommonAncestor {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 用二叉树递归套路做
    // 设head为当前遍历的二叉树的头结点，在以head为头的二叉树中出现p和q的最低公共祖先有两种情况
    // 情况1：p和q一个在左子树上，一个在右子树上，或某一个就是head，最低公共祖先是head
    // 情况2：p和q都在左子树或右子树上，最低公共祖先在左子树或右子树上
    // 需要向左子树右子树要的信息：左树右树有没有a，左树右树有没有b，左树右树上的最低公共祖先
    // 需要知道的子树信息：有没有a，有没有b，子树上的最低公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode nodeOne, TreeNode nodeTwo) {
        if (root == null) {
            return null;
        }
        return process(root, nodeOne, nodeTwo).minAncestor;
    }

    public Info process(TreeNode head, TreeNode nodeOne, TreeNode nodeTwo) {
        if (head == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(head.left, nodeOne, nodeTwo);
        Info rightInfo = process(head.right, nodeOne, nodeTwo);
        // 根据左树右树的信息推出以head为头的子树信息
        boolean hasNodeOne = head == nodeOne || leftInfo.hasNodeOne || rightInfo.hasNodeOne;
        boolean hasNodeTwo = head == nodeTwo || leftInfo.hasNodeTwo || rightInfo.hasNodeTwo;
        TreeNode minAncestor = null;
        // 情况1：head是nodeOne和nodeTwo的最低公共祖先
        if (head == nodeOne || head == nodeTwo || leftInfo.hasNodeOne && rightInfo.hasNodeTwo ||
                leftInfo.hasNodeTwo && rightInfo.hasNodeOne) {
            minAncestor = head;
        }
        // 情况2：最低公共祖先在左树上
        else if (leftInfo.minAncestor != null) {
            minAncestor = leftInfo.minAncestor;
        }
        // 情况2：最低公共祖先在右树上
        else if (rightInfo.minAncestor != null) {
            minAncestor = rightInfo.minAncestor;
        }
        return new Info(hasNodeOne, hasNodeTwo, minAncestor);
    }

    public static class Info {
        boolean hasNodeOne;
        boolean hasNodeTwo;
        TreeNode minAncestor;

        public Info(boolean hasNodeOne, boolean hasNodeTwo, TreeNode minAncestor) {
            this.hasNodeOne = hasNodeOne;
            this.hasNodeTwo = hasNodeTwo;
            this.minAncestor = minAncestor;
        }
    }
}
















