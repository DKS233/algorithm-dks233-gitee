package class23;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 *
 * @author dks233
 * @create 2022-05-24-10:52
 */
public class SmallCumulativeSumTwo {
    public static int getSumOne(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int number : arr) {
            sum += number;
        }
        if (arr.length % 2 == 0) {
            return process(arr, 0, sum / 2, arr.length / 2);
        } else {
            return Math.max(process(arr, 0, sum / 2, arr.length / 2),
                    process(arr, 0, sum / 2, arr.length / 2 + 1));
        }
    }

    /**
     * arr[index]及其之后的元素可以任意选择，最接近rest的情况下，该集合的累加和
     * 即较小集合的累加和，初始rest=sum/2
     * 较小集合累加和越接近rest，两个集合累加和越接近
     * 较小集合累加和小于等于rest/2的情况下，较小的集合累加和越大，两个集合累加和越接近
     * 总结：符合条件的较小集合是是累加和尽量靠近sum/2的集合
     *
     * @param arr   数组
     * @param index 遍历到的索引
     * @param rest  尽量接近的sum/2
     * @param pick  当前选定的个数
     * @return arr[index]及其之后的元素可以任意选择，最接近rest的情况下，该集合的累加和
     */
    public static int process(int[] arr, int index, int rest, int pick) {
        // index越界
        // pick==0 表示之前选定的个数正好符合要求，之前的选择有效
        // pick！=0 表示之前选定的个数不符合要求，之前的选择无效
        if (index == arr.length) {
            return pick == 0 ? 0 : -1;
        }
        // 不要arr[index]
        int p1 = process(arr, index + 1, rest, pick);
        // 这里p2初始值为-1
        // p2如果进不去if语句不能影响p1的输出
        // 如果p1=-1,p2进不去if语句，说明p1和p2都无效，无效统一返回-1
        // 要arr[index]
        int p2 = -1;
        if (rest - arr[index] >= 0) {
            int next = process(arr, index + 1, rest - arr[index], pick - 1);
            p2 = next == -1 ? -1 : arr[index] + next;
        }
        return Math.max(p1, p2);
    }

    // 暴力递归改动态规划
    // 分析可变参数：index:[0,arr.length],rest:[0,sum/2],pick:奇数[0,arr.length/2+1]偶数[0,arr.length/2]
    // pick:统一向上取整  pick:[0,(arr.length+1)/2] 比如长度是7，pick:[0,4] 比如长度是8，pick:[0,4]
    public static int getSumTwo(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int number : arr) {
            sum += number;
        }
        // dp[index][rest][pick] 表示从index开始，用pick个元素能组成rest的较小集合的累加和
        int indexCount = arr.length + 1;
        int restCount = sum / 2 + 1;
        int pickCount = (arr.length + 1) / 2 + 1;
        int[][][] dp = new int[indexCount][restCount][pickCount];
        for (int rest = 0; rest < restCount; rest++) {
            for (int pick = 1; pick < pickCount; pick++) {
                dp[arr.length][rest][pick] = -1;
            }
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest < restCount; rest++) {
                for (int pick = 0; pick < pickCount; pick++) {
                    int p1 = dp[index + 1][rest][pick];
                    int p2 = -1;
                    if (rest - arr[index] >= 0 && pick - 1 >= 0) {
                        // pick-1可能越界
                        int next = dp[index + 1][rest - arr[index]][pick - 1];
                        p2 = next == -1 ? -1 : arr[index] + next;
                    }
                    dp[index][rest][pick] = Math.max(p1, p2);
                }
            }
        }
        if (arr.length % 2 == 0) {
            return dp[0][sum / 2][arr.length / 2];
        } else {
            return Math.max(dp[0][sum / 2][arr.length / 2], dp[0][sum / 2][arr.length / 2 + 1]);
        }
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTimes = 10000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = randomArr(maxLen, maxValue);
            int one = getSumOne(randomArr);
            int two = getSumTwo(randomArr);
            if (one != two) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static int[] randomArr(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }
}
