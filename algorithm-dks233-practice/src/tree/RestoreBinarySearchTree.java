package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode99. 恢复二叉搜索树
 * TODO：空间复杂度没达到要求
 *
 * @author dks233
 * @create 2022-06-21-21:53
 */
public class RestoreBinarySearchTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // O(N)+O(N)
    public static class MethodOne {
        List<TreeNode> list = new ArrayList<>();

        // 中序遍历，找错误的节点，然后值进行交换
        // 出现错误节点：左边那个数大，右边那个数小
        // 特征：left.val > (left+1).val  right.val < (right-1).val
        public void recoverTree(TreeNode root) {
            midOrder(root);
            TreeNode leftNode = null;
            TreeNode rightNode = null;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).val > list.get(i + 1).val) {
                    rightNode = list.get(i + 1);
                    if (leftNode == null) {
                        leftNode = list.get(i);
                    }
                }
            }
            if (leftNode != null && rightNode != null) {
                int temp = leftNode.val;
                leftNode.val = rightNode.val;
                rightNode.val = temp;
            }
        }


        public void midOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            midOrder(root.left);
            list.add(root);
            midOrder(root.right);
        }
    }
}
