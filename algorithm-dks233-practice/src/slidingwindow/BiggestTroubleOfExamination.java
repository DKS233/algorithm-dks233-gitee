package slidingwindow;

/**
 * leetcode2024. 考试的最大困扰度
 *
 * @author dks233
 * @create 2022-09-12-13:55
 */
@SuppressWarnings("ALL")
public class BiggestTroubleOfExamination {
    // 不超过k次操作，最大连续T/F的数目
    // 分析：求区间内出现T/F的次数不超过k次的最大区间，区间中的元素数量即为所求
    public int maxConsecutiveAnswers(String answerKey, int k) {
        char[] str = answerKey.toCharArray();
        int getT = process(str, 'T', k);
        int getF = process(str, 'F', k);
        return Math.max(getT, getF);
    }

    // 出现c的次数不超过k次的最大区间
    public int process(char[] str, char c, int k) {
        // 最大区间长度
        int result = 0;
        // 统计c出现的次数
        int count = 0;
        int left = 0;
        int right = 0;
        while (right < str.length) {
            count += str[right] == c ? 1 : 0;
            while (count > k) {
                count -= str[left] == c ? 1 : 0;
                left++;
            }
            result = Math.max(result, right - left + 1);
            right++;
        }
        return result;
    }
}
