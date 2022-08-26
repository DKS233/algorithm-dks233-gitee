package other;

/**
 * leetcode189. 轮转数组
 *
 * @author dks233
 * @create 2022-08-26-17:12
 */
@SuppressWarnings("ALL")
public class RotationArray {
    // 步骤1：整个数组翻转
    // 步骤2：定位到k位置，[0,k-1] [k,nums.length-1]位置数组翻转
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
