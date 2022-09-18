package unionset;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * leetcode827. 最大人工岛
 * 参考文档：https://leetcode.cn/problems/making-a-large-island/solution/by-ac_oier-1kmp/
 *
 * @author dks233
 * @create 2022-09-18-10:42
 */
public class LargestArtificialIsland {
    public int largestIsland(int[][] grid) {
        int result = 0;
        int m = grid.length;
        int n = grid[0].length;
        UnionSet set = new UnionSet(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && i + 1 < m && grid[i + 1][j] == 1) {
                    set.union(i, j, i + 1, j);
                }
                if (grid[i][j] == 1 && i - 1 >= 0 && grid[i - 1][j] == 1) {
                    set.union(i, j, i - 1, j);
                }
                if (grid[i][j] == 1 && j + 1 < n && grid[i][j + 1] == 1) {
                    set.union(i, j, i, j + 1);
                }
                if (grid[i][j] == 1 && j - 1 >= 0 && grid[i][j - 1] == 1) {
                    set.union(i, j, i, j - 1);
                }
            }
        }
        for (int i = 0; i < set.sizes.length; i++) {
            result = Math.max(result, set.sizes[i]);
        }
        // 可以将1个0变成1
        // 计算上下左右所在集合的size，然后求和，再加上1就是当前0变成1后的size
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    result = Math.max(process(grid, set, i, j), result);
                }
            }
        }
        return result;
    }

    public int process(int[][] grid, UnionSet set, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        Set<Integer> needAdd = new HashSet<>();
        int curSum = 1;
        if (i + 1 < m && grid[i + 1][j] == 1) {
            needAdd.add(set.getNode(set.getIndex(i + 1, j)));
        }
        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
            needAdd.add(set.getNode(set.getIndex(i - 1, j)));
        }
        if (j + 1 < n && grid[i][j + 1] == 1) {
            needAdd.add(set.getNode(set.getIndex(i, j + 1)));
        }
        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
            needAdd.add(set.getNode(set.getIndex(i, j - 1)));
        }
        for (Integer value : needAdd) {
            curSum += set.sizes[value];
        }
        return curSum;
    }


    public static class UnionSet {
        int[] parents;
        int[] sizes;
        int m;
        int n;

        public UnionSet(int[][] arr) {
            m = arr.length;
            n = arr[0].length;
            parents = new int[m * n];
            sizes = new int[m * n];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public int getNode(int cur) {
            while (cur != parents[cur]) {
                cur = parents[cur];
            }
            return cur;
        }

        public void union(int x1, int y1, int x2, int y2) {
            int head1 = getNode(getIndex(x1, y1));
            int head2 = getNode(getIndex(x2, y2));
            if (head1 != head2) {
                parents[head1] = head2;
                sizes[head2] += sizes[head1];
            }
        }

        public int getIndex(int x, int y) {
            return y + x * n;
        }
    }

    public static void main(String[] args) {
        LargestArtificialIsland thisClass = new LargestArtificialIsland();
        System.out.println(thisClass.largestIsland(new int[][]{{1, 0}, {0, 1}}));
    }
}
