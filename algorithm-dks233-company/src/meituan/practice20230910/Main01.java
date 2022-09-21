package meituan.practice20230910;

import java.util.Scanner;

/**
 * 美团9月10号笔试题目1
 * 快到编程竞赛了，小美决定找朋友一起训练，小美拉了一份题单，共有n道题，而且题目难度都差不多，
 * 小美目前平均一小时写x道题，而小美的朋友平均一小时写y道题（即小美写一题需要1/x小时，小美的朋友写一题需要1/y小时）。
 * 小美按1、2、…、n的顺序写题，而她的朋友按n、n-1、…、1的顺序写题。她们决定了赢的规则：谁先写完编号为k的题，谁就获得胜利。
 * 那么，假设两人按不变的速度写题，且每写完一题立刻去写下一题，小美能获胜吗？
 * <p>
 * 输入t表示接下来有t轮数据
 * 接下来每行 n, x, y, k
 * 其中n表示作业的份数
 * x表示小美每小时做x份作业
 * y表示小团每小时做y份作业
 * k表示小美需要从第一份作业做到第k份，小团需要从第n份作业做到第k份作业
 * 如果小美快，输出"Win"，平局"Tie"，输掉"Lose"
 *
 * @author dks233
 * @create 2022-09-10-15:57
 */
@SuppressWarnings("ALL")
public class Main01 {
    // 分析：浮点数计算分别需要多长时间
    // 改进：未避免浮点数误差，可以用乘法
    // result=k*y-(n-k+1)*x
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int line = 0; line < t; line++) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int k = scanner.nextInt();
            double left = k / (double) x;
            double right = (n - k + 1) / (double) y;
            if (left == right) {
                System.out.println("Tie");
            } else if (left < right) {
                System.out.println("Win");
            } else {
                System.out.println("Lose");
            }
        }
    }
}
