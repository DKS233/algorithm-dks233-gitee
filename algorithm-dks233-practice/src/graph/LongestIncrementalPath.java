package graph;

/**
 * 剑指offer专项突击版：剑指 Offer II 112. 最长递增路径
 *
 * @author dks233
 * @create 2022-08-11-23:33
 */
@SuppressWarnings("ALL")
public class LongestIncrementalPath {
    int[][] count;

    public int longestIncreasingPath(int[][] matrix) {
        count = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                max = Math.max(process(matrix, row, column), max);
            }
        }
        return max;
    }

    // [row][column]出发能走的最长递增路径
    public int process(int[][] matrix, int row, int column) {
        if (count[row][column] != 0) {
            return count[row][column];
        }
        // 从当前位置往上下左右走，前提条件：上下左右的值比当前的值大
        // 注：这里curMax必须是1，如果进不去if语句中返回也得是1
        int curMax = 1;
        if (row - 1 >= 0 && matrix[row][column] < matrix[row - 1][column]) {
            curMax = Math.max(curMax, 1 + process(matrix, row - 1, column));
        }
        if (row + 1 < matrix.length && matrix[row][column] < matrix[row + 1][column]) {
            curMax = Math.max(curMax, 1 + process(matrix, row + 1, column));
        }
        if (column - 1 >= 0 && matrix[row][column] < matrix[row][column - 1]) {
            curMax = Math.max(curMax, 1 + process(matrix, row, column - 1));
        }
        if (column + 1 < matrix[0].length && matrix[row][column] < matrix[row][column + 1]) {
            curMax = Math.max(curMax, 1 + process(matrix, row, column + 1));
        }
        count[row][column] = curMax;
        return curMax;
    }
}
