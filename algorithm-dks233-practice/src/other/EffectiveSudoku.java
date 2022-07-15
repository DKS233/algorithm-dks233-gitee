package other;

import java.util.HashMap;

/**
 * leetcode36. 有效的数独
 * 参考文档：https://leetcode.cn/problems/valid-sudoku/solution/36-jiu-an-zhao-cong-zuo-wang-you-cong-shang-wang-x/
 *
 * @author dks233
 * @create 2022-07-15-18:14
 */
public class EffectiveSudoku {
    public static class MethodOne {
        // 每遍历到一个数判断该树是否符合数独规则
        // rows[9][10] 每一行的每个数是否出现过  注：第二位长度为10，对应[0,9]，为了后续存1->9的出现情况
        // columns[9][10] 每一行的每个数是否出现过
        // squares[9][10] 每个方块的每个数是否出现过
        // 注：每个[row,column]对应的方块索引为column/3+(row/3)*3
        // 时间复杂度：O(1)
        public boolean isValidSudoku(char[][] board) {
            boolean[][] rows = new boolean[9][10];
            boolean[][] columns = new boolean[9][10];
            boolean[][] squares = new boolean[9][10];
            for (int row = 0; row < 9; row++) {
                for (int column = 0; column < 9; column++) {
                    if (board[row][column] == '.') {
                        continue;
                    }
                    int curNumber = board[row][column] - '0';
                    // row行的curNumber这个数出现过
                    if (rows[row][curNumber]) {
                        return false;
                    }
                    // column列的curNumber这个数出现过
                    if (columns[column][curNumber]) {
                        return false;
                    }
                    // column/3+(row/3)*3方格内的curNumber这个数出现过
                    if (squares[column / 3 + (row / 3) * 3][curNumber]) {
                        return false;
                    }
                    // 处理完后，行、列、方格都将当前数标记为true，表示已经出现过
                    rows[row][curNumber] = true;
                    columns[column][curNumber] = true;
                    squares[column / 3 + (row / 3) * 3][curNumber] = true;
                }
            }
            return true;
        }
    }
}
