package week301;

import java.util.Arrays;

/**
 * leetcode2335. 装满杯子需要的最短总时长
 *
 * @author dks233
 * @create 2022-07-10-10:31
 */
public class One {
    int minValue = Integer.MAX_VALUE;

    public int fillCups(int[] amount) {
        int cold = amount[0];
        int warm = amount[1];
        int hot = amount[2];
        process(cold, warm, hot, 0);
        return minValue;
    }

    public void process(int cold, int warm, int hot, int ans) {
        if (cold < 0 || warm < 0 || hot < 0) {
            return;
        }
        if (cold == 0 && warm == 0 && hot == 0) {
            minValue = Math.min(minValue, ans);
            return;
        }
        process(cold - 1, warm - 1, hot, ans + 1);
        process(cold - 1, warm, hot - 1, ans + 1);
        process(cold, warm - 1, hot - 1, ans + 1);
        process(cold - 1, warm, hot, ans + 1);
        process(cold, warm - 1, hot, ans + 1);
        process(cold, warm, hot - 1, ans + 1);
    }

    public static class MethodOne {
        // 每轮优先拿小的和大的
        public int fillCups(int[] amount) {
            int ans = 0;
            while (amount[0] != 0 || amount[1] != 0 || amount[2] != 0) {
                Arrays.sort(amount);
                if (amount[0] == 0) {
                    if (amount[1] == 0 && amount[2] == 0) {
                        break;
                    } else if (amount[1] == 0 && amount[2] > 0) {
                        amount[2]--;
                        ans++;
                    } else if (amount[1] > 0 && amount[2] > 0) {
                        amount[1]--;
                        amount[2]--;
                        ans++;
                    }
                } else {
                    amount[0]--;
                    amount[2]--;
                    ans++;
                }
            }
            return ans;
        }
    }
}
