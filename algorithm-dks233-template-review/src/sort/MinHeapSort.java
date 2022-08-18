package sort;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 堆排序：小根堆
 * 从大到小排序
 *
 * @author dks233
 * @create 2022-04-08-11:34
 */
public class MinHeapSort {
    public static void minHeapSort(int[] arr) {

    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArrOne = ArrayUtils.randomArr(maxLen, maxValue);
            int[] copyArrOne = ArrayUtils.copyArr(randomArrOne);
            minHeapSort(randomArrOne);
            reverseArr(randomArrOne);
            Arrays.sort(copyArrOne);
            if (!ArrayUtils.isEquals(randomArrOne, copyArrOne)) {
                isSuccess = false;
                System.out.println("randomArrOne----->" + Arrays.toString(randomArrOne));
                System.out.println("copyArrOne----->" + Arrays.toString(copyArrOne));
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static void reverseArr(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
