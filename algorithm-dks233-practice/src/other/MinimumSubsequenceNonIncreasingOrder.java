package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode1403. 非递增顺序的最小子序列
 *
 * @author dks233
 * @create 2022-08-30-14:42
 */
@SuppressWarnings("ALL")
public class MinimumSubsequenceNonIncreasingOrder {
    // 求和大于sum/2的最小子序列
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int cur = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            cur += nums[i];
            result.add(nums[i]);
            if (cur > sum / 2) {
                return result;
            }
        }
        return new ArrayList<>();
    }
}
