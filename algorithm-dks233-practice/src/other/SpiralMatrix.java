package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode54. 螺旋矩阵
 * leetcode59. 螺旋矩阵 II
 *
 * @author dks233
 * @create 2022-07-06-21:06
 */
@SuppressWarnings("ALL")
public class SpiralMatrix {
    // 问题1：螺旋矩阵1
    // 顺时针顺序：右->下->左->上，然后更新上下左右边界
    // 时间复杂度：O(MN)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = matrix[0].length - 1;
        int upIndex = 0;
        int downIndex = matrix.length - 1;
        while (true) {
            // 向右
            for (int index = leftIndex; index <= rightIndex; index++) {
                list.add(matrix[upIndex][index]);
            }
            upIndex++;
            if (upIndex > downIndex) {
                break;
            }
            // 向下
            for (int index = upIndex; index <= downIndex; index++) {
                list.add(matrix[index][rightIndex]);
            }
            rightIndex--;
            if (rightIndex < leftIndex) {
                break;
            }
            // 向左
            for (int index = rightIndex; index >= leftIndex; index--) {
                list.add(matrix[downIndex][index]);
            }
            downIndex--;
            if (downIndex < upIndex) {
                break;
            }
            // 向上
            for (int index = downIndex; index >= upIndex; index--) {
                list.add(matrix[index][leftIndex]);
            }
            leftIndex++;
            if (leftIndex > rightIndex) {
                break;
            }
        }
        return list;
    }

    // 问题2：螺旋矩阵 II
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int leftIndex = 0;
        int rightIndex = matrix[0].length - 1;
        int upIndex = 0;
        int downIndex = matrix.length - 1;
        int number = 0;
        while (true) {
            // 向右
            for (int index = leftIndex; index <= rightIndex; index++) {
                matrix[upIndex][index] = ++number;
            }
            upIndex++;
            if (upIndex > downIndex) {
                break;
            }
            // 向下
            for (int index = upIndex; index <= downIndex; index++) {
                matrix[index][rightIndex] = ++number;
            }
            rightIndex--;
            if (rightIndex < leftIndex) {
                break;
            }
            // 向左
            for (int index = rightIndex; index >= leftIndex; index--) {
                matrix[downIndex][index] = ++number;
            }
            downIndex--;
            if (downIndex < upIndex) {
                break;
            }
            // 向上
            for (int index = downIndex; index >= upIndex; index--) {
                matrix[index][leftIndex] = ++number;
            }
            leftIndex++;
            if (leftIndex > rightIndex) {
                break;
            }
        }
        return matrix;
    }
}
