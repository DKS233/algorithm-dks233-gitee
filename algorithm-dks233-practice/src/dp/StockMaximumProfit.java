package dp;

/**
 * 剑指 Offer第二版 63. 股票的最大利润
 *
 * @author dks233
 * @create 2022-06-19-22:48
 */
public class StockMaximumProfit {
    // 暴力解法
    // O(N*N)
    public static class MethodOne {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            return process(prices);
        }

        public int process(int[] nums) {
            int max = Integer.MIN_VALUE;
            // i位置买，j位置卖
            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j < nums.length; j++) {
                    max = Math.max(max, nums[j] - nums[i]);
                }
            }
            return Math.max(max, 0);
        }
    }


    // 动态规划  O(N)
    public static class MethodTwo {
        // index：买入股票的时间
        // dp[index] [... , index]范围内买卖股票能获得的最大利润
        // dp[1] max(1-0) 这里的1-0表示prices[1]-prices[0]
        // dp[2] max(1-0,2-0,2-1)   max(dp[1],prices[2]-min(0~1))
        // dp[3] max(1-0,2-0,2-1,3-0,3-1,3-2)  max(dp[2],prices[3]-min(0~2))
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int[] dp = new int[prices.length];
            dp[0] = 0;
            int minPrice = prices[0];
            for (int index = 1; index < dp.length; index++) {
                dp[index] = Math.max(dp[index - 1], prices[index] - minPrice);
                minPrice = Math.min(minPrice, prices[index]);
            }
            return dp[dp.length - 1];
        }
    }
}
