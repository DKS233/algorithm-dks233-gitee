package other;

import java.util.Arrays;

/**
 * leetcode204. 计数质数
 * 参考文档：https://leetcode.cn/problems/count-primes/submissions/
 *
 * @author dks233
 * @create 2022-08-25-17:53
 */
public class PrimeCount {
    public int countPrimes(int n) {
        // 先把所有数标记为质数，然后将所有的合数标记为false
        boolean[] arr = new boolean[n];
        Arrays.fill(arr, true);
        for (int i = 2; i * i < n; i++) {
            if (arr[i]) {
                for (int j = i * i; j < n; j += i) {
                    arr[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i]) {
                count++;
            }
        }
        return count;
    }
}
