package other;

/**
 * leetcode26. 删除有序数组中的重复项
 * 参考文档：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/solution/shuang-zhi-zhen-shan-chu-zhong-fu-xiang-dai-you-hu/
 *
 * @author dks233
 * @create 2022-06-30-23:33
 */
public class RemoveDuplicatesFromAnOrderedArray {
    // 快慢指针
    // 慢指针指向最后一个不重复元素的位置
    // 快指针指向当前遍历到的位置
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int slow = 0;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[fast] == nums[slow]) {
                fast++;
            } else {
                nums[slow + 1] = nums[fast];
                slow++;
            }
        }
        return slow + 1;
    }

    public static void main(String[] args) {
        RemoveDuplicatesFromAnOrderedArray test = new RemoveDuplicatesFromAnOrderedArray();
        int[] nums = new int[]{1, 1, 2};
        System.out.println(test.removeDuplicates(nums));
    }
}
