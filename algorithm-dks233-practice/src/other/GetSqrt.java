package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 072. 求平方根
 *
 * @author dks233
 * @create 2022-08-17-20:00
 */
public class GetSqrt {
    public int mySqrt(int x) {
        int result = -1;
        int left = 0;
        int right = x;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if ((long) mid * mid <= x) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}
