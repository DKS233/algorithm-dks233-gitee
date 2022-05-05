package tree;


/**
 * leetcode98:验证二叉搜索树
 * 判断二叉树是不是搜索二叉树
 * 搜索二叉树：整棵树没有重复值，在一棵二叉树中，每一棵子树的头节点，左树上的值都比它小，右树上的值都比它大
 * 有重复值的需求：相同的放一起，不是在树上挂很多重复节点
 * 分析：用二叉树递归套路做
 * 分析：整棵树是搜索二叉树的情况
 * 左树是搜索二叉树，右树是搜索二叉树，左树的最大值小于头节点的值，右树的最小值大于头节点的值
 * 需要的左树右树信息：左树右树是不是搜索二叉树，左树右树最大值，子树最小值
 * 需要返回的子树信息：是不是搜索二叉树，子树最大值，子树最小值
 *
 * @author dks233
 * @create 2022-05-05-21:43
 */
public class IsSearchBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isSearchBinaryTree;
    }

    public Info process(TreeNode head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int maxValue = head.val;
        int minValue = head.val;
        if (leftInfo != null) {
            maxValue = Math.max(maxValue, leftInfo.maxValue);
            minValue = Math.min(minValue, leftInfo.minValue);
        }
        if (rightInfo != null) {
            maxValue = Math.max(maxValue, rightInfo.maxValue);
            minValue = Math.min(minValue, rightInfo.minValue);
        }
        boolean leftIsSearchBinaryTree = leftInfo == null || leftInfo.isSearchBinaryTree;
        boolean rightIsSearchBinaryTree = rightInfo == null || rightInfo.isSearchBinaryTree;
        boolean isSearchBinaryTree = leftIsSearchBinaryTree && rightIsSearchBinaryTree &&
                (leftInfo == null || leftInfo.maxValue < head.val) &&
                (rightInfo == null || rightInfo.minValue > head.val);
        return new Info(isSearchBinaryTree, maxValue, minValue);
    }

    public static class Info {
        boolean isSearchBinaryTree;
        int maxValue;
        int minValue;

        public Info(boolean isSearchBinaryTree, int maxValue, int minValue) {
            this.isSearchBinaryTree = isSearchBinaryTree;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }
}
