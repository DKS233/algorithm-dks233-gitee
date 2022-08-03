package graph.common;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图
 *
 * @author dks233
 * @create 2022-05-10-11:07
 */
@SuppressWarnings("ALL")
public class Graph {
    public HashMap<Integer, Node> nodes; // 点集
    public HashSet<Edge> edges; // 边集

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
