package other;

/**
 * leetcode33. 搜索旋转排序数组
 * 参考文档：https://leetcode.cn/problems/search-in-rotated-sorted-array/solution/sou-suo-xuan-zhuan-pai-xu-shu-zu-by-leetcode-solut/
 *
 * @author dks233
 * @create 2022-07-05-20:38
 */
@SuppressWarnings("ALL")
public class SearchRotationSortArray {
    // 二分法查找，[left,mid] [mid+1,right]肯定有一个是有序的
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            // 情况1：[left,mid]范围上数组是有序的
            // 注：left==right时，left==mid，[left,left]范围上数组是有序的
            if (nums[left] <= nums[mid]) {
                // 注：左边界=target时，左边界不能动
                if (nums[left] <= target && nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 情况2：[mid+1,right]范围上数组是有序的
            else {
                // 注：右边界等于target时，右边界不能动
                if (nums[right] >= target && nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
