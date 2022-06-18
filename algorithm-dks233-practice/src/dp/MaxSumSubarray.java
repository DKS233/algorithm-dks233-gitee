package dp;

/**
 * 剑指 Offer第二版  42. 连续子数组的最大和
 * 参考文档：https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/cong-bao-li-po-jie-dao-dong-tai-gui-hua-yfvkp/
 *
 * @author dks233
 * @create 2022-06-18-10:47
 */
public class MaxSumSubarray {
    // 暴力破解
    // 时间复杂度：O(N*N*N)
    public static class MethodOne {
        public int maxSubArray(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            int ans = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j < nums.length; j++) {
                    ans = Math.max(getSubArray(nums, i, j), ans);
                }
            }
            return ans;
        }

        public int getSubArray(int[] nums, int left, int right) {
            int sum = 0;
            for (int index = left; index <= right; index++) {
                sum += nums[index];
            }
            return sum;
        }
    }

    // 动态规划
    // 时间复杂度：O(N)
    public static class MethodTwo {
        public int maxSubArray(int[] nums) {
            // dp[i] 表示以i位置为结尾的子数组最大和
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            for (int i = 1; i < dp.length; i++) {
                dp[i] = dp[i - 1] <= 0 ? nums[i] : nums[i] + dp[i - 1];
            }
            int ans = Integer.MIN_VALUE;
            for (int i = 0; i < dp.length; i++) {
                ans = Math.max(ans, dp[i]);
            }
            return ans;
        }
    }
}
