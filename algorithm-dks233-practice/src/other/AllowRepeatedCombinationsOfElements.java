package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 081. 允许重复选择元素的组合
 * 剑指offer专项突击版：剑指 Offer II 082. 含有重复元素集合的组合
 *
 * @author dks233
 * @create 2022-07-23-15:29
 */
public class AllowRepeatedCombinationsOfElements {
    public static class ProblemOne {
        List<List<Integer>> list = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            process(candidates, target, 0, new ArrayList<>());
            return list;
        }

        /**
         * 同一层不能选相同的元素：去重
         *
         * @param nums           原始数组
         * @param rest           目标数
         * @param nextBeginIndex 下一轮遍历的起始位置
         * @param singleList     当前列表
         */
        public void process(int[] nums, int rest, int nextBeginIndex, List<Integer> singleList) {
            if (rest == 0) {
                list.add(new ArrayList<>(singleList));
                return;
            }
            for (int index = nextBeginIndex; index < nums.length; index++) {
                if (rest < nums[index]) {
                    continue;
                }
                singleList.add(nums[index]);
                process(nums, rest - nums[index], index, singleList);
                singleList.remove(singleList.size() - 1);
            }
        }
    }

    public static class ProblemTwo {
        List<List<Integer>> list = new ArrayList<>();

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            if (candidates == null || candidates.length == 0) {
                return list;
            }
            Arrays.sort(candidates);
            process(target, candidates, new ArrayList<>(), 0);
            return list;
        }

        /**
         * @param res            target被减掉后剩余的数
         * @param candidates     原始数组
         * @param curList        当前组合
         * @param nextBeginIndex 下一轮遍历的起始位置（下一轮：下一个分支）
         */
        public void process(int res, int[] candidates, ArrayList<Integer> curList, int nextBeginIndex) {
            if (res == 0) {
                list.add(new ArrayList<>(curList));
                return;
            }
            for (int index = nextBeginIndex; index < candidates.length; index++) {
                // 大剪枝
                // res >= candidates[index] 不能作为for循环index的判断条件，如果index不能减，后面的数应该继续尝试而不是中断循环，所以是continue
                // 不过如果事先对数组进行排序，res >= candidates[index]可以放在for循环判断条件中
                // 因为减index位置的数都小于0，那减后续的数也必定小于0，所以是break或放在for循环判断条件中
                if (res < candidates[index]) {
                    break;
                }
                // 小剪枝：同一层往右的数不能和往左的数相同（数组中元素重复的情况）
                if (index > nextBeginIndex && candidates[index] == candidates[index - 1]) {
                    continue;
                }
                curList.add(candidates[index]);
                // 注：这里nextBeginIndex越界不影响后续的递归
                process(res - candidates[index], candidates, curList, index + 1);
                curList.remove(curList.size() - 1);
            }
        }
    }
}
