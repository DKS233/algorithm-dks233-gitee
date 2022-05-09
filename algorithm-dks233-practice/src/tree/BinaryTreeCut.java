package tree;

/**
 * 剑指offer专项突击版：剑指OfferII047：二叉树剪枝
 * 给定一个二叉树根节点root，树的每个节点的值要么是0，要么是 1。请剪除该二叉树中所有节点的值为0的子树
 * 节点node的子树为node本身，以及所有node的后代
 *
 * @author dks233
 * @create 2022-05-09-9:33
 */
public class BinaryTreeCut {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 分析：剪除子树的条件
    // 从叶子节点往上递推：满足条件的子树剪掉：左子树为空，右子树为空，根节点值为0
    // 后序遍历
    public static class MethodOne {
        public TreeNode pruneTree(TreeNode head) {
            if (head == null) {
                return null;
            }
            head.left = pruneTree(head.left);
            head.right = pruneTree(head.right);
            if (head.val == 0 && head.left == null && head.right == null) {
                head = null;
            }
            return head;
        }
    }
}
