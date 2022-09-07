package other;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode1592. 重新排列单词间的空格
 *
 * @author dks233
 * @create 2022-09-07-8:09
 */
@SuppressWarnings("ALL")
public class RearrangeSpacesBetweenWords {
    public String reorderSpaces(String text) {
        List<String> list = new ArrayList<>();
        // list中所有字符串的字符数量和
        int charCount = 0;
        int len = text.length();
        int left = 0;
        int right = 0;
        while (right < len) {
            // 跳过空格
            while (right < len && text.charAt(right) == ' ') {
                right++;
            }
            if (right == len) {
                break;
            }
            left = right;
            // 遍历单词，添加到列表中
            while (right < len && text.charAt(right) != ' ') {
                right++;
            }
            list.add(text.substring(left, right));
            charCount += right - left;
        }
        if (list.size() == 1) {
            StringBuilder str = new StringBuilder(list.get(0));
            for (int i = str.length(); i < len; i++) {
                str.append(" ");
            }
            return str.toString();
        }
        // 每个单词后面的空格数
        int spaceCount = (len - charCount) / (list.size() - 1);
        // 最后需要添加的空格数量
        int endSpaceCount = (len - charCount) % (list.size() - 1);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            builder.append(list.get(i));
            for (int j = 0; j < spaceCount; j++) {
                builder.append(" ");
            }
        }
        builder.append(list.get(list.size() - 1));
        for (int i = 0; i < endSpaceCount; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        RearrangeSpacesBetweenWords thisClass = new RearrangeSpacesBetweenWords();
        System.out.println(thisClass.reorderSpaces("  this   is  a sentence "));
    }
}
