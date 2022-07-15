package other;

/**
 * leetcode581. 最短无序连续子数组
 * 参考文档：https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/solution/si-lu-qing-xi-ming-liao-kan-bu-dong-bu-cun-zai-de-/
 *
 * @author dks233
 * @create 2022-07-14-23:09
 */
@SuppressWarnings("ALL")
public class ShortestUnorderedContinuousSubarray {
    // 从左到右：维持一个max，表示遍历过的最大元素
    // 从左到右最后一个小于max的位置即为中段的右边界
    // 从右到左：维持一个min，表示遍历过的最小元素
    // 从右到左最后一个大于min的位置即为中段的左边界
    // 时间复杂度：O(N)
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int curMin = nums[nums.length - 1];
        int curMax = nums[0];
        // 如果数组有序，中段长度应该是0，即right-left+1=0
        int right = -1;
        int left = 0;
        // 先找中段的右边界
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] < curMax) {
                right = index;
            } else {
                curMax = nums[index];
            }
        }
        // 再找中段的左边界
        for (int index = nums.length - 1; index >= 0; index--) {
            if (nums[index] > curMin) {
                left = index;
            } else {
                curMin = nums[index];
            }
        }
        return right - left + 1;
    }
}
