package dp;

/**
 * leetcode1143. 最长公共子序列
 *
 * @author dks233
 * @create 2022-08-23-11:43
 */
public class LongestCommonSubsequence {
    public static class MethodOne {
        public int longestCommonSubsequence(String s1, String s2) {
            return process(s1.toCharArray(), s2.toCharArray(), 0, 0);
        }

        // [index1...] [index2...] 范围内的最长公共子序列
        public int process(char[] s1, char[] s2, int index1, int index2) {
            if (index1 == s1.length || index2 == s2.length) {
                return 0;
            }
            int result = 0;
            if (s1[index1] == s2[index2]) {
                result = Math.max(process(s1, s2, index1 + 1, index2 + 1) + 1, result);
            }
            result = Math.max(process(s1, s2, index1 + 1, index2), result);
            result = Math.max(process(s1, s2, index1, index2 + 1), result);
            result = Math.max(process(s1, s2, index1 + 1, index2 + 1), result);
            return result;
        }
    }

    public static class MethodTwo {
        public int longestCommonSubsequence(String s1, String s2) {
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            for (int index1 = s1.length() - 1; index1 >= 0; index1--) {
                for (int index2 = s2.length() - 1; index2 >= 0; index2--) {
                    int result = 0;
                    if (s1.charAt(index1) == s2.charAt(index2)) {
                        result = Math.max(dp[index1 + 1][index2 + 1] + 1, result);
                    }
                    result = Math.max(dp[index1 + 1][index2], result);
                    result = Math.max(dp[index1][index2 + 1], result);
                    result = Math.max(dp[index1 + 1][index2], result);
                    dp[index1][index2] = result;
                }
            }
            return dp[0][0];
        }

    }
}
