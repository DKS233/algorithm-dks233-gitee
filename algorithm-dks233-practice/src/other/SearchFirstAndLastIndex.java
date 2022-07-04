package other;

/**
 * leetcode34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * @author dks233
 * @create 2022-07-04-15:39
 */
@SuppressWarnings("ALL")
public class SearchFirstAndLastIndex {
    // 思路：二分法查找等于target的第一个位置和大于target的第一个位置的前一个元素
    // 时间复杂度：O(N)
    public int[] searchRange(int[] nums, int target) {
        int firstIndex = getBiggerIndex(nums, target - 1);
        int secondIndex = getBiggerIndex(nums, target) - 1;
        if (firstIndex <= secondIndex && nums[firstIndex] == target && nums[secondIndex] == target) {
            return new int[]{firstIndex, secondIndex};
        }
        return new int[]{-1, -1};
    }

    // 返回大于number的第一个元素的位置
    // 题目所求=[等于target的第一个位置，大于target的第一个位置的前一个位置]
    // 即=[大于target-1的第一个位置，大于target的第一个位置的前一个位置]
    public int getBiggerIndex(int[] nums, int number) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        // 特殊情况：ans不更新时，ans=nums.length
        // firstIndex不更新，firstIndex=nums.length，说明没有大于target-1的数，也就没有大于target的数，返回[-1,-1]
        // secondIndex不更新，secondIndex=nums.length-1，说明没有大于target的数，看firstIndex
        // 如果firstIndex=nums.length，返回[-1,-1]
        // 如果firstIndex<nums.length，只有满足if语句里的情况才返回{firstIndex,secondIndex}
        int ans = nums.length;
        while (leftIndex <= rightIndex) {
            int mid = leftIndex + ((rightIndex - leftIndex) >> 1);
            if (nums[mid] > number) {
                rightIndex = mid - 1;
                ans = mid;
            } else {
                leftIndex = mid + 1;
            }
        }
        return ans;
    }
}
