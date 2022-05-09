package tree;

import java.util.ArrayList;

/**
 * 剑指offer专项突击版：剑指OfferII049：从根节点到叶节点的路径数字之和
 * 给定一个二叉树的根节点 root ，树中每个节点都存放有一个0到9之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字123
 *
 * @author dks233
 * @create 2022-05-09-10:23
 */
public class PathSumFromRootToLeaf {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 方法1：记录所有路径，然后计算求和
    // 前序遍历
    // 最坏情况：满二叉树。有k层，节点总数N是2^k-1，叶子节点（路径）数是2^(k-1)，约占总节点数的二分之一
    // 每条路径是logN长度，所以时间复杂度为O(N*logN)，空间复杂度为O(N*logN)
    // 时间复杂度：O(N*logN)
    // 空间复杂度：O(N*logN)
    public static class MethodOne {

        ArrayList<Integer> singlePath = new ArrayList<>(); // 每条路径节点记录
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>(); // 所有路径记录
        int sum = 0; // 所有路径生成的数字之和

        public int sumNumbers(TreeNode head) {
            // 获取所有路径
            process(head);
            // 所有路径上的数字求和
            getSum(paths);
            return sum;
        }

        public void process(TreeNode head) {
            if (head == null) {
                return;
            }
            singlePath.add(head.val);
            // 当前路径遍历到了最后一个节点，将该路径加入到paths中
            if (head.left == null && head.right == null) {
                paths.add(new ArrayList<>(singlePath));
            }
            // 当前路径未遍历到最后一个节点，遍历其左右子树，继续当前路径
            else {
                process(head.left);
                process(head.right);
            }
            // 从下往上，子树遍历完后，将其从singlePath中删除，进入下一条路径
            singlePath.remove(singlePath.size() - 1);
        }

        // 时间复杂度：O(N*logN)
        // 空间复杂度：O(N*logN)
        public void getSum(ArrayList<ArrayList<Integer>> lists) {
            for (ArrayList<Integer> list : lists) {
                for (int i = 0; i < list.size(); i++) {
                    sum += list.get(i) * Math.pow(10, list.size() - 1 - i);
                }
            }
        }
    }

    // 方法2：记录所有路径，然后计算求和，方法1改进
    // 前序遍历
    // 每条路径遍历过程中依次累加，单条路径和累加到sum，不用list存储
    // 时间复杂度：O(N)
    // 空间复杂度：O(N)
    public static class MethodTwo {

        int sum = 0; // 所有路径生成的数字之和

        public int sumNumbers(TreeNode head) {
            process(head, 0);
            return sum;
        }

        // 单条路径累加和
        public void process(TreeNode head, int singleSum) {
            if (head == null) {
                return;
            }
            // 当前路径和累加
            singleSum = singleSum * 10 + head.val;
            // 当前路径遍历到了最后一个节点，当前路径和累加到总路径和中
            if (head.left == null && head.right == null) {
                sum += singleSum;
            }
            // 当前路径未遍历到最后一个节点，遍历其左右子树，继续当前路径
            else {
                process(head.left, singleSum);
                process(head.right, singleSum);
            }
        }
    }
}

















