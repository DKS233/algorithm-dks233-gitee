package other;

/**
 * leetcode526. 优美的排列
 * leetcode667. 优美的排列 II
 *
 * @author dks233
 * @create 2022-09-08-8:37
 */
@SuppressWarnings("ALL")
public class GracefulArrangement {
    // 问题1：暴力回溯
    public static class ProblemOneMethodOne {
        boolean[] visits;
        int count = 0;

        public int countArrangement(int n) {
            visits = new boolean[n + 1];
            process(1, n);
            return count;
        }

        public void process(int depth, int n) {
            if (depth == n + 1) {
                count++;
                return;
            }
            for (int i = 1; i <= n; i++) {
                if (!visits[i] && (i % depth == 0 || depth % i == 0)) {
                    visits[i] = true;
                    process(depth + 1, n);
                    visits[i] = false;
                }
            }
        }
    }

    public static class ProblemTwo {
        // [0,k] + [k+1,n]
        // 前半部分是共有k个差值的数组，后半部分是递增数组
        // k=3
        // 1 4 2 3 5 6 7 ...
        public int[] constructArray(int n, int k) {
            int[] nums = new int[n];
            nums[0] = 1;
            nums[1] = k + nums[0];
            for (int i = 2; i <= k; i++) {
                if (i % 2 == 0) {
                    nums[i] = nums[i - 2] + 1;
                } else {
                    nums[i] = nums[i - 2] - 1;
                }
            }
            for (int i = k + 1; i < n; i++) {
                nums[i] = i + 1;
            }
            return nums;
        }

    }
}
