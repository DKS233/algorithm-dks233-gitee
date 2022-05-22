package class21;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 *
 * @author dks233
 * @create 2022-05-22-8:40
 */
public class CurrencyCompositionTwo {
    // 暴力递归
    public static int getCountOne(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    // [index,...]组成rest的方法数
    public static int process(int[] arr, int index, int rest) {
        // index越界，rest==0说明之前的选择有效，rest!=0说明之前的选择无效
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        // 从左往右
        // 0位置面值选0张，0位置面试选1张，...
        int ans = 0;
        // 边界条件：indexCount * arr[index] == rest
        // 边界条件下，之后的rest会一直等于0，ans也会一直等于0，直到index越界，ans累加1
        for (int indexCount = 0; indexCount * arr[index] <= rest; indexCount++) {
            ans += process(arr, index + 1, rest - (indexCount * arr[index]));
        }
        return ans;
    }

    // 暴力递归改动态规划
    // 可变参数：index，rest
    // dp[index][rest]表示[index,...]组成rest的方法数
    public static int getCountTwo(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = 0;
                // 边界条件：indexCount * arr[index] == rest
                // 边界条件下，之后的rest会一直等于0，ans也会一直等于0，直到index越界，ans累加1
                // basecase不需要考虑rest<0，因为这里做了限制
                for (int indexCount = 0; indexCount * arr[index] <= rest; indexCount++) {
                    ans += dp[index + 1][rest - (indexCount * arr[index])];
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    // 严格表结构进一步优化
    // 图解：组货币2-动态规划.drawio
    public static int getCountThree(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int left = (rest - arr[index]) >= 0 ? dp[index][rest - arr[index]] : 0;
                int down = dp[index + 1][rest];
                dp[index][rest] = down + left;
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTimes = 100000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = getCountOne(arr, aim);
            int ans2 = getCountTwo(arr, aim);
            int ans3 = getCountThree(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }
}











