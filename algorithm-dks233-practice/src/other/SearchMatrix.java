package other;

/**
 * leetcode240. 搜索二维矩阵 II
 *
 * @author dks233
 * @create 2022-07-12-16:35
 */
public class SearchMatrix {
    // 从右上角开始
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int column = matrix[0].length - 1;
        while (!(row < 0 || row >= matrix.length || column < 0 || column >= matrix[0].length)) {
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] < target) {
                row++;
            } else {
                column--;
            }
        }
        return false;
    }
}
