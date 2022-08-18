package sort;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author dks233
 * @create 2022-03-29-20:37
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {

    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = ArrayUtils.randomArr(maxLen, maxValue);
            int[] copyArr = ArrayUtils.copyArr(randomArr);
            mergeSort(randomArr);
            Arrays.sort(copyArr);
            if (!ArrayUtils.isEquals(randomArr, copyArr)) {
                System.out.println("递归测试失败");
            }
        }
        System.out.println("测试成功");
    }


}
