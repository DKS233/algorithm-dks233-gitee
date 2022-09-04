package week309;

/**
 * 6168. 恰好移动 k 步到达某一位置的方法数目
 *
 * @author dks233
 * @create 2022-09-04-9:48
 */
@SuppressWarnings("ALL")
public class Two {
    // 方法1：暴力递归+记忆化搜索
    // // 优化：将取模的10^9+7提前算出来，不然每次取模都得运算一次
    public static class MethodOne {
        // 分析题中最远可以到达的位置范围是[-1000,2000]
        // 直接把起始位置和最终位置都往右移动1000，可以跳到的位置肯定是大于等于0的
        // [0,3000]
        int[][] dp;
        int mod = (int) (Math.pow(10, 9) + 7);

        public int numberOfWays(int startPos, int endPos, int k) {
            dp = new int[3001][k + 1];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    dp[i][j] = -1;
                }
            }
            int result = process(startPos + 1000, endPos + 1000, k);
            return result;
        }

        // 从index位置再走rest步到end的方法数
        public int process(int index, int end, int rest) {
            if (dp[index][rest] != -1) {
                return dp[index][rest];
            }
            if (rest == 0) {
                dp[index][rest] = index == end ? 1 : 0;
                return dp[index][rest];
            }
            int result = 0;
            result += process(index + 1, end, rest - 1) % mod;
            result += process(index - 1, end, rest - 1) % mod;
            dp[index][rest] = result % mod;
            return dp[index][rest];
        }
    }

    // 方法2：动态规划，空间优化
    // index范围  [startPos-1000,startPos+1000]->[0,2000]
    // 优化：将取模的10^9+7提前算出来，不然每次取模都得运算一次
    public static class MethodTwo {
        public int numberOfWays(int startPos, int endPos, int k) {
            int mod = (int) (Math.pow(10, 9) + 7);
            long[][] dp = new long[2001][k + 1];
            int add = 1000 - startPos;
            startPos += add;
            endPos += add;
            dp[endPos][0] = 1;
            // 每个位置依赖左上和左下
            for (int rest = 1; rest <= k; rest++) {
                dp[0][rest] += dp[1][rest - 1];
                for (int index = 1; index < dp.length - 1; index++) {
                    long result = 0;
                    result += dp[index + 1][rest - 1];
                    result += dp[index - 1][rest - 1];
                    dp[index][rest] = result % mod;
                }
                dp[dp.length - 1][rest] = dp[dp.length - 2][rest - 1];
            }
            return (int) dp[startPos][k];
        }
    }

}
