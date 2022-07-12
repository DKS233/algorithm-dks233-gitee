package dp;

/**
 * leetcode300. 最长递增子序列
 *
 * @author dks233
 * @create 2022-07-12-21:12
 */
@SuppressWarnings("ALL")
public class LongestIncreasingSubsequence {
    // 暴力递归
    public static class MethodOne {
        public int lengthOfLIS(int[] nums) {
            int maxLen = 1;
            for (int index = 0; index < nums.length; index++) {
                maxLen = Math.max(process(nums, index), maxLen);
            }
            return maxLen;
        }

        // 以index位置元素为头的最长递增子序列
        public int process(int[] nums, int index) {
            int ans = 1;
            for (int curIndex = index + 1; curIndex < nums.length; curIndex++) {
                if (nums[curIndex] > nums[index]) {
                    ans = Math.max(1 + process(nums, curIndex), ans);
                }
            }
            return ans;
        }
    }

    // 暴力递归改动态规划
    // dp[index]表示以index为头的最长递增子序列
    // 时间复杂度：O(N*N)
    public static class MethodTwo {
        public int lengthOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            dp[nums.length - 1] = 1;
            for (int index = nums.length - 2; index >= 0; index--) {
                int ans = 1;
                for (int curIndex = index + 1; curIndex < nums.length; curIndex++) {
                    if (nums[curIndex] > nums[index]) {
                        ans = Math.max(1 + dp[curIndex], ans);
                    }
                }
                dp[index] = ans;
            }
            int ans = 1;
            for (int index = 0; index < dp.length; index++) {
                ans = Math.max(dp[index], ans);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        MethodOne methodOne = new MethodOne();
        System.out.println(methodOne.lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9}));
    }
}
