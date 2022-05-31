package tree;

/**
 * leetcode701：向二叉搜索树中插入元素
 *
 * @author dks233
 * @create 2022-05-31-10:20
 */
@SuppressWarnings("ALL")
public class BstInsert {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class MethodOne {
        // 找到父节点，然后插入到左子树或者右子树
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            TreeNode parent = root;
            while (parent != null) {
                // 父节点的值比val大，往左树上插
                if (parent.val > val) {
                    if (parent.left == null) {
                        parent.left = new TreeNode(val);
                        break;
                    } else {
                        parent = parent.left;
                    }
                }
                // 父节点的值比val小，往右树上插
                else {
                    if (parent.right == null) {
                        parent.right = new TreeNode(val);
                        break;
                    } else {
                        parent = parent.right;
                    }
                }
            }
            return root;
        }
    }

    public static class MethodTwo {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            if (root.val > val) {
                root.left = insertIntoBST(root.left, val);
            } else {
                root.right = insertIntoBST(root.right, val);
            }
            return root;
        }
    }
}
