package class18;

/**
 * 假设有排成一行的N个位置记为1~N，N一定大于或等于2
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数
 * 图解：机器人走路-动态规划.drawio
 *
 * @author dks233
 * @create 2022-05-17-9:29
 */
public class RobotWalk {
    /**
     * 图解：机器人走路-动态规划.drawio
     *
     * @param n 总共有几个位置
     * @param m 初始位置
     * @param k 走几步
     * @param p 最终位置
     * @return 走的方法有几种
     */
    public static int getCountOne(int n, int m, int k, int p) {
        if (n < 2 || m < 1 || m > n || k < 1 || p < 1 || p > n) {
            return -1;
        }
        return process(m, k, p, n);
    }

    /**
     * @param cur  当前所在位置
     * @param rest 还剩多少步没走
     * @param end  最终位置
     * @param n    总共有多少个位置
     * @return 当前路径最终如果走到end，返回1，当前路径如果走不到end，就返回0
     */
    public static int process(int cur, int rest, int end, int n) {
        // 当前路径走完，是否到达了end，如果到达end，当前路径有效
        if (rest == 0) {
            return cur == end ? 1 : 0;
        }
        // 如果cur在最左边位置上，只能往右边走
        if (cur == 1) {
            return process(cur + 1, rest - 1, end, n);
        }
        // 如果cur在最右边位置上，只能往左边走
        if (cur == n) {
            return process(cur - 1, rest - 1, end, n);
        }
        // 如果cur介于1和end之间，能往左边走，能往右边走
        return process(cur - 1, rest - 1, end, n) + process(cur + 1, rest - 1, end, n);
    }

    /**
     * 图解：机器人走路-动态规划.drawio
     * 缓存表大小确定：根据可变参数的变化范围
     *
     * @param n 总共有几个位置
     * @param m 初始位置
     * @param k 走几步
     * @param p 最终位置
     * @return 走的方法有几种
     */
    public static int getCountTwo(int n, int m, int k, int p) {
        if (n < 2 || m < 1 || m > n || k < 1 || p < 1 || p > n) {
            return -1;
        }
        // 准备一个缓存，存当前位置和剩余步数，表示为(cur,rest)
        // 表示从当前位置出发，在剩余步数后，可以到达end的路径数量
        // 如果(cur,rest)计算过了，后续使用直接从dp里取
        // 初始值设置为-1，说明未计算过，计算过后(cur,rest)存到缓存里
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        return process(m, k, p, n, dp);
    }

    /**
     * @param cur  当前所在位置
     * @param rest 还剩多少步没走
     * @param end  最终位置
     * @param n    总共有多少个位置
     * @param dp   (cur,rest)数组，(当前位置，还剩多少步数）
     * @return 当前路径最终如果走到end，返回1，当前路径如果走不到end，就返回0
     */
    public static int process(int cur, int rest, int end, int n, int[][] dp) {
        // (cur,rest)!=-1 (cur,rest)计算过，直接从缓存里取
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int result = 0;
        // 当前路径走完，是否到达了end，如果到达end，当前路径有效
        if (rest == 0) {
            result = cur == end ? 1 : 0;
        }
        // 如果cur在最左边位置上，只能往右边走
        else if (cur == 1) {
            result = process(cur + 1, rest - 1, end, n, dp);
        }
        // 如果cur在最右边位置上，只能往左边走
        else if (cur == n) {
            result = process(cur - 1, rest - 1, end, n, dp);
        }
        // 如果cur介于1和end之间，能往左边走，能往右边走
        else {
            result = process(cur - 1, rest - 1, end, n, dp) + process(cur + 1, rest - 1, end, n, dp);
        }
        // (cur,rest)存到缓存中
        dp[cur][rest] = result;
        return result;
    }

    /**
     * 建表(cur,rest)，表示cur位置出发再走rest步可以找到几条符合要求的路径
     * 图解：机器人走路-动态规划.drawio
     *
     * @param n 总共有几个位置
     * @param m 初始位置
     * @param k 走几步
     * @param p 最终位置
     * @return 走的方法有几种
     */
    public static int getCountThree(int n, int m, int k, int p) {
        if (n < 2 || m < 1 || m > n || k < 1 || p < 1 || p > n) {
            return -1;
        }
        // 初始都是0
        int[][] dp = new int[n + 1][k + 1];
        // 先填第0列
        dp[4][0] = 1;
        // 从第1列开始，1列1列填
        for (int rest = 1; rest <= k; rest++) {
            for (int cur = 1; cur <= n; cur++) {
                if (cur == 1) {
                    dp[cur][rest] = dp[cur + 1][rest - 1];
                } else if (cur == n) {
                    dp[cur][rest] = dp[cur - 1][rest - 1];
                } else {
                    dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
                }
            }
        }
        return dp[m][k];
    }

    public static void main(String[] args) {
        System.out.println(getCountOne(5, 2, 6, 4));
        System.out.println(getCountTwo(5, 2, 6, 4));
        System.out.println(getCountThree(5, 2, 6, 4));
    }
}
