package dp;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;

/**
 * leetcode139. 单词拆分
 *
 * @author dks233
 * @create 2022-07-12-10:32
 */
@SuppressWarnings("ALL")
public class WordSplitting {
    // 暴力递归
    public static class MethodOne {
        public boolean wordBreak(String str, List<String> wordDict) {
            HashSet<String> set = new HashSet<>(wordDict);
            return process(str, 0, set);
        }

        // [index,..]位置的字符能否被拆分
        public boolean process(String str, int index, HashSet<String> set) {
            if (index == str.length()) {
                return true;
            }
            boolean ans = false;
            for (int end = index; end < str.length(); end++) {
                if (set.contains(str.substring(index, end + 1))) {
                    ans |= process(str, end + 1, set);
                }
            }
            return ans;
        }

    }

    // 动态规划
    // dp[index..]能不能被list中的单词拆分
    public static class MethodTwo {
        public boolean wordBreak(String str, List<String> wordDict) {
            boolean[] dp = new boolean[str.length() + 1];
            HashSet<String> set = new HashSet<>(wordDict);
            dp[str.length()] = true;
            for (int index = str.length() - 1; index >= 0; index--) {
                dp[index] = false;
                for (int end = index; end < str.length(); end++) {
                    if (set.contains(str.substring(index, end + 1))) {
                        dp[index] |= dp[end + 1];
                    }
                }
            }
            return dp[0];
        }
    }
}
