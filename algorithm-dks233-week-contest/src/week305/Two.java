package week305;

import java.util.*;

/**
 * leetcode6139. 受限条件下可到达节点的数目
 * 参考：https://www.bilibili.com/video/BV1CN4y1V7uE?spm_id_from=333.999.0.0&vd_source=cd2d9ceb11856332f08fec74b6d46d0d
 *
 * @author dks233
 * @create 2022-08-07-10:39
 */
@SuppressWarnings("ALL")
public class Two {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        HashSet<Integer> restrictNodes = new HashSet<>();
        for (int index = 0; index < restricted.length; index++) {
            restrictNodes.add(restricted[index]);
        }
        for (int index = 0; index < edges.length; index++) {
            hashMap.putIfAbsent(edges[index][0], new ArrayList<>());
            hashMap.putIfAbsent(edges[index][1], new ArrayList<>());
            if (!restrictNodes.contains(edges[index][1])) {
                hashMap.get(edges[index][0]).add(edges[index][1]);
            }
            if (!restrictNodes.contains(edges[index][0])) {
                hashMap.get(edges[index][1]).add(edges[index][0]);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        queue.offer(0);
        set.add(0);
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            for (Integer next : hashMap.get(poll)) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.offer(next);
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        Two two = new Two();
        two.reachableNodes(7, new int[][]{{0, 1}, {1, 2}, {3, 1}, {4, 0}, {0, 5}, {5, 6}}, new int[]{4, 5});
    }
}
