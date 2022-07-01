package other;

/**
 * leetcode27. 移除元素
 *
 * @author dks233
 * @create 2022-07-01-23:14
 */
@SuppressWarnings("ALL")
public class RemoveElement {
    // 双指针法
    // left指针指向当前遍历到的元素
    // right指针指向移除元素后新数组右边界
    // left位置元素等于val，right位置元素和left位置元素交换，right左移
    // 时间复杂度：O(N)，空间复杂度：O(1)
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] == val ? 0 : 1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] == val) {
                swap(nums, left, right);
                right--;
            } else {
                left++;
            }
        }
        return nums[right] == val ? right : right + 1;
    }

    public void swap(int[] nums, int indexOne, int indexTwo) {
        int temp = nums[indexOne];
        nums[indexOne] = nums[indexTwo];
        nums[indexTwo] = temp;
    }
}
