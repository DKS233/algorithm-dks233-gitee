package other;

/**
 * leetcode48. 旋转图像
 *
 * @author dks233
 * @create 2022-07-04-19:06
 */
@SuppressWarnings("ALL")
public class RatateImage {
    // 顺时针旋转可以看成：先沿对角线求转置，然后左右翻转
    // 时间复杂度：O(N)
    public void rotate(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = row + 1; column < matrix.length; column++) {
                swap(matrix, row, column, column, row);
            }
        }
        // 如果列数是3，需要翻转的是0和2
        // 如果列数是4，需要翻转的是0和3,1和2
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length / 2; column++) {
                swap(matrix, row, column, row, matrix.length - 1 - column);
            }
        }
    }

    public void swap(int[][] matrix, int rowOne, int columnOne, int rowTwo, int columnTwo) {
        int temp = matrix[rowOne][columnOne];
        matrix[rowOne][columnOne] = matrix[rowTwo][columnTwo];
        matrix[rowTwo][columnTwo] = temp;
    }
}
