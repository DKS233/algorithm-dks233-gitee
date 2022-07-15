package dp;

/**
 * leetcode647. 回文子串
 *
 * @author dks233
 * @create 2022-07-15-10:32
 */
@SuppressWarnings("ALL")
public class PalindromeSubstring {
    // 暴力解法
    // 时间复杂度：O(N*N*N)
    public static class MethodOne {
        public int countSubstrings(String str) {
            int result = str.length();
            for (int left = 0; left < str.length(); left++) {
                for (int right = left + 1; right < str.length(); right++) {
                    if (isPalSubStr(str.substring(left, right + 1))) {
                        result++;
                    }
                }
            }
            return result;
        }


        public boolean isPalSubStr(String str) {
            int left = 0;
            int right = str.length() - 1;
            while (left < right) {
                if (str.charAt(left) != str.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }

    // 动态规划
    // 时间复杂度：O(N*N)
    public static class MethodTwo {
        public int countSubstrings(String str) {
            int count = 0;
            // boolean[row][column]表示[row,column]范围内是不是回文子串
            // 情况1：[row,column]有一个元素，是回文子串，数量++
            // 情况2：[row,column]有两个元素且相同，是回文子串，数量++
            // 情况3：[row,column]大于等于3个元素，两边元素相同，且中间区域是回文子串，[row,column]是回文子串，数量++
            // 情况3依赖[row+1,column-1]，即左下角位置
            boolean[][] dp = new boolean[str.length()][str.length()];
            // 从左往右填每列元素（表格右上角）
            for (int column = 0; column < str.length(); column++) {
                for (int row = 0; row <= column; row++) {
                    if (row == column) {
                        dp[row][column] = true;
                        count++;
                    } else if (row == column - 1 && str.charAt(column) == str.charAt(row)) {
                        dp[row][column] = true;
                        count++;
                    } else if (column > row + 1 && str.charAt(column) == str.charAt(row) && dp[row + 1][column - 1]) {
                        dp[row][column] = true;
                        count++;
                    }
                }
            }
            return count;
        }
    }

    public static void main(String[] args) {
        MethodTwo methodTwo = new MethodTwo();
        System.out.println(methodTwo.countSubstrings("abc"));
    }
}
