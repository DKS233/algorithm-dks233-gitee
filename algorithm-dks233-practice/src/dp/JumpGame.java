package dp;

/**
 * leetcode55. 跳跃游戏
 * leetcode45. 跳跃游戏 II
 *
 * @author dks233
 * @create 2022-09-12-8:51
 */
@SuppressWarnings("ALL")
public class JumpGame {
    // 题目1：暴力递归
    public static class ProblemOneMethodOne {
        public boolean canJump(int[] nums) {
            return process(nums, 0);
        }

        // index位置出发能否到达下一个下标
        public boolean process(int[] nums, int index) {
            if (index == nums.length - 1) {
                return true;
            }
            if (index >= nums.length) {
                return false;
            }
            boolean result = false;
            // 当前位置可以选择走[1,nums[index]]步
            for (int step = 1; step <= nums[index]; step++) {
                result |= process(nums, index + step);
            }
            return result;
        }
    }

    // 题目1：暴力递归改动态规划
    public static class ProblemOneMethodTwo {
        public boolean canJump(int[] nums) {
            boolean[] dp = new boolean[nums.length];
            dp[nums.length - 1] = true;
            for (int index = nums.length - 2; index >= 0; index--) {
                boolean result = false;
                for (int step = 1; step <= nums[index]; step++) {
                    if (index + step < nums.length) {
                        result |= dp[index + step];
                        if (result) {
                            dp[index] = true;
                            break;
                        }
                    }
                }
                dp[index] = result;
            }
            return dp[0];
        }
    }

    // 题目1：维护可以跳到的最远距离，如果可以跳到的最远距离>=nums.lenght-1，可以直接跳
    public static class ProblemOneMethodThree {
        public boolean canJump(int[] nums) {
            int maxDistance = 0;
            for (int i = 0; i < nums.length; i++) {
                // 第i个元素能跳到的最远距离
                int cur = nums[i] + i;
                // 更新最远距离
                maxDistance = Math.max(maxDistance, cur);
                // 如果最远距离大于等于nums.length-1，说明可以直接跳到最后一个位置
                if (maxDistance >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }
    }

    // 题目2：暴力递归
    // 题目前提：假设你总是可以到达数组的最后一个位置
    public static class ProblemTwoMethodOne {
        public int jump(int[] nums) {
            return process(nums, 0);
        }

        // index出发能到达终点的最少跳跃次数
        public int process(int[] nums, int index) {
            if (index == nums.length - 1) {
                return 0;
            }
            int result = 10000;
            for (int step = 1; step <= nums[index]; step++) {
                if (index + step < nums.length) {
                    result = Math.min(1 + process(nums, index + step), result);
                }
            }
            return result;
        }
    }

    // 题目2：暴力递归改动态规划
    public static class ProblemTwoMethodTwo {
        public int jump(int[] nums) {
            int[] dp = new int[nums.length];
            dp[nums.length - 1] = 0;
            for (int index = nums.length - 2; index >= 0; index--) {
                int result = 10000;
                for (int step = 1; step <= nums[index]; step++) {
                    if (index + step < nums.length) {
                        result = Math.min(1 + dp[index + step], result);
                    }
                }
                dp[index] = result;
            }
            return dp[0];
        }
    }
}


















