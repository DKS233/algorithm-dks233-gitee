package other;

/**
 * leetcode283. 移动零
 *
 * @author dks233
 * @create 2022-07-14-0:07
 */
public class MoveZero {
    // 时间复杂度：O(N)
    // 空间复杂度：O(1)
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        // 第一次遍历把非零元素都移动到数组前面，第二次遍历把多余的置0
        // j表示已经处理的序列的尾部
        int j = 0;
        for (int index = 0; index < nums.length; index++) {
            // 碰到非零元素就把index位置的数换到j位置，然后j位置向右移动
            if (nums[index] != 0) {
                swap(nums, index, j++);
            }
        }
        for (int index = j + 1; index < nums.length; index++) {
            nums[index] = 0;
        }
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
