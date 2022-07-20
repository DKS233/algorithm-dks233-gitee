package dp;

/**
 * 剑指offer专项突击版：剑指 Offer II 020. 回文子字符串的个数
 *
 * @author dks233
 * @create 2022-07-19-23:19
 */
public class NumberOfPalindromeSubstrings {
    // dp[left,right]表示[left,right]范围内是否是回文子串
    // 一个元素：left=right，dp[left,right]=true
    // 两个元素：right-left=1，dp[left,right]=str[left]==str[right]
    // 大于等于三个元素：right-left>=2，dp[left,right]=str[left]==str[right]&&dp[left+1][right-1]
    public int countSubstrings(String s) {
        char[] str = s.toCharArray();
        boolean[][] dp = new boolean[str.length][str.length];
        // 先填对角线（范围内只有一个元素的）
        int count = 0;
        for (int left = 0; left < dp.length; left++) {
            dp[left][left] = true;
            count++;
        }
        // 再填右上角（left<right）
        // 分析位置依赖关系：每个位置依赖左下角
        for (int right = 0; right < dp.length; right++) {
            for (int left = 0; left < right; left++) {
                if (right - left == 1) {
                    dp[left][right] = str[left] == str[right];
                } else if (right - left >= 2) {
                    dp[left][right] = dp[left + 1][right - 1] && str[left] == str[right];
                }
                if (dp[left][right]) {
                    count++;
                }
            }
        }
        return count;
    }
}
