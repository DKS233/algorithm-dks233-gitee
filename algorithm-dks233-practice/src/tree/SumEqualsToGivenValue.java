package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指Offer34：二叉树中和为某一值的路径
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有从根节点到叶子节点路径总和等于给定目标和的路径
 *
 * @author dks233
 * @create 2022-05-08-11:48
 */
public class SumEqualsToGivenValue {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 先序遍历，用singlePath记录当前路径的节点，如果路径节点和等于target，将当前路径加入到paths
    // 图解：二叉树值等于某个值的路径.drawio
    public static class MethodOne {

        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> singlePath = new ArrayList<>();

        public List<List<Integer>> pathSum(TreeNode root, int target) {
            process(root, target);
            return paths;
        }

        public void process(TreeNode head, int target) {
            if (head == null) {
                return;
            }
            // 将当前节点值head.val加入到singlePath
            singlePath.add(head.val);
            // 目标值更新
            target -= head.val;
            // 当前节点为叶节点且路径和等于目标值，将当前路径加入到paths
            if (target == 0 && head.left == null && head.right == null) {
                paths.add(new ArrayList<>(singlePath));
            } else {
                // 递归遍历左右子节点
                process(head.left, target);
                process(head.right, target);
            }
            // 路径恢复：将当前节点从singlePath中删除，当前路径结束，进入下一条路径
            singlePath.remove(singlePath.size() - 1);
        }
    }
}
