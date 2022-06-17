package other;

/**
 * 剑指 Offer第二版 29. 顺时针打印矩阵
 *
 * @author dks233
 * @create 2022-06-17-19:30
 */
public class PrintMatrixClockwise {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] result = new int[matrix.length * matrix[0].length];
        int index = 0;
        // 顺时针顺序：右->下->左->上
        // 求初始情况下的上下左右四个边界
        // 每走完一个右/下/左/上，更新边界
        int right = matrix[0].length - 1, down = matrix.length - 1, left = 0, up = 0;
        while (true) {
            // 右
            for (int i = left; i <= right; i++) {
                result[index++] = matrix[up][i];
            }
            // 向右走完后更新上边界，越界说明遍历结束
            if (++up > down) {
                break;
            }
            // 下
            for (int i = up; i <= down; i++) {
                result[index++] = matrix[i][right];
            }
            // 向下走完后更新右边界，越界说明遍历结束
            if (--right < left) {
                break;
            }
            // 左
            for (int i = right; i >= left; i--) {
                result[index++] = matrix[down][i];
            }
            // 向左走完后更新下边界，越界说明遍历结束
            if (--down < up) {
                break;
            }
            // 上
            for (int i = down; i >= up; i--) {
                result[index++] = matrix[i][left];
            }
            // 向上走完后更新左边界，越界说明遍历结束
            if (++left > right) {
                break;
            }
        }
        return result;
    }
}
