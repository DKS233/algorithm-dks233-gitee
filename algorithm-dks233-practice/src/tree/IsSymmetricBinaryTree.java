package tree;

/**
 * 剑指 Offer28：对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的
 * 时间复杂度：O(N)
 * 空间复杂度：O(N) 二叉树退化为链表，系统使用O(N)大小的栈空间
 *
 * @author dks233
 * @create 2022-05-07-20:19
 */
public class IsSymmetricBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class MethodOne {
        public boolean isSymmetric(TreeNode head) {
            if (head == null) {
                return true;
            }
            return process(head.left, head.right);
        }

        // 如果一个树的左右子树镜像对称，那么这个树是对称的
        // 镜像对称的条件：左右子树根节点值相等，左树的左子树和右树的右子树镜像对称，左树的右子树和右树的左子树镜像对称
        private boolean process(TreeNode leftHead, TreeNode rightHead) {
            if (leftHead == null && rightHead == null) {
                return true;
            }
            if (leftHead == null || rightHead == null) {
                return false;
            }
            // 左子树头节点的值=右子树头节点的值
            // leftHead的左子树与rightHead的右子树是否对称
            // leftHead的右子树与rightHead的左子树是否对称
            // 以上三个条件同时满足，左右子树才对称，整棵二叉树才对称
            return leftHead.val == rightHead.val &&
                    process(leftHead.right, rightHead.left) &&
                    process(leftHead.left, rightHead.right);
        }
    }
}
