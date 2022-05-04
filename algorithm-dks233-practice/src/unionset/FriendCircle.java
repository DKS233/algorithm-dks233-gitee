package unionset;

/**
 * leetcode547
 * 省份数量问题（同class15：朋友圈问题)
 *
 * @author dks233
 * @create 2022-05-04-9:06
 */
public class FriendCircle {

    public int findCircleNum(int[][] friendCircle) {
        int n = friendCircle.length;
        UnionSet set = new UnionSet(n);
        // 初始情况下一人一个朋友圈，初始化过程中已经建好了
        // 统计右上角的朋友数量，进行朋友圈合并操作
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (friendCircle[i][j] == 1) {
                    set.union(i, j);
                }
            }
        }
        return set.sets;
    }

    public static class UnionSet {
        public int[] parents; // 记录每个节点的父节点，parent[i]=k 代表i用户的父节点是k
        public int[] size; // 记录每个集合的大小，只有i为代表节点是size[i]才有意义
        public int[] help; // 辅助数组
        public int sets; // 朋友圈数量（代表节点的数量，集合数量）

        // n：用户数量
        public UnionSet(int n) {
            parents = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
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
