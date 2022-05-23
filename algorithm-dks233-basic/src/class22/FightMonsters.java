package class22;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 *
 * @author dks233
 * @create 2022-05-23-9:16
 */
@SuppressWarnings("ALL")
public class FightMonsters {
    // k次打击后，砍死怪兽的概率 = k次打击后怪物被砍死的次数/总可能数
    public static double getProbabilityOne(int n, int m, int k) {
        if (n < 1 || m < 1 || k < 1) {
            return 0;
        }
        return process(n, m, k) / (Math.pow(m + 1, k));
    }

    /**
     * 总可能性数：全部走到countRest，Math.pow(m+1,k)
     * 原因：每一次砍血量的变化范围都是[0,m]，不管剩余血量，继续砍，到最后一步统计血量小于等于0的可能性数量
     * 剪枝：次数没用完，怪兽被砍死，统计可能性数量，Math.pow(m+1,countRest)
     * 原因：剩余血量小于等于0，已经被砍死，剩下的次数随便砍，反正到最后一步血量肯定小于等于0，知道结果，直接统计
     *
     * @param bloodRest 剩余血量
     * @param m         每次打击流失的血量范围 [0,m]
     * @param countRest 剩余砍击次数
     * @return 再打击rest次后怪兽被砍死的可能数
     */
    public static long process(int bloodRest, int m, int countRest) {
        // 次数用完了，看怪兽砍没砍死
        if (countRest == 0) {
            return bloodRest <= 0 ? 1 : 0;
        }
        // 次数没用完，怪兽被砍死，统计可能
        if (bloodRest <= 0) {
            return (long) Math.pow(m + 1, countRest);
        }
        long ans = 0L;
        for (int i = 0; i <= m; i++) {
            ans += process(bloodRest - i, m, countRest - 1);
        }
        return ans;
    }

    /**
     * 暴力递归改动态规划
     * 分析可变参数范围：bloodRest:[0,n]   countRest:[0,k]
     * 建二维表 dp[bloodRest][countRest] 表示countRest后把bloodRest血量怪兽砍死的次数
     *
     * @param n 怪兽初始血量
     * @param m 每次流失血量 [0,m]
     * @param k 砍击次数
     * @return k次打击后，英雄把怪兽砍死的概率
     */
    public static double getProbabilityTwo(int n, int m, int k) {
        if (n < 1 || m < 1 || k < 1) {
            return 0;
        }
        // dp[bloodRest][countRest]
        long[][] dp = new long[n + 1][k + 1];
        // 填dp[0][0]位置
        dp[0][0] = 1;
        // 填剩余位置，抄暴力递归
        // 每个countRest都依赖于countRest-1，所以求countRest得把所有countRest-1对应位置全部求出来
        for (int countRest = 1; countRest <= k; countRest++) {
            dp[0][countRest] = (long) Math.pow(m + 1, countRest);
            for (int bloodRest = 1; bloodRest <= n; bloodRest++) {
                long ans = 0L;
                for (int i = 0; i <= m; i++) {
                    if (bloodRest - i <= 0) {
                        ans += (long) Math.pow(m + 1, countRest - 1);
                    } else {
                        ans += dp[bloodRest - i][countRest - 1];
                    }
                }
                dp[bloodRest][countRest] = ans;
            }
        }
        return dp[n][k] / (Math.pow(m + 1, k));
    }

    // 动态规划优化
    // 每个位置依赖的位置数量是不确定的，有枚举行为，分析临近位置，看能不能把枚举行为替换掉
    public static double getProbabilityThree(int n, int m, int k) {
        if (n < 1 || m < 1 || k < 1) {
            return 0;
        }
        // dp[bloodRest][countRest]
        long[][] dp = new long[n + 1][k + 1];
        // 填dp[0][0]位置
        dp[0][0] = 1;
        // 填剩余位置，抄暴力递归
        // 每个countRest都依赖于countRest-1，所以求countRest得把所有countRest-1对应位置全部求出来
        for (int countRest = 1; countRest <= k; countRest++) {
            dp[0][countRest] = (long) Math.pow(m + 1, countRest);
            for (int bloodRest = 1; bloodRest <= n; bloodRest++) {
                // 基本优化，然后考虑边界条件（越界）
                // 基本优化：dp[bloodRest][countRest]=dp[bloodRest-1][countRest]
                // -dp[bloodRest-1-m][countRest-1]+dp[bloodRest][countRest-1]
                int ans = 0;
                ans += dp[bloodRest - 1][countRest];
                if (bloodRest - m - 1 >= 0) {
                    ans -= dp[bloodRest - m - 1][countRest - 1];
                } else {
                    ans -= Math.pow(m + 1, countRest - 1);
                }
                ans += dp[bloodRest][countRest - 1];
                dp[bloodRest][countRest] = ans;
            }
        }
        return dp[n][k] / (Math.pow(m + 1, k));
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int nMax = 10;
        int mMax = 10;
        int kMax = 10;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * nMax);
            int m = (int) (Math.random() * mMax);
            int k = (int) (Math.random() * kMax);
            double one = getProbabilityOne(n, m, k);
            double two = getProbabilityTwo(n, m, k);
            double three = getProbabilityThree(n, m, k);
            if (one != two || one != three) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
