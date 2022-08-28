package other;

import java.util.PriorityQueue;

/**
 * leetcode378. 有序矩阵中第 K小的元素
 *
 * @author dks233
 * @create 2022-08-27-22:54
 */
@SuppressWarnings("ALL")
public class MinKthElement {
    // 小根堆：归并
    public static class MethodOne {
        public int kthSmallest(int[][] matrix, int k) {
            PriorityQueue<int[]> heap = new PriorityQueue<>((int[] a, int[] b) -> matrix[a[0]][a[1]] - matrix[b[0]][b[1]]);
            for (int i = 0; i < matrix.length; i++) {
                heap.add(new int[]{i, 0});
            }
            int rest = k - 1;
            while (rest > 0) {
                int[] poll = heap.poll();
                if (poll[1] + 1 < matrix.length) {
                    heap.offer(new int[]{poll[0], poll[1] + 1});
                }
                rest--;
            }
            int[] result = heap.poll();
            return matrix[result[0]][result[1]];
        }
    }

    // 二分
    // 最小的符合条件的一定是在矩阵中
    public static class MethodTwo {
        public int kthSmallest(int[][] matrix, int k) {
            int left = matrix[0][0];
            int right = matrix[matrix.length - 1][matrix.length - 1];
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                int curCount = getCount(matrix, mid);
                if (curCount >= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        // 检查矩阵中有多少数小于等于mid
        public int getCount(int[][] matrix, int mid) {
            int row = matrix.length - 1;
            int column = 0;
            int count = 0;
            while (row >= 0 && column < matrix.length) {
                // 当前数小于等于mid，向右尝试
                // 当前数大于mid，上移，去上一行尝试
                if (matrix[row][column] <= mid) {
                    count += row + 1;
                    column++;
                } else {
                    row--;
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        MethodTwo methodTwo = new MethodTwo();
        methodTwo.kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8);
    }
}
