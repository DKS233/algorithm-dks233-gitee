package other;

import java.util.Map;

/**
 * 剑指offer专项突击版：剑指 Offer II 013. 二维子矩阵的和
 *
 * @author dks233
 * @create 2022-07-18-23:57
 */
@SuppressWarnings("ALL")
public class SumOfTwoDimensionalSubmatrix {
    public static class NumMatrix {
        // preSums[row][column]表示[0,0]到matrix[row-1][column-1]的矩阵求和
        // 初始化时间复杂度：O(MN)
        // 查询空间复杂度：O(1)
        int[][] preSums;

        public NumMatrix(int[][] matrix) {
            preSums = new int[matrix.length + 1][matrix[0].length + 1];
            for (int row = 0; row < matrix.length; row++) {
                for (int column = 0; column < matrix[0].length; column++) {
                    preSums[row + 1][column + 1] = preSums[row + 1][column]
                            + preSums[row][column + 1] - preSums[row][column] + matrix[row][column];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSums[row2 + 1][col2 + 1] - preSums[row1][col2 + 1] - preSums[row2 + 1][col1] + preSums[row1][col1];
        }
    }
}
