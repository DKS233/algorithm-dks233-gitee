package dp;

import java.math.BigInteger;

/**
 * 剑指 Offer 第二版 14- II. 剪绳子 II
 * 参考文档：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/solution/mian-shi-ti-14-ii-jian-sheng-zi-iitan-xin-er-fen-f/
 *
 * @author dks233
 * @create 2022-06-17-10:37
 */
public class CuttingRopTwo {
    public static class MethodOne {
        public int cuttingRope(int n) {
            return process(n);
        }

        public int process(int rest) {
            if (rest < 2) {
                return 0;
            }
            int max = 0;
            for (int i = 1; i <= rest; i++) {
                // 剩余的可以继续切割，也可以不切割
                int curMax = (int) (Math.max(i * (rest - i), i * process(rest - i)) % (Math.pow(10, 9) + 7));
                max = Math.max(curMax, max);
            }
            return max;
        }
    }

    public static class MethodTwo {
        // 暴力递归改动态规划
        // 分析可变参数，rest->[0,n]
        // dp[i]表示切割长度为i的绳子获得的最大乘积
        public int cuttingRope(int n) {
            BigInteger[] dp = new BigInteger[n + 1];
            BigInteger max = new BigInteger("0");
            // 抄暴力递归
            dp[0] = dp[1] = new BigInteger("0");
            for (int rest = 2; rest < dp.length; rest++) {
                for (int j = 1; j <= rest; j++) {
                    max = (dp[rest - j].multiply(BigInteger.valueOf(j))).max(BigInteger.valueOf(j * (rest - j))).max(max);
                }
                dp[rest] = max;
            }
            return dp[n].mod(BigInteger.valueOf((int) Math.pow(10, 9) + 7)).intValue();
        }
    }

    // 循环求余
    // 求余运算规则：(xy) % p = [(x%p)(y%p)]%p
    // x^a % p = (x^(a-1)%p)(x%p)%p
    // 本题中x<p 所以 x^a % p = ((x^(a-1)%p)x)p
    public static class MethodThree {
        public int cuttingRope(int n) {
            if (n <= 3) {
                return n - 1;
            }
            // 本题中x对应3，a对应n/3的整数部分
            // n>3 时，求n/3的整数部分a和余数部分b
            int p = (int) (Math.pow(10, 9) + 7);
            int a = n / 3;
            int b = n % 3;
            long res = 1L;
            // 求3^(a-1)次求余后的结果，需要留出一个3和余数进行合并
            for (int i = 0; i < a - 1; i++) {
                res = (res * 3) % p;
            }
            // b=0 返回3^a % p
            if (b == 0) {
                return (int) ((res * 3) % p);
            }
            // b=1 返回(3^(a-1) * 4) % p
            else if (b == 1) {
                return (int) ((res * 4) % p);
            }
            // b=2 返回3^a * 2 % p
            else {
                return (int) ((((res * 3) % p) * 2) % p);
            }
        }
    }
}
