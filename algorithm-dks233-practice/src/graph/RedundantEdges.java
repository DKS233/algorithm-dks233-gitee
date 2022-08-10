package graph;

/**
 * 剑指offer专项突击版：剑指 Offer II 118. 多余的边
 *
 * @author dks233
 * @create 2022-08-10-17:42
 */
@SuppressWarnings("ALL")
public class RedundantEdges {
    // 思路：并查集，如果是一个父节点，就返回该条边
    public int[] findRedundantConnection(int[][] edges) {
        UnionSet set = new UnionSet(edges.length);
        for (int index = 0; index < edges.length; index++) {
            if (set.getNode(edges[index][0]) == set.getNode(edges[index][1])) {
                return edges[index];
            } else {
                set.union(edges[index][0], edges[index][1]);
            }
        }
        return null;
    }

    public static class UnionSet {
        public int[] parents; // 记录每个节点的父节点，parent[i]=k 代表i用户的父节点是k
        public int[] size; // 记录每个集合的大小，只有i为代表节点是size[i]才有意义
        public int[] help; // 辅助数组
        public int sets; // 朋友圈数量（代表节点的数量，集合数量）

        public UnionSet(int n) {
            parents = new int[n + 1];
            size = new int[n + 1];
            help = new int[n + 1];
            sets = n;
            for (int i = 1; i <= n; i++) {
                parents[i] = i;
                size[i] = 1;
            }
        }

        // 找当前值所在集合的代表节点
        public int getNode(int cur) {
            int index = 0;
            // 一步步找父亲节点，直到cur==parent[cur]，表示cur为代表节点
            // 将过程中的节点存到help数组中
            while (cur != parents[cur]) {
                help[index++] = cur;
                cur = parents[cur];
            }
            // 过程中的节点的父节点都改为代表节点，扁平化链条
            // cur跳出循环时index++了，help[index+1]位置是没有元素的
            index--;
            while (index >= 0) {
                parents[help[index--]] = cur;
                size[cur]++;
            }
            return cur;
        }

        // 将a用户和b用户的朋友圈合并
        public void union(int a, int b) {
            int nodeA = getNode(a); // 找到a所在朋友圈的代表节点
            int nodeB = getNode(b); // 找到b所在朋友圈的代表节点
            if (nodeA != nodeB) {
                // 将size小的朋友圈合并到size大的朋友圈
                if (size[nodeA] > size[nodeB]) {
                    parents[nodeB] = nodeA;
                    size[nodeA] = size[nodeA] + size[nodeB];
                } else {
                    parents[nodeA] = nodeB;
                    size[nodeB] = size[nodeA] + size[nodeB];
                }
                sets--;
            }
        }
    }
}
