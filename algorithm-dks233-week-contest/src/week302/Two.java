package week302;

import java.util.*;

/**
 * leetcode6164. 数位和相等数对的最大和
 *
 * @author dks233
 * @create 2022-07-17-10:23
 */
public class Two {
    public int maximumSum(int[] nums) {
        // 排序
        Arrays.sort(nums);
        // 数位和数组
        int[] digitSum = new int[nums.length];
        for (int index = 0; index < nums.length; index++) {
            int digitCount = getDigitCount(nums[index]);
            for (int i = 0; i < digitCount; i++) {
                digitSum[index] += getDigitNumber(nums[index], i);
            }
        }
        // 数位和->对应的索引位置
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        for (int index = 0; index < digitSum.length; index++) {
            if (hashMap.containsKey(digitSum[index])) {
                hashMap.get(digitSum[index]).add(index);
            } else {
                hashMap.put(digitSum[index], new ArrayList<>());
                hashMap.get(digitSum[index]).add(index);
            }
        }
        int result = -1;
        for (Map.Entry<Integer, List<Integer>> entry : hashMap.entrySet()) {
            List<Integer> indexList = entry.getValue();
            if (indexList.size() >= 2) {
                result = Math.max(result, nums[indexList.get(indexList.size() - 1)] + nums[indexList.get(indexList.size() - 2)]);
            }
        }
        return result;
    }

    // 获得number在第digit数位上的值，从左往右第一位记为第0位
    // 234 从左往右依次记成0位，1位，2位
    // 234%10     2位  number/Math.pow(10,digit-3)%10
    // 234/10%10  1位  number/Math.pow(10,digit-2)%10
    // 234/100%10 0位  number/Math.pow(10,digit-1)%10
    public int getDigitNumber(int number, int digit) {
        return (int) ((number / Math.pow(10, getDigitCount(number) - digit - 1)) % 10);
    }

    // 获得number总共有多少数位
    public int getDigitCount(int number) {
        int ans = 0;
        while (number >= 1) {
            ans += 1;
            number /= 10;
        }
        return ans;
    }
}
