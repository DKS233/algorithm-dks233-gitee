package class19;

/**
 * leetcode1143：最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符
 * （也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 图解：最长公共子序列-动态规划.drawio
 *
 * @author dks233
 * @create 2022-05-19-8:37
 */
public class LongestCommonSubsequence {

    // 暴力递归
    public int longestCommonSubsequenceOne(String strOne, String strTwo) {
        if (strOne == null || strOne.length() == 0 || strTwo == null || strTwo.length() == 0) {
            return 0;
        }
        char[] str1 = strOne.toCharArray();
        char[] str2 = strTwo.toCharArray();
        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    // 返回str1[0, i]和str2[0,j]的最长公共子序列
    // 出现最长公共子序列的三种情况，三种情况有重复
    // 情况1：最长公共子序列不以i结尾，可能以j结尾
    // 情况2：最长公共子序列可能以i结尾，不以j结尾
    // 情况3：最长公共子序列以i结尾，以j结尾
    // 也可以写成4种（不+以，以+不，不+不，以+以）
    public int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            // i==0 j!=0 且 str1[0]==str2[j]
            if (str1[0] == str2[j]) {
                return 1;
            } else {
                // i==0 j!=0 且 str1[0]!=str2[j]
                // 看str2[0,j-1]上有没有等于str1[0]的字符
                return process(str1, str2, 0, j - 1);
            }
        } else if (j == 0) {
            // i！=0 j==0 且 str1[i]==str2[0]
            if (str1[i] == str2[0]) {
                return 1;
            } else {
                // i！=0 j==0 且 str1[i]!=str2[0]
                // 看str1[0,i-1]上有没有等于str2[0]的字符
                return process(str1, str2, i - 1, 0);
            }
        } else {
            // i != 0 && j != 0
            // 情况1：最长公共子序列不以i结尾，可能以j结尾
            int p1 = process(str1, str2, i - 1, j);
            // 情况2：最长公共子序列可能以i结尾，不以j结尾
            int p2 = process(str1, str2, i, j - 1);
            // 情况3：最长公共子序列以i结尾，以j结尾
            int p3 = str1[i] == str2[j] ? 1 + process(str1, str2, i - 1, j - 1) : 0;
            return Math.max(Math.max(p1, p2), p3);
        }
    }

    // 改动态规划
    // 情况1和情况2有重复：最长公共子序列不以i结尾，不以j结尾
    // 情况3和情况1情况2有重复：最长公共子序列以i结尾，以j结尾
    // 分析可变参数：i:[0,str1.length-1]  j:[0,str2.length-1]
    // 建二维表，dp(i,j) 表示str1[0,i]上做选择和str2[0,j]上做选择得到的最大公共子序列
    // 分析位置依赖关系，填表
    public int longestCommonSubsequence(String strOne, String strTwo) {
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
















