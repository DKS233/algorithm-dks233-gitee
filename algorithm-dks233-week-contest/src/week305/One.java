package week305;

import java.util.HashSet;

/**
 * leetcode6136. 算术三元组的数目
 * 参考：https://www.bilibili.com/video/BV1CN4y1V7uE?spm_id_from=333.999.0.0&vd_source=cd2d9ceb11856332f08fec74b6d46d0d
 *
 * @author dks233
 * @create 2022-08-07-10:32
 */
@SuppressWarnings("ALL")
public class One {
    // 哈希表
    // 时间复杂度：O(N)+O(N)
    public static class MethodTwo {
        public int arithmeticTriplets(int[] nums, int diff) {
            int count = 0;
            HashSet<Integer> set = new HashSet<>();
            for (int index = 0; index < nums.length; index++) {
                set.add(nums[index]);
            }
            for (int index = 0; index < nums.length; index++) {
                if (set.contains(nums[index] + diff) && set.contains(nums[index] + 2 * diff)) {
                    count++;
                }
            }
            return count;
        }
    }

    // 暴力解法
    // 时间复杂度：O(N*N*N)+O(1)
    public static class MethodOne {
        public int arithmeticTriplets(int[] nums, int diff) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length; j++) {
                    for (int k = 0; k < nums.length; k++) {
                        if (i < j && j < k) {
                            if (nums[j] - nums[i] == diff && nums[j] - nums[i] == nums[k] - nums[j]) {
                                count++;
                            }
                        }
                    }
                }
            }
            return count;
        }

    }
}
