package dp;

/**
 * leetcode221. 最大正方形
 * 参考文档：https://leetcode.cn/problems/maximal-square/solution/li-jie-san-zhe-qu-zui-xiao-1-by-lzhlyle/
 *
 * @author dks233
 * @create 2022-07-07-21:46
 */
@SuppressWarnings("ALL")
public class LargestSquare {
    public static class MethodOne {
        // 分析：设当前遍历到的位置是(row,column)
        // dp[row][column]表示以matrix[row][column]为右下角的正方形的最大边长
        // dp[row][column]由左边、上边和左上方的dp共同决定
        // 如果(row,column)位置是'1'，dp[row][column]=Math.min(dp左 , dp上 , dp左上)+1
        // 时间复杂度：O(MN)
        public int maximalSquare(char[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return 0;
            }
            int[][] dp = new int[matrix.length][matrix[0].length];
            // 先填第一行和第一列
            for (int column = 0; column < dp[0].length; column++) {
                dp[0][column] = matrix[0][column] == '1' ? 1 : 0;
            }
            for (int row = 0; row < dp.length; row++) {
                dp[row][0] = matrix[row][0] == '1' ? 1 : 0;
            }
            // 根据位置依赖填其他位置
            for (int row = 1; row < dp.length; row++) {
                for (int column = 1; column < dp[0].length; column++) {
                    if (matrix[row][column] == '1') {
                        dp[row][column] = Math.min(Math.min(dp[row - 1][column], dp[row][column - 1]), dp[row - 1][column - 1]) + 1;
                    }
                }
            }
            // 比较以每个位置为右下角的最大正方形边长，取最大的，返回面积
            int maxSide = 0;
            for (int row = 0; row < dp.length; row++) {
                for (int column = 0; column < dp[0].length; column++) {
                    maxSide = Math.max(maxSide, dp[row][column]);
                }
            }
            return maxSide * maxSide;
        }
    }
}
