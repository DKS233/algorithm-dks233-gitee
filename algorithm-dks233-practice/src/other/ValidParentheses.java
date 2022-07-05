package other;

import java.util.HashMap;
import java.util.Stack;

/**
 * leetcode20. 有效的括号
 *
 * @author dks233
 * @create 2022-07-05-16:06
 */
@SuppressWarnings("ALL")
public class ValidParentheses {
    // 用栈实现
    // 遇到左括号，压入栈中，遇到右括号弹出，如果是有效括号，弹出的应该是对应的左括号，遍历完整个字符串后栈应该是空的
    // 时间复杂度：O(N) 空间复杂度：O(N)
    public boolean isValid(String s) {
        char[] str = s.toCharArray();
        // 长度是奇数括号肯定不是两两对应的
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        // key：右括号   value：对应的左括号
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        for (int index = 0; index < str.length; index++) {
            // 如果当前遍历到的字符是右括号，弹出栈顶元素，比较右括号和弹出的左括号是否匹配
            // 如果栈为空或者弹出后左右括号不匹配，直接返回false，否则弹出左括号，继续遍历
            if (map.containsKey(str[index])) {
                if (stack.isEmpty() || stack.peek() != map.get(str[index])) {
                    return false;
                }
                stack.pop();
            }
            // 如果当前遍历到的字符是左括号，压入栈中
            else {
                stack.push(str[index]);
            }
        }
        // 如果是有效括号，遍历完整个字符串后栈应该是空的
        return stack.isEmpty();
    }
}
