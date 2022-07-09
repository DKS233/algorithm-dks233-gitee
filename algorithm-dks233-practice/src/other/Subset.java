package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode78. 子集
 *
 * @author dks233
 * @create 2022-07-09-9:45
 */
public class Subset {
    // 时间复杂度：O(n*Math.pow(2,n))
    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return list;
        }
        process(nums, 0, new ArrayList<>());
        return list;
    }

    /**
     * @param nums       原始数组
     * @param index      当前遍历到了哪个位置
     * @param singleList 当前拼接的子集
     */
    public void process(int[] nums, int index, List<Integer> singleList) {
        if (index == nums.length) {
            list.add(new ArrayList<>(singleList));
            return;
        }
        // 要当前数
        singleList.add(nums[index]);
        process(nums, index + 1, singleList);
        singleList.remove(singleList.size() - 1);
        // 不要当前数
        process(nums, index + 1, singleList);
    }
}
