package sort;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 冒泡排序，从小到大
 *
 * @author dks233
 * @create 2022-03-19-9:39
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {

    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = ArrayUtils.randomArr(maxLen, maxValue);
            int[] copyArr = ArrayUtils.copyArr(randomArr);
            bubbleSort(randomArr);
            Arrays.sort(copyArr);
            if (!ArrayUtils.isEquals(randomArr, copyArr)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
