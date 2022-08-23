package class20;

import java.util.Scanner;

/**
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回"马"从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 * 测试链接：https://www.nowcoder.com/questionTerminal/c45704a41617402fb5c34a1778bb2645
 *
 * @author dks233
 * @create 2022-05-20-20:13
 */
public class ChessHorse {
    // 暴力递归
    public static int getMethodOne(int x, int y, int k) {
        return process(0, 0, x, y, k);
    }

    // 遍历到(i,j)位置，还剩rest步，最终落在(x,y)上的方法数
    // x->[0,8] y->[0,9]
    public static int process(int i, int j, int x, int y, int rest) {
        if (i > 8 || i < 0 || j < 0 || j > 9) {
            return 0;
        }
        if (rest == 0) {
            return i == x && j == y ? 1 : 0;
        }
        // 不考虑越界有8种走法
        int count = 0;
        count += process(i - 1, j + 2, x, y, rest - 1);
        count += process(i - 2, j + 1, x, y, rest - 1);
        count += process(i - 1, j - 2, x, y, rest - 1);
        count += process(i - 2, j - 1, x, y, rest - 1);
        count += process(i + 1, j + 2, x, y, rest - 1);
        count += process(i + 2, j + 1, x, y, rest - 1);
        count += process(i + 1, j - 2, x, y, rest - 1);
        count += process(i + 2, j - 1, x, y, rest - 1);
        return count;
    }

    // 改动态规划
    // 三个可变参数：i,j,rest
    // 建三维数组dp
    // dp[i][j][rest] 到(i,j)位置，还剩rest步，最终落在(x,y)位置的方法数
    public static int getMethodTwo(int x, int y, int k) {
        // i [0,8]
        // j [0,9]
        // rest [0,k]
        int[][][] dp = new int[9][10][k + 1];
        dp[x][y][0] = 1;
        // 抄暴力递归过程
        for (int rest = 1; rest <= k; rest++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 10; j++) {
                    int count = 0;
                    count += process(i - 1, j + 2, dp, rest - 1);
                    count += process(i - 2, j + 1, dp, rest - 1);
                    count += process(i - 1, j - 2, dp, rest - 1);
                    count += process(i - 2, j - 1, dp, rest - 1);
                    count += process(i + 1, j + 2, dp, rest - 1);
                    count += process(i + 2, j + 1, dp, rest - 1);
                    count += process(i + 1, j - 2, dp, rest - 1);
                    count += process(i + 2, j - 1, dp, rest - 1);
                    dp[i][j][rest] = count;
                }
            }
        }
        return dp[0][0][k];
    }

    public static int process(int i, int j, int[][][] dp, int rest) {
        if (i > 8 || i < 0 || j < 0 || j > 9) {
            return 0;
        }
        return dp[i][j][rest];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int k = 10;
        System.out.println(getMethodOne(x, y, k));
        System.out.println(getMethodTwo(x, y, k));
    }

    // 测试网页提交代码
    public static class Main {
        // 横坐标9条线：x:[0,9] y:[0,8]
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int k = scanner.nextInt();
            // dp[rest][row][column] [row,column]再走rest步走到[x,y]的方法数
            int[][][] dp = new int[k + 1][9][10];
            dp[0][x][y] = 1;
            for (int rest = 1; rest <= k; rest++) {
                for (int row = 0; row < 9; row++) {
                    for (int column = 0; column < 10; column++) {
                        int result = 0;
                        result += getDp(rest - 1, row + 2, column + 1, dp);
                        result += getDp(rest - 1, row + 2, column - 1, dp);
                        result += getDp(rest - 1, row - 2, column + 1, dp);
                        result += getDp(rest - 1, row - 2, column - 1, dp);
                        result += getDp(rest - 1, row + 1, column + 2, dp);
                        result += getDp(rest - 1, row + 1, column - 2, dp);
                        result += getDp(rest - 1, row - 1, column + 2, dp);
                        result += getDp(rest - 1, row - 1, column - 2, dp);
                        dp[rest][row][column] = result;
                    }
                }
            }
            System.out.println(dp[k][0][0]);
        }

        public static int getDp(int rest, int row, int column, int[][][] dp) {
            if (row > 8 || row < 0 || column < 0 || column > 9) {
                return 0;
            }
            return dp[rest][row][column];
        }
    }
}
