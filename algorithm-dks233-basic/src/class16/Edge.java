package class16;

/**
 * 边
 *
 * @author dks233
 * @create 2022-05-10-11:04
 */
public class Edge {
    public int weight; // 权重
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
