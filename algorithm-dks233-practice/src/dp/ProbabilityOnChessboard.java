package dp;

/**
 * leetcode688. 骑士在棋盘上的概率
 *
 * @author dks233
 * @create 2022-08-23-23:20
 */
@SuppressWarnings("ALL")
public class ProbabilityOnChessboard {
    // 暴力递归
    public static class MethodOne {
        int[][] common = new int[][]{{2, -1}, {2, 1}, {-2, 1}, {-2, -1}, {1, -2}, {1, 2}, {-1, 2}, {-1, -2}};

        public double knightProbability(int n, int k, int row, int column) {
            return process(n, k, row, column);
        }

        // [row][column] 位置出发再走rest步，还在棋盘范围内的数量
        public double process(int n, int rest, int row, int column) {
            if (rest == 0) {
                return row >= 0 && row < n && column >= 0 && column < n ? 1 : 0;
            }
            double result = 0.0;
            for (int i = 0; i < common.length; i++) {
                int curRow = row + common[i][0];
                int curColumn = column + common[i][1];
                if (curRow >= 0 && curRow < n && curColumn >= 0 && curColumn < n) {
                    result += process(n, rest - 1, curRow, curColumn) / 8;
                }
            }
            return result;
        }
    }

    // 暴力递归改动态规划
    // dp[rest][row][column] [row][column] 位置出发再走rest步，还在棋盘范围内的数量
    public static class MethodTwo {
        int[][] common = new int[][]{{2, -1}, {2, 1}, {-2, 1}, {-2, -1}, {1, -2}, {1, 2}, {-1, 2}, {-1, -2}};

        public double knightProbability(int n, int k, int row, int column) {
            double[][][] dp = new double[k + 1][n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[0][i][j] = 1.0;
                }
            }
            for (int rest = 1; rest <= k; rest++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        double result = 0;
                        for (int m = 0; m < common.length; m++) {
                            int curRow = i + common[m][0];
                            int curColumn = j + common[m][1];
                            if (curRow >= 0 && curRow < n && curColumn >= 0 && curColumn < n) {
                                result += dp[rest - 1][curRow][curColumn] / 8;
                            }
                        }
                        dp[rest][i][j] = result;
                    }
                }
            }
            return dp[k][row][column];
        }
    }
}
