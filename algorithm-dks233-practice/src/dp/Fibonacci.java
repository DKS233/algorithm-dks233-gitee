package dp;

/**
 * 剑指 Offer 10- I. 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * @author dks233
 * @create 2022-05-19-15:31
 */
public class Fibonacci {
    // 暴力递归
    public static int process(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return process(n - 1) + process(n - 2);
    }

    // 改动态规划
    // 可变参数为n，建立一维表
    // 填表：0 1 2 位置，之后的index位置都是依赖index-1和index-2的值
    public static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int index = 3; index < n + 1; index++) {
            dp[index] = (dp[index - 1] % 1000000007 + dp[index - 2] % 1000000007) % 1000000007;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(100));
    }
}
