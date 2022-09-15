package binary;

import java.util.Scanner;

/**
 * 洛谷p1577：切绳子
 * 题目链接：https://www.luogu.com.cn/problem/P1577
 * 题目描述
 * 有N条绳子，它们的长度分别为 Li。如果从它们中切割出 K 条长度相同的绳子，这 K 条绳子每条最长能有多长？答案保留到小数点后 2 位(直接舍掉 2 位后的小数)。
 * 输入格式
 * 第一行两个整数 N 和 K，接下来 N 行，描述了每条绳子的长度 Li
 * 输出格式
 * 切割后每条绳子的最大长度。答案与标准答案误差不超过 0.01 或者相对误差不超过 1% 即可通过。
 *
 * @author dks233
 * @create 2022-09-15-10:57
 */
@SuppressWarnings("ALL")
public class CutARope {
    // 分析：找每条绳子需要切成多少长度，才能凑到k条
    // 分析：找的方式：二分法
    // 分析：都是浮点型数不好处理，将输入同时乘100，变成整数
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] ropes = new int[n];
        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ropes[i] = (int) (scanner.nextDouble() * 100);
            maxLen = Math.max(maxLen, ropes[i]);
        }
        int left = 0;
        int right = maxLen;
        int resultLen = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 不加这个过不了全部测试用例
            if (mid == 0) {
                break;
            }
            if (isCorrect(mid, k, ropes)) {
                resultLen = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.printf("%.2f", (double) resultLen / 100);
    }

    public static boolean isCorrect(int len, int k, int[] ropes) {
        int count = 0;
        for (int i = 0; i < ropes.length; i++) {
            count += ropes[i] / len;
        }
        return count >= k;
    }
}
