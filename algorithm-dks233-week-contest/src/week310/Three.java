package week310;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 6178. 将区间分为最少组数
 *
 * @author dks233
 * @create 2022-09-11-10:25
 */
@SuppressWarnings("ALL")
public class Three {
    public static class MethodOne {
        // 方法1：找最大的重合区间数
        // 参考leetcode253，-1表示结束，1表示开始
        public int minGroups(int[][] intervals) {
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            for (int i = 0; i < intervals.length; i++) {
                heap.offer(new int[]{intervals[i][0], -1});
                heap.offer(new int[]{intervals[i][1], 1});
            }
            int result = 0;
            int cur = 0;
            while (!heap.isEmpty()) {
                cur += heap.poll()[1];
                result = Math.max(Math.abs(cur), result);
            }
            return result;
        }
    }

    public static class MethodTwo {
        // 将数组按照左边界排序，堆顶存放右边界
        // |________|            堆为空，入堆(size = 0 -> size = 1)
        //     |________|        当前区间和堆顶有重合，入堆(size = 1 -> size = 2)
        //            |________| 当前区间和堆顶无重合，堆顶弹出，压入新的右边界(size = 2 -> size = 1 -> size = 2)
        public int minGroups(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            for (int i = 0; i < intervals.length; i++) {
                if (heap.isEmpty()) {
                    heap.offer(intervals[i][1]);
                } else {
                    if (intervals[i][0] <= heap.peek()) {
                        heap.offer(intervals[i][1]);
                    } else {
                        heap.poll();
                        heap.offer(intervals[i][1]);
                    }
                }
            }
            return heap.size();
        }
    }
}
