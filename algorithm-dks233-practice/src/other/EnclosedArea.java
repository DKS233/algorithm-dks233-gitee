package other;

/**
 * leetcode130. 被围绕的区域
 *
 * @author dks233
 * @create 2022-08-25-10:05
 */
public class EnclosedArea {
    public static class MethodOne {
        // dfs:顺着边界的O进行深度优先遍历，沿途的节点全部染成#字符，然后再遍历数组复原
        public void solve(char[][] board) {
            for (int row = 0; row < board.length; row++) {
                for (int column = 0; column < board[0].length; column++) {
                    boolean isEdge = row == 0 || row == board.length - 1 || column == 0 || column == board[0].length - 1;
                    if (isEdge && board[row][column] == 'O') {
                        dfs(board, row, column);
                    }
                }
            }
            for (int row = 0; row < board.length; row++) {
                for (int column = 0; column < board[0].length; column++) {
                    if (board[row][column] == 'O') {
                        board[row][column] = 'X';
                    }
                    if (board[row][column] == '#') {
                        board[row][column] = 'O';
                    }
                }
            }
        }

        public void dfs(char[][] board, int row, int column) {
            if (row >= board.length || row < 0 || column >= board[0].length || column < 0 || board[row][column] == 'X' || board[row][column] == '#') {
                return;
            }
            board[row][column] = '#';
            dfs(board, row, column + 1);
            dfs(board, row, column - 1);
            dfs(board, row + 1, column);
            dfs(board, row - 1, column);
        }
    }
}
