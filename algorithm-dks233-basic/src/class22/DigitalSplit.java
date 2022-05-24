package class22;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 * 对比测试：ZuoDigitalSplit.java
 *
 * @author dks233
 * @create 2022-05-23-22:11
 */
public class DigitalSplit {
    // 暴力递归
    public static int getCountOne(int number) {
        if (number <= 0) {
            return 0;
        }
        int[] arr = new int[number];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return process(arr, 0, number);
    }

    /**
     * @param arr   数值对应[0,number]
     * @param index 当前遍历到的数组索引位置
     * @param rest  剩余rest没被裂开
     * @return [index, ...]裂开rest的方法数
     */
    public static int process(int[] arr, int index, int rest) {
        // index越界，rest==0说明之前选择是有效的，方法数累加1
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ans = 0;
        // index位置选0个，选1个，选2个，...
        for (int indexCount = 0; indexCount * arr[index] <= rest; indexCount++) {
            ans += process(arr, index + 1, rest - indexCount * arr[index]);
        }
        return ans;
    }

    // 暴力递归改动态规划
    // 分析可变参数：index:[0,arr.length] rest:[0,number]
    // dp[index][rest]  arr[index,...]拆解rest的方法数
    public static int getCountTwo(int number) {
        if (number <= 0) {
            return 0;
        }
        int[] arr = new int[number];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int[][] dp = new int[arr.length + 1][number + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= number; rest++) {
                int ans = 0;
                for (int indexCount = 0; indexCount * arr[index] <= rest; indexCount++) {
                    ans += dp[index + 1][rest - indexCount * arr[index]];
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][number];
    }

    // 不优化时每个位置依赖的位置数是不确定的，发生了枚举行为，画二维图分析位置依赖，去掉枚举行为
    // 分析可变参数：index:[0,arr.length] rest:[0,number]
    // dp[index][rest]  arr[index,...]拆解rest的方法数
    public static int getCountThree(int number) {
        if (number <= 0) {
            return 0;
        }
        int[] arr = new int[number];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int[][] dp = new int[arr.length + 1][number + 1];
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= number; rest++) {
                if (rest - arr[index] >= 0) {
                    dp[index][rest] = dp[index][rest - arr[index]] + dp[index + 1][rest];
                } else {
                    dp[index][rest] = dp[index + 1][rest];
                }
            }
        }
        return dp[0][number];
    }

    public static void main(String[] args) {
        int maxValue = 30;
        int testTimes = 10000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int number = (int) (Math.random() * (maxValue)) + 1;
            int myOne = getCountOne(number);
            int myTwo = getCountTwo(number);
            int myThree = getCountThree(number);
            int zuoOne = ZuoDigitalSplit.ways(number);
            int zuoTwo = ZuoDigitalSplit.dp1(number);
            int zuoThree = ZuoDigitalSplit.dp2(number);
            if (!(myOne == myTwo && myOne == myThree && myOne == zuoOne && myOne == zuoTwo && myOne == zuoThree)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
