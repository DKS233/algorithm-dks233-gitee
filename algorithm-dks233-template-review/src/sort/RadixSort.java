package sort;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 基数排序
 * 针对数组元素大于等于0的情况
 * 数组中有元素小于0时，求出数组最小值，如果最小值为负数，数组中每个数加上最小值的绝对值，将数组元素都变为大于等于0的数，
 * 等全部排序完毕再统一减去最小数的绝对值
 * 注：本题未考虑溢出问题
 *
 * @author dks233
 * @create 2022-08-18-22:24
 */
@SuppressWarnings("ALL")
public class RadixSort {
    // 针对非负数组-普通写法
    public static void radixSortNoNegative(int[] arr) {

    }

    // number一共有多少位
    public static int getDigitCount(int number) {
        return -1;
    }

    // number在index位上的值是多少
    public static int getDigit(int number, int index) {
        return -1;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            // 针对非负数组-普通写法
            int[] randomArrOne = ArrayUtils.randomArrNoNegative(maxLen, maxValue);
            int[] copyArrOne = ArrayUtils.copyArr(randomArrOne);
            radixSortNoNegative(randomArrOne);
            Arrays.sort(copyArrOne);
            if (!ArrayUtils.isEquals(randomArrOne, copyArrOne)) {
                isSuccess = false;
                System.out.println("针对非负数组-普通写法测试出错");
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
