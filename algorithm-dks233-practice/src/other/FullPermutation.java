package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode46. 全排列
 * 参考文档：https://leetcode.cn/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
 * leetcode47. 全排列 II
 * 参考文档：https://leetcode.cn/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
 *
 * @author dks233
 * @create 2022-07-06-13:03
 */
@SuppressWarnings("ALL")
public class FullPermutation {
    public static class ProblemOne {
        List<List<Integer>> list = new ArrayList<>();
        boolean[] used; // 标记index位置的数是否被使用过

        public List<List<Integer>> permute(int[] nums) {
            used = new boolean[nums.length];
            process(nums, 0, new ArrayList<>());
            return list;
        }

        // depth：当前遍历到哪一层了
        public void process(int[] nums, int depth, ArrayList<Integer> singleList) {
            if (depth == nums.length) {
                list.add(new ArrayList<>(singleList));
                return;
            }
            for (int index = 0; index < nums.length; index++) {
                if (!used[index]) {
                    singleList.add(nums[index]);
                    used[index] = true;
                    process(nums, depth + 1, singleList);
                    used[index] = false;
                    singleList.remove(singleList.size() - 1);
                }
            }
        }
    }

    public static class ProblemTwo {
        List<List<Integer>> list = new ArrayList<>();
        boolean[] used; // 标记index位置的数是否被使用过

        public List<List<Integer>> permuteUnique(int[] nums) {
            used = new boolean[nums.length];
            // 排序是剪枝的前提
            Arrays.sort(nums);
            process(nums, 0, new ArrayList<>());
            return list;
        }

        // depth：当前遍历到哪一层了
        public void process(int[] nums, int depth, ArrayList<Integer> singleList) {
            if (depth == nums.length) {
                list.add(new ArrayList<>(singleList));
                return;
            }
            for (int index = 0; index < nums.length; index++) {
                if (!used[index]) {
                    // 同一层数字不能有重复，需要进行剪枝
                    if (index > 0 && nums[index] == nums[index - 1] && !used[index - 1]) {
                        continue;
                    }
                    singleList.add(nums[index]);
                    used[index] = true;
                    process(nums, depth + 1, singleList);
                    used[index] = false;
                    singleList.remove(singleList.size() - 1);
                }
            }
        }
    }
}
