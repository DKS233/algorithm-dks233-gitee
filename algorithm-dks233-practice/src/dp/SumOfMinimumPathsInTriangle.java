package dp;

import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 100. 三角形中最小路径之和
 *
 * @author dks233
 * @create 2022-07-23-21:36
 */
public class SumOfMinimumPathsInTriangle {
    public int minimumTotal(List<List<Integer>> list) {
        // dp[row][column]表示[row][column]走到最下面一行的路径和
        // 每个位置都依赖其下方和右下方
        int[][] dp = new int[list.size()][list.get(list.size() - 1).size()];
        // 先填最后一行
        for (int column = 0; column < dp[0].length; column++) {
            dp[list.size() - 1][column] = list.get(list.size() - 1).get(column);
        }
        // 根据位置依赖关系填其他位置
        for (int row = list.size() - 2; row >= 0; row--) {
            for (int column = 0; column < list.get(row).size(); column++) {
                dp[row][column] = list.get(row).get(column) + Math.min(dp[row + 1][column], dp[row + 1][column + 1]);
            }
        }
        return dp[0][0];
    }
}
