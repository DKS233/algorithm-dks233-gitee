package tree;

/**
 * 958. 二叉树的完全性检验
 *
 * @author dks233
 * @create 2022-08-20-15:44
 */
public class CompleteBinaryTree {
    /**
     * 判断二叉树是不是完全二叉树
     * 情况1：左子树是满二叉树，右子树是满二叉树，左子树高度=右子树高度
     * 情况2：左子树是满二叉树，右子树是完全二叉树，左子树高度=右子树高度
     * 情况3：左子树是完全二叉树，右子树是满二叉树，左子树高度=右子树高度+1
     * 需要左子树右子树信息：是否是完全二叉树，高度，是否是满二叉树（子树需满足条件：左子树高度=右子树高度 且 左子树节点数=右子树节点数）
     * 总结：是否完全，高度，是否满
     *
     * @param head 二叉树头节点
     * @return 二叉树是否是完全二叉树
     */
    public static boolean isCompleteTree(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process(head).isComplete;
    }

    public static Info process(TreeNode head) {
        if (head == null) {
            return new Info(true, 0, true);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isComplete = false;
        boolean p1 = isFull;
        boolean p2 = leftInfo.isFull && rightInfo.isComplete && leftInfo.height == rightInfo.height;
        boolean p3 = leftInfo.isComplete && rightInfo.isFull && leftInfo.height == rightInfo.height + 1;
        isComplete = p1 || p2 || p3;
        return new Info(isComplete, height, isFull);
    }

    public static class Info {
        boolean isComplete;
        int height;
        boolean isFull;

        public Info(boolean isComplete, int height, boolean isFull) {
            this.isComplete = isComplete;
            this.height = height;
            this.isFull = isFull;
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
