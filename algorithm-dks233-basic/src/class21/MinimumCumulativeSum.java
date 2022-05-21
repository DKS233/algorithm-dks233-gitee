package class21;

/**
 * leetcode64： 最小路径和
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 *
 * @author dks233
 * @create 2022-05-21-17:27
 */
public class MinimumCumulativeSum {
    // 暴力递归
    public static int minPathSumOne(int[][] grid) {
        int result = process(grid, 0, 0);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    // (row,column)位置到右下角的最小距离累加和
    public static int process(int[][] grid, int row, int column) {
        // 越界，路径无效
        // 返回-1，表明没走到右下角
        if (row < 0 || row > grid.length - 1 || column < 0 || column > grid[0].length - 1) {
            return -1;
        }
        if (row == grid.length - 1 && column == grid[0].length - 1) {
            return grid[row][column];
        }
        // 往右走和往下走
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        int right = process(grid, row, column + 1);
        int down = process(grid, row + 1, column);
        if (right != -1) {
            p1 = grid[row][column] + process(grid, row, column + 1);
        }
        if (down != -1) {
            p2 = grid[row][column] + process(grid, row + 1, column);
        }
        return Math.min(p1, p2);
    }

    // 改动态规划
    // 可变参数row,column
    // 建立二维数组：dp[row][column]表示(row,column)出发到右下角的最小距离
    // 分析各个位置的依赖，先填右下角，然后其他位置都依赖于右边位置和下边位置
    // 下面有简写：6ms->2ms
    public static int minPathSum(int[][] grid) {
        int rowCount = grid.length;
        int columnCount = grid[0].length;
        int[][] dp = new int[rowCount][columnCount];
        dp[rowCount - 1][columnCount - 1] = grid[rowCount - 1][columnCount - 1];
        for (int row = rowCount - 1; row >= 0; row--) {
            for (int column = columnCount - 1; column >= 0; column--) {
                if (row == rowCount - 1 && column == columnCount - 1) {
                    continue;
                }
                // int min = Integer.MAX_VALUE
                // 如果下右都越界了，dp[row][column]=Integer.MAX_VALUE，只有右下角是下右都越界，但是上面已经处理掉了
                // 其他位置起码有一个没越界，min最少能进一个if语句，得出的最小距离一定有效
                int min = Integer.MAX_VALUE;
                if (pick(grid, row, column + 1) != -1) {
                    min = Math.min(min, grid[row][column] + dp[row][column + 1]);
                }
                if (pick(grid, row + 1, column) != -1) {
                    min = Math.min(min, grid[row][column] + dp[row + 1][column]);
                }
                dp[row][column] = min;
            }
        }
        return dp[0][0];
    }

    // 如果横纵坐标越界，返回-1
    // 如果横坐标没有越界，返回该位置上的值
    public static int pick(int[][] grid, int row, int column) {
        if (row < 0 || row > grid.length - 1 || column < 0 || column > grid[0].length - 1) {
            return -1;
        }
        return grid[row][column];
    }

    // 上述动态规划精简写法
    // 6ms->2ms
    public static class SimpleMethod {
        public static int minPathSum(int[][] grid) {
            int rowCount = grid.length;
            int columnCount = grid[0].length;
            int[][] dp = new int[rowCount][columnCount];
            dp[rowCount - 1][columnCount - 1] = grid[rowCount - 1][columnCount - 1];
            // 将没有右边界或者下边界的位置先填好
            // 没有右边界的位置
            for (int row = rowCount - 2; row >= 0; row--) {
                dp[row][columnCount - 1] = dp[row + 1][columnCount - 1] + grid[row][columnCount - 1];
            }
            // 没有下边界的位置
            for (int column = columnCount - 2; column >= 0; column--) {
                dp[rowCount - 1][column] = dp[rowCount - 1][column + 1] + grid[rowCount - 1][column];
            }
            for (int row = rowCount - 2; row >= 0; row--) {
                for (int column = columnCount - 2; column >= 0; column--) {
                    int p1 = grid[row][column] + dp[row][column + 1];
                    int p2 = grid[row][column] + dp[row + 1][column];
                    dp[row][column] = Math.min(p1, p2);
                }
            }
            return dp[0][0];
        }
    }

    // 动态规划优化版本：不用建m*n的数组，建一个1*n的数组，然后自我更新
    // 2ms
    // 图解：二维数组最小距离和-动态规划.drawio
    public static class BetterMethod {
        public static int minPathSum(int[][] grid) {
            int rowCount = grid.length;
            int columnCount = grid[0].length;
            int[] dp = new int[columnCount];
            // 常规解法中的二维数组最后一行填到dp中
            dp[columnCount - 1] = grid[rowCount - 1][columnCount - 1];
            // 第一轮每个位置只依赖右边位置
            for (int column = columnCount - 2; column >= 0; column--) {
                dp[column] = grid[rowCount - 1][column] + dp[column + 1];
            }
            // 第二轮开始每个位置更新后的值依赖当前没更新的值和更新过的右边位置（最右边位置除外）
            // dp从右往左更新
            for (int row = rowCount - 2; row >= 0; row--) {
                dp[columnCount - 1] = grid[row][columnCount - 1] + dp[columnCount - 1];
                for (int column = dp.length - 2; column >= 0; column--) {
                    int rightPath = grid[row][column] + dp[column + 1];
                    int downPath = grid[row][column] + dp[column];
                    dp[column] = Math.min(rightPath, downPath);
                }
            }
            return dp[0];
        }
    }

}









