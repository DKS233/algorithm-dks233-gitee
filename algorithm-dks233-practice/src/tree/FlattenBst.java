package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 052. 展平二叉搜索树
 *
 * @author dks233
 * @create 2022-07-22-16:19
 */
public class FlattenBst {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    List<TreeNode> list = new ArrayList<>();

    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        midOrder(root);
        for (int index = 0; index < list.size() - 1; index++) {
            list.get(index).left = null;
            list.get(index).right = list.get(index + 1);
        }
        list.get(list.size() - 1).right = null;
        list.get(list.size() - 1).left = null;
        return list.get(0);
    }

    public void midOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        midOrder(head.left);
        list.add(head);
        midOrder(head.right);
    }
}
