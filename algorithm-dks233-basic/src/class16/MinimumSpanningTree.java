package class16;

import java.util.*;

/**
 * 最小生成树Kruskal算法
 * 并查集中节点在一个集合内表示节点间已经连通
 *
 * @author dks233
 * @create 2022-05-11-15:12
 */
@SuppressWarnings("ALL")
public class MinimumSpanningTree {
    public static class MyUnionSet {
        public HashMap<Node, Node> parentMap; // 集合中的节点和其父节点
        public HashMap<Node, Integer> sizeMap; // key为代表节点，value为所代表集合的size

        public MyUnionSet(HashMap<Node, Node> parentMap, HashMap<Node, Integer> sizeMap) {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public MyUnionSet(Collection<Node> nodes) {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (Node node : nodes) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 找当前集合所在的代表节点
        public Node getNode(Node cur) {
            // 存从当前节点找代表节点过程中经过的节点
            Stack<Node> stack = new Stack<>();
            // 找代表节点
            while (cur != parentMap.get(cur)) {
                stack.push(cur);
                cur = parentMap.get(cur);
            }
            // 将stack中记录的节点的父节点都变成代表节点，扁平化链条
            if (!stack.isEmpty()) {
                parentMap.put(stack.pop(), cur);
            }
            return cur;
        }

        // 判断nodeOne和nodeTwo是不是在一个集合
        public boolean isSameSet(Node nodeOne, Node nodeTwo) {
            return getNode(nodeOne) == getNode(nodeTwo);
        }

        // 合并两个节点所在的集合
        // 将较小的集合连到较大的集合上
        public void union(Node nodeOne, Node nodeTwo) {
            if (!isSameSet(nodeOne, nodeTwo)) {
                Integer sizeOne = sizeMap.get(nodeOne);
                Integer sizeTwo = sizeMap.get(nodeTwo);
                if (sizeOne > sizeTwo) {
                    parentMap.put(nodeTwo, nodeOne);
                    sizeMap.put(nodeOne, sizeOne + sizeTwo);
                    sizeMap.remove(nodeTwo);
                } else {
                    parentMap.put(nodeOne, nodeTwo);
                    sizeMap.put(nodeTwo, sizeOne + sizeTwo);
                    sizeMap.remove(nodeOne);
                }
            }
        }
    }

    // 最小生成树kruskal算法
    // (1)按照权重对边进行排序，依次考虑权重较小大较大的边
    // (2)如果当前的边两个节点已经在一个集合内，说明两个节点间已经连通，丢弃该边，否则会形成环
    // (3)如果当前的边两个节点不在一个集合内，说明两个节点间没连通，选中该条边，集合合并，两节点连通
    public static Set<Edge> kruskalMethod(Graph graph) {
        // 初始化，每个节点生成自己的集合，彼此间未连通
        MyUnionSet myUnionSet = new MyUnionSet(graph.nodes.values());
        // 需要返回的边集
        HashSet<Edge> result = new HashSet<>();
        // 准备小根堆，所有边放到小根堆中，然后按照边的权重依次弹出，依次处理
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            minHeap.offer(edge);
        }
        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            // 当前边的两个节点为加入一个集合内，就选中该条边，集合合并，两节点连通
            if (!myUnionSet.isSameSet(edge.from, edge.to)) {
                myUnionSet.union(edge.from, edge.to);
                result.add(edge);
            }
        }
        return result;
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    // 最小生成树prim算法
    // 同一个点解锁两次就会形成环
    public static Set<Edge> primMethod(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new EdgeComparator());
        // 解锁的点进入set
        HashSet<Node> set = new HashSet<>();
        // 最终选中的边
        HashSet<Edge> result = new HashSet<>();
        // 随机选中一个开始的点
        for (Node node : graph.nodes.values()) {
            // 该点未被解锁过，解锁该点加入到set中
            if (!set.contains(node)) {
                set.add(node);
                // 该点出发的所有边都解锁
                for (Edge edge : node.edges) {
                    minHeap.add(edge);
                }
                while (!minHeap.isEmpty()) {
                    // 弹出解锁的边中权重最小的边
                    Edge edge = minHeap.poll();
                    Node toNode = edge.to;
                    // toNode不在set里说明toNode未被解锁过，解锁edge不会形成环
                    // toNode在set里说明toNode已经被解锁过，解锁edge会形成环
                    // 如果会形成环，跳过if语句，继续弹
                    // 如果不会形成环，进入if语句
                    if (!set.contains(toNode)) {
                        // 选中边，解锁节点
                        result.add(edge);
                        set.add(toNode);
                        // 选中的节点出发的所有边都被解锁
                        for (Edge nextEdge : toNode.edges) {
                            minHeap.add(nextEdge);
                        }
                    }
                }
                // if结束后所有节点都被解锁，直接break结束循环
                // 即使不break，下次进入循环，别的节点都已经被选中，不会进入if语句
                break;
            }
        }
        return result;
    }
}
