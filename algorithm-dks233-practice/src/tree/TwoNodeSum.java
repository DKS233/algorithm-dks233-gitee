package tree;

import java.util.HashSet;

/**
 * 剑指offer专项突击版：剑指 Offer II 056. 二叉搜索树中两个节点之和
 *
 * @author dks233
 * @create 2022-07-22-22:56
 */
public class TwoNodeSum {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 注：避免出现k=head.val+head.val的情况
    // 解决：在head.val添加前先判断set是否含有k-head.val
    public static class MethodOne {
        HashSet<Integer> set = new HashSet<>();
        boolean ans = false;

        public boolean findTarget(TreeNode root, int k) {
            midOrder(root, k);
            return ans;
        }

        public void midOrder(TreeNode head, int k) {
            if (head == null) {
                return;
            }
            midOrder(head.left, k);
            if (set.contains(k - head.val)) {
                ans = true;
                return;
            }
            set.add(head.val);
            midOrder(head.right, k);
        }
    }
}
