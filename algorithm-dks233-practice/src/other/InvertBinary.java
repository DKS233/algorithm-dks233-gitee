package other;

/**
 * leetcode190. 颠倒二进制位
 *
 * @author dks233
 * @create 2022-08-29-11:11
 */
@SuppressWarnings("ALL")
public class InvertBinary {
    // n的二进制位从右往左计算
    // 结果从左往右拼接
    public int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int digit = (n >> i) & 1;
            if (digit == 1) {
                ans |= (1 << (31 - i));
            }
        }
        return ans;
    }

}
