package week305;

/**
 * leetcode6137. 检查数组是否存在有效划分
 * 参考：https://www.bilibili.com/video/BV1CN4y1V7uE?spm_id_from=333.999.0.0&vd_source=cd2d9ceb11856332f08fec74b6d46d0d
 *
 * @author dks233
 * @create 2022-08-07-11:04
 */
@SuppressWarnings("ALL")
public class Three {
    // 暴力递归
    public static class MethodOne {
        public boolean validPartition(int[] nums) {
            int index = 0;
            return process(nums, 0);
        }

        // index出发能否到达到达数组边界
        public boolean process(int[] nums, int index) {
            if (index == nums.length) {
                return true;
            }
            boolean ans = false;
            if (index + 1 < nums.length && nums[index] == nums[index + 1]) {
                ans |= process(nums, index + 2);
            }
            if (index + 1 < nums.length && index + 2 < nums.length && nums[index] == nums[index + 1] && nums[index + 1] == nums[index + 2]) {
                ans |= process(nums, index + 3);
            }
            if (index + 1 < nums.length && index + 2 < nums.length && nums[index] + 1 == nums[index + 1] && nums[index + 1] + 1 == nums[index + 2]) {
                ans |= process(nums, index + 3);
            }
            return ans;
        }
    }

    // 暴力递归改动态规划
    // dp[index] index出发能否到达到达数组边界
    public static class MethodTwo {
        public boolean validPartition(int[] nums) {
            boolean[] dp = new boolean[nums.length + 1];
            dp[nums.length] = true;
            for (int index = nums.length - 1; index >= 0; index--) {
                boolean ans = false;
                if (index + 1 < nums.length && nums[index] == nums[index + 1]) {
                    ans |= dp[index + 2];
                }
                if (index + 1 < nums.length && index + 2 < nums.length && nums[index] == nums[index + 1] && nums[index + 1] == nums[index + 2]) {
                    ans |= dp[index + 3];
                }
                if (index + 1 < nums.length && index + 2 < nums.length && nums[index] + 1 == nums[index + 1] && nums[index + 1] + 1 == nums[index + 2]) {
                    ans |= dp[index + 3];
                }
                dp[index] = ans;
            }
            return dp[0];
        }
    }
}
