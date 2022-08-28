package week308;

import java.util.Stack;

/**
 * leetcode6161. 从字符串中移除星号
 *
 * @author dks233
 * @create 2022-08-28-10:30
 */
@SuppressWarnings("ALL")
public class Two {
    // 栈模拟
    public String removeStars(String s) {
        char[] str = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int index = 0; index < str.length; index++) {
            if (stack.isEmpty()) {
                stack.push(str[index]);
            } else {
                if (str[index] == '*') {
                    stack.pop();
                } else {
                    stack.push(str[index]);
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        Two two = new Two();
        System.out.println(two.removeStars("leet**cod*e"));
    }
}
