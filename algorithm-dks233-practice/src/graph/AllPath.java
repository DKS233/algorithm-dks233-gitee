package graph;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.*;

/**
 * 剑指offer专项突击版：剑指 Offer II 110. 所有路径
 * 参考文档：https://leetcode.cn/problems/all-paths-from-source-to-target/solution/gong-shui-san-xie-yun-yong-dfs-bao-sou-s-xlz9/
 *
 * @author dks233
 * @create 2022-08-09-23:41
 */
@SuppressWarnings("ALL")
public class AllPath {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> singleList = new ArrayList<>();


    // 思路：dfs，遇到n-1路径数就+1
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        singleList.add(0);
        dfs(0, graph);
        return list;
    }

    public void dfs(int node, int[][] graph) {
        if (node == graph.length - 1) {
            list.add(new ArrayList<>(singleList));
            return;
        }
        for (int next : graph[node]) {
            singleList.add(next);
            dfs(next, graph);
            singleList.remove(singleList.size() - 1);
        }
    }
}
