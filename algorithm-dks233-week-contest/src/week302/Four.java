package week302;

import java.util.Arrays;

/**
 * leetcode6164. 数位和相等数对的最大和
 *
 * @author dks233
 * @create 2022-07-17-10:23
 */
public class Four {
    public int minOperations(int[] nums, int[] numsDivide) {
        // 找numsDivide的最大公约数
        int maxCommonDivisor = numsDivide[0];
        for (int index = 1; index < numsDivide.length; index++) {
            maxCommonDivisor = Math.min(maxCommonDivisor(numsDivide[0], numsDivide[index]), maxCommonDivisor);
        }
        // nums排序
        Arrays.sort(nums);
        // 统计nums中各元素出现的次数
        // 如果最小值可以被最大公约数整除，停止，否则一直删除最小元素
        int index = 0;
        int count = 0;
        while (true) {
            int min = nums[index];
            if (maxCommonDivisor >= min && maxCommonDivisor % min == 0) {
                break;
            }
            while (index < nums.length && nums[index] == min) {
                count++;
                index++;
            }
            // 删到越界仍然满足不了条件
            if (index == nums.length) {
                return -1;
            }
            // 未越界，去下一个不等于最小元素的地方判断
        }
        return count;
    }

    public int maxCommonDivisor(int m, int n) {
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        while (m % n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        return n;
    }
}
