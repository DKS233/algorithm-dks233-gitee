package week309;

/**
 * 6169. 最长优雅子数组
 * 参考文档：https://leetcode.cn/problems/longest-nice-subarray/solution/bao-li-mei-ju-pythonjavacgo-by-endlessch-z6t6/
 *
 * @author dks233
 * @create 2022-09-04-9:48
 */
@SuppressWarnings("ALL")
public class Three {
    public static class MethodOne {
        // 优雅子数组中的元素相互进行与操作结果为0，所以二进制位的1不重合
        // 用或操作将优雅子数组中的元素标记起来，后续的数和标记的数进行与操作判断是否可以加到优雅子数组中
        public int longestNiceSubarray(int[] nums) {
            int result = 1;
            for (int i = 0; i < nums.length; i++) {
                int orNum = 0;
                int j = i;
                while (j < nums.length && (orNum & nums[j]) == 0) {
                    orNum |= nums[j];
                    j++;
                }
                result = Math.max(result, j - i);
            }
            return result;
        }
    }

    // 滑动窗口
    public static class MethodTwo {
        // 优雅子数组中的元素相互进行与操作结果为0，所以二进制位的1不重合
        // 用或操作将优雅子数组中的元素标记起来，后续的数和标记的数进行与操作判断是否可以加到优雅子数组中
        public int longestNiceSubarray(int[] nums) {
            int left = 0;
            int right = 0;
            int orNum = 0;
            int result = 1;
            while (right < nums.length) {
                // 如果右边不满足，去除左边，左边界右移
                while ((nums[right] & orNum) > 0) {
                    orNum ^= nums[left];
                    left++;
                }
                // 如果满足就一直将右边界添加到优雅子数组中
                orNum |= nums[right++];
                result = Math.max(right - left, result);
            }
            return result;
        }
    }

}
