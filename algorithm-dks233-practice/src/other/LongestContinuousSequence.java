package other;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode128. 最长连续序列
 *
 * @author dks233
 * @create 2022-07-10-22:28
 */
@SuppressWarnings("ALL")
public class LongestContinuousSequence {
    public static class MethodOne {
        // 时间复杂度：O(N)
        // 空间复杂度：O(N)
        public int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for (int index = 0; index < nums.length; index++) {
                set.add(nums[index]);
            }
            int maxLen = 0;
            // 只从最小的元素开始向上查找，看其代表的最长连续序列
            for (int index = 0; index < nums.length; index++) {
                int curLen = 1;
                // 没有小于nums[index]的，说明在nums[index]为首的子序列中，它是最小的
                int curNumber = nums[index];
                if (!set.contains(curNumber - 1)) {
                    while (set.contains(curNumber + 1)) {
                        curLen++;
                        curNumber++;
                    }
                }
                maxLen = Math.max(curLen, maxLen);
            }
            return maxLen;
        }
    }
}
