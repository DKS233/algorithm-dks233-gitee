package other;

import java.util.Arrays;

/**
 * leetcode204. 计数质数
 * 参考文档：https://leetcode.cn/problems/count-primes/solution/kuai-lai-miao-dong-shai-zhi-shu-by-sweetiee/
 *
 * @author dks233
 * @create 2022-08-25-17:53
 */
@SuppressWarnings("ALL")
public class PrimeCount {
    // 暴力解法：每个数验证范围：[2,sqrt(number)]
    public static class MethodOne {
        public int countPrimes(int n) {
            int count = 0;
            for (int i = 2; i < n; i++) {
                count += process(i) ? 1 : 0;
            }
            return count;
        }

        public boolean process(int number) {
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // 埃氏筛
    public static class MethdTwo {
        public int countPrimes(int n) {
            boolean[] is = new boolean[n];
            Arrays.fill(is, true);
            for (int i = 2; i * i < n; i++) {
                if (is[i]) {
                    // 轮到3的时候从3*3开始
                    // 因为1*3,2*3都被过滤掉了
                    for (int j = i * i; j < n; j += i) {
                        is[j] = false;
                    }
                }
            }
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (is[i]) {
                    count++;
                }
            }
            return count;
        }
    }
}
