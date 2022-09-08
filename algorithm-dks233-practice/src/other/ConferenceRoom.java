package other;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * leetcode252. 会议室
 * leetcode253. 会议室 II
 *
 * @author dks233
 * @create 2022-09-08-10:03
 */
@SuppressWarnings("ALL")
public class ConferenceRoom {
    // 判断能不能参加完所有会议
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int time = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (time <= intervals[i][0]) {
                time = intervals[i][1];
            } else {
                return false;
            }
        }
        return true;
    }

    public int minMeetingRooms(int[][] intervals) {
        // 开始会议：占用会议室，会议室数量+1
        // 结束会议：离开会议室，会议室数量-1
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> {
                    if (a[0] == b[0]) {
                        return a[1] - b[1];
                    } else {
                        return a[0] - b[0];
                    }
                }
        );
        for (int i = 0; i < intervals.length; i++) {
            heap.offer(new int[]{intervals[i][0], 1});
            heap.offer(new int[]{intervals[i][1], -1});
        }
        int count = 0;
        int max = 0;
        while (!heap.isEmpty()) {
            count += heap.poll()[1];
            max = Math.max(max, count);
        }
        return max;
    }
}
