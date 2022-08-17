package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指offer专项突击版：89. 格雷编码
 * 参考文档：https://leetcode.cn/problems/gray-code/solution/gray-code-jing-xiang-fan-she-fa-by-jyd/
 *
 * @author dks233
 * @create 2022-08-17-20:29
 */
public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        int add = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add(add + list.get(j));
            }
            add <<= 1;
        }
        return list;
    }
}
