package other;

/**
 * leetcode557. 反转字符串中的单词 III
 *
 * @author dks233
 * @create 2022-07-09-16:24
 */
@SuppressWarnings("ALL")
public class ReverseWordsInString {
    public String reverseWords(String s) {
        char[] str = s.toCharArray();
        // 记录每个单词的起始位置
        int left = 0;
        // 记录每个单词的结束位置
        int right = 0;
        while (left < str.length) {
            // 单词的起始位置是left
            // 找到单词的结束位置
            right = left;
            // 此时的right位置是空格，该单词的范围是[left,right-1]
            while (right < str.length && str[right] != ' ') {
                right++;
            }
            // 反转单词
            swap(str, left, right - 1);
            // 下一个单词的开始位置是right+1
            left = right + 1;
        }
        return String.valueOf(str);
    }

    public void swap(char[] str, int left, int right) {
        int leftIndex = left;
        int rightIndex = right;
        while (leftIndex < rightIndex) {
            char temp = str[leftIndex];
            str[leftIndex] = str[rightIndex];
            str[rightIndex] = temp;
            leftIndex++;
            rightIndex--;
        }
    }
}
