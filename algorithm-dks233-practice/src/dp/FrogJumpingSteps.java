package dp;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * @author dks233
 * @create 2022-05-19-15:53
 */
public class FrogJumpingSteps {
    public static class MethodOne {
        // 暴力递归
        public int numWays(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }
            return process(n, 0);
        }

        // 从index跳到n最多有多少种跳法
        // n：总台阶数
        public int process(int n, int index) {
            // index到n位置，当前跳是有效的
            if (index == n) {
                return 1;
            }
            // index越界，当前跳是无效的
            if (index > n) {
                return 0;
            }
            // 跳一级
            int p1 = process(n, index + 1);
            // 跳两级
            int p2 = process(n, index + 2);
            return p1 + p2;
        }
    }

    // 改动态规划
    // 可变参数是index，范围是[0,n]，建长度为n+1的一维数组dp
    // dp[index] 到index位置跳到n位置可以有几种跳法
    // 首先填dp[n]=1
    // 其他位置都依赖于index+1位置和index+2位置
    // 注意n-1位置的index+2位置越界，看做0，所以直接取index+1的数
    public static class MethodTwo {
        public int numWays(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[n] = 1;
            dp[n - 1] = 1;
            for (int index = n - 2; index >= 0; index--) {
                dp[index] = (dp[index + 1] % 1000000007 + dp[index + 2] % 1000000007) % 1000000007;
            }
            return dp[0];
        }
    }
}
