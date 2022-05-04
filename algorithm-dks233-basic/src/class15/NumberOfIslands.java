package class15;

/**
 * leetcode200：求岛屿的数量
 *
 * @author dks233
 * @create 2022-05-04-10:26
 */
public class NumberOfIslands {
    // 方法1：递归
    // 感染：遍历每个位置，看当前位置上下左右，如果是'1'就连起来，将'1'变成别的数值，比如'2'，感染成功
    // 如果不把'1'变成别的字符，会造成死循环
    // 最后返回感染区域的数量
    // 时间复杂度：O(M*N) 递归过程中每个位置经过4次（上下左右节点分别经过）
    public static class RecursiveMethod {
        public int numIslands(char[][] grid) {
            int count = 0;
            int row = grid.length;
            int column = grid[0].length;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (grid[i][j] == '1') {
                        process(grid, i, j);
                        count++;
                    }
                }
            }
            return count;
        }

        private void process(char[][] grid, int i, int j) {
            if (i >= grid.length || i < 0 || j >= grid[0].length || j < 0 || grid[i][j] != '1') {
                return;
            }
            grid[i][j] = '2';
            process(grid, i - 1, j);
            process(grid, i + 1, j);
            process(grid, i, j + 1);
            process(grid, i, j - 1);
        }
    }

    // 方法二：利用并查集
    // 初始时有多少个'1'就有多少块岛屿，每个位置上如果是'1'，看其上边和左边是否是'1'
    // 左边有1就合并，上边有1就合并
    // 时间复杂度：O(M*N) 注：每次union平均复杂度为O(1)(不需要证明）
    public static class UnionSetMethod {
        public int numIslands(char[][] grid) {
            // 初始化并查集
            MyUnionSet myUnionSet = new MyUnionSet(grid);
            int row = grid.length;
            int column = grid[0].length;
            // 观察每个位置的左上位置
            // 如果当前位置是'1'，左位置是1，就合并
            // 如果当前位置是'1'，上位置是1，就合并
            // 先将没有左边或上边的行和列考虑完（第0列和第0行）
            for (int i = 1; i < row; i++) {
                if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                    myUnionSet.union(i, 0, i - 1, 0);
                }
            }
            for (int j = 1; j < column; j++) {
                if (grid[0][j] == '1' && grid[0][j - 1] == '1') {
                    myUnionSet.union(0, j, 0, j - 1);
                }
            }
            // 再考虑有左边界和上边界的情况
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < column; j++) {
                    if (grid[i][j] == '1' && grid[i - 1][j] == '1') {
                        myUnionSet.union(i, j, i - 1, j);
                    }
                    if (grid[i][j] == '1' && grid[i][j - 1] == '1') {
                        myUnionSet.union(i, j, i, j - 1);
                    }
                }
            }
            return myUnionSet.sets;
        }
    }

    public static class MyUnionSet {
        public int[] parent; // 记录每个节点的父亲节点 parent[i]=k 表示i的父亲节点是k
        public int[] size; // 记录每个集合的大小，i为代表节点时size[i]才有意义
        public int[] help; // 辅助数组
        public int sets; // 集合数量（岛屿数量)
        public int column; // grid数组的列数
        public int row; // grid数组的行数

        public MyUnionSet(char[][] grid) {
            row = grid.length;
            column = grid[0].length;
            int length = row * column;
            parent = new int[length];
            size = new int[length];
            help = new int[length];
            sets = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (grid[i][j] == '1') {
                        int index = getIndex(i, j);
                        parent[index] = index;
                        size[index] = 1;
                        sets++;
                    }
                }
            }
        }

        // 得到当前集合的代表节点
        public int getNode(int index) {
            int i = 0;
            // 得到代表节点的过程中将过程中的节点记录到辅助数组中
            while (index != parent[index]) {
                help[i++] = index;
                index = parent[index];
            }
            // 跳出循环的时候i是++后的，help[i+1]位置没有元素
            i--;
            // 将过程中的节点的父节点赋值为代表节点，扁平化链条（下次过程中的节点再找代表节点就一步到位了）
            while (i >= 0) {
                parent[help[i--]] = index;
            }
            return index;
        }

        // 将两个节点所在的集合合并
        public void union(int i1, int j1, int i2, int j2) {
            int a = getIndex(i1, j1);
            int b = getIndex(i2, j2);
            // 得到两个集合的代表节点
            int headA = getNode(a);
            int headB = getNode(b);
            if (headA != headB) {
                int sizeA = size[headA];
                int sizeB = size[headB];
                if (sizeA > sizeB) {
                    parent[headB] = headA;
                    size[headA] = sizeA + sizeB;
                } else {
                    parent[headA] = headB;
                    size[headB] = sizeA + sizeB;
                }
                sets--;
            }
        }

        // 得到数组下标
        // grid 0行2列 数组下标：2
        // grid 2行1列 数组下标：grid列数*2+1
        // grid 2行2列 数组下标：grid列数*2+2
        public int getIndex(int i, int j) {
            return column * i + j;
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        UnionSetMethod method = new UnionSetMethod();
        int numIslands = method.numIslands(grid);
        System.out.println(numIslands);
    }
}
