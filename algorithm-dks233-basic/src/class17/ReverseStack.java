package class17;

import java.util.Stack;

/**
 * 给定一个栈，请逆序这个栈，不能申请额外的数据结构，只能使用递归函数
 *
 * @author dks233
 * @create 2022-05-16-15:54
 */
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
}
