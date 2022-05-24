package class23;

import common.ArrayUtils;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 *
 * @author dks233
 * @create 2022-05-24-9:48
 */
public class SmallCumulativeSum {
    // 暴力递归
    public static int getSumOne(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int number : arr) {
            sum += number;
        }
        return process(arr, 0, sum / 2);
    }

    // arr[index]及其之后的元素可以任意选择，最接近rest的情况下，该集合的累加和
    // 即较小集合的累加和，初始rest=sum/2
    // 较小集合累加和越接近rest，两个集合累加和越接近
    // 较小集合累加和小于等于rest的情况下，较小的集合累加和越大，两个集合累加和越接近
    // 总结：符合条件的较小集合是是累加和尽量靠近sum/2的集合
    public static int process(int[] arr, int index, int rest) {
        // index越界，说明没数可以选择了
        if (index == arr.length) {
            return 0;
        }
        // 不要arr[index]
        int p1 = process(arr, index + 1, rest);
        // 要arr[index]
        int p2 = 0;
        if (rest - arr[index] >= 0) {
            p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
        }
        return Math.max(p1, p2);
    }

    // 暴力递归改动态规划
    // 可变参数：index:[0,arr.length] rest:[0,sum/2]
    // dp[index][rest]  arr[index]及其之后的元素可以任意选择，最接近rest的情况下，该集合的累加和
    public static int getSumTwo(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int number : arr) {
            sum += number;
        }
        int[][] dp = new int[arr.length + 1][sum / 2 + 1];
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum / 2; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if (rest - arr[index] >= 0) {
                    p2 = arr[index] + dp[index + 1][rest - arr[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum / 2];
    }

    public static void main(String[] args) {
        int maxLen = 23;
        int maxValue = 233;
        int testTimes = 10000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = ArrayUtils.randomArrNoNegative(maxLen, maxValue);
            int one = getSumOne(randomArr);
            int two = getSumTwo(randomArr);
            if (one != two) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}













