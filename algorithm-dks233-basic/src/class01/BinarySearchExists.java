package class01;

import java.util.Arrays;

/**
 * 在一个有序数组中，找某个数是否存在
 *
 * @author dks233
 * @create 2022-03-20-15:25
 */
public class BinarySearchExists {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSucccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = randomArr(maxLen, maxValue);
            Arrays.sort(randomArr);
            int number = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
            boolean exists = isExists(randomArr, number);
            boolean comparator = comparator(randomArr, number);
            if (!exists == comparator) {
                isSucccess = false;
                break;
            }
        }
        System.out.println(isSucccess ? "测试成功" : "测试失败");
    }

    public static boolean isExists(int[] arr, int number) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == number) {
                return true;
            } else if (arr[mid] > number) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] randomArr = new int[len];
        for (int i = 0; i < randomArr.length; i++) {
            randomArr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }

    // 对数器
    public static boolean comparator(int[] arr, int number) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        for (int value : arr) {
            if (value == number) {
                return true;
            }
        }
        return false;
    }
}
