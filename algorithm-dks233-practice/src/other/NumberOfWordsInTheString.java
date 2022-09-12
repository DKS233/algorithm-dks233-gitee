package other;

/**
 * leetcode434. 字符串中的单词数
 *
 * @author dks233
 * @create 2022-09-12-16:14
 */
@SuppressWarnings("ALL")
public class NumberOfWordsInTheString {
    public int countSegments(String s) {
        int count = 0;
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            while (right < s.length() && s.charAt(right) != ' ') {
                right++;
            }
            // 全是空格的情况
            if (right != left) {
                count++;
            }
            while (right < s.length() && s.charAt(right) == ' ') {
                right++;
            }
            left = right;
        }
        return count;
    }
}
