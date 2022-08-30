package other;

/**
 * leetcode1422. 分割字符串的最大得分
 *
 * @author dks233
 * @create 2022-08-30-18:06
 */
@SuppressWarnings("ALL")
public class MaxScoreUserGroupingSplitString {
    public int maxScore(String s) {
        // count[0] 0出现的次数 count[1] 1出现的次数
        int[] count = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            count[str[i] - '0']++;
        }
        int maxScore = 0;
        // 遍历字符数组，根据遍历到的位置进行分割，分析分割后的得分
        // 记录[...index]范围内的0的数量
        int leftZeroCount = 0;
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == '0') {
                leftZeroCount += 1;
                count[0]--;
                maxScore = Math.max(leftZeroCount + count[1], maxScore);
            } else {
                count[1]--;
                maxScore = Math.max(leftZeroCount + count[1], maxScore);
            }
        }
        return maxScore;
    }
}
