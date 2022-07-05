package other;

/**
 * leetcode35. 搜索插入位置
 * 参考文档：https://leetcode.cn/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/
 *
 * @author dks233
 * @create 2022-07-05-20:48
 */
@SuppressWarnings("ALL")
public class SearchInsertionLocation {
    // 找到大于target的第一个位置，然后看前一个数
    // 如果前一个数等于target，返回前一个数索引
    // 如果前一个数小于target，返回大于target的第一个位置
    public int searchInsert(int[] nums, int target) {
        int firstBigIndex = process(nums, target, 0, nums.length - 1);
        if (firstBigIndex == 0) {
            return nums[firstBigIndex] >= target ? 0 : 1;
        }
        return nums[firstBigIndex - 1] == target ? firstBigIndex - 1 : firstBigIndex;
    }

    public int process(int[] nums, int target, int left, int right) {
        int ans = nums.length;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
