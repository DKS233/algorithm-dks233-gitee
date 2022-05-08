package tree;

/**
 * leetcode543：二叉树的直径
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点
 * 注意：两结点之间的路径长度是以它们之间边的数目表示
 * 分析：二叉树递归套路
 * 设递归过程中头节点为head，得到最大路径两种情况
 * 情况1：最大路径经过head，最大路径=左树高度+右树高度+1
 * 情况2：最大路径不经过head，最大路径=max(左树最大路径，右树最大路径)
 * 需要知道的左树右树的信息：左树高度，右树高度，左树最大路径，右树最大路径
 * 需要返回的子树信息：高度，最大路径
 *
 * @author dks233
 * @create 2022-05-08-20:59
 */
public class BinaryTreeDiameter {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int diameterOfBinaryTree(TreeNode head) {
        if (head == null) {
            return 0;
        }
        // 以前的理解：最大距离是节点数
        // 题目要求：两结点之间的路径长度是以它们之间边的数目表示
        return process(head).maxDistance - 1;
    }

    public Info process(TreeNode head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = 0;
        // 情况1
        int p1 = leftInfo.height + rightInfo.height + 1;
        // 情况2
        int p2 = Math.max(leftInfo.maxDistance, rightInfo.maxDistance);
        maxDistance = Math.max(p1, p2);
        return new Info(height, maxDistance);
    }

    public static class Info {
        int height;
        int maxDistance;

        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }
}
