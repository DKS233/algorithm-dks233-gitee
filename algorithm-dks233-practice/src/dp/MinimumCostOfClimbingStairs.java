package dp;

/**
 * 剑指offer专项突击版：剑指 Offer II 088. 爬楼梯的最少成本
 *
 * @author dks233
 * @create 2022-07-23-11:51
 */
@SuppressWarnings("ALL")
public class MinimumCostOfClimbingStairs {
    // 注：到了length-1位置没上楼梯，需要走到length位置
    public static class MethodOne {
        public int minCostClimbingStairs(int[] cost) {
            return Math.min(process(cost, 0), process(cost, 1));
        }

        // 从index位置开始的最小成本
        public int process(int[] cost, int index) {
            if (index == cost.length) {
                return 0;
            }
            // 爬一格
            int p1 = Integer.MAX_VALUE;
            if (index + 1 <= cost.length) {
                p1 = cost[index] + process(cost, index + 1);
            }
            // 爬两格
            int p2 = Integer.MAX_VALUE;
            if (index + 2 <= cost.length) {
                p2 = cost[index] + process(cost, index + 2);
            }
            return Math.min(p1, p2);
        }
    }

    public static class MethodTwo {
        public int minCostClimbingStairs(int[] cost) {
            int[] dp = new int[cost.length + 1];
            dp[cost.length] = 0;
            for (int index = cost.length - 1; index >= 0; index--) {
                int p1 = cost[index] + dp[index + 1];
                int p2 = Integer.MAX_VALUE;
                if (index + 2 <= cost.length) {
                    p2 = cost[index] + dp[index + 2];
                }
                dp[index] = Math.min(p1, p2);
            }
            return Math.min(dp[0], dp[1]);
        }

    }
}
