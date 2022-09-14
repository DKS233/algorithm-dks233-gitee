package dp;


/**
 * leetcode121. 买卖股票的最佳时机
 * leetcode122. 买卖股票的最佳时机 II
 * 参考文档：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solution/best-time-to-buy-and-sell-stock-ii-zhuan-hua-fa-ji/
 *
 * @author dks233
 * @create 2022-06-26-17:32
 */
@SuppressWarnings("ALL")
public class BuyAndSellStock {
    // 问题一暴力解法
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

    // 问题一动态规划
    // dp[index]表示[... index]范围内买卖股票可以获得的最大利润
    // dp[1] 1-0
    // dp[2] Math.max(1-0,2-0,2-1) Math.max(dp[1],nums[2]-Math.min(nums[0],nums[1]))
    // dp[3] Math.max(1-0,2-0,2-1,3-0,3-1,3-2) Math.max(dp[2],nums[3]-Math.min(nums[0],nums[1],nums[2]))
    public static class MethodTwo {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length < 1) {
                return 0;
            }
            int[] dp = new int[prices.length];
            dp[0] = 0;
            int minValue = prices[0];
            for (int index = 1; index < dp.length; index++) {
                dp[index] = Math.max(dp[index - 1], prices[index] - Math.min(minValue, prices[index - 1]));
                minValue = Math.min(minValue, prices[index]);
            }
            return dp[dp.length - 1];
        }
    }

    // 问题二动态规划
    public static class ProblemTwoMethodOne {
        // 上涨交易日都买卖
        // 下降交易日都不买卖
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length < 1) {
                return 0;
            }
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                int temp = prices[i] - prices[i - 1];
                if (temp > 0) {
                    maxProfit += temp;
                }
            }
            return maxProfit;
        }
    }

}
