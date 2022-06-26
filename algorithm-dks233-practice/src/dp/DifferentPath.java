package dp;

/**
 * leetcode62. 不同路径
 * leetcode63. 不同路径 II
 *
 * @author dks233
 * @create 2022-06-26-10:01
 */
public class DifferentPath {
    // 题目1
    // 暴力递归
    public static class ProblemOneMethodOne {
        public int uniquePaths(int m, int n) {
            if (m <= 1 || n <= 1) {
                return 1;
            }
            return process(0, 0, m, n);
        }

        // 从(row,column)位置出发到右下角有多少种不同的路径
        public int process(int row, int column, int m, int n) {
            if (row >= m || column >= n) {
                return -1;
            }
            if (row == m - 1 && column == n - 1) {
                return 1;
            }
            // 往右边走
            int p1 = 0;
            if (process(row, column + 1, m, n) != -1) {
                p1 = process(row, column + 1, m, n);
            }
            // 往下边走
            int p2 = 0;
            if (process(row + 1, column, m, n) != -1) {
                p2 = process(row + 1, column, m, n);
            }
            return p1 + p2;
        }
    }

    // 题目1：暴力递归改动态规划
    // 分析可变参数：row,column
    // dp[row][column]表示从(row,column)位置出发到达右下角的方法数
    // 分析位置依赖关系：每个位置依赖其右边和下边
    public static class ProblemOneMethodTwo {
        public int uniquePaths(int m, int n) {
            if (m <= 1 || n <= 1) {
                return 1;
            }
            int[][] dp = new int[m][n];
            dp[m - 1][n - 1] = 1;
            // 先把最后一行和最后一列填了，因为它们右和下不全
            for (int row = m - 2; row >= 0; row--) {
                dp[row][n - 1] = dp[row + 1][n - 1];
            }
            for (int column = n - 2; column >= 0; column--) {
                dp[m - 1][column] = dp[m - 1][column + 1];
            }
            for (int row = m - 2; row >= 0; row--) {
                for (int column = n - 2; column >= 0; column--) {
                    int p1 = dp[row][column + 1];
                    int p2 = dp[row + 1][column];
                    dp[row][column] = p1 + p2;
                }
            }
            return dp[0][0];
        }
    }

    // 题目2：暴力递归
    public static class ProblemTwoMethodOne {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            // m <= 1或n <= 1的情况
            if (m <= 1) {
                for (int column = 0; column < n; column++) {
                    if (obstacleGrid[m - 1][column] == 1) {
                        return 0;
                    }
                }
            }
            if (n <= 1) {
                for (int row = 0; row < m; row++) {
                    if (obstacleGrid[row][n - 1] == 1) {
                        return 0;
                    }
                }
            }
            // 到这儿说明m <= 1 或 n <= 1的整条路径上没有障碍物
            if (m <= 1 || n <= 1) {
                return 1;
            }
            // 右下角有障碍物，方法数为0
            if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) {
                return 0;
            }
            return process(0, 0, m, n, obstacleGrid);
        }

        // 从(row,column)位置出发到右下角有多少种不同的路径
        public int process(int row, int column, int m, int n, int[][] nums) {
            // 异常情况：越界、右下角有障碍物（上面判断过了）、当前位置是障碍物
            if (row >= m || column >= n) {
                return -1;
            }
            if (nums[row][column] == 1) {
                return -1;
            }
            if (row == m - 1 && column == n - 1) {
                return 1;
            }
            // 往右边走
            int p1 = 0;
            if (process(row, column + 1, m, n, nums) != -1) {
                p1 = process(row, column + 1, m, n, nums);
            }
            // 往下边走
            int p2 = 0;
            if (process(row + 1, column, m, n, nums) != -1) {
                p2 = process(row + 1, column, m, n, nums);
            }
            return p1 + p2;
        }
    }

    // 题目2：暴力递归改动态规划
    public static class ProblemTwoMethodTwo {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            // m <= 1或n <= 1的情况
            if (m <= 1) {
                for (int column = 0; column < n; column++) {
                    if (obstacleGrid[m - 1][column] == 1) {
                        return 0;
                    }
                }
            }
            if (n <= 1) {
                for (int row = 0; row < m; row++) {
                    if (obstacleGrid[row][n - 1] == 1) {
                        return 0;
                    }
                }
            }
            // 到这儿说明m <= 1 或 n <= 1的整条路径上没有障碍物
            if (m <= 1 || n <= 1) {
                return 1;
            }
            // 右下角有障碍物，方法数为0
            if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1) {
                return 0;
            }
            // 从这里开始正式动态规划过程
            // 可变参数和位置依赖关系与题目1一样，只是加了个判断条件
            int[][] dp = new int[m][n];
            dp[m - 1][n - 1] = 1;
            for (int row = m - 2; row >= 0; row--) {
                dp[row][n - 1] = obstacleGrid[row + 1][n - 1] == 1 ? 0 : dp[row + 1][n - 1];
            }
            for (int column = n - 2; column >= 0; column--) {
                dp[m - 1][column] = obstacleGrid[m - 1][column + 1] == 1 ? 0 : dp[m - 1][column + 1];
            }
            for (int row = m - 2; row >= 0; row--) {
                for (int column = n - 2; column >= 0; column--) {
                    int p1 = 0;
                    if (obstacleGrid[row][column + 1] != 1) {
                        p1 = dp[row][column + 1];
                    }
                    int p2 = 0;
                    if (obstacleGrid[row + 1][column] != 1) {
                        p2 = dp[row + 1][column];
                    }
                    dp[row][column] = p1 + p2;
                }
            }
            return dp[0][0];
        }
    }
}
