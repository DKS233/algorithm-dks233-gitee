package other;

/**
 * leetcode334. 递增的三元子序列
 * 参考文档：https://leetcode.cn/problems/increasing-triplet-subsequence/solution/c-xian-xing-shi-jian-fu-za-du-xiang-xi-jie-xi-da-b/
 *
 * @author dks233
 * @create 2022-08-26-23:55
 */
@SuppressWarnings("ALL")
public class IncrementalTernarySubsequence {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= min) {
                min = nums[i];
            } else if (nums[i] <= mid) {
                mid = nums[i];
            } else if (nums[i] > mid) {
                return true;
            }
        }
        return false;
    }
}
