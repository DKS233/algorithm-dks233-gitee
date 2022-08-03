package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer专项突击版：剑指 Offer II 107. 矩阵中的距离
 * 参考文档：https://leetcode.cn/problems/01-matrix/solution/2chong-bfs-xiang-jie-dp-bi-xu-miao-dong-by-sweetie/
 *
 * @author dks233
 * @create 2022-08-03-15:54
 */
public class DistanceInMatrix {
    public static class MethodOne {
        public int[][] updateMatrix(int[][] mat) {
            // 距离矩阵，初始化，-1表示未被访问过的1
            int m = mat.length;
            int n = mat[0].length;
            int[][] distance = new int[m][n];
            // 将所有0加入队列
            Queue<int[]> queue = new LinkedList<>();
            for (int row = 0; row < m; row++) {
                for (int column = 0; column < n; column++) {
                    if (mat[row][column] == 0) {
                        queue.offer(new int[]{row, column});
                    } else {
                        distance[row][column] = -1;
                    }
                }
            }
            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                int row = poll[0];
                int column = poll[1];
                if (row - 1 >= 0 && distance[row - 1][column] == -1) {
                    queue.offer(new int[]{row - 1, column});
                    distance[row - 1][column] = distance[row][column] + 1;
                }
                if (row + 1 < m && distance[row + 1][column] == -1) {
                    queue.offer(new int[]{row + 1, column});
                    distance[row + 1][column] = distance[row][column] + 1;
                }
                if (column - 1 >= 0 && distance[row][column - 1] == -1) {
                    queue.offer(new int[]{row, column - 1});
                    distance[row][column - 1] = distance[row][column] + 1;
                }
                if (column + 1 < n && distance[row][column + 1] == -1) {
                    queue.offer(new int[]{row, column + 1});
                    distance[row][column + 1] = distance[row][column] + 1;
                }
            }
            return distance;
        }
    }
}
