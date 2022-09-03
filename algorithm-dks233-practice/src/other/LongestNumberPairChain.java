package other;

import java.util.Arrays;

/**
 * leetcode646. 最长数对链
 *
 * @author dks233
 * @create 2022-09-03-14:21
 */
@SuppressWarnings("ALL")
public class LongestNumberPairChain {
    // 方法2：暴力递归改动态规划
    public static class MethodTwo {
        public int findLongestChain(int[][] pairs) {
            // 按照起始的数排序，不然有可能后续有些数取不到
            Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
            int[] dp = new int[pairs.length + 1];
            for (int index = pairs.length - 1; index >= 0; index--) {
                int result = 1;
                for (int next = index + 1; next < pairs.length; next++) {
                    if (pairs[next][0] > pairs[index][1]) {
                        result = Math.max(1 + dp[next], result);
                    }
                }
                result = Math.max(result, dp[index + 1]);
                dp[index] = result;
            }
            return dp[0];
        }
    }

    // 方法1：暴力递归
    public static class MethodOne {
        public int findLongestChain(int[][] pairs) {
            // 按照起始的数排序，不然有可能后续有些数取不到
            Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
            return process(pairs, 0);
        }

        // [index...]能组成多少符合条件的数对链
        public int process(int[][] nums, int index) {
            if (index == nums.length) {
                return 0;
            }
            int result = 1;
            // 选当前数对
            for (int next = index + 1; next < nums.length; next++) {
                if (nums[next][0] > nums[index][1]) {
                    result = Math.max(1 + process(nums, next), result);
                }
            }
            // 不选当前数对
            result = Math.max(result, process(nums, index + 1));
            return result;
        }
    }

}
