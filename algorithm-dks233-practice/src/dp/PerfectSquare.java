package dp;


/**
 * leetcode279. 完全平方数
 *
 * @author dks233
 * @create 2022-07-12-16:43
 */
public class PerfectSquare {
    // 暴力递归
    public static class MethodOne {
        public int numSquares(int n) {
            return process(n);
        }

        // 和为number的最少数量
        public int process(int number) {
            if (number == 0) {
                return 0;
            }
            int minCount = number;
            for (int i = 1; i * i <= number; i++) {
                minCount = Math.min(process(number - i * i) + 1, minCount);
            }
            return minCount;
        }
    }

    // 暴力递归改动态规划
    // dp[number] 和为number的完全平方数的最少数量
    public static class MethodTwo {
        public int numSquares(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            for (int number = 1; number < dp.length; number++) {
                int minCount = number;
                for (int i = 1; i * i <= number; i++) {
                    minCount = Math.min(dp[number - i * i] + 1, minCount);
                }
                dp[number] = minCount;
            }
            return dp[n];
        }
    }
}
