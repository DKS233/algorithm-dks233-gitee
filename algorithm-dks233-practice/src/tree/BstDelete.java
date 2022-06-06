package tree;

/**
 * leetcode450：删除二叉搜索树的节点
 *
 * @author dks233
 * @create 2022-05-31-10:59
 */
public class BstDelete {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static class MethodOne {
        /**
         * 方法1：和方法2区别是处理root既有左子节点又有右子节点的情况
         * 情况1：root.val>key，需要删除的节点在左子树
         * 情况2：root.val<key，需要删除的节点在右子树
         * 情况3：root.val==key，需要删除的节点就是root
         * root没左子树也没右子树，删除root
         * root有左子树，用中序遍历前驱节点代替root，然后删除前驱节点。
         * root有右子树，用中序遍历后继节点代替root，然后删除后继节点。
         * root既有左子树，又有右子树，前驱或后继代替
         * 注：key如果一直找不到匹配的节点，会一直递归下去，直到子树边界，递归结束，不改变二叉树结构
         *
         * @param root 根节点
         * @param key  需要删除节点的值
         * @return 新二叉搜索树的根节点
         */
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) {
                return null;
            }
            // 情况1，去左子树进行删除操作
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            }
            // 情况2，去右子树进行删除操作
            else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else {
                // 情况3，需要删除的就是root
                // 如果root没左子树也没右子树，删除root
                if (root.left == null && root.right == null) {
                    root = null;
                }
                // 如果root有左子树，找到前驱节点，代替root，然后删除前驱节点（无法交换，所以进行值替代）
                else if (root.left != null) {
                    TreeNode predecessor = getPredecessor(root);
                    root.val = predecessor.val;
                    root.left = deleteNode(root.left, predecessor.val);
                }
                // 如果root有右子树，找到后继节点，代替root，然后删除后继节点（无法交换，所以进行值替代）
                else {
                    TreeNode successor = getSuccessor(root);
                    root.val = successor.val;
                    root.right = deleteNode(root.right, successor.val);
                }
            }
            return root;
        }

        /**
         * 方法2：和方法1区别是处理root既有左子节点又有右子节点的情况
         * 情况1：root.val>key，需要删除的节点在左子树
         * 情况2：root.val<key，需要删除的节点在右子树
         * 情况3：root.val==key，需要删除的节点就是root
         * root没左子树也没右子树，删除root
         * root有左子树，没右子树，用左子节点直接代替root。
         * root有右子树，没左子树，用右子节点直接代替root。
         * root既有左子树，又有右子树，前驱或后继代替root，然后删除前驱或后继
         * 注：key如果一直找不到匹配的节点，会一直递归下去，直到子树边界，递归结束，不改变二叉树结构
         *
         * @param root 根节点
         * @param key  需要删除节点的值
         * @return 新二叉搜索树的根节点
         */
        public TreeNode deleteNodeTwo(TreeNode root, int key) {
            if (root == null) {
                return null;
            }
            // 情况1，去左子树进行删除操作
            if (root.val > key) {
                root.left = deleteNodeTwo(root.left, key);
            }
            // 情况2，去右子树进行删除操作
            else if (root.val < key) {
                root.right = deleteNodeTwo(root.right, key);
            } else {
                // 情况3，需要删除的就是root
                // 如果root没左子树也没右子树，删除root
                if (root.left == null && root.right == null) {
                    root = null;
                }
                // 如果root有左子树，没右子树，用左子树代替root
                else if (root.left != null && root.right == null) {
                    root = root.left;
                }
                // 如果root有右子树，没左子树，用右子树代替root
                else if (root.right != null && root.left == null) {
                    root = root.right;
                }
                // 如果root有左子树也有右子树，用前驱或后继节点代替root，然后删除前驱或后继节点
                else {
                    TreeNode successor = getSuccessor(root);
                    root.val = successor.val;
                    root.right = deleteNodeTwo(root.right, successor.val);
                }
            }
            return root;
        }

        // 返回前驱节点
        public TreeNode getPredecessor(TreeNode root) {
            TreeNode left = root.left;
            while (left.right != null) {
                left = left.right;
            }
            return left;
        }

        // 返回后继节点
        public TreeNode getSuccessor(TreeNode root) {
            TreeNode right = root.right;
            while (right.left != null) {
                right = right.left;
            }
            return right;
        }
    }
}
