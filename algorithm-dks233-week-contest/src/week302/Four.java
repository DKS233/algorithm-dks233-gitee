package week302;

import java.util.Arrays;

/**
 * leetcode6164. 数位和相等数对的最大和
 *
 * @author dks233
 * @create 2022-07-17-10:23
 */
public class Four {
    public static void main(String[] args) {
        Four four = new Four();
        int[] nums1 = new int[]{2, 8, 2};
        int[] nums2 = new int[]{6, 8, 9};
        four.minOperations(nums1, nums2);
    }

    public int minOperations(int[] nums, int[] numsDivide) {
        // 找numsDivide的最大公约数
        int maxCommonDivisor = numsDivide[0];
        for (int index = 1; index < numsDivide.length; index++) {
            maxCommonDivisor = maxCommonDivisor(maxCommonDivisor, numsDivide[index]);
        }
        // nums排序
        Arrays.sort(nums);
        if (maxCommonDivisor == 1) {
            return nums[0] == 1 ? 0 : -1;
        }
        System.out.println(maxCommonDivisor);
        // 统计nums中各元素出现的次数
        // 如果最小值可以被最大公约数整除，停止，否则一直删除最小元素
        int index = 0;
        int count = 0;
        while (true) {
            while (index < nums.length && !(nums[index] <= maxCommonDivisor && (maxCommonDivisor % nums[index] == 0))) {
                count++;
                index++;
            }
            // 越界也找不到符合要求的
            if (index == nums.length) {
                return -1;
            }
            if (nums[index] <= maxCommonDivisor && maxCommonDivisor % nums[index] == 0) {
                break;
            }
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
