package other;

import java.util.Stack;

/**
 * leetcode946. 验证栈序列
 *
 * @author dks233
 * @create 2022-08-31-0:13
 */
@SuppressWarnings("ALL")
public class VerifyStackSequence {
    // 栈模拟压入弹出
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            // 栈顶元素和popped中j位置元素相同时，弹出栈顶元素
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
