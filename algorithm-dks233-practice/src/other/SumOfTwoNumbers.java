package other;

import java.util.HashMap;

/**
 * leetcode1. 两数之和
 *
 * @author dks233
 * @create 2022-06-27-21:27
 */
public class SumOfTwoNumbers {
    // 暴力解法
    // 时间复杂度：O(N*N)
    public static class MethodOne {
        public int[] twoSum(int[] nums, int target) {
            int sum = 0;
            for (int left = 0; left < nums.length; left++) {
                for (int right = left + 1; right < nums.length; right++) {
                    sum = nums[left] + nums[right];
                    if (sum == target) {
                        return new int[]{left, right};
                    }
                }
            }
            return new int[0];
        }
    }

    // 用哈希表
    // 时间复杂度：O(1)
    public static class MethodTwo {
        public int[] twoSum(int[] nums, int target) {
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (int index = 0; index < nums.length; index++) {
                if (hashMap.containsKey(target - nums[index])) {
                    return new int[]{index, hashMap.get(target - nums[index])};
                }
                hashMap.put(nums[index], index);
            }
            return new int[0];
        }
    }
}
