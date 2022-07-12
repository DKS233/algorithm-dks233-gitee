package dp;

/**
 * leetcode152. 乘积最大子数组
 *
 * @author dks233
 * @create 2022-07-12-11:41
 */
public class ProductMaximumSubarray {
    public static class MethodOne {
        public int maxProduct(int[] nums) {
            // maxDp[index] 表示以index为结尾的乘积最大值
            int[] maxDp = new int[nums.length];
            // minDp[index]表示以index为结尾的乘积最小值
            int[] minDp = new int[nums.length];
            maxDp[0] = nums[0];
            minDp[0] = nums[0];
            for (int index = 1; index < nums.length; index++) {
                if (nums[index] >= 0) {
                    maxDp[index] = Math.max(maxDp[index - 1] * nums[index], nums[index]);
                    minDp[index] = Math.min(minDp[index - 1] * nums[index], nums[index]);
                } else {
                    maxDp[index] = Math.max(minDp[index - 1] * nums[index], nums[index]);
                    minDp[index] = Math.min(maxDp[index - 1] * nums[index], nums[index]);
                }
            }
            int maxValue = Integer.MIN_VALUE;
            for (int index = 0; index < maxDp.length; index++) {
                maxValue = Math.max(maxDp[index], maxValue);
            }
            return maxValue;
        }
    }
}
