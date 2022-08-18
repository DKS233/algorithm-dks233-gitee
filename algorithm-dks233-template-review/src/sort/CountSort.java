package sort;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 计数排序
 * 分配：扫描一遍原始数组，以当前值- minValue 作为下标，将该下标的计数器增1。
 * 收集：扫描一遍计数器数组，按顺序把值收集起来
 * 计数排序本质上是一种特殊的桶排序，当桶的个数最大的时候，就是计数排序
 *
 * @author dks233
 * @create 2022-08-18-22:17
 */
@SuppressWarnings("ALL")
public class CountSort {
    public static void countSort(int[] arr) {

    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLen = 233;
        int maxValue = 23333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = randomArr(maxLen, maxValue);
            int[] copyArr = ArrayUtils.copyArr(randomArr);
            countSort(randomArr);
            Arrays.sort(copyArr);
            if (!ArrayUtils.isEquals(randomArr, copyArr)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen)) + 1;
        int[] randomArr = new int[len];
        for (int index = 0; index < randomArr.length; index++) {
            randomArr[index] = (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }
}
