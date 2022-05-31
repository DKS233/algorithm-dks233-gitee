package tree;

/**
 * leetcode700:查找二叉搜索树的元素
 *
 * @author dks233
 * @create 2022-05-31-10:12
 */
@SuppressWarnings("ALL")
public class BstSearch {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class MethodOne {
        public TreeNode searchBST(TreeNode root, int val) {
            TreeNode node = root;
            while (true) {
                if (node == null) {
                    break;
                }
                if (node.val == val) {
                    return node;
                } else if (node.val < val) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
            return null;
        }
    }

    public static class MethodTwo {
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null || root.val == val) {
                return root;
            }
            return root.val < val ? searchBST(root.right, val) : searchBST(root.left, val);
        }
    }
}
