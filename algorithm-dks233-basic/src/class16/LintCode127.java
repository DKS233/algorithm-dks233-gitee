package class16;

import java.util.*;

/**
 * LintCode127：拓扑排序
 *
 * @author dks233
 * @create 2022-05-11-9:32
 */
@SuppressWarnings("ALL")
public class LintCode127 {
    // 题目中节点定义
    public static class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public static class MethodOne {
        /**
         * 拓扑排序，返回排好序的图
         *
         * @param graph 图
         * @return 排好序的图
         */
        public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
            // 缓存：存每个节点的点次，前面的节点需要的话直接从map里取
            HashMap<DirectedGraphNode, Long> map = new HashMap<>();
            // 将所有节点的点次都存到map里
            for (int i = 0; i < graph.size(); i++) {
                process(graph.get(i), map);
            }
            // 将map中的所有节点提取出来，按照节点点次排好序
            ArrayList<Record> recordList = new ArrayList<>();
            for (Map.Entry<DirectedGraphNode, Long> entry : map.entrySet()) {
                recordList.add(new Record(entry.getKey(), entry.getValue()));
            }
            recordList.sort(new MyComparator());
            // 将排好序的图返回
            ArrayList<DirectedGraphNode> result = new ArrayList<>();
            for (int i = 0; i < recordList.size(); i++) {
                result.add(recordList.get(i).node);
            }
            return result;
        }

        // 记录每个节点的点次
        public static class Record {
            public DirectedGraphNode node;
            public Long nodes;

            public Record(DirectedGraphNode node, Long nodes) {
                this.node = node;
                this.nodes = nodes;
            }
        }

        public static class MyComparator implements Comparator<Record> {

            @Override
            public int compare(Record o1, Record o2) {
                if (o1.nodes == o2.nodes) {
                    return 0;
                }
                // 返回数小于0，左边的排前面
                // 返回数大于0，右边的排前面
                // 左边点次大时，希望左边的记录排前面，返回负数
                return o1.nodes > o2.nodes ? -1 : 1;
            }
        }

        public static long process(DirectedGraphNode node, HashMap<DirectedGraphNode, Long> map) {
            if (map.containsKey(node)) {
                return map.get(node);
            }
            // 节点点次
            long nodes = 0;
            for (DirectedGraphNode next : node.neighbors) {
                nodes += process(next, map);
            }
            // 将当前节点的点次存到缓存中
            // 当前节点的点次等于next节点的点次加上当前节点
            map.put(node, nodes + 1);
            return map.get(node);
        }
    }

    // 和方法1类似，只是方法1统计的是点次，方法2统计的是深度
    public static class MethodTwo {
        /**
         * 拓扑排序，返回排好序的图
         *
         * @param graph 图
         * @return 排好序的图
         */
        public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
            // 缓存，统计每个点能走出的最大深度
            HashMap<DirectedGraphNode, Long> map = new HashMap<>();
            for (int i = 0; i < graph.size(); i++) {
                process(graph.get(i), map);
            }
            // 提取每个节点能走出的最大深度，然后进行排序
            ArrayList<Record> recordList = new ArrayList<>();
            for (Map.Entry<DirectedGraphNode, Long> entry : map.entrySet()) {
                recordList.add(new Record(entry.getKey(), entry.getValue()));
            }
            recordList.sort(new MyComparatorTwo());
            // 排好序后，准备list，存排好序的节点，返回
            ArrayList<DirectedGraphNode> list = new ArrayList<>();
            for (int i = 0; i < recordList.size(); i++) {
                list.add(recordList.get(i).node);
            }
            return list;
        }

        public static Long process(DirectedGraphNode node, HashMap<DirectedGraphNode, Long> map) {
            // 如果当前点最大深度已经统计过，直接拿
            if (map.containsKey(node)) {
                return map.get(node);
            }
            // 如果当前点最大深度没有统计过，统计直接邻居的最大深度，然后加上当前节点
            Long depth = 0L;
            for (DirectedGraphNode next : node.neighbors) {
                depth += process(next, map);
            }
            // 将当前节点最大深度加入到缓存中，方便后续直接获取
            map.put(node, depth + 1);
            // 返回当前节点最大深度
            return map.get(node);
        }

        // 统计每个点能走出的最大深度
        public static class Record {
            public DirectedGraphNode node;
            public Long depth;

            public Record(DirectedGraphNode node, Long depth) {
                this.node = node;
                this.depth = depth;
            }
        }

        public static class MyComparatorTwo implements Comparator<Record> {
            // 返回的数小于0，左边的排前面；返回的数大于0，右边的排前面
            // 左边深度大时，希望左边的记录排前面，返回负数
            @Override
            public int compare(Record o1, Record o2) {
                if (o1.depth == o2.depth) {
                    return 0;
                }
                return o1.depth > o2.depth ? -1 : 1;
            }
        }
    }

    public static void main(String[] args) {
        DirectedGraphNode zero = new DirectedGraphNode(0);
        DirectedGraphNode one = new DirectedGraphNode(1);
        DirectedGraphNode two = new DirectedGraphNode(2);
        DirectedGraphNode three = new DirectedGraphNode(3);
        DirectedGraphNode four = new DirectedGraphNode(4);
        DirectedGraphNode five = new DirectedGraphNode(5);
        ArrayList<DirectedGraphNode> zeroList = new ArrayList<>();
        zeroList.add(one);
        zeroList.add(two);
        zeroList.add(three);
        ArrayList<DirectedGraphNode> oneList = new ArrayList<>();
        oneList.add(four);
        ArrayList<DirectedGraphNode> twoList = new ArrayList<>();
        twoList.add(four);
        twoList.add(five);
        ArrayList<DirectedGraphNode> threeList = new ArrayList<>();
        threeList.add(four);
        threeList.add(five);
        ArrayList<DirectedGraphNode> list = new ArrayList<>();
        list.add(zero);
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        zero.neighbors = zeroList;
        one.neighbors = oneList;
        two.neighbors = twoList;
        three.neighbors = threeList;
        MethodOne methodOne = new MethodOne();
        methodOne.topSort(list);
    }
}
