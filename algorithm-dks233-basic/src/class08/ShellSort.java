package class08;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 希尔排序
 *
 * @author dks233
 * @create 2022-08-19-0:04
 */
public class ShellSort {
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int gap = arr.length / 2;
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j >= gap; j -= gap) {
                    if (arr[j - gap] > arr[j]) {
                        int temp = arr[j];
                        arr[j] = arr[j - gap];
                        arr[j - gap] = temp;
                    }
                }
            }
            gap /= 2;
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isCorrect = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = ArrayUtils.randomArr(maxLen, maxValue);
            int[] copyArr = ArrayUtils.copyArr(randomArr);
            shellSort(randomArr);
            Arrays.sort(copyArr);
            if (!ArrayUtils.isEquals(randomArr, copyArr)) {
                isCorrect = false;
                break;
            }
        }
        System.out.println(isCorrect ? "测试成功" : "测试失败");
    }
}
