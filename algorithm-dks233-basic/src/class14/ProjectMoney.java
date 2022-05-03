package class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 做项目挣钱
 *
 * @author dks233
 * @create 2022-05-03-10:28
 */
public class ProjectMoney {
    public static class Project {
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    /**
     * 做项目最终剩下的资金
     * 分析：建小根堆（根据花费排），建大根堆（根据利润排）
     * 每轮根据当前资金将能做的项目从小根堆弹出，加入大根堆
     * 大根堆是根据利润排的，先做堆顶的项目，做完后更新资金
     * 再根据资金从小根堆弹出，加入大根堆
     *
     * @param costs    项目花费数组
     * @param profiles 项目利润数组
     * @param K        最多做 K个项目
     * @param M        初始资金是M
     * @return 最终的资金
     */
    public static int getMaxMoney(int[] costs, int[] profiles, int K, int M) {
        PriorityQueue<Project> minHeap = new PriorityQueue<>(new CountComparator());
        PriorityQueue<Project> maxHeap = new PriorityQueue<>(new ProfileComparator());
        // 初始：把所有项目加入到小根堆中
        for (int i = 0; i < costs.length; i++) {
            minHeap.offer(new Project(costs[i], profiles[i]));
        }
        // 每轮安排一个项目
        for (int i = 0; i < K; i++) {
            // 将所有可以做的项目加入到大根堆中
            while (!minHeap.isEmpty() && minHeap.peek().cost <= M) {
                maxHeap.offer(minHeap.poll());
            }
            // 大根堆为空，没有加入进来项目，说明当前资金不够做花费最小的项目
            // 也有可能是给的项目小于K，项目都做完了
            if (maxHeap.isEmpty()) {
                return M;
            }
            // 资金更新
            M += maxHeap.poll().profit;
        }
        return M;
    }

    // 比较器
    // 返回负数：第一个排前面
    // 返回正数：第二个排前面
    public static class CountComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o1.cost - o2.cost;
        }
    }

    public static class ProfileComparator implements Comparator<Project> {
        @Override
        public int compare(Project o1, Project o2) {
            return o2.profit - o1.profit;
        }
    }
}
