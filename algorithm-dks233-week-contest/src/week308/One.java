package week308;

import java.util.Arrays;

/**
 * leetcode6160. 和有限的最长子序列
 *
 * @author dks233
 * @create 2022-08-28-10:30
 */
@SuppressWarnings("ALL")
public class One {
    // 动态规划会超时
    // 对nums进行排序，然后从右到右累加，用前缀和
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[] arr = new int[queries.length];
        int[] preSums = new int[nums.length + 1];
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            preSums[i + 1] = preSum;
        }
        for (int i = 0; i < arr.length; i++) {
            int maxLen = 0;
            for (int j = 1; j < preSums.length; j++) {
                if (preSums[j] <= queries[i]) {
                    maxLen = j;
                } else {
                    break;
                }
            }
            arr[i] = maxLen;
        }
        return arr;
    }
}
