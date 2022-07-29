package other;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.StreamSupport;

/**
 * 剑指offer专项突击版：剑指 Offer II 036. 后缀表达式
 *
 * @author dks233
 * @create 2022-07-29-16:51
 */
@SuppressWarnings("ALL")
public class PostfixExpression {
    // 栈：遇到表达式就弹出栈顶的两个元素，然后计算结果压入栈中
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int index = 0; index < tokens.length; index++) {
            if ("+".equals(tokens[index])) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(num2 + num1);
            } else if ("-".equals(tokens[index])) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(num2 - num1);
            } else if ("*".equals(tokens[index])) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(num2 * num1);
            } else if ("/".equals(tokens[index])) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                stack.push(num2 / num1);
            } else {
                stack.push(Integer.valueOf(tokens[index]));
            }
        }
        return stack.pop();
    }
}
