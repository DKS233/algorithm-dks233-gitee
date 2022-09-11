package slidingwindow;

/**
 * leetcode剑指 Offer II 008. 和大于等于 target 的最短子数组
 *
 * @author dks233
 * @create 2022-09-11-8:35
 */
@SuppressWarnings("ALL")
public class SubarraySumGreaterThanTarget {
    public int minSubArrayLen(int target, int[] nums) {
        long limit = (long) target;
        long curSum = 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            curSum += nums[right];
            while (curSum >= limit) {
                minLen = Math.min(minLen, right - left + 1);
                curSum -= nums[left];
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
