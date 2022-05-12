package class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 实现Dijkstra算法
 *
 * @author dks233
 * @create 2022-05-12-9:05
 */
public class Dijkstra {
    @SuppressWarnings("MapOrSetKeyShouldOverrideHashCodeEquals")
    public static class MethodOne {
        // from称作点a
        public static HashMap<Node, Integer> getMinDistance(Node from) {
            // 距离表，记录a到该点的最小距离
            HashMap<Node, Integer> distanceMap = new HashMap<>();
            distanceMap.put(from, 0);
            // 被锁定的记录（点）
            HashSet<Node> lockedNodes = new HashSet<>();
            // 从未锁定的点中选取距离最小的点，更新距离表
            Node minNode = getMinDistanceUnlockedNodes(distanceMap, lockedNodes);
            while (minNode != null) {
                // 点a到当前minNode的最小距离
                int minDistance = distanceMap.get(minNode);
                // 更新a到当前遍历点的最小距离( a->minNode->toNode = (a->minNode) + (minNode->toNode) )
                for (Edge edge : minNode.edges) {
                    Node toNode = edge.to;
                    if (!distanceMap.containsKey(toNode)) {
                        distanceMap.put(toNode, minDistance + edge.weight);
                    } else {
                        distanceMap.put(toNode, Math.min(distanceMap.get(toNode), minDistance + edge.weight));
                    }
                    // 锁定toNode
                    lockedNodes.add(toNode);
                    // 进入下一轮选取
                    minNode = getMinDistanceUnlockedNodes(distanceMap, lockedNodes);
                }
            }
            return distanceMap;
        }

        // 从未锁定的点中选取距离最小的点
        public static Node getMinDistanceUnlockedNodes(HashMap<Node, Integer> distanceMap,
                                                       HashSet<Node> lockedNodes) {
            Node minNode = null;
            int minDistance = Integer.MAX_VALUE;
            for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
                Node node = entry.getKey();
                Integer distance = entry.getValue();
                if (!lockedNodes.contains(node) && distance < minDistance) {
                    minNode = node;
                    minDistance = distance;
                }
            }
            return minNode;
        }
    }

    public static class MethodTwo {

    }
}
