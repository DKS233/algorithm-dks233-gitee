package dp;

/**
 * leetcode91. 解码方法
 *
 * @author dks233
 * @create 2022-08-24-23:18
 */
@SuppressWarnings("ALL")
public class DecodingMethod {
    // 暴力递归
    public static class MethodOne {
        public int numDecodings(String s) {
            return process(s.toCharArray(), 0);
        }

        // [index,...] 能解码成功的方法数
        public int process(char[] str, int index) {
            if (index == str.length) {
                return 1;
            }
            if (str[index] == '0') {
                return 0;
            }
            int result = 0;
            // 当前位置如果不是0
            // 情况1：尝试解码当前位置
            int p1 = process(str, index + 1);
            // 情况2：尝试解码当前位置和下一个位置
            int p2 = 0;
            if (index + 1 < str.length) {
                int curAndNext = (str[index] - '0') * 10 + str[index + 1] - '0';
                if (curAndNext > 0 && curAndNext <= 26) {
                    p2 = process(str, index + 2);
                }
            }
            return p1 + p2;
        }
    }

    // 暴力递归改动态规划
    public static class MethodTwo {
        public int numDecodings(String s) {
            char[] str = s.toCharArray();
            int[] dp = new int[str.length + 1];
            dp[str.length] = 1;
            for (int index = str.length - 1; index >= 0; index--) {
                int result = 0;
                if (str[index] == '0') {
                    dp[index] = 0;
                    continue;
                }
                int p1 = dp[index + 1];
                int p2 = 0;
                if (index + 1 < str.length) {
                    int curAndNext = (str[index] - '0') * 10 + str[index + 1] - '0';
                    if (curAndNext > 1 && curAndNext <= 26) {
                        p2 = dp[index + 2];
                    }
                }
                dp[index] = p1 + p2;
            }
            return dp[0];
        }
    }
}
