package other;

/**
 * leetcode172. 阶乘后的零
 *
 * @author dks233
 * @create 2022-08-26-16:35
 */
@SuppressWarnings("ALL")
public class ZeroAfterFactorial {
    // n/5+n/25+n/125+....
    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}
