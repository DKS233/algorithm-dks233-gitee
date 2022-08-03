package graph.common;

import java.util.ArrayList;

/**
 * 点
 *
 * @author dks233
 * @create 2022-05-10-10:58
 */
public class Node {
    /**
     * 编号，id
     */
    public int value;
    /**
     * 有多少个点指向它（入度）
     */
    public int in;
    /**
     * 有多少个点从它走出去（出度）
     */
    public int out;
    /**
     * 直接邻居（从自己出发能到谁）
     */
    public ArrayList<Node> nexts;
    /**
     * 从自己出发能直接找到的边
     */
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
