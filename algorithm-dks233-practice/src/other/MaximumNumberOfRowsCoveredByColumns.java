package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode2397. 被列覆盖的最多行数
 *
 * @author dks233
 * @create 2022-09-05-15:43
 */
@SuppressWarnings("ALL")
public class MaximumNumberOfRowsCoveredByColumns {
    // 暴力枚举
    public static class MethodOne {
        public int maximumRows(int[][] mat, int cols) {
            if (mat[0].length <= cols) {
                return mat.length;
            }
            return process(mat, cols, 0, new ArrayList<>());
        }

        // singleList：当前选中的列
        // cur：当前做选择的列
        public int process(int[][] mat, int cols, int cur, List<Integer> singleList) {
            if (cur == mat[0].length) {
                return singleList.size() == cols ? getRowCount(mat, singleList) : 0;
            }
            // 选当前列
            singleList.add(cur);
            int p1 = process(mat, cols, cur + 1, singleList);
            singleList.remove(singleList.size() - 1);
            // 不选当前列
            int p2 = process(mat, cols, cur + 1, singleList);
            return Math.max(p1, p2);
        }

        // 返回：当前的选择下被覆盖的行
        public int getRowCount(int[][] mat, List<Integer> singleList) {
            int rowCount = 0;
            for (int i = 0; i < mat.length; i++) {
                boolean flag = true;
                for (int j = 0; j < mat[i].length; j++) {
                    if (mat[i][j] == 1 && !singleList.contains(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    rowCount++;
                }
            }
            return rowCount;
        }
    }
}
