package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 008. 和大于等于 target 的最短子数组
 *
 * @author dks233
 * @create 2022-07-18-15:42
 */
@SuppressWarnings("ALL")
public class SumBiggerEqualTargetShortedSubArray {
    // 左右指针
    public static class MethodTwo {
        // 时间复杂度：O(N)
        public int minSubArrayLen(int target, int[] nums) {
            int left = 0;
            int right = 0;
            int sum = 0;
            int minLen = Integer.MAX_VALUE;
            sum = nums[0];
            while (right < nums.length) {
                // 如果sum>=target，先保存结果，sum可以试试稍微变小，left右移
                if (sum >= target) {
                    minLen = Math.min(right - left + 1, minLen);
                    sum -= nums[left];
                    left++;
                }
                // 如果sum<target，sum需要变大，right右移
                else {
                    right++;
                    if (right < nums.length) {
                        sum += nums[right];
                    }
                }
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }
    }

    // 暴力解法：前缀和
    public static class MethodOne {
        // 前缀和  preSums[index] 表示之前的元素和
        public int minSubArrayLen(int target, int[] nums) {
            if (nums.length == 1) {
                return nums[0] == target ? 1 : 0;
            }
            int[] preSums = new int[nums.length + 1];
            int preSum = 0;
            for (int index = 1; index <= nums.length; index++) {
                preSum += nums[index - 1];
                preSums[index] = preSum;
            }
            // 遍历前缀和数组，判断哪个范围的元素和大于等于target
            int minLen = Integer.MAX_VALUE;
            for (int index = 0; index < nums.length; index++) {
                if (nums[index] >= target) {
                    return 1;
                }
            }
            for (int left = 0; left < preSums.length; left++) {
                for (int right = left + 1; right < preSums.length; right++) {
                    if (preSums[right] - preSums[left] >= target) {
                        minLen = Math.min(minLen, right - left);
                    }
                }
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }

    }
}
