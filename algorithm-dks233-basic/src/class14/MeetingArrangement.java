package class14;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 求最多的宣讲场次
 *
 * @author dks233
 * @create 2022-05-02-21:38
 */
public class MeetingArrangement {
    public static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // 按照结束时间早安排会议
    public static int meetingArrangement(Meeting[] meetings) {
        Arrays.sort(meetings, new MyComparator());
        int timePoint = 0; // 时间点
        int meetingCount = 0; // 安排会议数量
        for (int i = 0; i < meetings.length; i++) {
            if (timePoint <= meetings[i].start) {
                timePoint = meetings[i].end;
                meetingCount++;
            }
        }
        return meetingCount;
    }

    // 谁结束时间早谁排前面，谁最先安排
    public static class MyComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end;
        }
    }

    // 对数器
    public static int comparator(Meeting[] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }
        return process(meetings, 0, 0);
    }

    /**
     * 返回安排的最大会议数
     *
     * @param notArrangeMeetings 未被安排的会议
     * @param arrange            已经安排的会议数量
     * @param timePoint          当前时间点（随安排的会议的结束时间更新）
     * @return 安排的最大会议数
     */
    public static int process(Meeting[] notArrangeMeetings, int arrange, int timePoint) {
        if (notArrangeMeetings.length == 0) {
            return arrange;
        }
        int maxCount = arrange;
        // 第一次安排的会议：所有会议都列举
        // 确定第一次安排的会议后，再列举其他会议
        for (int i = 0; i < notArrangeMeetings.length; i++) {
            if (timePoint <= notArrangeMeetings[i].start) {
                // i位置的会议已经被安排，从未被安排列表中剔除
                Meeting[] nextNotArrangeMeetings = getNotArrangeMeetings(notArrangeMeetings, i);
                // 更新已安排会议数量，安排下一个会议
                maxCount = Math.max(maxCount, process(nextNotArrangeMeetings,
                        arrange + 1, notArrangeMeetings[i].end));
            }
        }
        return maxCount;
    }

    private static Meeting[] getNotArrangeMeetings(Meeting[] notArrangeMeetings, int i) {
        Meeting[] meetings = new Meeting[notArrangeMeetings.length - 1];
        int index = 0;
        for (int j = 0; j < notArrangeMeetings.length; j++) {
            if (j != i) {
                meetings[index++] = notArrangeMeetings[j];
            }
        }
        return meetings;
    }

    public static Meeting[] randomMeeting(int meetingMaxSize, int timeMaxValue) {
        Meeting[] meetings = new Meeting[(int) (Math.random() * (meetingMaxSize + 1))];
        for (int i = 0; i < meetings.length; i++) {
            int start = (int) (Math.random() * (timeMaxValue + 1));
            int end = (int) (Math.random() * (timeMaxValue + 1));
            if (start == end) {
                meetings[i] = new Meeting(start, start + 1);
            } else {
                meetings[i] = new Meeting(Math.min(start, end), Math.max(start, end));
            }
        }
        return meetings;
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int meetingMaxSize = 12;
        int timeMaxValue = 20;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Meeting[] meetings = randomMeeting(meetingMaxSize, timeMaxValue);
            int arrangement = meetingArrangement(meetings);
            int comparator = comparator(meetings);
            if (comparator != arrangement) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
