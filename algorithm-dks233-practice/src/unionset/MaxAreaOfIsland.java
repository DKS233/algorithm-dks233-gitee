package unionset;

/**
 * leetcode695：岛屿的最大面积
 * 给你一个大小为mxn的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个1必须在水平或者竖直的四个方向上相邻。
 * 你可以假设grid的四个边缘都被0（代表水）包围着。
 * 岛屿的面积是岛上值为1的单元格的数目。
 * 计算并返回grid中最大的岛屿面积。如果没有岛屿，则返回面积为 0
 *
 * @author dks233
 * @create 2022-05-10-10:03
 */
public class MaxAreaOfIsland {
    // 时间复杂度：O(MN)
    // 空间复杂度：O(MN)
    public int maxAreaOfIsland(int[][] grid) {
        // 每个节点所在集合和左上角的节点所在集合进行合并
        MyUnionSet myUnionSet = new MyUnionSet(grid);
        // 先处理没有左或者上的节点
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == 1 && grid[i - 1][0] == 1) {
                myUnionSet.union(i, 0, i - 1, 0);
            }
        }
        for (int j = 1; j < grid[0].length; j++) {
            if (grid[0][j] == 1 && grid[0][j - 1] == 1) {
                myUnionSet.union(0, j, 0, j - 1);
            }
        }
        // 处理其他节点
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && grid[i - 1][j] == 1) {
                    myUnionSet.union(i, j, i - 1, j);
                }
                if (grid[i][j] == 1 && grid[i][j - 1] == 1) {
                    myUnionSet.union(i, j, i, j - 1);
                }
            }
        }
        return myUnionSet.maxSets;
    }

    public static class MyUnionSet {
        public int[] parents; // 记录每个节点的父节点，parent[i]=k表示i的父节点是k
        public int[] size; // 记录每个集合的大小，只有i为代表节点时size[i]才有效
        public int sets; // 集合数量
        public int maxSets; // 最大集合里有多少节点
        public int[] help; // 辅助数组
        public int row; // 行数
        public int column; // 列数

        public MyUnionSet(int[][] grid) {
            row = grid.length;
            column = grid[0].length;
            parents = new int[row * column];
            size = new int[row * column];
            help = new int[row * column];
            sets = 0;
            maxSets = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (grid[i][j] == 1) {
                        parents[getIndex(i, j)] = getIndex(i, j);
                        size[getIndex(i, j)] = 1;
                        // 初始化过程中设置最大集合长度为1
                        maxSets = 1;
                    }
                }
            }
        }

        // 得到当前节点所在集合的代表节点
        public int getNode(int cur) {
            int index = 0;
            while (cur != parents[cur]) {
                help[index++] = cur;
                cur = parents[cur];
            }
            index--;
            while (index >= 0) {
                parents[help[index--]] = cur;
            }
            return cur;
        }

        // 合并两个节点所在集合
        public void union(int i1, int j1, int i2, int j2) {
            int a = getIndex(i1, j1);
            int b = getIndex(i2, j2);
            int headA = getNode(a);
            int headB = getNode(b);
            if (headA != headB) {
                int sizeA = size[headA];
                int sizeB = size[headB];
                if (sizeA > sizeB) {
                    parents[headB] = headA;
                    size[headA] = sizeA + sizeB;
                    // 每个集合的大小变化一次，更新一次最大集合
                    maxSets = Math.max(size[headA], maxSets);
                } else {
                    parents[headA] = headB;
                    size[headB] = sizeA + sizeB;
                    // 每个集合的大小变化一次，更新一次最大集合
                    maxSets = Math.max(size[headB], maxSets);
                }
            }
        }

        public int getIndex(int i, int j) {
            return column * i + j;
        }
    }
}












