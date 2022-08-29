package other;

/**
 * leetcode326. 3 的幂
 * 参考文档：https://leetcode.cn/problems/power-of-three/solution/gong-shui-san-xie-yi-ti-san-jie-shu-xue-8oiip/
 *
 * @author dks233
 * @create 2022-08-29-12:03
 */
@SuppressWarnings("ALL")
public class PowOfThree {
    // 只要余数为0，就一直除
    // 如果最终剩个1，说明n是3的幂次方
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;

    }
}
