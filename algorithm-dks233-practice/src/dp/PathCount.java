package dp;

/**
 * 剑指offer专项突击版：剑指 Offer II 098. 路径的数目
 *
 * @author dks233
 * @create 2022-07-26-10:26
 */
@SuppressWarnings("ALL")
public class PathCount {
    // 暴力递归
    public static class MethodOne {
        public int uniquePaths(int m, int n) {
            return process(m, n, 0, 0);
        }

        // [row,column]到[m-1,n-1]有多少条不同的路径
        public int process(int m, int n, int row, int column) {
            if (row < 0 || row >= m || column < 0 || column >= n) {
                return 0;
            }
            if (row == m - 1 && column == n - 1) {
                return 1;
            }
            int ans = 0;
            // 当前位置往下走
            ans += process(m, n, row + 1, column);
            // 当前位置往右走
            ans += process(m, n, row, column + 1);
            return ans;
        }
    }

    // 暴力递归改动态规划
    public static class MethodTwo {
        public int uniquePaths(int m, int n) {
            // dp[row][column]表示[row,column]位置到右下角有多少条路径
            int[][] dp = new int[m][n];
            dp[m - 1][n - 1] = 1;
            // 每个位置依赖右下角
            // 先填没有右或者没有下的边界
            for (int row = dp.length - 2; row >= 0; row--) {
                dp[row][n - 1] = dp[row + 1][n - 1];
            }
            for (int column = dp[0].length - 2; column >= 0; column--) {
                dp[m - 1][column] = dp[m - 1][column + 1];
            }
            // 每个row从右往左填，整体从下往上填
            for (int row = dp.length - 2; row >= 0; row--) {
                for (int column = dp[0].length - 2; column >= 0; column--) {
                    dp[row][column] += dp[row + 1][column];
                    dp[row][column] += dp[row][column + 1];
                }
            }
            return dp[0][0];
        }
    }
}
