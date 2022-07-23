package dp;

/**
 * 剑指offer专项突击版：剑指 Offer II 089. 房屋偷盗
 * 剑指offer专项突击版：剑指 Offer II 090. 环形房屋偷盗
 *
 * @author dks233
 * @create 2022-07-23-11:26
 */
public class StealHouse {
    public static class ProblemOneMethodOne {
        public int rob(int[] nums) {
            return process(nums, 0);
        }

        public int process(int[] nums, int index) {
            if (index >= nums.length) {
                return 0;
            }
            // 偷当前房屋
            int p1 = nums[index] + process(nums, index + 2);
            // 不偷当前房屋
            int p2 = process(nums, index + 1);
            return Math.max(p1, p2);
        }
    }

    public static class ProblemOneMethodTwo {
        public int rob(int[] nums) {
            int[] dp = new int[nums.length + 1];
            for (int index = nums.length - 1; index >= 0; index--) {
                int p1 = nums[index] + ((index + 2 < dp.length) ? dp[index + 2] : 0);
                int p2 = dp[index + 1];
                dp[index] = Math.max(p1, p2);
            }
            return dp[0];
        }
    }

    // 类比问题一：不能同时偷第一个和最后一个
    public static class ProblemTwo {
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }
            int[] arr1 = new int[nums.length - 1];
            int[] arr2 = new int[nums.length - 1];
            System.arraycopy(nums, 0, arr1, 0, nums.length - 1);
            System.arraycopy(nums, 1, arr2, 0, nums.length - 1);
            return Math.max(process(arr1), process(arr2));
        }

        public int process(int[] nums) {
            int[] dp = new int[nums.length + 1];
            for (int index = nums.length - 1; index >= 0; index--) {
                int p1 = nums[index] + ((index + 2 < dp.length) ? dp[index + 2] : 0);
                int p2 = dp[index + 1];
                dp[index] = Math.max(p1, p2);
            }
            return dp[0];
        }
    }
}
