package dp;

/**
 * leetcode53.最大子数组和
 * 给定一个整数数组，找出一个具有最大和的连续子数组（子数组最少包含一个元素）
 *
 * @author dks233
 * @create 2022-07-04-20:28
 */
@SuppressWarnings("ALL")
public class MaxSubArraySum {
    // dp[index] 表示以index为结尾的最大连续子数组的和
    // dp[0]=nums[0]
    // 时间复杂度：O(N)
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int index = 1; index < dp.length; index++) {
            dp[index] = dp[index - 1] > 0 ? dp[index - 1] + nums[index] : nums[index];
        }
        int ans = Integer.MIN_VALUE;
        for (int index = 0; index < dp.length; index++) {
            ans = Math.max(dp[index], ans);
        }
        return ans;
    }
}
