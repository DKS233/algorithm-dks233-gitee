package other;

/**
 * leetcode58. 最后一个单词的长度
 *
 * @author dks233
 * @create 2022-07-06-16:47
 */
public class LengthOfTheLastWord {
    public int lengthOfLastWord(String s) {
        // 从右往左遍历，找到最后一个非空格字符，然后从这个字符开始，到下一个空格之间的单词即为最后一个单词
        char[] str = s.toCharArray();
        int index = str.length - 1;
        for (; index >= 0; index--) {
            if (str[index] != ' ') {
                break;
            }
        }
        int length = 0;
        for (; index >= 0; index--) {
            if (str[index] != ' ') {
                length++;
            } else {
                break;
            }
        }
        return length;
    }
}
