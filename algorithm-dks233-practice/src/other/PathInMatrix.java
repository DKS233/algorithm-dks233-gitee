package other;

/**
 * 剑指 Offer第二版  12. 矩阵中的路径
 *
 * @author dks233
 * @create 2022-06-17-21:57
 */
public class PathInMatrix {
    // 时间复杂度：O(3^K * MN)  K为字符串长度 每个位置都需要去上下左右判断
    // 但是上下左右中有一个位置已经做过判断，需要判断的其实是三个位置
    // exist方法中for循环循环本身复杂度是O(MN)，里层find方法复杂度为O(3^K)，所以总的复杂度为O(3^K * MN)
    // 空间复杂度：O(K) 递归深度不超过K，需要使用的栈空间O(K)
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        char[] chars = word.toCharArray();
        boolean ans = false;
        // 第一个字符如果存在，继续判断
        // 第一个字符如果不存在，直接返回false
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == chars[0]) {
                    ans = find(board, chars, i, j, 0);
                    if (ans) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // board遍历到了(boardRow,boardColumn)位置
    // word遍历到了index位置
    // 注：index和越界两个条件先后关系不能改变，特殊情况：board中只有一个元素a，要检索a，如果越界判断放前面，直接返回false了
    public boolean find(char[][] board, char[] word, int boardRow, int boardColumn, int index) {
        // index能到word.length说明在index=word.length-1的字符是匹配的
        if (index >= word.length) {
            return true;
        }
        // 越界
        if (boardRow < 0 || boardRow >= board.length || boardColumn < 0 || boardColumn >= board[0].length) {
            return false;
        }
        // 当前位置字符匹配，word看index+1位置，board看上下左右位置
        boolean ans = false;
        if (board[boardRow][boardColumn] == word[index]) {
            // 题目要求：同一单元格内的字母不允许被重复使用
            // 如果board当前位置字符匹配，在当前路径的之后选择中该字符都不应该再被使用
            // 所以在当前路径中，先把匹配的字符位置先转换成其他字符
            board[boardRow][boardColumn] = '\0';
            ans = find(board, word, boardRow + 1, boardColumn, index + 1) ||
                    find(board, word, boardRow - 1, boardColumn, index + 1) ||
                    find(board, word, boardRow, boardColumn + 1, index + 1) ||
                    find(board, word, boardRow, boardColumn - 1, index + 1);
            // 当前路径判断完后，字符位置恢复
            board[boardRow][boardColumn] = word[index];
        }
        return ans;
    }
}
