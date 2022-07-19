package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 012. 左右两边子数组的和相等
 *
 * @author dks233
 * @create 2022-07-18-23:55
 */
public class LeftRightSubArraySumEqual {
    public int pivotIndex(int[] nums) {
        // preSums[index] 表示index之前元素的前缀和，0位置之前元素的前缀和是0
        int[] preSums = new int[nums.length + 1];
        preSums[0] = 0;
        int sum = 0;
        for (int index = 1; index < preSums.length; index++) {
            sum += nums[index - 1];
            preSums[index] = sum;
        }
        // 某一个元素左右两侧元素用前缀和表示为，设元素在nums索引为index
        // 左侧：preSums[index] 右侧：preSums[nums.length]-preSums[index]-nums[index]
        for (int index = 0; index < nums.length; index++) {
            if (preSums[nums.length] - preSums[index] - nums[index] == preSums[index]) {
                return index;
            }
        }
        return -1;
    }
}
