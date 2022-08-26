package other;

/**
 * leetcode162. 寻找峰值
 *
 * @author dks233
 * @create 2022-08-26-11:39
 */
@SuppressWarnings("ALL")
public class FindPeak {
    // 二分法求局部最大值
    // 参考体系班局部最小值
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int left = 1;
        int right = nums.length - 2;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid - 1] > nums[mid]) {
                right = mid - 1;
            } else if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }
}
