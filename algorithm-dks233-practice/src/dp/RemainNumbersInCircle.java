package dp;

/**
 * 剑指 Offer第二版 62. 圆圈中最后剩下的数字
 * 参考文档：https://blog.csdn.net/u011500062/article/details/72855826
 * 参考文档：https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/huan-ge-jiao-du-ju-li-jie-jue-yue-se-fu-huan-by-as/
 *
 * @author dks233
 * @create 2022-06-19-21:49
 */
public class RemainNumbersInCircle {
    // 动态规划：dp[M][N]表示N个人报数，每报到M时杀掉那个人，最终的胜利者编号
    // dp[N][M]=(dp[N-1][M]+M)%N
    public static class MethodOne {
        // n=1时，dp[1][M]=0
        // initial=dp[1][m]=0
        public int lastRemaining(int n, int m) {
            int initial = 0;
            for (int i = 2; i <= n; i++) {
                initial = (initial + m) % i;
            }
            return initial;
        }
    }
}
