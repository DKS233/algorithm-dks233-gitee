package class20;

/**
 * leetcode516：最长回文子序列
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列
 *
 * @author dks233
 * @create 2022-05-19-10:29
 */
@SuppressWarnings("ALL")
public class LongestPalindromeSubseq {
    // 方法1求最长回文子序列
    // 原字符串和逆序串的最长公共子序列就是最长回文子序列
    // 最长公共子序列：LongestCommonSubsequence.java
    public static class MethodOne {
        public static int longestPalindromeSubseq(String word) {
            if (word == null || word.length() == 0) {
                return 0;
            }
            return longestCommonSubsequence(reverse(word), word);
        }

        public static String reverse(String word) {
            char[] str = word.toCharArray();
            char[] reverse = new char[str.length];
            for (int i = 0; i < str.length; i++) {
                reverse[i] = str[str.length - 1 - i];
            }
            return String.valueOf(reverse);
        }

        public static int longestCommonSubsequence(String strOne, String strTwo) {
            if (strOne == null || strOne.length() == 0 || strTwo == null || strTwo.length() == 0) {
                return 0;
            }
            char[] str1 = strOne.toCharArray();
            char[] str2 = strTwo.toCharArray();
            int[][] dp = new int[str1.length][str2.length];
            dp[0][0] = str1[0] == str2[0] ? 1 : 0;
            // 先填0行0列
            for (int j = 1; j < dp[0].length; j++) {
                dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
            }
            for (int i = 1; i < dp.length; i++) {
                dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
            }
            // 根据依赖关系填剩余行和列
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    int p1 = dp[i - 1][j];
                    int p2 = dp[i][j - 1];
                    int p3 = str1[i] == str2[j] ? 1 + dp[i - 1][j - 1] : 0;
                    dp[i][j] = Math.max(p1, Math.max(p2, p3));
                }
            }
            return dp[str1.length - 1][str2.length - 1];
        }
    }

    // 方法2：暴力递归
    public static class MethodTwo {
        public static int longestPalindromeSubseq(String word) {
            if (word == null || word.length() == 0) {
                return 0;
            }
            return process(word.toCharArray(), 0, word.toCharArray().length - 1);
        }

        // [left,right]范围上最长回文子序列
        public static int process(char[] str, int left, int right) {
            // [left,right]范围内只有一个字符
            if (left == right) {
                return 1;
            }
            // [left,right]范围内有两个字符
            if (left == right - 1) {
                return str[left] == str[right] ? 2 : 1;
            }
            // [left,right]范围内有至少三个字符
            // 情况1：最长回文子序列以str[left]开头，以str[right]结尾
            int p1 = str[left] == str[right] ? 2 + process(str, left + 1, right - 1) : 0;
            // 情况2：最长回文子序列以str[left]开头，不以str[right]结尾
            int p2 = process(str, left, right - 1);
            // 情况3：最长回文子序列不以str[left]开头，以str[right]结尾
            int p3 = process(str, left + 1, right);
            // 情况4：最长回文子序列不以str[left]开头，不以str[right]结尾
            int p4 = process(str, left + 1, right - 1);
            return Math.max(Math.max(p1, p2), Math.max(p3, p4));
        }
    }

    // 动态规划
    // 可变参数left和right，建立二维表
    // 分析位置依赖关系，填表
    // 图解：最长回文子序列-动态规划.drawio
    public static class MethodThree {
        public static int longestPalindromeSubseq(String word) {
            if (word == null || word.length() == 0) {
                return 0;
            }
            char[] str = word.toCharArray();
            int[][] dp = new int[str.length][str.length];
            // 填left==right位置
            for (int i = 0; i < dp.length; i++) {
                dp[i][i] = 1;
            }
            // 填left=right-1位置
            for (int i = 0; i < dp.length - 1; i++) {
                dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
            }
            // 填剩余位置
            // (0,2)->(1,3)->(2,4)->(3,5)
            // (0,3)->(1,4)->(2,5)
            // (0,4)->(1,5)
            // (0,5)
            for (int gap = 2; gap < dp.length; gap++) {
                for (int i = 0; i < dp.length - gap; i++) {
                    int left = i;
                    int right = i + gap;
                    int p1 = str[left] == str[right] ? 2 + dp[left + 1][right - 1] : 0;
                    int p2 = dp[left][right - 1];
                    int p3 = dp[left + 1][right];
                    int p4 = dp[left + 1][right - 1];
                    dp[left][right] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
                }
            }
            return dp[0][dp[0].length - 1];
        }
    }

    public static void main(String[] args) {
        MethodThree.longestPalindromeSubseq("bbbabd");
    }
}













