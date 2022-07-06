package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * leetcode41. 缺失的第一个正数
 *
 * @author dks233
 * @create 2022-07-06-16:14
 */
@SuppressWarnings("ALL")
public class MissingFirstPositiveNumber {
    // 题目要求：O(N)+O(1)
    // O(N)+O(N)的解法，用hashset存nums所有的数
    // 如果不缺失整数，hashset应该存的是1,2,...，从1开始依次去哈希表里查找
    public int firstMissingPositive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int index = 0; index < nums.length; index++) {
            set.add(nums[index]);
        }
        for (int number = 1; number <= nums.length; number++) {
            if (!set.contains(number)) {
                return number;
            }
        }
        return nums.length + 1;
    }

    // O(N)+O(1)的解法，原地哈希，把值为number的数映射到number-1位置上
    // 映射规则：nums[number-1]=number
    public static class BetterMethod {
        public int firstMissingPositive(int[] nums) {
            // 遍历数组，遇到不符合映射规则的就交换
            // 进行交换的条件：当前数大于0，小于等于nums.length，当前数不符合映射规则
            for (int index = 0; index < nums.length; index++) {
                while (nums[index] > 0 && nums[index] <= nums.length && nums[nums[index] - 1] != nums[index]) {
                    swap(nums, index, nums[index] - 1);
                }
            }
            // 交换完毕后，再次遍历数组，查找不符合映射规则的数，即缺失的正整数
            for (int index = 0; index < nums.length; index++) {
                if (nums[index] != index + 1) {
                    return index + 1;
                }
            }
            // 如果都符合映射规则，返回nums.length+1
            return nums.length + 1;
        }

        public void swap(int[] nums, int indexOne, int indexTwo) {
            int temp = nums[indexOne];
            nums[indexOne] = nums[indexTwo];
            nums[indexTwo] = temp;
        }
    }
}
