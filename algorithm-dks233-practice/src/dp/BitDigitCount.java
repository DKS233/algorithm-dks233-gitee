package dp;

/**
 * leetcode338. 比特位计数
 * 参考文档：https://leetcode.cn/problems/counting-bits/solution/hen-qing-xi-de-si-lu-by-duadua/
 *
 * @author dks233
 * @create 2022-07-14-13:56
 */
public class BitDigitCount {
    public static class MethodOne {
        // 时间复杂度：O(N)
        public int[] countBits(int n) {
            int[] ans = new int[n + 1];
            for (int number = 0; number <= n; number++) {
                int count = 0;
                for (int index = 0; index < 31; index++) {
                    if (((number >> index) & 1) == 1) {
                        count++;
                    }
                }
                ans[number] = count;
            }
            return ans;
        }
    }

    // 时间复杂度：O(N)
    public static class MethodTwo {
        // dp[number]表示number对应二进制的1的个数
        public int[] countBits(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            for (int number = 1; number <= n; number++) {
                // 如果是奇数，1的个数比前面那个偶数多一个
                if (number % 2 != 0) {
                    dp[number] = dp[number - 1] + 1;
                }
                // 如果是偶数，1的个数和number/2一样多
                else {
                    dp[number] = dp[number / 2];
                }
            }
            return dp;
        }
    }
}
