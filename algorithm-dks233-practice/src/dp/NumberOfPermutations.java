package dp;

import java.util.HashMap;

/**
 * 剑指offer专项突击版：剑指 Offer II 104. 排列的数目
 *
 * @author dks233
 * @create 2022-07-28-15:42
 */
@SuppressWarnings("ALL")
public class NumberOfPermutations {
    // 暴力递归+记忆化搜索
    public static class MethodOne {
        HashMap<Integer, Integer> map = new HashMap<>();

        public int combinationSum4(int[] nums, int target) {
            return process(nums, target);
        }

        public int process(int[] nums, int rest) {
            if (map.containsKey(rest)) {
                return map.get(rest);
            }
            if (rest < 0) {
                return 0;
            }
            if (rest == 0) {
                return 1;
            }
            int ans = 0;
            for (int index = 0; index < nums.length; index++) {
                ans += process(nums, rest - nums[index]);
            }
            map.put(rest, ans);
            return ans;
        }
    }

    public static class MethodTwo {
        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];
            dp[0] = 1;
            for (int rest = 1; rest < dp.length; rest++) {
                for (int index = 0; index < nums.length; index++) {
                    if (rest - nums[index] >= 0) {
                        dp[rest] += dp[rest - nums[index]];
                    }
                }
            }
            return dp[target];
        }
    }

}
