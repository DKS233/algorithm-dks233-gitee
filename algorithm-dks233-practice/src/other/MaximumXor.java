package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 067. 最大的异或
 *
 * @author dks233
 * @create 2022-07-27-23:46
 */
@SuppressWarnings("ALL")
public class MaximumXor {
    public static class MethodOne {
        public int findMaximumXOR(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            int max = 0;
            for (int left = 0; left < nums.length; left++) {
                for (int right = left + 1; right < nums.length; right++) {
                    max = Math.max(nums[left] ^ nums[right], max);
                }
            }
            return max;
        }
    }
}
