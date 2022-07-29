package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode54. 螺旋矩阵
 * leetcode59. 螺旋矩阵 II
 * leetcode2326. 螺旋矩阵 IV
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

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] matrix = new int[m][n];
        // 上下左右边界
        int left = 0, right = n - 1, up = 0, down = m - 1;
        int row = 0, column = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = -1;
            }
        }
        matrix[row][column] = head.val;
        // 顺时针顺序：右下左上
        while (up <= down && right >= left) {
            // 右
            while (column + 1 <= right) {
                if (head != null && head.next != null) {
                    head = head.next;
                    matrix[row][++column] = head.val;
                } else {
                    break;
                }
            }
            up++;
            // 下
            while (row + 1 <= down) {
                if (head != null && head.next != null) {
                    head = head.next;
                    matrix[++row][column] = head.val;
                } else {
                    break;
                }
            }
            right--;
            // 左
            while (column - 1 >= left) {
                if (head != null && head.next != null) {
                    head = head.next;
                    matrix[row][--column] = head.val;
                } else {
                    break;
                }
            }
            down--;
            // 上
            while (row - 1 >= up) {
                if (head != null && head.next != null) {
                    head = head.next;
                    matrix[--row][column] = head.val;
                } else {
                    break;
                }
            }
            left++;
        }
        return matrix;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
