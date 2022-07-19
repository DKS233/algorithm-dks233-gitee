package other;

import java.util.HashMap;

/**
 * 剑指offer专项突击版：剑指 Offer II 011. 0 和 1 个数相同的子数组
 * 剑指offer专项突击版：剑指 Offer II 010. 和为 k 的子数组
 *
 * @author dks233
 * @create 2022-07-18-20:46
 */
@SuppressWarnings("ALL")
public class ZeroCountEqualsToOne {
    // 问题1：前缀和与哈希表
    // 时间复杂度：O(N)
    public static class MethodTwo {
        // 把0看做-1，题目转变为求和为0的最长连续子数组
        public int findMaxLength(int[] nums) {
            // key:前缀和，value：该前缀和第一次出现的索引位置
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            // 后续出现和为0时，比如nums[0]=0（此题中被看成了-1） nums[1]=1 1位置的前缀和是1，子数组长度为2，0的前缀和起始位置设置成-1
            hashMap.put(0, -1);
            // 前缀和
            int preSum = 0;
            // 和为0的子数组最大长度
            int maxLen = Integer.MIN_VALUE;
            for (int index = 0; index < nums.length; index++) {
                preSum += nums[index] == 0 ? -1 : 1;
                // 如果map中有preSum，说明之前索引位置到当前位置范围内元素和为0
                if (hashMap.containsKey(preSum)) {
                    maxLen = Math.max(maxLen, index - hashMap.get(preSum));
                }
                // map中没有preSum，将当前前缀和与对应的索引存到map中
                else {
                    hashMap.put(preSum, index);
                }
            }
            return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
        }
    }

    // 问题一：暴力解法
    public static class ProblemMethodOne {
        // 前缀和解法
        // 把0换成-1，求和为0的最长子数组
        // 时间复杂度：O(N*N)
        public int findMaxLength(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }
            for (int index = 0; index < nums.length; index++) {
                // 把0变成-1
                if (nums[index] == 0) {
                    nums[index] = -1;
                }
            }
            // 求前缀和，preSums[index] 表示index之前的元素的和
            int curSum = 0;
            int[] preSums = new int[nums.length + 1];
            for (int index = 1; index < preSums.length; index++) {
                curSum += nums[index - 1];
                preSums[index] = curSum;
            }
            // 遍历前缀和
            // preSums[3]-preSums[1] 表示[1,2]范围内的和，即长度为2的子数组的和
            int maxLen = Integer.MIN_VALUE;
            for (int left = 0; left < preSums.length; left++) {
                for (int right = left + 1; right < preSums.length; right++) {
                    if (preSums[right] - preSums[left] == 0) {
                        maxLen = Math.max(maxLen, right - left);
                    }
                }
            }
            return maxLen == Integer.MIN_VALUE ? 0 : maxLen;
        }
    }

    // 问题二
    public static class ProblemTwoMethodOne {
        // 前缀和+哈希表
        // 时间复杂度：O(N)
        public int subarraySum(int[] nums, int k) {
            // key:前缀和 value:前缀和出现的个数
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            // 如果后续第一次出现preSum等于k，需要判断map是否包含0这个前缀，然后count+=map.contains(0)，所以0对应的个数应该是1
            hashMap.put(0, 1);
            // 前缀和
            int preSum = 0;
            // 和为k的连续子数组个数
            int count = 0;
            for (int index = 0; index < nums.length; index++) {
                preSum += nums[index];
                // 如果包含preSum-k，说明之前前缀和preSum-k出现的索引位置到当前位置和是k
                if (hashMap.containsKey(preSum - k)) {
                    count += hashMap.get(preSum - k);
                }
                hashMap.put(preSum, hashMap.getOrDefault(preSum, 0) + 1);
            }
            return count;
        }
    }
}















