package week303;

/**
 * 6125. 相等行列对
 *
 * @author dks233
 * @create 2022-07-24-10:18
 */
public class Two {
    boolean[][] dp;

    public int equalPairs(int[][] grid) {
        dp = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                boolean cur = process(grid, row, column, grid.length);
                if (cur) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean process(int[][] nums, int row, int column, int n) {
        for (int index = 0; index < n; index++) {
            if (nums[row][index] != nums[index][column]) {
                return false;
            }
        }
        return true;
    }
}
