package dp;

import java.util.PriorityQueue;

/**
 * leetcode516. 最长回文子序列
 *
 * @author dks233
 * @create 2022-08-23-15:07
 */
public class LongestPalindromeSubsequence {
    public static class MethodOne {
        public int longestPalindromeSubseq(String s) {
            char[] str = s.toCharArray();
            return process(str, 0, str.length - 1);
        }

        // [left,right]范围内的最长回文子序列
        public int process(char[] str, int left, int right) {
            if (left == right) {
                return 1;
            }
            if (left > right) {
                return 0;
            }
            int result = 0;
            if (str[left] == str[right]) {
                result = Math.max(process(str, left + 1, right - 1) + 2, result);
            }
            result = Math.max(process(str, left + 1, right), result);
            result = Math.max(process(str, left, right - 1), result);
            result = Math.max(process(str, left + 1, right - 1), result);
            return result;
        }
    }

    public static class MethodTwo {
        public int longestPalindromeSubseq(String s) {
            char[] str = s.toCharArray();
            int[][] dp = new int[str.length][str.length];
            for (int left = dp.length - 1; left >= 0; left--) {
                dp[left][left] = 1;
            }
            for (int right = 1; right < dp[0].length; right++) {
                for (int left = right - 1; left >= 0; left--) {
                    int result = 0;
                    if (str[left] == str[right]) {
                        result = Math.max(dp[left + 1][right - 1] + 2, result);
                    }
                    result = Math.max(dp[left + 1][right], result);
                    result = Math.max(dp[left][right - 1], result);
                    result = Math.max(dp[left + 1][right - 1], result);
                    dp[left][right] = result;
                }
            }
            return dp[0][str.length - 1];
        }
    }
}
