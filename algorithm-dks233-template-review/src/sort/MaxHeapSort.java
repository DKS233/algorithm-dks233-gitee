package sort;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 堆排序：大根堆
 * 从小到大排序
 *
 * @author dks233
 * @create 2022-04-05-16:47
 */
public class MaxHeapSort {
    public static void maxHeapSort(int[] arr) {

    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArrOne = ArrayUtils.randomArr(maxLen, maxValue);
            int[] copyArrOne = ArrayUtils.copyArr(randomArrOne);
            maxHeapSort(randomArrOne);
            Arrays.sort(copyArrOne);
            if (!ArrayUtils.isEquals(randomArrOne, copyArrOne)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
