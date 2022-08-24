package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode51. N 皇后
 *
 * @author dks233
 * @create 2022-08-24-11:37
 */
@SuppressWarnings("ALL")
public class NQueen {
    List<List<String>> list = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // record[i]=j 表示第i行第j列的记录
        int[] record = new int[n];
        process(record, n, 0);
        return list;
    }

    // 填第row行的皇后
    public void process(int[] record, int n, int row) {
        // 能够到达边界，肯定是符合条件的
        if (row == n) {
            fill(record);
            return;
        }
        // 依次尝试i的每一列
        for (int column = 0; column < n; column++) {
            if (isValid(record, row, column)) {
                record[row] = column;
                process(record, n, row + 1);
            }
        }
    }

    // 要填入的record[i]=j 是否是合法的
    public boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(k - i) == Math.abs(record[k] - j)) {
                return false;
            }
        }
        return true;
    }

    public void fill(int[] record) {
        List<String> singleList = new ArrayList<>();
        for (int i = 0; i < record.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < record[i]; j++) {
                builder.append(".");
            }
            builder.append("Q");
            for (int j = record[i] + 1; j < record.length; j++) {
                builder.append(".");
            }
            singleList.add(builder.toString());
        }
        list.add(new ArrayList<>(singleList));
    }
}
