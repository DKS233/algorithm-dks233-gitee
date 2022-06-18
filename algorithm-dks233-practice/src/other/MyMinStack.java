package other;

import java.util.Stack;

/**
 * 剑指 Offer第二版 30. 包含min函数的栈
 *
 * @author dks233
 * @create 2022-06-18-9:39
 */
public class MyMinStack {
    public static class MinStack {
        Stack<Integer> stackOne;
        Stack<Integer> stackTwo;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stackOne = new Stack<>();
            stackTwo = new Stack<>();
        }

        public void push(int x) {
            stackOne.push(x);
            if (stackTwo.size() == 0 || x <= stackTwo.peek()) {
                stackTwo.push(x);
            }
        }

        public void pop() {
            int pop = stackOne.pop();
            if (pop == stackTwo.peek()) {
                stackTwo.pop();
            }
        }

        public int top() {
            return stackOne.peek();
        }

        public int min() {
            return stackTwo.peek();
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.peek());
        System.out.println(stack.peek());
        for (Integer i : stack) {
            System.out.println(i);
        }
        stack.push(stack.peek());
        System.out.println("=========");
        for (Integer i : stack) {
            System.out.println(i);
        }
    }
}
