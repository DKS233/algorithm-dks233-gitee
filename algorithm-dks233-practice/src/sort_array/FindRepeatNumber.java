package sort_array;

import java.util.HashSet;

/**
 * 剑指offer03：数组中重复的数字
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，
 * 但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * @author dks233
 * @create 2022-04-24-8:48
 */
public class FindRepeatNumber {
    // 方法一：哈希表
    // 时间复杂度：O(N) 空间复杂度：O(N)
    // HashSet添加和查找元素复杂度都是O(1)
    public int findRepeatNumberOne(int[] arr) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int value : arr) {
            if (hashSet.contains(value)) {
                return value;
            } else {
                hashSet.add(value);
            }
        }
        return -1;
    }

    // 方法二：原地交换
    // 注：数组索引和数组元素的值都位于[0,n-1]，可以一一对应，即数组元素等于i就放到i位置
    // 图解：数组中的重复数字.drawio
    // 时间复杂度：O(N) 额外空间复杂度：O(1)
    public int findRepeatNumberTwo(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 调整i位置
            // i位置调整完毕就调整下一个位置
            while (arr[i] != i) {
                // 如：0位置上是2,2位置上也是2,2为重复元素
                if (arr[arr[i]] == arr[i]) {
                    return arr[i];
                }
                // 如：0位置上是2,2位置上不是2，交换，2位置调整完毕
                // 交换完毕后继续调整当前位置，直到当前位置的值和当前位置的索引相等
                swap(arr, i, arr[i]);
            }
        }
        // 数组中没有重复元素，数组调整完毕
        // 因为数组元素索引范围为[0,n-1]，数组元素的值范围为[0,n-1]，所以没有重复元素时数组元素的值和索引是一一对应的
        // 即i位置的值是i
        return -1;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
