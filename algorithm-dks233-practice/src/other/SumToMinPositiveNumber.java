package other;

/**
 * leetcode1413. 逐步求和得到正数的最小值
 *
 * @author dks233
 * @create 2022-08-30-15:58
 */
@SuppressWarnings("ALL")
public class SumToMinPositiveNumber {
    // 分析：最小前缀和如果小于0，结果等于最小前缀和取反+1
    // 最小前缀和如果大于等于0，结果是1（题目要求结果是正数）
    public int minStartValue(int[] nums) {
        int result = 0;
        int preSum = 0;
        int minPresum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            minPresum = Math.min(preSum, minPresum);
        }
        if (minPresum >= 0) {
            return 1;
        }
        return -minPresum + 1;
    }
}
