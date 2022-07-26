package dp;

import java.util.HashMap;

/**
 * 剑指offer专项突击版：剑指 Offer II 093. 最长斐波那契数列
 * 参考文档：https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/solution/zhuang-tai-ding-yi-hen-shi-zhong-yao-by-christmas_/
 * 参考文档：https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence/solution/by-ac_oier-beo2/
 *
 * @author dks233
 * @create 2022-07-26-10:46
 */
@SuppressWarnings("ALL")
public class LongestFibonacciSequence {
    public static class MethodTwo {
        // dp[i][j] 以arr[i]和arr[j]结尾的最长斐波那契数列
        public int lenLongestFibSubseq(int[] arr) {
            int[][] dp = new int[arr.length][arr.length];
            // key->数组元素 value->对应的索引
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            for (int index = 0; index < arr.length; index++) {
                hashMap.put(arr[index], index);
            }
            // 分析位置依赖关系
            // 如果i之前存在k，使arr[k]+arr[i]=arr[j]
            // dp[i][j]=max(dp[i][j],dp[k][i]+1)
            int maxLen = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    int diff = arr[j] - arr[i];
                    // 剪枝：diff>=arr[i]时，前面没有数比arr[i]小
                    // 当前j变大后，diff只会更大，所以直接break
                    if (diff >= arr[i]) {
                        break;
                    }
                    if (hashMap.containsKey(diff) && hashMap.get(diff) < i) {
                        dp[i][j] = Math.max(dp[i][j], dp[hashMap.get(diff)][i] + 1);
                    }
                    maxLen = Math.max(maxLen, dp[i][j] == 0 ? 0 : dp[i][j] + 2);
                }
            }
            return maxLen;
        }
    }

    // 暴力解法，遍历所有子序列
    public static class MethodOne {
        public int lenLongestFibSubseq(int[] arr) {
            int maxLen = 0;
            for (int left = 0; left < arr.length; left++) {
                for (int right = left + 1; right < arr.length; right++) {
                    int curLeft = left;
                    int curRight = right;
                    int count = 0;
                    int find = find(arr, curLeft, curRight);
                    while (find != -1) {
                        curLeft = curRight;
                        curRight = find;
                        count++;
                        find = find(arr, curLeft, curRight);
                    }
                    maxLen = Math.max(count == 0 ? 0 : count + 2, maxLen);
                }
            }
            return maxLen;
        }

        public int find(int[] arr, int left, int right) {
            for (int index = right + 1; index < arr.length; index++) {
                if (arr[index] == arr[left] + arr[right]) {
                    return index;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        MethodOne methodOne = new MethodOne();
        System.out.println(methodOne.lenLongestFibSubseq(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }
}
