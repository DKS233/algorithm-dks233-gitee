package class08;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 计数排序
 * 本题解默认数组为非负数组
 *
 * @author dks233
 * @create 2022-04-13-22:44
 */
public class CountSort {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 23333;
        int maxValue = 233;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = randomArr(maxLen, maxValue);
            int[] copyArr = ArrayUtils.copyArr(randomArr);
            countSort(randomArr);
            comparator(copyArr);
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
        for (int element : randomArr) {
            element = (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }

    // 对一组在0~200范围内的数进行排序
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        // 每个桶范围：[0,max]
        // bucket数组索引就是0->max，数组值为0->max的数出现的次数
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j] > 0) {
                arr[i++] = j;
                bucket[j]--;
            }
        }
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

}




























