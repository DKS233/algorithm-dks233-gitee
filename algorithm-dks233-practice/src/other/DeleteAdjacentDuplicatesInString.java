package other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode1047. 删除字符串中的所有相邻重复项
 *
 * @author dks233
 * @create 2022-09-12-16:32
 */
@SuppressWarnings("ALL")
public class DeleteAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] str = s.toCharArray();
        char needChar = ' ';
        for (int i = 0; i < str.length; i++) {
            if (stack.isEmpty()) {
                stack.addLast(str[i]);
            } else if (stack.peekLast() == str[i]) {
                stack.pollLast();
            } else {
                stack.addLast(str[i]);
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pollFirst());
        }
        return builder.toString();
    }
}
