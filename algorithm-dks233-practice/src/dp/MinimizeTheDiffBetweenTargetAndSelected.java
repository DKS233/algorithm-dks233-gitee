package dp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

/**
 * leetcode1981. 最小化目标值与所选元素的差
 *
 * @author dks233
 * @create 2022-09-16-20:44
 */
@SuppressWarnings("ALL")
public class MinimizeTheDiffBetweenTargetAndSelected {
    // 暴力递归
    public static class MethodOne {
        public int minimizeTheDifference(int[][] mat, int target) {
            return process(mat, target, 0, 0);
        }

        // row行 curSum当前累加的和 返回：从当前行选起，最小差
        public int process(int[][] mat, int target, int row, int curSum) {
            if (row == mat.length) {
                return Math.abs(target - curSum);
            }
            int result = Integer.MAX_VALUE;
            for (int column = 0; column < mat[0].length; column++) {
                result = Math.min(process(mat, target, row + 1, curSum + mat[row][column]), result);
            }
            return result;
        }
    }

    public static class MethodTwo {
        public int minimizeTheDifference(int[][] mat, int target) {
            int sum = 0;
            for (int i = 0; i < mat.length; i++) {
                int curRowMax = mat[i][0];
                for (int j = 0; j < mat[0].length; j++) {
                    curRowMax = Math.max(curRowMax, mat[i][j]);
                }
                sum += curRowMax;
            }
            int[][] dp = new int[mat.length + 1][sum + 1];
            for (int curSum = 0; curSum < dp[0].length; curSum++) {
                dp[mat.length][curSum] = Math.abs(target - curSum);
            }
            for (int row = mat.length - 1; row >= 0; row--) {
                for (int curSum = dp[0].length - 1; curSum >= 0; curSum--) {
                    int result = Integer.MAX_VALUE;
                    for (int column = 0; column < mat[0].length; column++) {
                        if (curSum + mat[row][column] < dp[0].length) {
                            result = Math.min(dp[row + 1][curSum + mat[row][column]], result);
                        }
                    }
                    dp[row][curSum] = result;
                }
            }
            return dp[0][0];
        }
    }
}
