package class01;

import java.util.Arrays;

/**
 * 在有序数组中，找>=某个数的最左侧位置
 *
 * @author dks233
 * @create 2022-03-20-16:24
 */
public class BinarySearchNearLeft {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = randomArr(maxLen, maxValue);
            Arrays.sort(randomArr);
            int number = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
            int searchNearleft = searchNearleft(randomArr, number);
            int comparator = comparator(randomArr, number);
            if (searchNearleft != comparator) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static int searchNearleft(int[] arr, int number) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= number) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return index;
    }

    // 对数器
    public static int comparator(int[] arr, int number) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= number) {
                return i;
            }
        }
        return -1;
    }

    public static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }
}
