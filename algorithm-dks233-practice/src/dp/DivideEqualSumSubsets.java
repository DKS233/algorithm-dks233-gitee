package dp;

/**
 * leetcode416. 分割等和子集
 *
 * @author dks233
 * @create 2022-07-13-9:24
 */
public class DivideEqualSumSubsets {
    // 暴力递归
    public static class MethodOne {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int number : nums) {
                sum += number;
            }
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            return process(nums, 0, target);
        }

        // [index,...]能否组成rest
        public boolean process(int[] nums, int index, int rest) {
            if (index == nums.length) {
                return rest == 0;
            }
            if (rest == 0) {
                return true;
            }
            boolean ans = false;
            // 要index位置的数
            if (rest - nums[index] >= 0) {
                ans |= process(nums, index + 1, rest - nums[index]);
            }
            // 不要index位置的数
            ans |= process(nums, index + 1, rest);
            return ans;
        }
    }

    // 暴力递归改动态规划
    // dp[index][rest] [index...]能否组成rest
    public static class MethodTwo {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int number : nums) {
                sum += number;
            }
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            boolean[][] dp = new boolean[nums.length + 1][target + 1];
            dp[nums.length][0] = true;
            for (int index = 0; index < nums.length; index++) {
                dp[index][0] = true;
            }
            for (int index = nums.length - 1; index >= 0; index--) {
                for (int rest = 0; rest <= target; rest++) {
                    dp[index][rest] = false;
                    if (rest - nums[index] >= 0) {
                        dp[index][rest] |= dp[index + 1][rest - nums[index]];
                    }
                    dp[index][rest] |= dp[index + 1][rest];
                }
            }
            return dp[0][target];
        }
    }
}
