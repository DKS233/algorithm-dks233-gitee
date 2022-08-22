package class17;

import java.util.Stack;

/**
 * 给定一个栈，请逆序这个栈，不能申请额外的数据结构，只能使用递归函数
 * 测试网址：https://www.nowcoder.com/questionTerminal/ba7d7f5d1edf4d1690d66e12e951f6ea#:~:text=%E6%80%9D%E6%83%B3%EF%BC%9A%E6%8A%8A%E6%A0%88%E7%9A%84%E9%80%86%E5%BA%8F,%E4%B8%80%E4%B8%AA%E5%87%BD%E6%95%B0%E5%AE%9E%E7%8E%B0%EF%BC%89%E3%80%82
 *
 * @author dks233
 * @create 2022-05-16-15:54
 */
@SuppressWarnings("ALL")
public class ReverseStack {
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    // 栈底元素移除掉
    // 上面的元素盖下来
    // 返回移除掉的栈底元素
    public static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static Stack<Integer> comparator(Stack<Integer> stack) {
        Stack<Integer> reverseStack = new Stack<>();
        while (!stack.isEmpty()) {
            reverseStack.push(stack.pop());
        }
        return reverseStack;
    }

    public static boolean isEquals(Stack<Integer> stackOne, Stack<Integer> stackTwo) {
        while (!stackOne.isEmpty()) {
            if (!stackOne.pop().equals(stackTwo.pop())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Stack<Integer> stack = new Stack<>();
            Stack<Integer> copyStack = new Stack<>();
            for (int j = 0; j < maxLen; j++) {
                if (stack.isEmpty()) {
                    int value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
                    stack.push(value);
                    copyStack.push(value);
                } else {
                    if (Math.random() < 0.5) {
                        stack.pop();
                        copyStack.pop();
                    } else {
                        int value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
                        stack.push(value);
                        copyStack.push(value);
                    }
                }
            }
            reverse(stack);
            Stack<Integer> comparator = comparator(copyStack);
            if (!isEquals(stack, comparator)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    // 测试网址通过的代码
    public static class TestCorrect {
        public int[] reverseStackRecursively(int[] stack, int top) {
            Stack<Integer> myStack = new Stack<>();
            for (int index = stack.length - 1; index >= 0; index--) {
                myStack.push(stack[index]);
            }
            reverse(myStack);
            for (int index = 0; index < stack.length; index++) {
                stack[index] = myStack.pop();
            }
            return stack;
        }

        // 逆序栈
        // 不断删除栈底元素，上面元素盖下来
        public void reverse(Stack<Integer> stack) {
            if (stack.isEmpty()) {
                return;
            }
            int result = f(stack);
            reverse(stack);
            stack.push(result);
        }

        // 栈底元素删除掉，然后上面的元素盖下来
        public int f(Stack<Integer> stack) {
            int popElement = stack.pop();
            if (stack.isEmpty()) {
                return popElement;
            } else {
                int last = f(stack);
                stack.push(popElement);
                return last;
            }
        }
    }
}
