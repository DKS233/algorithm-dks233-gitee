package other;

/**
 * leetcode384. 打乱数组
 *
 * @author dks233
 * @create 2022-08-28-0:07
 */
@SuppressWarnings("ALL")
public class ScrambleArray {
    public static class Solution {
        int[] initial;

        public Solution(int[] nums) {
            initial = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                initial[i] = nums[i];
            }
        }

        public int[] reset() {
            return initial;
        }

        // i位置和[i,n-1]位置的随机一个数进行交换
        // [0,n-1-i] + i
        public int[] shuffle() {
            int[] nums = initial.clone();
            for (int i = 0; i < nums.length; i++) {
                int swapIndex = i + (int) (Math.random() * (nums.length - i));
                swap(nums, i, swapIndex);
            }
            return nums;
        }

        public void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }
}
