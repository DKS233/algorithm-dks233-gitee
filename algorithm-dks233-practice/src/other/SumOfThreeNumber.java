package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode15. 三数之和
 * 参考文档：https://leetcode.cn/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
 * leetcode16. 最接近的三数之和
 * 参考文档：https://leetcode.cn/problems/3sum-closest/solution/hua-jie-suan-fa-16-zui-jie-jin-de-san-shu-zhi-he-b/
 *
 * @author dks233
 * @create 2022-06-30-21:17
 */
public class SumOfThreeNumber {
    // 题目1要求：找出所有和为0且不重复的三元组
    // 思路：排序+双指针
    // 三个去重操作：index位置去重，left位置去重，right位置去重
    // 时间复杂度：O(N*N)
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            // index位置大于0，三数之和肯定大于0
            if (nums[index] > 0) {
                return list;
            }
            // index位置去重
            if (index > 0 && nums[index] == nums[index - 1]) {
                continue;
            }
            // 固定index位置，双指针指向index位置右边的左右边界
            int left = index + 1;
            int right = nums.length - 1;
            // index=nums.length-2时，left==right，跳出循环
            // index=nums.length-1时，left>right，跳出循环
            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];
                // 三数之和小于0，需要变大，left右移
                if (sum < 0) {
                    left++;
                }
                // 三数之和大于0，需要变小，right左移
                else if (sum > 0) {
                    right--;
                }
                // 三数之和等于0，left右移，right左移，index/left/right三个位置需要去重（index位置去重在上面）
                else {
                    list.add(Arrays.asList(nums[index], nums[left], nums[right]));
                    // left位置去重
                    while (left < right && nums[left + 1] == nums[left]) {
                        left++;
                    }
                    // right位置去重
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return list;
    }

    // 题目2：找出数组中和与target最接近的三个数，返回这三个数的和
    // 时间复杂度：O(N*N)
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int index = 0; index < nums.length; index++) {
            // index位置去重
            if (index > 0 && nums[index] == nums[index - 1]) {
                continue;
            }
            // 固定index位置，双指针指向index位置右边的左右边界
            int left = index + 1;
            int right = nums.length - 1;
            // index=nums.length-2时，left==right，跳出循环
            // index=nums.length-1时，left>right，跳出循环
            while (left < right) {
                int sum = nums[index] + nums[left] + nums[right];
                // 三数之和小于target，需要变大，left右移
                if (sum < target) {
                    ans = Math.abs(target - sum) < Math.abs(ans - target) ? sum : ans;
                    left++;
                }
                // 三数之和大于target，需要变小，right左移
                else if (sum > target) {
                    ans = Math.abs(target - sum) < Math.abs(ans - target) ? sum : ans;
                    right--;
                }
                // 三数之和等于target，直接返回target
                else {
                    return sum;
                }
            }
        }
        return ans;
    }
}
