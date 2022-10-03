package other;

/**
 * 剑指offer第二版：剑指 Offer 16. 数值的整数次方
 *
 * @author dks233
 * @create 2022-10-03-9:51
 */
@SuppressWarnings("ALL")
public class TheIntegerPowerOfANumber {
    // n的二进制表示为：b1 b2 ... bm（b1为个位）
    // x的n次表示为：x^(b1+2b2+...) -> x^(b1) * x^(2b2) * ...
    public double myPow(double x, int n) {
        long n1 = n;
        if (n1 < 0) {
            n1 = -n1;
            x = 1 / x;
        }
        double result = 1.0;
        for (int i = 0; i < 32; i++) {
            if (((n1 >> i) & 1) == 1) {
                result *= x;
            }
            x *= x;
        }
        return result;
    }
}
