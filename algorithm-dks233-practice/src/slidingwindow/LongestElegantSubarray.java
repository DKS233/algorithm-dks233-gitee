package slidingwindow;

/**
 * leetcode2401. 最长优雅子数组
 *
 * @author dks233
 * @create 2022-09-10-14:44
 */
@SuppressWarnings("ALL")
public class LongestElegantSubarray {
    // 最长优雅子数组内的数按与运算等于0，所以数组中的数二进制的1不重合
    // 所以可以用或运算统计优雅子数组的数，后续的数和这个数进行与运算如果等于0，说明和数组中其他数做与运算都等于0
    public int longestNiceSubarray(int[] nums) {
        int left = 0;
        int right = 1;
        int or = nums[0];
        int maxLen = 1;
        while (right < nums.length) {
            // 如果右边界不符合就一直就一直更新左边界
            while (left < nums.length && (nums[right] & or) != 0) {
                or ^= nums[left];
                left++;
            }
            // 右边界符合条件就统计最大长度，然后更新右边界
            or |= nums[right++];
            maxLen = Math.max(right - left, maxLen);
        }
        return maxLen;
    }
}
