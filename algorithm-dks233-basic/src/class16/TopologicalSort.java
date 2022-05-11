package class16;

import java.util.*;

/**
 * 拓扑排序步骤
 * 在一张图中，先找到入度为0的点，把它删掉，删掉之后，消除这个点的影响，再去找入度为0的点
 *
 * @author dks233
 * @create 2022-05-10-16:01
 */
@SuppressWarnings("MapOrSetKeyShouldOverrideHashCodeEquals")
public class TopologicalSort {
    public static List<Node> topologicalSort(Graph graph) {
        // key:节点 value:节点入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // zeroInQueue存放入度为0的节点
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.offer(node);
            }
        }
        // 排序结果
        ArrayList<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node node = zeroInQueue.poll();
            result.add(node);
            for (Node next : node.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.offer(next);
                }
            }
        }
        return result;
    }
}
