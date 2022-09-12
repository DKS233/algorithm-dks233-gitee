package other;

/**
 * leetcode299. 猜数字游戏
 *
 * @author dks233
 * @create 2022-09-12-15:16
 */
@SuppressWarnings("ALL")
public class NumberGuessingGame {
    // 奶牛=出现字符的公共次数之和-公牛
    public String getHint(String secret, String guess) {
        // 统计secret和guess中字符出现的次数
        int[] sCount = new int[10];
        int[] gCount = new int[10];
        // 统计数字和位置都猜对的字符数量
        int correct = 0;
        char[] sStr = secret.toCharArray();
        char[] gStr = guess.toCharArray();
        for (int i = 0; i < sStr.length; i++) {
            sCount[sStr[i] - '0']++;
            gCount[gStr[i] - '0']++;
            if (gStr[i] == sStr[i]) {
                correct++;
            }
        }
        // 公牛=correct
        // 奶牛=sCount和gCount出现频次相同的字符个数（取最小值）- 公牛
        int sum = 0;
        for (int i = 0; i < sCount.length; i++) {
            sum += Math.min(sCount[i], gCount[i]);
        }
        int bulls = correct;
        int cows = sum - correct;
        return bulls + "A" + cows + "B";
    }
}
