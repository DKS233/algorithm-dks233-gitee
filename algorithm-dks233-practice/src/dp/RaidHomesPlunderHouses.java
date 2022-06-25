package dp;

/**
 * leetcode198. 打家劫舍
 * leetcode213. 打家劫舍 II
 *
 * @author dks233
 * @create 2022-06-25-16:15
 */
public class RaidHomesPlunderHouses {
    // 问题1
    // 暴力递归
    public static class ProblemOneSolution {
        public int rob(int[] nums) {
            return process(nums, 0);
        }

        // 从index位置开始能获得的最大金额
        public int process(int[] nums, int index) {
            if (index >= nums.length) {
                return 0;
            }
            // 选择当前位置的房屋
            int p1 = nums[index] + process(nums, index + 2);
            // 选择下一个位置的房屋
            int p2 = Integer.MIN_VALUE;
            if (index + 1 < nums.length) {
                p2 = nums[index + 1] + process(nums, index + 3);
            }
            return Math.max(p1, p2);
        }
    }

    // 暴力递归改动态规划
    // 分析可变参数：index：[0,nums.length+2]
    // dp[index]表示从index位置开始打劫能获得的最大金额
    // 时间复杂度：O(N)
    public static class ProblemOneMethodTwo {
        public int rob(int[] nums) {
            int[] dp = new int[nums.length + 3];
            for (int index = nums.length + 2; index >= nums.length; index--) {
                dp[index] = 0;
            }
            // 照抄暴力递归的过程
            for (int index = nums.length - 1; index >= 0; index--) {
                int p1 = nums[index] + dp[index + 2];
                int p2 = Integer.MIN_VALUE;
                if (index + 1 < nums.length) {
                    p2 = nums[index + 1] + dp[index + 3];
                }
                dp[index] = Math.max(p1, p2);
            }
            return dp[0];
        }
    }

    // 问题2
    // 暴力递归
    public static class ProblemTwoSolution {
        public int rob(int[] nums) {
            if (nums == null || nums.length < 1) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            // 抢第一个，不能抢最后一个
            int[] numsOne = new int[nums.length - 1];
            System.arraycopy(nums, 0, numsOne, 0, numsOne.length);
            // 抢最后一个，不能抢第一个
            int[] numsTwo = new int[nums.length - 1];
            System.arraycopy(nums, 1, numsTwo, 0, numsTwo.length);
            // 第一个和最后一个都不抢，这种情况最终结果肯定小于抢一个的结果
            return Math.max(process(numsOne, 0), process(numsTwo, 0));
        }

        // 从index位置开始能获得的最大金额
        public int process(int[] nums, int index) {
            if (index >= nums.length) {
                return 0;
            }
            // 选择当前位置的房屋
            int p1 = nums[index] + process(nums, index + 2);
            // 选择下一个位置的房屋
            int p2 = Integer.MIN_VALUE;
            if (index + 1 < nums.length) {
                p2 = nums[index + 1] + process(nums, index + 3);
            }
            return Math.max(p1, p2);
        }
    }

    // 暴力递归改动态规划
    // 时间复杂度：O(N)
    public static class ProblemTwoMethodTwo {
        public int rob(int[] nums) {
            if (nums == null || nums.length < 1) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            // 抢第一个，不能抢最后一个
            int[] numsOne = new int[nums.length - 1];
            System.arraycopy(nums, 0, numsOne, 0, numsOne.length);
            // 抢最后一个，不能抢第一个
            int[] numsTwo = new int[nums.length - 1];
            System.arraycopy(nums, 1, numsTwo, 0, numsTwo.length);
            return Math.max(getDpResult(numsOne), getDpResult(numsTwo));
        }

        public int getDpResult(int[] nums) {
            int[] dp = new int[nums.length + 3];
            for (int index = nums.length + 2; index >= nums.length; index--) {
                dp[index] = 0;
            }
            // 照抄暴力递归的过程
            for (int index = nums.length - 1; index >= 0; index--) {
                int p1 = nums[index] + dp[index + 2];
                int p2 = Integer.MIN_VALUE;
                if (index + 1 < nums.length) {
                    p2 = nums[index + 1] + dp[index + 3];
                }
                dp[index] = Math.max(p1, p2);
            }
            return dp[0];
        }
    }
}
