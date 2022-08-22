package other;

import java.util.List;

/**
 * leetcode面试题 08.06. 汉诺塔问题
 * 题目地址：https://leetcode.cn/problems/hanota-lcci/
 *
 * @author dks233
 * @create 2022-08-22-17:03
 */
@SuppressWarnings("ALL")
public class HanotaProblem {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        process(A.size(), A, B, C);
    }

    // from other to 将n个圆盘从from经过other移动到to
    // 将第1-n-1个圆盘从from移动到other
    // 将第n个圆盘从from移动到to
    // 将第1-n-1个圆盘从other移动到to
    public void process(int n, List<Integer> from, List<Integer> other, List<Integer> to) {
        if (n == 1) {
            to.add(from.remove(from.size() - 1));
            return;
        }
        process(n - 1, from, to, other);
        to.add(from.remove(from.size() - 1));
        process(n - 1, other, from, to);
    }
}
