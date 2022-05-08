package tree;

/**
 * leetcode617：合并二叉树
 * 给你两棵二叉树： root1 和 root2 。
 * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。
 * 你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
 * 返回合并后的二叉树。
 * 注意: 合并过程必须从两个树的根节点开始。
 *
 * @author dks233
 * @create 2022-05-08-19:54
 */
public class MergeBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {

        }

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 如果两个二叉树的对应节点都为空，则合并后的二叉树的对应节点也为空；
    // 如果两个二叉树的对应节点只有一个为空，则合并后的二叉树的对应节点为其中的非空节点；
    // 如果两个二叉树的对应节点都不为空，则合并后的二叉树的对应节点的值为两个二叉树的对应节点的值之和
    // 时间复杂度：O(min(m,n)) 两个二叉树对应节点同时不为空时才会进行合并操作
    // 空间复杂度：O(min(m,n)) 递归调用的层数不超过较小二叉树的最大高度，最坏情况下，二叉树高度等于节点数
    public static class Solution {
        public TreeNode mergeTrees(TreeNode headOne, TreeNode headTwo) {
            if (headOne == null && headTwo == null) {
                return null;
            }
            if (headOne == null) {
                return headTwo;
            }
            if (headTwo == null) {
                return headOne;
            }
            TreeNode newHead = new TreeNode(headOne.val + headTwo.val);
            newHead.left = mergeTrees(headOne.left, headTwo.left);
            newHead.right = mergeTrees(headOne.right, headTwo.right);
            return newHead;
        }
    }

}
