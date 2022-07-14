package dp;

/**
 * leetcode309. 最佳买卖股票时机含冷冻期
 * 参考文档：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/fei-zhuang-tai-ji-de-dpjiang-jie-chao-ji-tong-su-y/
 *
 * @author dks233
 * @create 2022-07-14-10:56
 */
@SuppressWarnings("ALL")
public class StockTrading {
    // 当天三种状态 dp[index][column] 当天该状态下获得的最大利润
    // dp[index][0] 持有股票
    // dp[index][1] 不持有股票，且当天没卖出
    // dp[index][2] 不持有股票，且当天卖出了
    public int maxProfit(int[] nums) {
        int[][] dp = new int[nums.length][3];
        // 初始化
        dp[0][0] = -nums[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        for (int index = 1; index < nums.length; index++) {
            // 状态1：当天持有股票
            // 情况1：继承昨天的股票，昨天肯定是持股的
            // 情况2：昨天不持股且没卖出，今天买入了
            dp[index][0] = Math.max(dp[index - 1][0], dp[index - 1][1] - nums[index]);
            // 状态2：当天不持有股票，且当天没卖出
            // 情况1：昨天不持股且没卖出
            // 情况2：昨天不持股且卖出了
            dp[index][1] = Math.max(dp[index - 1][1], dp[index - 1][2]);
            // 状态3：当天不持有股票，且当天卖出了
            // 情况1：昨天持股，今天卖出
            dp[index][2] = dp[index - 1][0] + nums[index];
        }
        // 最后一天不持股的两种情况求最大值
        return Math.max(dp[nums.length - 1][1], dp[nums.length - 1][2]);
    }
}
