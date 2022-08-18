package sort;

import common.ArrayUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 快速排序
 * 递归版本
 *
 * @author dks233
 * @create 2022-04-03-17:45
 */
public class QuickSort {
    // 快速排序递归实现
    private static void quickSort(int[] arr) {

    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = ArrayUtils.randomArr(maxLen, maxValue);
            int[] copyArr = ArrayUtils.copyArr(randomArr);
            quickSort(randomArr);
            Arrays.sort(copyArr);
            if (!ArrayUtils.isEquals(randomArr, copyArr)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
