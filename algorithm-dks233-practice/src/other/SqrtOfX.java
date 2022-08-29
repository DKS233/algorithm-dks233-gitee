package other;

/**
 * leetcode69. x 的平方根
 *
 * @author dks233
 * @create 2022-08-29-10:38
 */
public class SqrtOfX {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int left = 0;
        int right = x;
        int result = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (x / mid > mid) {
                result = mid;
                left = mid + 1;
            } else if (x / mid < mid) {
                right = mid - 1;
            } else {
                result = mid;
                break;
            }
        }
        return result;
    }
}
