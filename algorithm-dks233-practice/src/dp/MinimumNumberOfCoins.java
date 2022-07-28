package dp;

/**
 * 剑指offer专项突击版：剑指 Offer II 103. 最少的硬币数目
 *
 * @author dks233
 * @create 2022-07-28-17:04
 */
public class MinimumNumberOfCoins {
    public static class MethodOne {
        public int coinChange(int[] coins, int amount) {
            int result = process(coins, 0, amount);
            if (result == Integer.MAX_VALUE) {
                return -1;
            } else {
                return result;
            }
        }

        // [index...]能组成rest的最少硬币数
        public int process(int[] nums, int index, int rest) {
            if (index == nums.length) {
                return rest == 0 ? 0 : Integer.MAX_VALUE;
            }
            // 当前位置的硬币从0个开始尝试，逐渐变多
            int ans = Integer.MAX_VALUE;
            for (int count = 0; count * nums[index] <= rest; count++) {
                int nextSelection = process(nums, index + 1, rest - count * nums[index]);
                if (nextSelection != Integer.MAX_VALUE) {
                    ans = Math.min(nextSelection + count, ans);
                }
            }
            return ans;
        }
    }

    // 动态规划
    public static class MethodTwo {
        public int coinChange(int[] coins, int amount) {
            // dp[index][rest]  [index...]能组成rest的最少硬币数
            int[][] dp = new int[coins.length + 1][amount + 1];
            for (int rest = 1; rest < dp[0].length; rest++) {
                dp[coins.length][rest] = Integer.MAX_VALUE;
            }
            for (int index = dp.length - 2; index >= 0; index--) {
                for (int rest = 1; rest < dp[0].length; rest++) {
                    int ans = Integer.MAX_VALUE;
                    for (int count = 0; count * coins[index] <= rest; count++) {
                        if (dp[index + 1][rest - count * coins[index]] != Integer.MAX_VALUE) {
                            ans = Math.min(ans, count + dp[index + 1][rest - count * coins[index]]);
                        }
                    }
                    dp[index][rest] = ans;
                }
            }
            return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
        }
    }

    // 动态规划优化
    public static class MethodThree {
        public static int coinChange(int[] arr, int aim) {
            int[][] dp = new int[arr.length + 1][aim + 1];
            dp[arr.length][0] = 0;
            for (int rest = 1; rest <= aim; rest++) {
                dp[arr.length][rest] = Integer.MAX_VALUE;
            }
            for (int index = arr.length - 1; index >= 0; index--) {
                for (int rest = 0; rest <= aim; rest++) {
                    // 判断 rest - arr[index] < 0  防止越过二维表的边界
                    // 判断 dp[index][rest - arr[index]] == Integer.MAX_VALUE 防止后面+1后整数越界
                    if (rest - arr[index] < 0 || dp[index][rest - arr[index]] == Integer.MAX_VALUE) {
                        dp[index][rest] = dp[index + 1][rest];
                    } else {
                        dp[index][rest] = Math.min(dp[index + 1][rest], dp[index][rest - arr[index]] + 1);
                    }
                }
            }
            return dp[0][aim] == Integer.MAX_VALUE ? -1 : dp[0][aim];
        }
    }
}
