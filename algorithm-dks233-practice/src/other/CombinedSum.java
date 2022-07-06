package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode39. 组合总和
 * 参考文档：https://leetcode.cn/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
 * leetcode40. 组合总和 II
 * 参考文档：https://leetcode.cn/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/
 *
 * @author dks233
 * @create 2022-07-06-0:05
 */
@SuppressWarnings("ALL")
public class CombinedSum {
    // 题目1
    // 针对下面没有去重的回溯算法进行改进
    // 每一次搜索的时候设置下一轮搜索的起点nextBeginIndex，即每层的第二个节点开始，都不能搜索同一层节点已经使用过的candidate里的元素
    public static class MethodRemoveDuplicate {
        List<List<Integer>> list = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            if (candidates == null || candidates.length == 0) {
                return list;
            }
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
            // 以为[2,3,6,7]为例
            // 第一大轮：以2为起点有多少组组合和等于target，每层搜索的起点都是0位置
            // 第二大轮：以3为起点有多少组组合和等于target，每层搜索的起点都是1位置，剪掉了包含2的情况
            // 第三大轮：以6为起点有多少组组合和等于target，每层搜索的起点都是2位置，剪掉了包含2和3的情况
            // 第四大轮：以7位起点有多少组组合和等于target，每层搜索的起点都是7位置，剪掉了包含2和3和6的情况
            // 同时，对于每层，从该层的第二个节点开始，同一层节点不能重复使用
            // 每层从左往右看，最左边的分支是2，2以下的分支已经考虑了所有包含2的情况，所以往右的分支跳过2
            for (int index = nextBeginIndex; index < candidates.length; index++) {
                // res >= candidates[index] 不能作为for循环index的判断条件，如果index不能减，后面的数应该继续尝试而不是中断循环
                // 不过如果事先对数组进行排序，res >= candidates[index]可以放在for循环判断条件中，因为减index位置的数都小于0，那减后续的数也必定小于0
                if (res < candidates[index]) {
                    continue;
                }
                curList.add(candidates[index]);
                process(res - candidates[index], candidates, curList, index);
                curList.remove(curList.size() - 1);
            }
        }
    }

    // 错误解法
    // 没有去重的回溯算法
    public static class MethodNoRemoveDuplicate {
        List<List<Integer>> list = new ArrayList<>();

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            if (candidates == null || candidates.length == 0) {
                return list;
            }
            process(target, candidates, new ArrayList<>());
            return list;
        }

        /**
         * @param res        target被减后剩余的数
         * @param candidates 原始数组
         * @param curList    当前组合
         */
        public void process(int res, int[] candidates, ArrayList<Integer> curList) {
            if (res == 0) {
                list.add(new ArrayList<>(curList));
                return;
            }
            for (int index = 0; index < candidates.length; index++) {
                // res >= candidates[index] 不能作为for循环index的判断条件，如果index不能减，后面的数应该继续尝试而不是中断循环
                // 不过如果事先对数组进行排序，res >= candidates[index]可以放在for循环判断条件中，因为减index位置的数都小于0，那减后续的数也必定小于0
                if (res < candidates[index]) {
                    continue;
                }
                curList.add(candidates[index]);
                process(res - candidates[index], candidates, curList);
                curList.remove(curList.size() - 1);
            }
        }
    }

    // 题目2
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
