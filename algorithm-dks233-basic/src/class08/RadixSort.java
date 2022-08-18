package class08;

import common.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 基数排序
 * 针对数组元素大于等于0的情况
 * 数组中有元素小于0时，求出数组最小值，如果最小值为负数，数组中每个数加上最小值的绝对值，将数组元素都变为大于等于0的数，
 * 等全部排序完毕再统一减去最小数的绝对值
 * 注：本题未考虑溢出问题
 *
 * @author dks233
 * @create 2022-04-14-10:10
 */
public class RadixSort {

    // 针对非负数组-普通写法
    public static void radixSortNoNegative(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 数组中最大的数有多少位
        int maxDigit = getMaxDigit(arr);
        // 准备10个桶，对应0到9共10个数
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }
        // 从个位数开始，依次进桶出桶
        for (int i = 1; i <= maxDigit; i++) {
            for (int j = 0; j < arr.length; j++) {
                // 获取数组元素每个十进制位上的数（个位，十位，百位，...）
                int indexValue = getIndexValue(arr[j], i);
                // 将对应的数组元素按照十进制位上的数放到桶中
                // 如：个位数上是0，就放到0桶中
                buckets.get(indexValue).add(arr[j]);
            }
            // 全部放到桶中后，每个桶中元素按照先进先出的顺序出桶
            int currentIndex = 0;
            for (int k = 0; k < buckets.size(); k++) {
                while (!buckets.get(k).isEmpty()) {
                    arr[currentIndex++] = buckets.get(k).remove(0);
                }
            }
        }
    }

    // 针对非负数组（优雅写法）
    public static void radixSortNoNegativeBetter(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int[] help = new int[arr.length];
        // 数组中最大值的最大位数
        int maxDigit = getMaxDigit(arr);
        // 遍历每位，分别基数排序
        for (int i = 1; i <= maxDigit; i++) {
            int j = 0;
            int k = 0;
            // 先准备count数组和count'数组
            int[] count = new int[10];
            // 得到count数组
            for (j = 0; j < arr.length; j++) {
                int indexValue = getIndexValue(arr[j], i);
                count[indexValue]++;
            }
            // 得到count'数组
            for (k = 1; k < 10; k++) {
                count[k] = count[k - 1] + count[k];
            }
            // 从右往左遍历
            for (j = arr.length - 1; j >= 0; j--) {
                int indexValue = getIndexValue(arr[j], i);
                help[count[indexValue] - 1] = arr[j];
                count[indexValue]--;
            }
            // 将help拷贝到原数组
            for (j = 0; j < arr.length; j++) {
                arr[j] = help[j];
            }
        }
    }

    // 数组可以有负元素
    public static void radixSortCanNegative(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 求出数组最小值，如果最小值是负数，加上绝对值，将数组元素都变为非负数组
        int min = Integer.MAX_VALUE;
        for (int element : arr) {
            min = Math.min(element, min);
        }
        if (min < 0) {
            for (int index = 0; index < arr.length; index++) {
                arr[index] += Math.abs(min);
            }
        }
        // 数组中最大的数有多少位
        int maxDigit = getMaxDigit(arr);
        // 准备10个桶，对应0到9共10个数
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }
        // 从个位数开始，依次进桶出桶
        for (int i = 1; i <= maxDigit; i++) {
            for (int j = 0; j < arr.length; j++) {
                // 获取数组元素每个十进制位上的数（个位，十位，百位，...）
                int indexValue = getIndexValue(arr[j], i);
                // 将对应的数组元素按照十进制位上的数放到桶中
                // 如：个位数上是0，就放到0桶中
                buckets.get(indexValue).add(arr[j]);
            }
            // 全部放到桶中后，每个桶中元素按照先进先出的顺序出桶
            int currentIndex = 0;
            for (int k = 0; k < buckets.size(); k++) {
                while (!buckets.get(k).isEmpty()) {
                    arr[currentIndex++] = buckets.get(k).remove(0);
                }
            }
        }
        // 最小值是负数的话，统一减去最小值绝对值
        if (min < 0) {
            for (int index = 0; index < arr.length; index++) {
                arr[index] -= Math.abs(min);
            }
        }
    }

    // 获取数组中最大值的位数
    public static int getMaxDigit(int[] arr) {
        int maxDigit = 1;
        for (int number : arr) {
            int count = 0;
            while (number >= 1) {
                count += 1;
                number /= 10;
            }
            maxDigit = Math.max(count, maxDigit);
        }
        return maxDigit;
    }

    // 获取number的index位置上的数的值 index=1表示个位数
    public static int getIndexValue(int number, int index) {
        return ((number / (int) Math.pow(10, index - 1)) % 10);
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
            // 针对非负数组-优雅写法
            int[] randomArrThree = ArrayUtils.randomArrNoNegative(maxLen, maxValue);
            int[] copyArrThree = ArrayUtils.copyArr(randomArrThree);
            radixSortNoNegativeBetter(randomArrThree);
            Arrays.sort(copyArrThree);
            if (!ArrayUtils.isEquals(randomArrThree, copyArrThree)) {
                isSuccess = false;
                System.out.println("针对非负数组-优雅写法测试出错");
            }
            // 针对可以有负数的数组
            int[] randomArrTwo = ArrayUtils.randomArr(maxLen, maxValue);
            int[] copyArrTwo = ArrayUtils.copyArr(randomArrTwo);
            radixSortCanNegative(randomArrTwo);
            Arrays.sort(copyArrTwo);
            if (!ArrayUtils.isEquals(randomArrTwo, copyArrTwo)) {
                isSuccess = false;
                System.out.println("针对可以有负数的数组测试出错");
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
