package other;

import java.util.HashMap;

/**
 * leetcode560. 和为 K 的子数组
 *
 * @author dks233
 * @create 2022-07-14-21:57
 */
public class SubArraySumEqualsToK {
    // 暴力解法：超时
    public static class MethodOne {
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            for (int index = 0; index < nums.length; index++) {
                count += process(nums, index, k);
            }
            return count;
        }

        // 以index为头可以组成rest的子数组数量
        // 注：某个范围内等于k时，需要继续计算，因为后续的数加起来可能等于0，总和仍然是k
        // 即：不能以rest==0作为 base case
        public int process(int[] nums, int index, int rest) {
            // 越界，返回0
            // 下方逻辑nums[index]==rest已经进行了判断，如果越界判断语句为rest==0?1:0，会产生重复计算
            if (index == nums.length) {
                return 0;
            }
            // 没越界，当前数就是某个子数组结尾，统计子数组数量，然后继续计算，统计之后的元素
            int ans = 0;
            if (nums[index] == rest) {
                ans += 1;
            }
            ans += process(nums, index + 1, rest - nums[index]);
            return ans;
        }
    }

    // 暴力解法简写
    // 时间复杂度：O(N*N)
    public static class AnotherMethodOne {
        public int subarraySum(int[] nums, int k) {
            int count = 0;
            for (int left = 0; left < nums.length; left++) {
                int sum = 0;
                for (int right = left; right < nums.length; right++) {
                    sum += nums[right];
                    if (sum == k) {
                        count++;
                    }
                }
            }
            return count;
        }
    }

    // 前缀和
    // 时间复杂度：O(N*N)
    public static class MethodTwo {
        public int subarraySum(int[] nums, int k) {
            // 先构建前缀和数组
            // 前缀和数组preSums[index] 表示[0,index-1]范围内的和
            int[] preSums = new int[nums.length + 1];
            preSums[0] = 0;
            for (int index = 1; index < preSums.length; index++) {
                preSums[index] = preSums[index - 1] + nums[index - 1];
            }
            // preSums[right+1]-preSums[left]=k
            // 说明[left,right]范围内和为k
            int count = 0;
            for (int left = 0; left < nums.length; left++) {
                for (int right = left; right < nums.length; right++) {
                    if (preSums[right + 1] - preSums[left] == k) {
                        count++;
                    }
                }
            }
            return count;
        }
    }

    // 前缀和+哈希表
    // 时间复杂度：O(N)
    public static class MethodThree {
        public int subarraySum(int[] nums, int k) {
            // key：前缀和
            // value：前缀和出现的次数
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            hashMap.put(0, 1);
            int preSum = 0;
            int count = 0;
            for (int index = 0; index < nums.length; index++) {
                preSum += nums[index];
                if (hashMap.containsKey(preSum - k)) {
                    count += hashMap.get(preSum - k);
                }
                hashMap.put(preSum, hashMap.getOrDefault(preSum, 0) + 1);
            }
            return count;
        }
    }
}
