package class21;

import common.ArrayUtils;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 * TODO:从左往右+从右往左反向练习
 *
 * @author dks233
 * @create 2022-05-21-21:58
 */
public class CurrencyComposition {
    public static int getMethodCountOne(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        return process(arr, 0, aim);
    }

    // [index,arr.length-1]能组出aim的方法数
    // aim为剩余的钱
    public static int process(int[] arr, int index, int aim) {
        if (aim < 0) {
            return 0;
        }
        if (index == arr.length) {
            return aim == 0 ? 1 : 0;
        }
        // 要index位置货币
        int p1 = process(arr, index + 1, aim - arr[index]);
        // 不要index位置货币
        int p2 = process(arr, index + 1, aim);
        return p1 + p2;
    }

    // 暴力递归改动态规划
    // 可变参数：aim和index
    // dp[index][rest]  [index,arr.length-1]在剩余rest钱的情况下能组出rest的方法数
    public static int getMethodCountTwo(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = ((rest - arr[index]) >= 0) ? dp[index + 1][rest - arr[index]] : 0;
                int p2 = dp[index + 1][rest];
                dp[index][rest] = p1 + p2;
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLen = 23;
        int maxValue = 233;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = ArrayUtils.randomArrNoNegative(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            if (getMethodCountOne(arr, aim) != getMethodCountTwo(arr, aim)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

}
