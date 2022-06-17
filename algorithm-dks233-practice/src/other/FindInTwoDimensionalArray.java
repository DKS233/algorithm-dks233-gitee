package other;

/**
 * 剑指 Offer第二版 04. 二维数组中的查找
 *
 * @author dks233
 * @create 2022-06-17-20:03
 */
@SuppressWarnings("ALL")
public class FindInTwoDimensionalArray {
    // 从右上角开始找，如果目标数小于当前数，就去左边找，如果目标数大于当前数，就去下边找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return isFind(matrix, 0, matrix[0].length - 1, target);
    }

    public boolean isFind(int[][] matrix, int row, int column, int target) {
        if (row >= matrix.length || column >= matrix[0].length || row < 0 || column < 0) {
            return false;
        }
        if (matrix[row][column] == target) {
            return true;
        } else if (matrix[row][column] > target) {
            return isFind(matrix, row, column - 1, target);
        } else {
            return isFind(matrix, row + 1, column, target);
        }
    }
}
