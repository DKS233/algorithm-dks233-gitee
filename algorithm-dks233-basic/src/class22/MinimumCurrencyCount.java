package class22;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 *
 * @author dks233
 * @create 2022-05-23-17:08
 */
public class MinimumCurrencyCount {
    public static int getMinCountOne(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return -1;
        }
        return processOne(arr, 0, aim);
    }

    // arr[index,...]组成rest的最少货币数
    public static int processOne(int[] arr, int index, int rest) {
        // index越界，rest==0，说明[0,arr.length-1]位置作出的选择是有效的，结束递归，统计最小张数
        // rest!=0 表示怎么都搞不定rest
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        // index位置要0个，要1个，要2个，...
        int ans = Integer.MAX_VALUE;
        for (int indexCount = 0; indexCount * arr[index] <= rest; indexCount++) {
            int next = processOne(arr, index + 1, rest - indexCount * arr[index]);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(indexCount + next, ans);
            }
        }
        return ans;
    }

    // 暴力递归改动态规划
    // 分析可变参数：index:[0,arr.length] rest:[0,aim]
    // dp[index][rest]表示[index,...]组成rest的最少货币数
    public static int getMinCountTwo(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return -1;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[arr.length][rest] = Integer.MAX_VALUE;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int indexCount = 0; indexCount * arr[index] <= rest; indexCount++) {
                    int next = dp[index + 1][rest - indexCount * arr[index]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, indexCount + next);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    // 图解：组货币4-动态规划.drawio
    // 不优化版本的在求每个位置的(index,rest)时依赖的位置有几个是不确定的，产生枚举行为，画图分析依赖，去掉枚举行为
    public static int getMinCountThree(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return -1;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 0;
        for (int rest = 1; rest <= aim; rest++) {
            dp[arr.length][rest] = Integer.MAX_VALUE;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 判断 rest - arr[index] < 0  防止越过二维表的边界
                // 判断 dp[index][rest - arr[index]] == Integer.MAX_VALUE 防止后面+1后整数越界
                if (rest - arr[index] < 0 || dp[index][rest - arr[index]] == Integer.MAX_VALUE) {
                    dp[index][rest] = dp[index + 1][rest];
                } else {
                    dp[index][rest] = Math.min(dp[index + 1][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }


    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = getMinCountOne(arr, aim);
            int ans2 = getMinCountTwo(arr, aim);
            int ans3 = getMinCountThree(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("功能测试结束");
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

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
