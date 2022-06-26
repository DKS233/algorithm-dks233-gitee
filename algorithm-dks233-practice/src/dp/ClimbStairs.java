package dp;

/**
 * leetcode70. 爬楼梯
 *
 * @author dks233
 * @create 2022-06-26-9:37
 */
public class ClimbStairs {
    // 暴力递归
    public static class MethodOne {
        public int climbStairs(int n) {
            return process(n, 0);
        }

        // 返回：从index位置到n位置有多少种方法
        public int process(int n, int index) {
            if (index == n) {
                return 1;
            }
            // 跳1个台阶
            int p1 = 0;
            if (index + 1 <= n) {
                p1 = process(n, index + 1);
            }
            // 跳2个台阶
            int p2 = 0;
            if (index + 2 <= n) {
                p2 = process(n, index + 2);
            }
            return p1 + p2;
        }
    }

    // 暴力递归改动态规划
    // 分析可变参数：index:[0,n]
    // dp[index]表示从index位置到n位置有多少种方法
    public static class MethodTwo {
        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            dp[n] = 1;
            for (int index = n - 1; index >= 0; index--) {
                int p1 = 0;
                if (index + 1 <= n) {
                    p1 = dp[index + 1];
                }
                int p2 = 0;
                if (index + 2 <= n) {
                    p2 = dp[index + 2];
                }
                dp[index] = p1 + p2;
            }
            return dp[0];
        }
    }

}
