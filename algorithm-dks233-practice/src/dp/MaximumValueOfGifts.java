package dp;

/**
 * 剑指offer47：礼物的最大价值
 * 分析：暴力递归->动态规划->看能否优化
 *
 * @author dks233
 * @create 2022-05-30-15:41
 */
public class MaximumValueOfGifts {
    public static class MethodOne {
        public int maxValue(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            return process(grid, 0, 0);
        }

        // 当前在(row,column)位置
        // 返回：当前位置到右下角时积累的礼物数量
        public int process(int[][] grid, int row, int column) {
            // 之前的路径有效
            // 到达最终位置，统计
            if (row == grid.length - 1 && column == grid[0].length - 1) {
                return grid[row][column];
            }
            // 未到达最终位置，继续走
            // 往右走，通过if排除越界情况
            int p1 = -1;
            if (column + 1 < grid[0].length) {
                p1 = grid[row][column] + process(grid, row, column + 1);
            }
            int p2 = -1;
            // 往下走，通过if排除越界情况
            if (row + 1 < grid.length) {
                p2 = grid[row][column] + process(grid, row + 1, column);
            }
            return Math.max(p1, p2);
        }
    }

    // 暴力递归改动态规划
    // 分析可变参数：row:[0,grid.length] column:[0,grid[0].length]
    // 建二维动态规划表：dp[row][column] 从当前位置到右下角能获得的礼物最大值
    public static class MethodTwo {
        public int maxValue(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int m = grid.length;
            int n = grid[0].length;
            int[][] dp = new int[m][n];
            // 先填[grid.length-1][grid[0].length-1]位置
            dp[m - 1][n - 1] = grid[m - 1][n - 1];
            // 填其他位置
            // 分析位置依赖：每个位置依赖右边位置和下边位置
            // 先填没有右边界的列
            for (int row = m - 2; row >= 0; row--) {
                dp[row][n - 1] = grid[row][n - 1] + dp[row + 1][n - 1];
            }
            // 再填没有下边界的行
            for (int column = n - 2; column >= 0; column--) {
                dp[m - 1][column] = grid[m - 1][column] + dp[m - 1][column + 1];
            }
            if (n > 1 && m > 1) {
                // 再填普遍位置
                for (int row = m - 2; row >= 0; row--) {
                    for (int column = n - 2; column >= 0; column--) {
                        // 往右走
                        int p1 = grid[row][column] + dp[row][column + 1];
                        // 往下走
                        int p2 = grid[row][column] + dp[row + 1][column];
                        dp[row][column] = Math.max(p1, p2);
                    }
                }
            }
            return dp[0][0];
        }
    }

}
