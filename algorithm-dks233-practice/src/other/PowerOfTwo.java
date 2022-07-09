package other;

/**
 * leetcode231. 2 的幂
 *
 * @author dks233
 * @create 2022-07-09-10:46
 */
@SuppressWarnings("ALL")
public class PowerOfTwo {
    // 如果一个数是2的幂，其二进制位肯定只有一个1
    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }
}
