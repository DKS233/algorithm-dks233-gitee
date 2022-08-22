package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode655. 输出二叉树
 *
 * @author dks233
 * @create 2022-08-22-10:46
 */
public class PrintBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<String>> printTree(TreeNode root) {
        // getHeight求出的高度是节点数
        // 题中的高度等于节点数-1
        int height = getHeight(root) - 1;
        int m = height + 1;
        int n = (int) Math.pow(2, height + 1) - 1;
        String[][] matrix = new String[m][n];
        preOrder(root, matrix, 0, (n - 1) / 2, height);
        List<List<String>> result = new ArrayList<>();
        for (int row = 0; row < m; row++) {
            List<String> singleList = new ArrayList<>();
            for (int column = 0; column < n; column++) {
                if (matrix[row][column] == null) {
                    singleList.add("");
                } else {
                    singleList.add(matrix[row][column]);
                }
            }
            result.add(new ArrayList<>(singleList));
        }
        return result;
    }

    public void preOrder(TreeNode head, String[][] matrix, int row, int column, int height) {
        if (head == null) {
            return;
        }
        matrix[row][column] = String.valueOf(head.val);
        preOrder(head.left, matrix, row + 1, column - (int) (Math.pow(2, height - row - 1)), height);
        preOrder(head.right, matrix, row + 1, column + (int) (Math.pow(2, height - row - 1)), height);
    }

    public int getHeight(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return Math.max(getHeight(head.left), getHeight(head.right)) + 1;
    }
}
