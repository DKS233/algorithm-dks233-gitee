package tree;

/**
 * leetcode538：把二叉搜索树转换为累加树
 * 给出二叉搜索树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），
 * 使每个节点node的新值等于原树中大于或等于 node.val 的值之和
 *
 * @author dks233
 * @create 2022-05-08-20:47
 */
public class BinarySearchTreeToCumulativeTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 分析：二叉搜索树，中序遍历：从小到大，中序遍历反过来，从大到小
    // 左头右->右头左
    // 按照右头左的方式遍历，每个节点的值变成前边节点值的和
    // O(N)+O(N)
    public static class MethodOne {
        int preSum = 0;

        public TreeNode convertBST(TreeNode head) {
            process(head);
            return head;
        }

        public void process(TreeNode head) {
            if (head == null) {
                return;
            }
            process(head.right);
            preSum += head.val;
            head.val = preSum;
            process(head.left);
        }
    }
}
