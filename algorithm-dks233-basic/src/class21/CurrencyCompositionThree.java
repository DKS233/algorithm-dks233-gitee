package class21;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 *
 * @author dks233
 * @create 2022-05-22-10:46
 */
public class CurrencyCompositionThree {
    // 暴力递归
    public static int getCountOne(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 用HashMap实现面值去重
        // key->面值 value->面值对应货币有几张
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int value : arr) {
            if (hashMap.containsKey(value)) {
                hashMap.put(value, hashMap.get(value) + 1);
            } else {
                hashMap.put(value, 1);
            }
        }
        // 构建两个数组，面值数组和对应的数量数组
        int length = hashMap.size();
        int[] face = new int[length];
        int[] count = new int[length];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            Integer faceValue = entry.getKey();
            Integer countValue = entry.getValue();
            face[index] = faceValue;
            count[index] = countValue;
            index++;
        }
        return process(face, count, 0, aim);
    }

    /**
     * [index,...]组成rest的方法数
     *
     * @param face  面值数组
     * @param count 每张面值对应的数量，和面值数组一一对应
     * @param index 遍历到的数组位置
     * @param rest  剩余多少钱
     * @return index和之后的面值组成rest的方法数
     */
    public static int process(int[] face, int[] count, int index, int rest) {
        // index越界
        // rest == 0 说明之前做的选择是有效的
        // rest != 0 说明之前做的选择是无效的
        if (index == face.length) {
            return rest == 0 ? 1 : 0;
        }
        // index位置要0张，要1张，要2张，...
        // 边界条件：indexCount * face[index] == rest ，之后的rest都等于0，ans也等于0，直到index越界的时候ans累加1
        // basecase不需要考虑rest<0，因为这里做了限制
        int ans = 0;
        for (int indexCount = 0; indexCount * face[index] <= rest && indexCount <= count[index]; indexCount++) {
            ans += process(face, count, index + 1, rest - indexCount * face[index]);
        }
        return ans;
    }

    // 暴力递归改动态规划
    // 可变参数：index rest
    // dp[index][rest]表示[index,...]组成rest的方法数
    public static int getCountTwo(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 用HashMap实现面值去重
        // key->面值 value->面值对应货币有几张
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int value : arr) {
            if (hashMap.containsKey(value)) {
                hashMap.put(value, hashMap.get(value) + 1);
            } else {
                hashMap.put(value, 1);
            }
        }
        // 构建两个数组，面值数组和对应的数量数组
        int length = hashMap.size();
        int[] face = new int[length];
        int[] count = new int[length];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            Integer faceValue = entry.getKey();
            Integer countValue = entry.getValue();
            face[i] = faceValue;
            count[i] = countValue;
            i++;
        }
        // index:[0,face.length] rest:[0,aim]
        // dp[index][rest]表示[index,...]组成rest的方法数
        int[][] dp = new int[face.length + 1][aim + 1];
        dp[face.length][0] = 1;
        for (int index = face.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ans = 0;
                for (int indexCount = 0; indexCount * face[index] <= rest && indexCount <= count[index]; indexCount++) {
                    ans += dp[index + 1][rest - indexCount * face[index]];
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    // 动态规划优化
    // 图解：组货币3-动态规划.drawio
    public static int getCountThree(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 用HashMap实现面值去重
        // key->面值 value->面值对应货币有几张
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int value : arr) {
            if (hashMap.containsKey(value)) {
                hashMap.put(value, hashMap.get(value) + 1);
            } else {
                hashMap.put(value, 1);
            }
        }
        // 构建两个数组，面值数组和对应的数量数组
        int length = hashMap.size();
        int[] face = new int[length];
        int[] count = new int[length];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            Integer faceValue = entry.getKey();
            Integer countValue = entry.getValue();
            face[i] = faceValue;
            count[i] = countValue;
            i++;
        }
        // index:[0,face.length] rest:[0,aim]
        // dp[index][rest]表示[index,...]组成rest的方法数
        int[][] dp = new int[face.length + 1][aim + 1];
        dp[face.length][0] = 1;
        for (int index = face.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int left = rest - face[index] >= 0 ? dp[index][rest - face[index]] : 0;
                int down = dp[index + 1][rest];
                int delete = (rest - (count[index] + 1) * face[index]) >= 0 ?
                        dp[index + 1][rest - (count[index] + 1) * face[index]] : 0;
                dp[index][rest] = left + down - delete;
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        boolean isSuccess = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = getCountOne(arr, aim);
            int ans2 = getCountTwo(arr, aim);
            int ans3 = getCountThree(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                isSuccess = false;
                printArray(arr);
                System.out.println(ans1 + "--->" + ans2 + "--->" + ans3);
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
