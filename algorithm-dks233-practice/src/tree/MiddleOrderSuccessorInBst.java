package tree;

/**
 * 剑指offer专项突击版：剑指 Offer II 053. 二叉搜索树中的中序后继
 *
 * @author dks233
 * @create 2022-07-22-16:40
 */
public class MiddleOrderSuccessorInBst {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur = root;
        TreeNode ans = null;
        while (cur != null) {
            if (cur.val > p.val) {
                ans = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return ans;
    }
}
