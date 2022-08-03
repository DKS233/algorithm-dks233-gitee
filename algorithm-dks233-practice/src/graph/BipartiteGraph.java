package graph;

import java.util.ArrayDeque;

/**
 * 剑指offer专项突击版：剑指 Offer II 106. 二分图
 * 参考文档：https://leetcode.cn/problems/is-graph-bipartite/solution/bfs-dfs-bing-cha-ji-san-chong-fang-fa-pan-duan-er-/
 *
 * @author dks233
 * @create 2022-08-02-23:52
 */
public class BipartiteGraph {
    public static class Bfs {
        public boolean isBipartite(int[][] graph) {
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            int[] visit = new int[graph.length];
            // 未说所有节点间都是连通的
            // 所以需要遍历所有节点，检查是否被访问
            for (int index = 0; index < graph.length; index++) {
                if (visit[index] != 0) {
                    continue;
                }
                // 每弹出一个节点，将节点染色，然后将相邻节点染成另外一个颜色
                queue.offerFirst(index);
                visit[index] = 1;
                while (!queue.isEmpty()) {
                    int curIndex = queue.pollLast();
                    for (int next : graph[curIndex]) {
                        if (visit[next] == visit[curIndex]) {
                            return false;
                        }
                        if (visit[next] == 0) {
                            queue.offerFirst(next);
                            visit[next] = -visit[curIndex];
                        }
                    }
                }
            }
            return true;
        }
    }

    public static class Dfs {
        public boolean isBipartite(int[][] graph) {
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            int[] visit = new int[graph.length];
            for (int index = 0; index < graph.length; index++) {
                if (visit[index] != 0) {
                    continue;
                }
                stack.push(index);
                visit[index] = 1;
                while (!stack.isEmpty()) {
                    int curIndex = stack.pop();
                    for (int next : graph[curIndex]) {
                        if (visit[next] == visit[curIndex]) {
                            return false;
                        }
                        if (visit[next] == 0) {
                            stack.push(curIndex);
                            stack.push(next);
                            visit[next] = -visit[curIndex];
                        }
                    }
                }
            }
            return true;
        }
    }
}
