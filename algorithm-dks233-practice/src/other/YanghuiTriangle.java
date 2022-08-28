package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode118. 杨辉三角
 *
 * @author dks233
 * @create 2022-08-28-22:47
 */
@SuppressWarnings("ALL")
public class YanghuiTriangle {
    public List<List<Integer>> generate(int n) {
        List<List<Integer>> list = new ArrayList<>();
        // row行有row+1个元素
        for (int row = 0; row < n; row++) {
            List<Integer> curList = new ArrayList<>();
            for (int i = 0; i <= row; i++) {
                if (i == 0 || i == row) {
                    curList.add(1);
                } else {
                    curList.add(list.get(row - 1).get(i - 1) + list.get(row - 1).get(i));
                }
            }
            list.add(curList);
        }
        return list;
    }
}
