package sort;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 选择排序，从小到大排序
 *
 * @author dks233
 * @create 2022-03-19-10:18
 */
public class SelectSort {
    public static void selectSort(int[] arr) {

    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = ArrayUtils.randomArr(maxLen, maxValue);
            int[] copyArr = ArrayUtils.copyArr(arr);
            selectSort(arr);
            Arrays.sort(copyArr);
            boolean equals = ArrayUtils.isEquals(arr, copyArr);
            if (!equals) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");

    }

}
