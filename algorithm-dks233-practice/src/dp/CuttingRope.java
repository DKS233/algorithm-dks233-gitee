package dp;

/**
 * 剑指 Offer第二版  14- I. 剪绳子
 *
 * @author dks233
 * @create 2022-06-17-9:53
 */
public class CuttingRope {
    public static class MethodOne {
        public int cuttingRope(int n) {
            return process(n);
        }

        public int process(int rest) {
            // 每次最少切成两段，如果小于2，无法切
            if (rest < 2) {
                return 0;
            }
            int max = 0;
            for (int i = 1; i <= rest; i++) {
                // 剩余的可以继续切割，可以不切割
                int curMax = Math.max((i * (rest - i)), i * process(rest - i));
                max = Math.max(curMax, max);
            }
            return max;
        }
    }


    public static class MethodTwo {
        // 暴力递归改动态规划
        // 分析可变参数：rest->[0,n]
        // dp[i]表示i长度切割后的最大乘积
        public int cuttingRope(int n) {
            int[] dp = new int[n + 1];
            // 抄暴力递归过程
            dp[0] = dp[1] = 0;
            int max = 0;
            for (int rest = 2; rest < dp.length; rest++) {
                for (int j = 1; j <= rest; j++) {
                    int curMax = Math.max(j * (rest - j), j * dp[rest - j]);
                    max = Math.max(curMax, max);
                }
                dp[rest] = max;
            }
            return dp[n];
        }
    }
}
