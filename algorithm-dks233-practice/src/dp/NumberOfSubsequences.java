package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 097. 子序列的数目
 *
 * @author dks233
 * @create 2022-07-27-21:54
 */
public class NumberOfSubsequences {
    // 纯暴力
    public static class MethodOne {
        int count;

        public int numDistinct(String s, String t) {
            count = 0;
            process(s.toCharArray(), t, 0, "");
            return count;
        }

        public void process(char[] str, String target, int index, String curStr) {
            if (index == str.length) {
                count += target.equals(curStr) ? 1 : 0;
                return;
            }
            // 要当前元素
            process(str, target, index + 1, curStr + str[index]);
            // 不要当前元素
            process(str, target, index + 1, curStr);
        }
    }

    // 暴力递归
    public static class MethodTwo {
        public int numDistinct(String s, String t) {
            return process(s.toCharArray(), t.toCharArray(), 0, 0);
        }

        // str[strIndex...]，target[targetIndex...]的匹配个数
        public int process(char[] str, char[] target, int strIndex, int targetIndex) {
            // targetIndex越界，target全部匹配成功
            if (targetIndex == target.length) {
                return 1;
            }
            // target未全部匹配成功，但是str已经遍历完毕
            if (strIndex == str.length) {
                return 0;
            }
            int ans = 0;
            // 要str的当前字符，进行匹配
            if (str[strIndex] == target[targetIndex]) {
                ans += process(str, target, strIndex + 1, targetIndex + 1);
            }
            // 不要str的当前字符，进行匹配
            ans += process(str, target, strIndex + 1, targetIndex);
            return ans;
        }
    }

    public static class MethodThree {
        // dp[strIndex][targetIndex] 表示 str[strIndex...]，target[targetIndex...]的匹配个数
        public int numDistinct(String s, String t) {
            int[][] dp = new int[s.length() + 1][t.length() + 1];
            for (int strIndex = 0; strIndex < dp.length; strIndex++) {
                dp[strIndex][t.length()] = 1;
            }
            for (int strIndex = dp.length - 2; strIndex >= 0; strIndex--) {
                for (int targetIndex = dp[0].length - 2; targetIndex >= 0; targetIndex--) {
                    if (s.charAt(strIndex) == t.charAt(targetIndex)) {
                        dp[strIndex][targetIndex] += dp[strIndex + 1][targetIndex + 1];
                    }
                    dp[strIndex][targetIndex] += dp[strIndex + 1][targetIndex];
                }
            }
            return dp[0][0];
        }
    }
}
