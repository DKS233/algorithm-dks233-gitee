package other;


/**
 * leetcode621. 任务调度器
 * 参考文档：https://leetcode.cn/problems/task-scheduler/solution/tong-zi-by-popopop/
 *
 * @author dks233
 * @create 2022-07-15-0:21
 */
@SuppressWarnings("ALL")
public class TaskScheduler {
    // 桶思想
    // 最短时间=（桶个数-1）*（n+1）+最后一桶的任务个数
    // 分析1：桶个数即为出现最多的任务数量
    // 分析2：如果a是出现次数最多的任务，b出现次数和a相同，那最后一桶的任务个数需要加1
    // 分析3：当任务很多，间隔很短时，可能会出现桶填满桶仍然不够的情况，此时可以想象在右边又扩充了几个桶（或者下方），此时任务数即为最短时间
    // 时间复杂度：O(N)
    public static class MethodOne {
        public int leastInterval(char[] tasks, int n) {
            // 获取桶个数，即出现最多的任务数量
            int maxTask = 0;
            int[] charCount = new int[26];
            for (int index = 0; index < tasks.length; index++) {
                charCount[tasks[index] - 'A']++;
            }
            for (int index = 0; index < 26; index++) {
                maxTask = Math.max(charCount[index], maxTask);
            }
            // 获取最后一桶的任务个数
            int endTask = 0;
            for (int index = 0; index < 26; index++) {
                if (charCount[index] == maxTask) {
                    endTask++;
                }
            }
            // 计算最短时间
            // minOne =（桶个数-1）*（n+1）+最后一桶的任务个数
            // minTwo = task.length
            // 有间隔的时候：minOne>minTwo
            // 没有间隔的时候: minTwo>=minOne(多了右边扩充的那几个桶)
            int minTime = Math.max((maxTask - 1) * (n + 1) + endTask, tasks.length);
            return minTime;
        }
    }
}
