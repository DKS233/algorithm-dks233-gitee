package dp;

/**
 * leetcode322. 零钱兑换
 *
 * @author dks233
 * @create 2022-07-11-21:54
 */
@SuppressWarnings("ALL")
public class Change {
    // 暴力递归
    public static class MethodOne {
        int minCount = Integer.MAX_VALUE;

        public int coinChange(int[] coins, int amount) {
            return process(coins, 0, amount);
        }

        /**
         * @param coins 货币数组
         * @param index 当前遍历到的索引
         * @param rest  剩余货币
         * @return [index...]组成rest的最少方法数
         */
        public int process(int[] coins, int index, int rest) {
            if (index == coins.length) {
                return rest == 0 ? 0 : Integer.MAX_VALUE;
            }
            int ans = Integer.MAX_VALUE;
            for (int indexCount = 0; indexCount * coins[index] <= rest; indexCount++) {
                int next = process(coins, index + 1, rest - indexCount * coins[index]);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(indexCount + next, ans);
                }
            }
            return ans;
        }
    }

    // 暴力递归改动态规划
    // 分析可变参数：index:[0,coins.length+1] rest:[0,amount]
    // dp[index][rest] 表示 [index...]组成rest的最小方法数
    // 分析位置依赖关系：每个位置依赖下一行
    public static class MethodTwo {
        public int coinChange(int[] coins, int amount) {
            int[][] dp = new int[coins.length + 1][amount + 1];
            for (int rest = 1; rest < dp[0].length; rest++) {
                dp[coins.length][rest] = Integer.MAX_VALUE;
            }
            for (int index = dp.length - 2; index >= 0; index--) {
                for (int rest = 0; rest < dp[0].length; rest++) {
                    int ans = Integer.MAX_VALUE;
                    for (int indexCount = 0; indexCount * coins[index] <= rest; indexCount++) {
                        int next = dp[index + 1][rest - indexCount * coins[index]];
                        if (next != Integer.MAX_VALUE) {
                            ans = Math.min(indexCount + next, ans);
                        }
                    }
                    dp[index][rest] = ans;
                }
            }
            return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
        }
    }

    public static void main(String[] args) {
        MethodOne methodOne = new MethodOne();
        methodOne.coinChange(new int[]{1, 2, 5}, 11);
    }
}
