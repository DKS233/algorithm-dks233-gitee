package tree;

import java.util.LinkedList;
import java.util.List;

/**
 * leetcode96.不同的二叉搜索树
 * 给你一个整数n，求恰由n个节点组成且节点值从1到 n 互不相同的二叉搜索树有多少种？返回满足题意的二叉搜索树的种数
 * leetcode95.不同的二叉搜索树 II
 * 给你一个整数n，请你生成并返回所有由n个节点组成且节点值从1到n互不相同的不同二叉搜索树。可以按任意顺序返回答案。
 *
 * @author dks233
 * @create 2022-06-24-20:59
 */
public class DifferentBinarySearchTree {
    // G(n)->由n个节点可以组成多少棵二叉搜索树
    // f(i)->以i为头可以组成多少个二叉搜索树
    // G(n)=f(1)+f(2)+f(3)+...+f(n)
    // i左边有i-1个节点，右边有n-i个节点，f(i)=G(i-1)*G(n-i)
    // =>G(n)=G(0)G(n-1)+G(1)G(n-2)+...+G(n-1)G(0)
    // 注：时间复杂度：O(N*N) 空间复杂度：O(1)
    public int numTrees(int n) {
        // dp[i]表示由i个节点可以组成多少棵二叉搜索树
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j <= i - 1; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }

    public List<TreeNode> generateTrees(int n) {
        return process(1, n);
    }

    // 返回[left,right]内可以产生的二叉搜索树list
    public List<TreeNode> process(int left, int right) {
        LinkedList<TreeNode> ans = new LinkedList<>();
        if (left > right) {
            ans.add(null);
            return ans;
        }
        // i为头节点可以产生多少棵二叉搜索树
        for (int i = left; i <= right; i++) {
            // 递归遍历左子树，得到左子树可以产生的二叉搜索树集合
            List<TreeNode> leftList = process(left, i - 1);
            // 递归遍历右子树，得到右子树可以产生的二叉搜索树集合
            List<TreeNode> rightList = process(i + 1, right);
            // 从左右子树中随便选一棵树组成二叉树，添加到最终集合中
            for (TreeNode leftNode : leftList) {
                for (TreeNode rightNode : rightList) {
                    TreeNode head = new TreeNode(i);
                    head.left = leftNode;
                    head.right = rightNode;
                    ans.add(head);
                }
            }
        }
        return ans;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
