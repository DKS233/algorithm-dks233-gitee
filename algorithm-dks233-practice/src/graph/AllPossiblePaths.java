package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode797. 所有可能的路径
 *
 * @author dks233
 * @create 2022-09-16-8:10
 */
@SuppressWarnings("ALL")
public class AllPossiblePaths {
    List<List<Integer>> list;
    int n;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        list = new ArrayList<>();
        n = graph.length;
        List<Integer> singleList = new ArrayList<>();
        singleList.add(0);
        process(graph, 0, singleList);
        return list;
    }

    public void process(int[][] graph, int index, List<Integer> singleList) {
        // 有向无环图，到达n-1就直接记录，然后终止dfs，因为当前路径不可能再回到n-1
        if (singleList.contains(n - 1)) {
            list.add(new ArrayList<>(singleList));
            return;
        }
        if (index == n) {
            return;
        }
        for (int i = 0; i < graph[index].length; i++) {
            singleList.add(graph[index][i]);
            process(graph, graph[index][i], singleList);
            singleList.remove(singleList.size() - 1);
        }
    }
}
