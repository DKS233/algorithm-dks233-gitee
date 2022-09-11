package slidingwindow;

/**
 * leetcode剑指 Offer II 009. 乘积小于 K 的子数组
 *
 * @author dks233
 * @create 2022-09-11-9:03
 */
public class SubarrayWithProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int cur = 1;
        int left = 0;
        int right = 0;
        int count = 0;
        while (right < nums.length) {
            cur *= nums[right];
            while (left <= right && cur >= k) {
                cur /= nums[left];
                left++;
            }
            // 统计right为结尾的符合条件的子数组数量
            count += right - left + 1;
            right++;
        }
        return count;
    }
}
