package class17;

import class16.Edge;
import class16.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Dijkstra算法非优化和优化实现
 * TODO：写法整合，统一用Record(node+distance)或者map(node->distance)
 *
 * @author dks233
 * @create 2022-05-15-16:32
 */
@SuppressWarnings("ALL")
public class Dijkstra {
    public static class MethodOne {
        // 返回from节点到所有节点的最小距离
        public HashMap<Node, Integer> getDistanceMap(Node from) {
            // from节点到图中所有节点的最小距离
            HashMap<Node, Integer> distanceMap = new HashMap<>();
            distanceMap.put(from, 0);
            // 被锁定的节点
            HashSet<Node> lockedNodes = new HashSet<>();
            // 从当前未被锁定的记录中选择距离最小的（距离表示from到当前节点的最小距离）
            Node minNode = getMinUnLockedNode(distanceMap, lockedNodes);
            // 找不到锁定的记录就退出循环
            while (minNode != null) {
                Integer distance = distanceMap.get(minNode);
                // 拿到minNode，更新记录表
                for (Edge edge : minNode.edges) {
                    Node toNode = edge.to;
                    // from->toNode
                    // from->minNode + minNode->toNode
                    if (!distanceMap.containsKey(toNode)) {
                        distanceMap.put(toNode, distance + edge.weight);
                    } else {
                        distanceMap.put(toNode, Math.min(distance + edge.weight, distanceMap.get(toNode)));
                    }
                }
                // 更新完后，锁定记录
                lockedNodes.add(minNode);
                // 下一轮minNode
                minNode = getMinUnLockedNode(distanceMap, lockedNodes);
            }
            return distanceMap;
        }

        // 每次找记录，都得遍历map进行查找，很慢
        // 优化：方法二，加强堆实现
        public Node getMinUnLockedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> lockedNodes) {
            Node minNode = null; // 未被解锁的记录中距离最小的记录对应的点
            int minDistance = Integer.MAX_VALUE; // 未被解锁的记录中距离最小的记录对应的距离
            for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
                Node node = entry.getKey();
                Integer distance = entry.getValue();
                if (!lockedNodes.contains(node) && distance < minDistance) {
                    minDistance = distance;
                    minNode = node;
                }
            }
            return minNode;
        }
    }

    // 加强堆实现
    public static class MethodTwo {
        public static HashMap<Node, Integer> getMinDistance(Node head, int size) {
            NodeHeap nodeHeap = new NodeHeap(size);
            nodeHeap.addOrUpdateOrIgnore(head, 0);
            HashMap<Node, Integer> result = new HashMap<>();
            while (!nodeHeap.isEmpty()) {
                NodeRecord record = nodeHeap.pop();
                Node node = record.node;
                int distance = record.distance;
                for (Edge edge : node.edges) {
                    nodeHeap.addOrUpdateOrIgnore(edge.to, distance + edge.weight);
                }
                result.put(node, distance);
            }
            return result;
        }
    }

    public static class NodeHeap {
        public Node[] heap;
        public HashMap<Node, Integer> distanceMap; // 距离表
        public HashMap<Node, Integer> indexMap; // 反向索引表
        public int heapSize; // 堆大小（节点数量）

        public NodeHeap(int size) {
            this.heap = new Node[size];
            this.distanceMap = new HashMap<>();
            this.indexMap = new HashMap<>();
            this.heapSize = 0;
        }

        public void swap(int indexOne, int indexTwo) {
            indexMap.put(heap[indexOne], indexTwo);
            indexMap.put(heap[indexTwo], indexOne);
            Node temp = heap[indexOne];
            heap[indexOne] = heap[indexTwo];
            heap[indexTwo] = temp;
        }

        // 节点三种状态
        // (1) 没进过堆，没被处理过
        // (2) 进过堆，但是该节点的记录已经被锁定，indexMap.get(node)变成-1
        // (3) 进过堆，该节点对应的记录没被锁定，可以进行更新
        // 返回true，代表状态2/状态3
        // 返回false，代表状态1
        public boolean isEnteredHeap(Node node) {
            return indexMap.containsKey(node);
        }

        // 返回true，代表状态3
        public boolean isInHeap(Node node) {
            return isEnteredHeap(node) && indexMap.get(node) != -1;
        }

        // 对应状态1，就add
        // 对应状态2，就ignore
        // 对应状态3，就update
        public void addOrUpdateOrIgnore(Node node, int distance) {
            // update
            if (isInHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(node, indexMap.get(node));
            }
            // add
            if (!isEnteredHeap(node)) {
                distanceMap.put(node, distance);
                heap[heapSize] = node;
                indexMap.put(node, heapSize);
                heapInsert(node, heapSize++);
            }
            // ignore
        }

        private void heapInsert(Node node, int index) {
            while (distanceMap.get(heap[index]) < distanceMap.get(heap[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(heap[0], distanceMap.get(heap[0]));
            swap(0, heapSize - 1);
            indexMap.put(heap[heapSize - 1], -1);
            distanceMap.remove(heap[heapSize - 1]);
            heap[heapSize - 1] = null;
            heapify(0, --heapSize);
            return nodeRecord;
        }

        private void heapify(int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int smallest = left + 1 < heapSize &&
                        distanceMap.get(heap[left + 1]) < distanceMap.get(heap[left]) ? left + 1 : left;
                smallest = distanceMap.get(heap[index]) < distanceMap.get(heap[smallest]) ? index : smallest;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        public boolean isEmpty() {
            return this.heapSize == 0;
        }
    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}
