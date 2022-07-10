package other;

/**
 * leetcode79. 单词搜索
 *
 * @author dks233
 * @create 2022-07-10-23:01
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        char[] str = word.toCharArray();
        boolean ans = false;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == str[0]) {
                    ans |= process(board, row, column, str, 0);
                }
            }
        }
        return ans;
    }

    public boolean process(char[][] board, int row, int column, char[] str, int index) {
        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length) {
            return false;
        }
        if (board[row][column] != str[index]) {
            return false;
        }
        if (index == str.length - 1) {
            return board[row][column] == str[index];
        }
        boolean ans = false;
        char cur = board[row][column];
        board[row][column] = '1';
        ans = process(board, row - 1, column, str, index + 1)
                || process(board, row + 1, column, str, index + 1)
                || process(board, row, column - 1, str, index + 1)
                || process(board, row, column + 1, str, index + 1);
        board[row][column] = cur;
        return ans;
    }
}
