package week306;

/**
 * 6148. 矩阵中的局部最大值
 *
 * @author dks233
 * @create 2022-08-14-10:29
 */
public class One {
    public int[][] largestLocal(int[][] grid) {
        int[][] ans = new int[grid.length - 2][grid.length - 2];
        for (int row = 0; row < grid.length - 2; row++) {
            for (int column = 0; column < grid.length - 2; column++) {
                int max = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        max = Math.max(grid[row + i][column + j], max);
                    }
                }
                ans[row][column] = max;
            }
        }
        return ans;
    }
}
