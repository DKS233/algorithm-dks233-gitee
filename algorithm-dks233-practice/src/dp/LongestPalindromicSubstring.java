package dp;

/**
 * leetcode5. 最长回文子串
 *
 * @author dks233
 * @create 2022-06-29-22:48
 */
public class LongestPalindromicSubstring {
    // 动态规划
    // dp[left][right]表示[left,right]是不是回文子串
    // dp[left][right]=str[left]==str[right]&&dp[left+1][right-1]，即依赖左下角
    // 当right-left+1==2或right-left+1==3时，dp[left][right]=str[left]==str[right]
    // O(N*N)
    public static class MethodTwo {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            char[] str = s.toCharArray();
            boolean[][] dp = new boolean[str.length][str.length];
            // 记录最长回文子串开始索引和长度
            int maxLen = 1;
            int beginIndex = 0;
            // 先填对角线
            for (int left = 0; left < str.length; left++) {
                dp[left][left] = true;
            }
            // 根据位置依赖关系填其他位置
            // 沿对角线填，假设字符串长度为5
            // (0,1)-(1,2)-(2,3)-(3,4)
            // (0,2)-(1,3)-(2,4)
            // (0,3)-(1,4)
            // (0,4)
            for (int gap = 1; gap < str.length; gap++) {
                for (int left = 0; left < str.length - gap; left++) {
                    int right = left + gap;
                    if (str[left] == str[right]) {
                        if (right - left + 1 == 2 || right - left + 1 == 3) {
                            dp[left][right] = true;
                        } else {
                            dp[left][right] = dp[left + 1][right - 1];
                        }
                        if (dp[left][right] && (right - left + 1) > maxLen) {
                            beginIndex = left;
                            maxLen = right - left + 1;
                        }
                    }
                }
            }
            return s.substring(beginIndex, beginIndex + maxLen);
        }
    }

    // 暴力解法
    // O(N*N*N)
    public static class MethodOne {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            char[] str = s.toCharArray();
            String ans = s.substring(0, 1);
            for (int left = 0; left < str.length; left++) {
                for (int right = left + 1; right < str.length; right++) {
                    if (isPalindromicSubstring(str, left, right)) {
                        ans = (right - left + 1) > ans.length() ? s.substring(left, right + 1) : ans;
                    }
                }
            }
            return ans;
        }

        public boolean isPalindromicSubstring(char[] str, int left, int right) {
            while (left < right) {
                if (str[left] != str[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }

    // 暴力解法优化
    // 没必要每次都截取子串，记录起始位置和长度即可
    // O(N*N*N)
    public static class AnotherMethodOne {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            char[] str = s.toCharArray();
            int beginIndex = 0;
            int maxLen = 1;
            for (int left = 0; left < str.length; left++) {
                for (int right = left + 1; right < str.length; right++) {
                    if (isPalindromicSubstring(str, left, right) && (right - left + 1) > maxLen) {
                        beginIndex = left;
                        maxLen = right - left + 1;
                    }
                }
            }
            return s.substring(beginIndex, beginIndex + maxLen);
        }

        public boolean isPalindromicSubstring(char[] str, int left, int right) {
            while (left < right) {
                if (str[left] != str[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
}
