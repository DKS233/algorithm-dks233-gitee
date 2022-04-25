package sort_array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 剑指offer39：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素
 * 众数：数组中出现次数最多的数
 * 要求：O(N)+O(1)
 *
 * @author dks233
 * @create 2022-04-25-9:04
 */
public class MoreThanHalf {
    // 方法1：HashMap
    // 时间复杂度：O(N)  空间复杂度：O(N)
    public static int moreThanHalfOne(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!hashMap.containsKey(arr[i])) {
                hashMap.put(arr[i], 0);
            } else {
                hashMap.put(arr[i], hashMap.get(arr[i]) + 1);
            }
        }
        for (Integer arrElement : hashMap.keySet()) {
            if (hashMap.get(arrElement) >= arr.length / 2) {
                return arrElement;
            }
        }
        return arr[0];
    }

    // 方法2：数组排序，数组中点的元素一定是出现次数超过数组一半的数
    public static int moreThanHalfTwo(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length / 2];
    }

    // 方法3：投票
    // 将所求的数称为众数，众数票数为1，非众数票数为-1,将第一个数记为众数，票数为1
    // 遍历数组，如果票数变成0，将下一个数记为众数，遍历到最后一个元素vote必大于0，最后记录的众数即为所求
    // 时间复杂度：O(N)，空间复杂度：O(1)
    public static int moreThanHalfThree(int[] arr) {
        int ans = arr[0];
        int vote = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == ans) {
                vote++;
            } else {
                // 如果arr.length-2位置vote变成0，因为整个数组vote肯定大于0，所以最后一个数肯定是众数
                if (--vote == 0) {
                    ans = arr[i + 1];
                }
            }
        }
        return ans;
    }
}
