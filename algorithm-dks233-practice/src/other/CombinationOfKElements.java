package other;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 080. 含有 k 个元素的组合
 *
 * @author dks233
 * @create 2022-07-25-10:32
 */
@SuppressWarnings("ALL")
public class CombinationOfKElements {
    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        process(n, 1, k, new ArrayList<>());
        return list;
    }

    // cur：当前遍历到的数
    // k：集合元素限制值
    // singleList：当前集合
    public void process(int n, int cur, int k, List<Integer> singleList) {
        if (singleList.size() == k) {
            list.add(new ArrayList<>(singleList));
            return;
        }
        if (cur > n) {
            return;
        }
        // 要当前数
        singleList.add(cur);
        process(n, cur + 1, k, singleList);
        singleList.remove(singleList.size() - 1);
        // 不要当前数
        process(n, cur + 1, k, singleList);
    }
}
