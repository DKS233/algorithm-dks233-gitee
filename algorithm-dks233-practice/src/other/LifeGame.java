package other;

/**
 * leetcode289. 生命游戏
 *
 * @author dks233
 * @create 2022-08-26-18:02
 */
@SuppressWarnings("ALL")
public class LifeGame {
    // 原地修改
    public static class MethodTwo {
        int[][] common = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        // 1:活细胞 0:死细胞
        // 1周围只有1个1，死亡；1周围有2-3个1，存活；1周围超过3个1，死亡
        // 0周围有3个活细胞，复活
        // 活细胞变成死细胞，将[row,column]设置成-1；死细胞变成活细胞，将[row,column]设置成2
        // 后续遍历过程中，应该不受前面细胞影响，识别状态发生改变的细胞
        public void gameOfLife(int[][] board) {
            int m = board.length;
            int n = board[0].length;
            for (int row = 0; row < m; row++) {
                for (int column = 0; column < n; column++) {
                    board[row][column] = getStatus(board, row, column);
                }
            }
            // 最后遍历board，复原
            // 2复原成1，-1复原成0
            for (int row = 0; row < m; row++) {
                for (int column = 0; column < n; column++) {
                    if (board[row][column] > 0) {
                        board[row][column] = 1;
                    } else {
                        board[row][column] = 0;
                    }
                }
            }
        }

        // [row,column]位置状态，返回1代表活，返回0代表死
        public int getStatus(int[][] board, int row, int column) {
            // 上+下+左+右+左上+左下+右上+右下
            if (board[row][column] == 1) {
                int count = 0;
                for (int i = 0; i < common.length; i++) {
                    count += get(board, row + common[i][0], column + common[i][1]) == 1 ? 1 : 0;
                }
                if (count < 2 || count > 3) {
                    return -1;
                }
                return 1;
            } else {
                int count = 0;
                for (int i = 0; i < common.length; i++) {
                    count += get(board, row + common[i][0], column + common[i][1]) == 1 ? 1 : 0;
                }
                if (count == 3) {
                    return 2;
                }
                return 0;
            }
        }

        public int get(int[][] board, int row, int column) {
            if (row < 0 || row >= board.length || column < 0 || column >= board[0].length) {
                return -1;
            }
            if (board[row][column] == -1) {
                return 1;
            }
            if (board[row][column] == 2) {
                return 0;
            }
            return board[row][column];
        }
    }

    // 暴力解法
    public static class MethodOne {
        int[][] common = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        // 1:活细胞 0:死细胞
        // 1周围只有1个1，死亡；1周围有2-3个1，存活；1周围超过3个1，死亡
        // 0周围有3个活细胞，复活
        public void gameOfLife(int[][] board) {
            int m = board.length;
            int n = board[0].length;
            int[][] count = new int[m][n];
            for (int row = 0; row < m; row++) {
                for (int column = 0; column < n; column++) {
                    count[row][column] = getStatus(board, row, column);
                }
            }
            for (int row = 0; row < m; row++) {
                for (int column = 0; column < n; column++) {
                    board[row][column] = count[row][column];
                }
            }
        }

        // [row,column]位置状态，返回1代表活，返回0代表死
        public int getStatus(int[][] board, int row, int column) {
            // 上+下+左+右+左上+左下+右上+右下
            if (board[row][column] == 1) {
                int count = 0;
                for (int i = 0; i < common.length; i++) {
                    count += get(board, row + common[i][0], column + common[i][1]) == 1 ? 1 : 0;
                }
                if (count < 2 || count > 3) {
                    return 0;
                }
                return 1;
            } else {
                int count = 0;
                for (int i = 0; i < common.length; i++) {
                    count += get(board, row + common[i][0], column + common[i][1]) == 1 ? 1 : 0;
                }
                if (count == 3) {
                    return 1;
                }
                return 0;
            }
        }

        public int get(int[][] board, int row, int column) {
            if (row < 0 || row >= board.length || column < 0 || column >= board[0].length) {
                return -1;
            }
            return board[row][column];
        }
    }
}
