package dp;

import java.util.HashMap;

/**
 * leetcode337. 打家劫舍 III
 * TODO:缺少严格动态规划解法，只做了暴力递归和记忆化搜索
 *
 * @author dks233
 * @create 2022-07-14-11:24
 */
@SuppressWarnings("ALL")
public class RaidHomesAndPlunderHousesThree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 暴力递归
    public static class MethodOne {
        public int rob(TreeNode root) {
            return process(root);
        }

        public int process(TreeNode head) {
            if (head == null) {
                return 0;
            }
            // 头抢了，子树不能抢
            int p1 = head.val;
            p1 += head.left == null ? 0 : process(head.left.left) + process(head.left.right);
            p1 += head.right == null ? 0 : process(head.right.left) + process(head.right.right);
            // 头不抢，左右子树都可抢
            int p2 = process(head.left) + process(head.right);
            return Math.max(p1, p2);
        }
    }

    // 记忆化搜索
    public static class MethodTwo {
        HashMap<TreeNode, Integer> hashMap = new HashMap<>();

        public int rob(TreeNode root) {
            return process(root);
        }

        public int process(TreeNode head) {
            if (head == null) {
                return 0;
            }
            if (hashMap.containsKey(head)) {
                return hashMap.get(head);
            }
            // 头抢了，子树不能抢
            int p1 = head.val;
            p1 += head.left == null ? 0 : process(head.left.left) + process(head.left.right);
            p1 += head.right == null ? 0 : process(head.right.left) + process(head.right.right);
            // 头不抢，左右子树都可抢
            int p2 = process(head.left) + process(head.right);
            hashMap.put(head, Math.max(p1, p2));
            return Math.max(p1, p2);
        }
    }
}
