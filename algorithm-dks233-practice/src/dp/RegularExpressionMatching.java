package dp;

/**
 * 剑指 Offer 19. 正则表达式匹配
 * 参考文档：https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/solution/zhu-xing-xiang-xi-jiang-jie-you-qian-ru-shen-by-je/
 *
 * @author dks233
 * @create 2022-06-21-11:36
 */
public class RegularExpressionMatching {
    // dp[i][j] s的前i个字符和p的前j个字符是否匹配
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        // 空串，空正则
        dp[0][0] = true;
        // 非空串，非空正则
        for (int i = 1; i <= n; i++) {
            dp[i][0] = false;
        }
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 情况1
                if (i > 0 && p.charAt(j - 1) != '*' && p.charAt(j - 1) != '.' && s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 情况2
                else if (i > 0 && p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 情况3
                else if (p.charAt(j - 1) == '*') {
                    // 情况3的两种情况可以看成是：看c*和不看c*
                    // 看c*和不看c*的dp[i][j]两个if语句可能发生覆盖，所以得用|=
                    // 不看c*，直接废掉p的最后两个字符（这种情况适用于s为空串的情况）
                    if (j >= 2) {
                        dp[i][j] |= dp[i][j - 2];
                    }
                    // 看c*，看s的最后一个字符和j-2第字符是否匹配
                    if (j >= 2 && i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }
        return dp[n][m];
    }
}
