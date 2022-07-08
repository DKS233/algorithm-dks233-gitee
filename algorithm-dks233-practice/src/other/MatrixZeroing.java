package other;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode73. 矩阵置零
 *
 * @author dks233
 * @create 2022-07-08-20:31
 */
@SuppressWarnings("ALL")
public class MatrixZeroing {
    public static class MethodOne {
        // 记录需要删除的行和列，然后逐一删除
        // 时间复杂度：O(MN)
        // 空间复杂度：O(M)+O(N)
        public void setZeroes(int[][] matrix) {
            Set<Integer> rowSet = new HashSet<>();
            Set<Integer> columnSet = new HashSet<>();
            for (int row = 0; row < matrix.length; row++) {
                for (int column = 0; column < matrix[0].length; column++) {
                    if (matrix[row][column] == 0) {
                        rowSet.add(row);
                        columnSet.add(column);
                    }
                }
            }
            rowSet.forEach(row -> {
                for (int column = 0; column < matrix[0].length; column++) {
                    matrix[row][column] = 0;
                }
            });
            columnSet.forEach(column -> {
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][column] = 0;
                }
            });
        }
    }

    public static class MethodTwo {
        // 用第一行和第一列充当标志位，表示该行和该列是否需要置0
        public void setZeroes(int[][] matrix) {
            // 先遍历第一行和第一列，看第一行和第一列是否需要置0
            boolean firstRow = false;
            boolean firstColumn = false;
            for (int column = 0; column < matrix[0].length; column++) {
                if (matrix[0][column] == 0) {
                    firstRow = true;
                }
            }
            for (int row = 0; row < matrix.length; row++) {
                if (matrix[row][0] == 0) {
                    firstColumn = true;
                }
            }
            // 遍历其他行和其他列，如果有0，修改对应的标志位
            for (int row = 1; row < matrix.length; row++) {
                for (int column = 1; column < matrix[0].length; column++) {
                    if (matrix[row][column] == 0) {
                        matrix[row][0] = 0;
                        matrix[0][column] = 0;
                    }
                }
            }
            // 将需要置0的行和列置0（标志位除外）
            for (int row = 1; row < matrix.length; row++) {
                for (int column = 1; column < matrix[0].length; column++) {
                    if (matrix[0][column] == 0 || matrix[row][0] == 0) {
                        matrix[row][column] = 0;
                    }
                }
            }
            // 再看第一行和第一列是否需要置0
            if (firstRow) {
                for (int column = 0; column < matrix[0].length; column++) {
                    matrix[0][column] = 0;
                }
            }
            if (firstColumn) {
                for (int row = 0; row < matrix.length; row++) {
                    matrix[row][0] = 0;
                }
            }
        }
    }
}
