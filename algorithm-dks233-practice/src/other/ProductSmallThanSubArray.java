package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 009. 乘积小于 K 的子数组
 * 参考文档：https://leetcode.cn/problems/subarray-product-less-than-k/solution/jian-dan-yi-dong-xiang-xi-zhu-jie-shuang-jvy3/
 *
 * @author dks233
 * @create 2022-07-18-23:28
 */
public class ProductSmallThanSubArray {
    // 时间复杂度：O(N)
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0] < k ? 1 : 0;
        }
        int left = 0;
        int right = 0;
        int curMultiply = 1;
        int count = 0;
        while (right < nums.length) {
            curMultiply *= nums[right];
            while (left <= right && curMultiply >= k) {
                curMultiply /= nums[left];
                left++;
            }
            // 每轮统计以right为结尾的乘积小于k的子数组数量
            count += right - left + 1;
            right++;
        }
        return count;
    }
}
