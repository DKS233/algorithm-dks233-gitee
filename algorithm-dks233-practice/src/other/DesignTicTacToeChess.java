package other;

/**
 * leetcode348. 设计井字棋
 *
 * @author dks233
 * @create 2022-09-05-10:30
 */
@SuppressWarnings("ALL")
public class DesignTicTacToeChess {
    // 未优化版：用二维矩阵表示玩家分数
    public static class TicTacToeOne {
        // 所有数组：第0列表示1号玩家 第1列表示2号玩家
        int[][] rows;
        int[][] columns;
        int[] leftUp; // 左上角到右下角的对角线
        int[] rightUp; // 右上角到左下角的对角线
        int limit;

        public TicTacToeOne(int n) {
            limit = n;
            rows = new int[n][2];
            columns = new int[n][2];
            leftUp = new int[2];
            rightUp = new int[2];
        }

        public int move(int row, int col, int player) {
            rows[row][player - 1]++;
            columns[col][player - 1]++;
            if (row == col) {
                leftUp[player - 1]++;
            }
            if (row + col == limit - 1) {
                rightUp[player - 1]++;
            }
            if (get(limit)) {
                return player;
            }
            return 0;
        }

        public boolean get(int n) {
            for (int i = 0; i < rows.length; i++) {
                if (rows[i][0] == n || rows[i][1] == n || columns[i][0] == n || columns[i][1] == n) {
                    return true;
                }
            }
            for (int i = 0; i < 2; i++) {
                if (leftUp[i] == n || rightUp[i] == n) {
                    return true;
                }
            }
            return false;
        }
    }

    // 优化版
    // 优化1：一维矩阵，将玩家看做1和-1
    // 优化2：只需要判断发生改变的行列和对角线就行，不需要遍历
    public static class TicTacToe {
        int[] rows;
        int[] columns;
        int leftUp; // 左上角到右下角的对角线
        int rightUp; // 右上角到左下角的对角线
        int limit;

        public TicTacToe(int n) {
            limit = n;
            rows = new int[n];
            columns = new int[n];
            leftUp = 0;
            rightUp = 0;
        }

        // 1号玩家用1表示 2号玩家用-1表示
        public int move(int row, int col, int player) {
            int add = player == 1 ? 1 : -1;
            rows[row] += add;
            columns[col] += add;
            if (row == col) {
                leftUp += add;
            }
            if (row + col == limit - 1) {
                rightUp += add;
            }
            if (Math.abs(leftUp) == limit || Math.abs(rightUp) == limit
                    || Math.abs(rows[row]) == limit || Math.abs(columns[col]) == limit) {
                return player;
            }
            return 0;
        }
    }
}
