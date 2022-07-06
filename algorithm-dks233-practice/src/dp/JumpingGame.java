package dp;


/**
 * leetcode55:跳跃游戏
 * 参考文档：https://leetcode.cn/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/
 *
 * @author dks233
 * @create 2022-07-06-22:41
 */
@SuppressWarnings("ALL")
public class JumpingGame {
    // 此题最优解，贪心算法
    // 更新最远可以到达的位置maxIndex
    // 复杂度：O(N)+O(1)
    public static class BetterMethod {
        public boolean canJump(int[] nums) {
            int maxIndex = 0;
            for (int index = 0; index < nums.length; index++) {
                // index不在最远可以到达的位置范围内
                if (index > maxIndex) {
                    return false;
                }
                // index在最远可以到达的位置范围内，更新maxIndex
                maxIndex = Math.max(maxIndex, index + nums[index]);
            }
            return maxIndex >= nums.length - 1;
        }
    }

    // 暴力递归
    public static class MethodOne {
        public boolean canJump(int[] nums) {
            return process(nums, 0);
        }

        // index：当前遍历到的位置
        // 返回：index出发能否到达终点
        // 设定：一直往右跳，往左跳没意义
        public boolean process(int[] nums, int index) {
            if (index == nums.length - 1) {
                return true;
            }
            // 从当前位置跳，最多可以跳num[index]步，但是nums[index]==0时，index不能跳，肯定到不了终点，直接返回false
            if (nums[index] == 0) {
                return false;
            }
            boolean ans = false;
            for (int step = 1; step <= nums[index] && index + step < nums.length; step++) {
                ans |= process(nums, index + step);
                if (ans) {
                    break;
                }
            }
            return ans;
        }
    }

    // 暴力递归改动态规划
    // dp[index]表示从index位置出发是否可以到达终点
    // 分析时间复杂度：O(max(step)*N) N是数组长度
    public static class MethodTwo {
        public boolean canJump(int[] nums) {
            boolean[] dp = new boolean[nums.length];
            dp[nums.length - 1] = true;
            for (int index = nums.length - 2; index >= 0; index--) {
                if (nums[index] == 0) {
                    dp[index] = false;
                } else {
                    boolean ans = false;
                    for (int step = 1; step <= nums[index] && index + step < nums.length; step++) {
                        ans |= dp[index + step];
                        if (ans) {
                            break;
                        }
                    }
                    dp[index] = ans;
                }
            }
            return dp[0];
        }
    }
}
