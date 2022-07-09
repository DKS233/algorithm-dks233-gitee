package other;

import java.util.Stack;

/**
 * leetcode155. 最小栈
 *
 * @author dks233
 * @create 2022-07-09-10:30
 */
@SuppressWarnings("ALL")
public class MinStack {
    // 原始栈
    Stack<Integer> stackOne;
    // 最小元素栈，栈顶为原始栈中的最小元素
    Stack<Integer> stackTwo;

    public MinStack() {
        stackOne = new Stack<>();
        stackTwo = new Stack<>();
    }

    public void push(int val) {
        stackOne.push(val);
        if (stackTwo.isEmpty()) {
            stackTwo.push(val);
        } else {
            // 重复压入最小元素
            if (stackTwo.peek() < val) {
                stackTwo.push(stackTwo.peek());
            }
            // val是栈中最小元素，压入val
            else {
                stackTwo.push(val);
            }
        }
    }

    public void pop() {
        if (!stackOne.isEmpty()) {
            stackOne.pop();
            stackTwo.pop();
        }
    }

    public int top() {
        return stackOne.peek();
    }

    public int getMin() {
        return stackTwo.peek();
    }
}
