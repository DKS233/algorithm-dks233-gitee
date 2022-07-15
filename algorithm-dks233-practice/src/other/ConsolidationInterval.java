package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode56. 合并区间
 * 参考文档：https://leetcode.cn/problems/merge-intervals/solution/java-yi-dong-yi-jie-xiao-lu-gao-by-spirit-9-40/
 *
 * @author dks233
 * @create 2022-07-15-22:57
 */
@SuppressWarnings("ALL")
public class ConsolidationInterval {
    public static class ProblemOne {
        // 数组1和数组2有重合区域（前提：都是按左边界排好序的）
        // leftTwo>=rightOne
        public int[][] merge(int[][] intervals) {
            if (intervals.length == 1) {
                return intervals;
            }
            List<int[]> list = new ArrayList<>();
            // 将所有区间排序，left小的放前面
            Arrays.sort(intervals, (one, two) -> one[0] - two[0]);
            int index = 0;
            while (index < intervals.length) {
                // 每轮更新后的左右边界
                int left = intervals[index][0];
                int right = intervals[index][1];
                // 从左往右遍历，发现有重叠的就更新右边界
                while (index < intervals.length - 1
                        && right >= intervals[index + 1][0]) {
                    right = Math.max(right, intervals[index + 1][1]);
                    index++;
                }
                // 跳出循环两种情况
                // 情况1：index越界
                if (index == intervals.length - 1) {
                    list.add(new int[]{left, right});
                    return list.toArray(new int[0][]);
                }
                // 情况2：index的元素不会和之后元素合并
                list.add(new int[]{left, right});
                index++;
            }
            return list.toArray(new int[0][]);
        }
    }


}
