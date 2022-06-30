package other;

/**
 * leetcode7. 整数反转
 * 参考文档：https://leetcode.cn/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/
 *
 * @author dks233
 * @create 2022-06-30-10:37
 */
public class IntegerReverse {
    public int reverse(int number) {
        int ans = 0;
        while (number != 0) {
            // 末尾数字
            int end = number % 10;
            if ((ans > Integer.MAX_VALUE / 10) || (ans == Integer.MAX_VALUE / 10 && end > 7)) {
                return 0;
            }
            if ((ans < Integer.MIN_VALUE / 10) || (ans == Integer.MIN_VALUE / 10 && end < -8)) {
                return 0;
            }
            ans = ans * 10 + end;
            number = number / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE / 10);
        System.out.println(Integer.MIN_VALUE / 10);
    }
}
