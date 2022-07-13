package other;

/**
 * leetcode437. 路径总和 III
 *
 * @author dks233
 * @create 2022-07-13-17:33
 */
public class PathSumThree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // dfs:统计每个节点为头节点的所有路径
    // 时间复杂度：O(N*N)
    public static class MethodOne {
        public int pathSum(TreeNode head, int targetSum) {
            if (head == null) {
                return 0;
            }
            // 路径数=每轮递归以head为头节点的路径数+以head.left为头结点的路径数+以head.right为头节点的路径数
            int p1 = process(head, targetSum);
            int p2 = pathSum(head.left, targetSum);
            int p3 = pathSum(head.right, targetSum);
            return p1 + p2 + p3;
        }

        // 返回以head为头节点，和为rest的路径数目
        public int process(TreeNode head, int rest) {
            if (head == null) {
                return 0;
            }
            int count = 0;
            // 当前节点值等于rest
            if (head.val == rest) {
                count++;
            }
            // 当前节点值不等于rest，去左右子节点继续计算
            count += process(head.left, rest - head.val);
            count += process(head.right, rest - head.val);
            return count;
        }
    }
}
