package dp;

/**
 * 剑指 Offer 60. n个骰子的点数
 *
 * @author dks233
 * @create 2022-06-19-20:16
 */
public class DicePoints {
    // 所有的可能性是6^n
    // 扔出的点数范围：[n,6n]
    public static class MethodOne {
        // 动态规划：dp[count][countSum]
        // count:投掷count颗骰子
        // countSum：投掷count颗骰子后的点数
        // dp[count][countSum]投掷count颗骰子后点数为countSum的次数
        // 分析位置依赖：dp[count][countSum]=∑dp[count-1][countSum-i]  i范围是从1到6
        public double[] dicesProbability(int n) {
            int[][] dp = new int[n + 1][6 * n + 1];
            for (int countSum = 1; countSum <= 6; countSum++) {
                dp[1][countSum] = 1;
            }
            for (int count = 2; count <= n; count++) {
                for (int countSum = count; countSum <= 6 * n; countSum++) {
                    for (int i = 1; i <= 6 && countSum >= i; i++) {
                        dp[count][countSum] += dp[count - 1][countSum - i];
                    }
                }
            }
            double[] ans = new double[5 * n + 1];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = dp[n][i + n] / Math.pow(6, n);
            }
            return ans;
        }
    }
}
