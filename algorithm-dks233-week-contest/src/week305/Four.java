package week305;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.util.HashMap;

/**
 * leetcode6138. 最长理想子序列
 * 参考：https://www.bilibili.com/video/BV1CN4y1V7uE?spm_id_from=333.999.0.0&vd_source=cd2d9ceb11856332f08fec74b6d46d0d
 *
 * @author dks233
 * @create 2022-08-07-11:10
 */
@SuppressWarnings("ALL")
public class Four {
    // 暴力递归
    public static class MethodOne {
        public int longestIdealString(String s, int k) {
            int max = 0;
            for (int index = 0; index < s.length(); index++) {
                max = Math.max(process(s.toCharArray(), index, k), max);
            }
            return max;
        }

        // index出发组成的最长子序列
        public int process(char[] str, int index, int k) {
            if (index == str.length) {
                return 0;
            }
            // 要当前字符，不尝试之后字符
            int p1 = 0;
            int i = index + 1;
            while (i < str.length) {
                if (Math.abs(str[i] - str[index]) <= k) {
                    p1 = Math.max(p1, process(str, i, k) + 1);
                }
                i++;
            }
            // 要当前字符，直接返回，不尝试后面的字符
            int p2 = 1;
            return Math.max(p1, p2);
        }
    }

    // 暴力递归改动态规划
    // 超时
    public static class MethodTwo {
        public int longestIdealString(String s, int k) {
            // dp[index] index出发组成的最长子序列
            int[] dp = new int[s.length() + 1];
            dp[s.length()] = 0;
            int max = 0;
            for (int index = s.length() - 1; index >= 0; index--) {
                int p1 = 1;
                int i = index + 1;
                while (i < s.length()) {
                    if (Math.abs(s.charAt(i) - s.charAt(index)) <= k) {
                        p1 = Math.max(p1, dp[i] + 1);
                    }
                    i++;
                }
                int p2 = 1;
                dp[index] = Math.max(p1, p2);
                max = Math.max(max, dp[index]);
            }
            return max;
        }
    }

    // 暴力递归改动态规划
    // 优化版
    public static class MethodThree {
        public int longestIdealString(String s, int k) {
            // dp[index] index出发组成的最长子序列
            int[] dp = new int[s.length() + 1];
            dp[s.length()] = 0;
            // hashMap表示以key这个字母出发的最长子序列
            HashMap<Character, Integer> hashMap = new HashMap<>();
            int max = 0;
            for (int index = s.length() - 1; index >= 0; index--) {
                int p1 = 0;
                for (int i = 0; i <= k; i++) {
                    if (hashMap.containsKey((char) (s.charAt(index) + i))) {
                        p1 = Math.max(p1, hashMap.get((char) (s.charAt(index) + i)) + 1);
                    }
                    if (hashMap.containsKey((char) (s.charAt(index) - i))) {
                        p1 = Math.max(p1, hashMap.get((char) (s.charAt(index) - i)) + 1);
                    }
                }
                int p2 = 1;
                dp[index] = Math.max(p1, p2);
                hashMap.put(s.charAt(index), dp[index]);
                max = Math.max(dp[index], max);
            }
            return max;
        }
    }

    public static void main(String[] args) {
        MethodTwo four = new MethodTwo();
        four.longestIdealString("pvjcci", 4);
    }
}
