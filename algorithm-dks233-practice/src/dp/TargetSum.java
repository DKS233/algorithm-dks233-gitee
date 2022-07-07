package dp;

/**
 * leetcode494. 目标和
 *
 * @author dks233
 * @create 2022-07-07-15:55
 */
@SuppressWarnings("ALL")
public class TargetSum {
    // 暴力递归
    public static class MethodOne {
        public int findTargetSumWays(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            return process(nums, target, 0);
        }

        // 返回：[index,...]能组成rest的组合个数
        public int process(int[] nums, int rest, int index) {
            // 越界，rest等于0说明之前做的选择是有效的，组合个数累加1
            if (rest == 0 && index == nums.length) {
                return 1;
            }
            // 越界，但是rest不等于0，组合个数不累加
            if (index >= nums.length) {
                return 0;
            }
            // 加当前数，去下一个位置做决定
            int p1 = process(nums, rest + nums[index], index + 1);
            // 减当前数，去下一个位置做决定
            int p2 = process(nums, rest - nums[index], index + 1);
            return p1 + p2;
        }
    }

    // 暴力递归改动态规划
    // dp[rest][index] 表示[index,...]能组成rest的组合个数
    // rest取值范围:[target-sum,target+sum]  target是数组和
    // index取值范围:[0,nums.length]
    // 分析位置依赖：每列都依赖下一列
    public static class MethodTwo {
        // 两种情况需要排序：
        // 情况1：target为负数，sum小于target绝对值
        // 情况2：target为正数，sum小于target
        // 情况1和情况2合起来：sum小于target绝对值，组合树肯定是0，直接返回0
        // 由于用到数组，将rest转换成正数区间内，两边同时加sum-target，将rest范围转换为[0,sum*2]
        // 按暴力递归：dp[rest]=dp[rest+nums[index]][index+1]+dp[rest-nums[index]][index+1]
        // rest∈[target-sum,target+sum]
        // 转到正数区间：dp[rest+sum-target]=dp[rest+nums[index]+sum-target][index+1]+dp[rest-nums[index]+sum-target][index+1]
        // rest∈[target-sum,target+sum]
        public int findTargetSumWays(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            if (sum < Math.abs(target)) {
                return 0;
            }
            int[][] dp = new int[2 * sum + 1][nums.length + 1];
            dp[sum - target][nums.length] = 1;
            for (int index = nums.length - 1; index >= 0; index--) {
                for (int rest = target - sum; rest <= target + sum; rest++) {
                    int p1 = 0;
                    int conditionOne = rest + nums[index] + sum - target;
                    if (conditionOne >= 0 && conditionOne <= sum * 2) {
                        p1 = dp[conditionOne][index + 1];
                    }
                    int p2 = 0;
                    int conditionTwo = rest - nums[index] + sum - target;
                    if (conditionTwo >= 0 && conditionTwo <= sum * 2) {
                        p2 = dp[conditionTwo][index + 1];
                    }
                    dp[rest + sum - target][index] = p1 + p2;
                }
            }
            return dp[sum][0];
        }
    }


    public static void main(String[] args) {
        MethodTwo methodTwo = new MethodTwo();
        int[] nums = new int[]{1, 1, 1, 1, 1};
        System.out.println(methodTwo.findTargetSumWays(nums, 3));
    }
}
