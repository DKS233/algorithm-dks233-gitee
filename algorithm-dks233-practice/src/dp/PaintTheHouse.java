package dp;

/**
 * 剑指offer专项突击版：剑指 Offer II 091. 粉刷房子
 *
 * @author dks233
 * @create 2022-07-25-13:21
 */
public class PaintTheHouse {

    public int minCost(int[][] costs) {
        // dp[count][color] 第count个房屋涂color颜色，加上之前的房屋，所花费的最小成本
        int[][] dp = new int[costs.length][costs[0].length];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int count = 1; count < dp.length; count++) {
            dp[count][0] = Math.min(dp[count - 1][1], dp[count - 1][2]) + costs[count][0];
            dp[count][1] = Math.min(dp[count - 1][0], dp[count - 1][2]) + costs[count][1];
            dp[count][2] = Math.min(dp[count - 1][0], dp[count - 1][1]) + costs[count][2];
        }
        return Math.min(Math.min(dp[dp.length - 1][0], dp[dp.length - 1][1]), dp[dp.length - 1][2]);
    }

    public static class MethodTwo {
        // 原地修改数组
        public int minCost(int[][] costs) {
            // costs[count][color] 第count个房屋涂color颜色，加上之前的房屋，所花费的最小成本
            for (int count = 1; count < costs.length; count++) {
                costs[count][0] = Math.min(costs[count - 1][1], costs[count - 1][2]) + costs[count][0];
                costs[count][1] = Math.min(costs[count - 1][0], costs[count - 1][2]) + costs[count][1];
                costs[count][2] = Math.min(costs[count - 1][0], costs[count - 1][1]) + costs[count][2];
            }
            return Math.min(Math.min(costs[costs.length - 1][0], costs[costs.length - 1][1]), costs[costs.length - 1][2]);
        }
    }

}
