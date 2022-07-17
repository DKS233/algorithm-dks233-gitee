package double_week82;

/**
 * leetcode2331. 计算布尔二叉树的值
 *
 * @author dks233
 * @create 2022-07-09-23:10
 */
public class One {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean evaluateTree(TreeNode root) {
        process(root);
        return root.val == 1;
    }

    // 0->false
    // 1->true
    // 2->or
    // 3->and
    public void process(TreeNode head) {
        if (head == null) {
            return;
        }
        process(head.left);
        process(head.right);
        // 左右树为空，head不变
        if (head.left != null && head.right != null) {
            // true+false+or
            if (head.left.val == 1 && head.right.val == 0 && head.val == 2) {
                head.val = 1;
            }
            // true+false+and
            else if (head.left.val == 1 && head.right.val == 0 && head.val == 3) {
                head.val = 0;
            }
            // false+true+or
            else if (head.left.val == 0 && head.right.val == 1 && head.val == 2) {
                head.val = 1;
            }
            // false+true+and
            else if (head.left.val == 0 && head.right.val == 1 && head.val == 3) {
                head.val = 0;
            } else if (head.left.val == 0 && head.right.val == 0) {
                head.val = 0;
            } else if (head.left.val == 1 && head.right.val == 1) {
                head.val = 1;
            }
        }
    }

}
