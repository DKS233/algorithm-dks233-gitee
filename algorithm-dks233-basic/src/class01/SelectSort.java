package class01;

import java.util.Arrays;

/**
 * 选择排序，从小到大排序
 *
 * @author dks233
 * @create 2022-03-19-10:18
 */
public class SelectSort {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArr(maxLen, maxValue);
            int[] copyArr = copyArr(arr);
            selectSort(arr);
            comparator(copyArr);
            boolean equals = isEquals(arr, copyArr);
            if (!equals) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");

    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 每轮排好序的范围
        // 0 0->1 0->2 ... 0->n-1
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, minValueIndex, i);
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] randomArr = new int[len];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }

    public static int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < copyArr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    public static boolean isEquals(int[] arr, int[] copyArr) {
        if (arr == null && copyArr != null || arr != null && copyArr == null) {
            return false;
        }
        if (arr == null && copyArr == null) {
            return false;
        }
        if (arr.length != copyArr.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != copyArr[i]) {
                return false;
            }
        }
        return true;
    }
}
