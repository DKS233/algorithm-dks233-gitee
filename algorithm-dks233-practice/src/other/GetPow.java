package other;

/**
 * leetcode50. Pow(x, n)
 * 参考文档：https://leetcode.cn/problems/powx-n/
 *
 * @author dks233
 * @create 2022-08-26-10:34
 */
@SuppressWarnings("ALL")
public class GetPow {
    public double myPow(double x, int n) {
        // 如果n是负数，转换为myPow(1/x,-n)
        long n1 = n;
        if (n1 < 0) {
            n1 = -n1;
            x = 1 / x;
        }
        double result = 1.0;
        for (int digit = 0; digit < 32; digit++) {
            if (((n1 >> digit) & 1) == 1) {
                result *= x;
            }
            x = x * x;
        }
        return result;
    }

    public static void main(String[] args) {
        GetPow getPow = new GetPow();
        System.out.println(getPow.myPow(2, -2));
    }
}
