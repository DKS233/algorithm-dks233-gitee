package sort_array;

import java.util.Arrays;

/**
 * 剑指offer21：调整数组顺序，让奇数位于偶数前面
 * 所有奇数在数组的前半部分，所有偶数在数组的后半部分
 *
 * @author dks233
 * @create 2022-04-24-14:57
 */
public class OddBeforeEven {
    // 方法1：类比快排，把数组分为奇数区和偶数区域
    // 方法中的oddArea为奇数区右边界，没用到此变量，声明是为了便于理解
    // 时间复杂度：O(N) 空间复杂度：O(1)
    public static int[] oddEvenOne(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int oddArea = -1;
        int evenArea = arr.length;
        int index = 0;
        while (index < evenArea) {
            if (arr[index] % 2 == 0) {
                swap(arr, index, --evenArea);
            } else {
                oddArea++;
                index++;
            }
        }
        return arr;
    }

    // 方法二：左右双指针分别代表奇数和偶数
    // oddIndex左边都是奇数
    // evenIndex右边都是偶数
    public static int[] oddEvenOneTwo(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        int oddIndex = 0;
        int evenIndex = arr.length - 1;
        while (oddIndex < evenIndex) {
            if (arr[oddIndex] % 2 == 0) {
                swap(arr, oddIndex, evenIndex--);
            } else {
                oddIndex++;
            }
        }
        return arr;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4};
        int[] ints = oddEvenOne(arr);
        System.out.println(Arrays.toString(ints));
    }
}
