package graph;

import graph.common.Graph;
import graph.common.Node;

import java.util.*;

/**
 * leetcode207. 课程表
 * leetcode210. 课程表 II
 *
 * @author dks233
 * @create 2022-08-03-23:35
 */
@SuppressWarnings("ALL")
public class SchoolTimetable {
    public static class ProblemOne {
        // [a,b] 先学b再学a
        // 拓扑排序的步骤：在一张图中找到入度为0的点，删除，然后消除这个点的影响，再去找入度为0的点
        public boolean canFinish(int numCourses, int[][] nums) {
            // 存入度为0的点
            Queue<Node> zeroQueue = new LinkedList<>();
            // key：node value：入度
            HashMap<Node, Integer> inMap = new HashMap<>();
            // 确定所有节点的入度和next节点
            // key:节点值 value:节点
            HashMap<Integer, Node> nodeMap = new HashMap<>();
            for (int index = 0; index < nums.length; index++) {
                int from = nums[index][1];
                int to = nums[index][0];
                if (!nodeMap.containsKey(from)) {
                    nodeMap.put(from, new Node(from));
                }
                if (!nodeMap.containsKey(to)) {
                    nodeMap.put(to, new Node(to));
                }
                Node fromNode = nodeMap.get(from);
                Node toNode = nodeMap.get(to);
                toNode.in++;
                fromNode.nexts.add(toNode);
            }
            for (Node node : nodeMap.values()) {
                inMap.put(node, node.in);
                if (node.in == 0) {
                    zeroQueue.offer(node);
                }
            }
            while (!zeroQueue.isEmpty()) {
                Node node = zeroQueue.poll();
                for (Node next : node.nexts) {
                    inMap.put(next, inMap.get(next) - 1);
                    if (inMap.get(next) == 0) {
                        zeroQueue.offer(next);
                    }
                }
            }
            // 如果还有课程入度不为0，返回false
            for (Node node : inMap.keySet()) {
                if (inMap.get(node) != 0) {
                    return false;
                }
            }
            return true;
        }

        public static class Node {
            int value;
            int in; // 入度
            List<Node> nexts; // 从当前node出发能直接到达的节点

            public Node(int value) {
                this.value = value;
                this.in = 0;
                this.nexts = new ArrayList<>();
            }
        }
    }

    public static class ProblemTwo {
        public int[] findOrder(int numCourses, int[][] nums) {
            // 存入度为0的点
            Queue<Node> zeroQueue = new LinkedList<>();
            // key：node value：入度
            HashMap<Node, Integer> inMap = new HashMap<>();
            // 确定所有节点的入度和next节点
            // key:节点值 value:节点
            HashMap<Integer, Node> nodeMap = new HashMap<>();
            for (int index = 0; index < numCourses; index++) {
                nodeMap.put(index, new Node(index));
            }
            for (int index = 0; index < nums.length; index++) {
                int from = nums[index][1];
                int to = nums[index][0];
                Node fromNode = nodeMap.get(from);
                Node toNode = nodeMap.get(to);
                toNode.in++;
                fromNode.nexts.add(toNode);
            }
            // 进行拓扑排序，更新count
            for (Node node : nodeMap.values()) {
                inMap.put(node, node.in);
                if (node.in == 0) {
                    zeroQueue.offer(node);
                }
            }
            int[] result = new int[numCourses];
            int index = 0;
            while (!zeroQueue.isEmpty()) {
                Node node = zeroQueue.poll();
                result[index++] = node.value;
                for (Node next : node.nexts) {
                    inMap.put(next, inMap.get(next) - 1);
                    if (inMap.get(next) == 0) {
                        zeroQueue.offer(next);
                    }
                }
            }
            // 如果还有课程入度不为0，没有符合条件的课程安排
            boolean flag = true;
            for (Node node : inMap.keySet()) {
                if (inMap.get(node) != 0) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                return new int[]{};
            }
            return result;
        }

        public static class Node {
            int value;
            int in; // 入度
            List<Node> nexts; // 从当前node出发能直接到达的节点

            public Node(int value) {
                this.value = value;
                this.in = 0;
                this.nexts = new ArrayList<>();
            }
        }
    }

    public static void main(String[] args) {
        ProblemTwo problemTwo = new ProblemTwo();
        problemTwo.findOrder(2, new int[][]{{1, 0}});
    }

    // 复习拓扑排序
    // 删除入度为0的点，消除它的影响，然后删除下一个入度为0的点
    public static class ReviewSort {
        public static List<Node> topologicalSort(Graph graph) {
            // 存入度为0的点
            Queue<Node> zeroQueue = new LinkedList<>();
            // key:node value:入度
            HashMap<Node, Integer> inMap = new HashMap<>();
            for (Node node : graph.nodes.values()) {
                inMap.put(node, node.in);
                if (node.in == 0) {
                    zeroQueue.offer(node);
                }
            }
            List<Node> result = new ArrayList<>();
            while (!zeroQueue.isEmpty()) {
                Node node = zeroQueue.poll();
                result.add(node);
                for (Node next : node.nexts) {
                    inMap.put(next, inMap.get(next) - 1);
                    if (inMap.get(next) == 0) {
                        zeroQueue.offer(next);
                    }
                }
            }
            return result;
        }
    }
}
