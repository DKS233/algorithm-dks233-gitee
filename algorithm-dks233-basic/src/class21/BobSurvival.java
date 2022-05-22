package class21;

/**
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 *
 * @author dks233
 * @create 2022-05-22-16:59
 */
public class BobSurvival {
    // 总共有几种走法：Math.pow(4,k)
    // 生还概率=生还走法/总走法
    public static double getProbabilityOne(int n, int m, int row, int col, int k) {
        return process(n, m, row, col, k) / Math.pow(4, k);
    }

    // (row,column)再走rest步还活着的走法
    public static int process(int n, int m, int row, int column, int rest) {
        if (row < 0 || row >= n || column < 0 || column >= m) {
            return 0;
        }
        // 没越界，而且没法走了，还活着
        if (rest == 0) {
            return 1;
        }
        int left = process(n, m, row, column - 1, rest - 1);
        int right = process(n, m, row, column + 1, rest - 1);
        int up = process(n, m, row - 1, column, rest - 1);
        int down = process(n, m, row + 1, column, rest - 1);
        return left + right + up + down;
    }

    // 暴力递归改动态规划
    // 可变参数：row,column,rest
    // dp[row][column][rest]表示从(row,column)再走rest步还活着的走法
    // 分析位置依赖
    public static double getProbabilityTwo(int n, int m, int row, int col, int k) {
        int[][][] dp = new int[n][m][k + 1];
        // 先填rest == 0的面
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0] = 1;
            }
        }
        // rest位置都依赖于rest-1位置
        // 所以要填rest，先把rest-1全部填了，rest循环要放最外面
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int left = pick(n, m, i, j - 1, rest - 1, dp);
                    int right = pick(n, m, i, j + 1, rest - 1, dp);
                    int up = pick(n, m, i - 1, j, rest - 1, dp);
                    int down = pick(n, m, i + 1, j, rest - 1, dp);
                    dp[i][j][rest] = left + right + up + down;
                }
            }
        }
        return dp[row][col][k] / Math.pow(4, k);
    }

    public static int pick(int n, int m, int row, int column, int rest, int[][][] dp) {
        if (row < 0 || row >= n || column < 0 || column >= m) {
            return 0;
        }
        return dp[row][column][rest];
    }

    public static void main(String[] args) {
        System.out.println(getProbabilityOne(50, 50, 6, 6, 10));
        System.out.println(getProbabilityTwo(50, 50, 6, 6, 10));
    }

}
