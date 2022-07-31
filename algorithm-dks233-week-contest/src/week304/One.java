package week304;

import java.util.Arrays;
import java.util.HashSet;

/**
 * leetcode6132. 使数组中所有元素都等于零
 *
 * @author dks233
 * @create 2022-07-31-10:27
 */
@SuppressWarnings("ALL")
public class One {
    public static class MethodTwo {
        // 统计nums中除0外不同元素的个数
        public int minimumOperations(int[] nums) {
            HashSet<Integer> set = new HashSet<>();
            for (int index = 0; index < nums.length; index++) {
                if (nums[index] != 0) {
                    set.add(nums[index]);
                }
            }
            return set.size();
        }
    }

    // 暴力
    public static class MethodOne {
        public int minimumOperations(int[] nums) {
            Arrays.sort(nums);
            int count = 0;
            while (nums[nums.length - 1] != 0) {
                int x = 0;
                for (int index = 0; index < nums.length; index++) {
                    if (nums[index] > 0) {
                        x = nums[index];
                        break;
                    }
                }
                for (int index = 0; index < nums.length; index++) {
                    if (nums[index] > 0) {
                        nums[index] -= x;
                    }
                }
                count++;
                Arrays.sort(nums);
            }
            return count;
        }
    }
}
