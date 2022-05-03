package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * leetcode502
 * IPO：首次公开募股
 * 做项目赚钱问题
 *
 * @author dks233
 * @create 2022-05-03-10:51
 */
public class IPO {
    /**
     * 做完项目手里剩的最大资金
     * 分析：准备一个小根堆（按花费排），准备一个大根堆（按利润排）
     * 假设每轮安排一个项目，每轮根据现有资金将可以做的项目从小根堆加入到大根堆中
     * 大根堆堆顶的项目是利润最高的，做大根堆堆顶的项目，然后更新资金
     * 再根据资金将小根堆中可以做的项目加入到大根堆中
     *
     * @param k       最多做k个项目
     * @param w       初始资金
     * @param profits 项目的利润列表
     * @param counts  项目的花费列表
     * @return 做完项目手里剩的最大资金
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] counts) {
        PriorityQueue<Project> minHeap = new PriorityQueue<>(new CountComparator());
        PriorityQueue<Project> maxHeap = new PriorityQueue<>(new ProfileComparator());
        // 初始化：将所有项目加入到小根堆中
        for (int i = 0; i < profits.length; i++) {
            minHeap.offer(new Project(profits[i], counts[i]));
        }
        for (int i = 0; i < k; i++) {
            // 根据当前资金将可以做的项目从小根堆加入到大根堆中
            while (!minHeap.isEmpty() && minHeap.peek().count <= w) {
                maxHeap.offer(minHeap.poll());
            }
            // 如果大根堆中没加进来元素，说明没有从小根堆中弹出元素加入到大根堆中
            // 当前资金连花费最小的项目都不够做，或者给的项目小于k，项目已经全部做完
            if (maxHeap.isEmpty()) {
                return w;
            }
            // 做项目，更新当前资金
            w += maxHeap.poll().profile;
        }
        return w;
    }

    public static class CountComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o1.count - o2.count;
        }
    }

    public static class ProfileComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profile - o1.profile;
        }
    }

    public static class Project {
        int profile;
        int count;

        public Project(int profile, int count) {
            this.profile = profile;
            this.count = count;
        }
    }
}
