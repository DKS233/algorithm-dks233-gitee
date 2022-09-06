package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode254. 因子的组合
 *
 * @author dks233
 * @create 2022-09-05-10:06
 */
@SuppressWarnings("ALL")
public class CombinationOfFactors {
    // 暴力解法：回溯
    public static class MethodOne {
        List<List<Integer>> list = new ArrayList<>();

        public List<List<Integer>> getFactors(int n) {
            if (n == 1) {
                return list;
            }
            process(n, new ArrayList<>(), 2);
            return list;
        }

        // 设置start：每轮从start开始遍历，不会重复
        public void process(int number, List<Integer> singleList, int start) {
            if (number == 1) {
                // 因子只有自己的情况
                if (singleList.size() == 1) {
                    return;
                }
                list.add(new ArrayList<>(singleList));
                return;
            }
            // 这里i<=number原因
            // number=3 start=3 i得能取到3
            for (int i = start; i <= number; i++) {
                if (number % i == 0) {
                    singleList.add(i);
                    process(number / i, singleList, i);
                    singleList.remove(singleList.size() - 1);
                }
            }
        }
    }

    // 回溯优化：每轮只需要判断i*i<=number
    public static class MethodTwo {
        List<List<Integer>> list = new ArrayList<>();

        public List<List<Integer>> getFactors(int n) {
            if (n == 1) {
                return list;
            }
            process(n, new ArrayList<>(), 2);
            return list;
        }

        public void process(int number, List<Integer> singleList, int start) {
            if (number == 1) {
                // 因子只有自己的情况
                if (singleList.size() == 1) {
                    return;
                }
                list.add(new ArrayList<>(singleList));
                return;
            }
            for (int i = start; i * i <= number; i++) {
                if (number % i == 0) {
                    singleList.add(i);
                    singleList.add(number / i);
                    list.add(new ArrayList<>(singleList));
                    singleList.remove(singleList.size() - 1);
                    process(number / i, singleList, i);
                    singleList.remove(singleList.size() - 1);
                }
            }
        }
    }
}
