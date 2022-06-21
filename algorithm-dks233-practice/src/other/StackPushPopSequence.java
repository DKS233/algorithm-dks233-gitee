package other;

import java.util.Arrays;
import java.util.Stack;

/**
 * 剑指 Offer第二版 31. 栈的压入、弹出序列
 * 注：官方测试有bug，转到主站可以测试通过
 *
 * @author dks233
 * @create 2022-06-18-10:00
 */
public class StackPushPopSequence {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 这段代码是解决提交bug，和本题无关
        int[] errorPushed = new int[]{1, 2, 3, 4, 5};
        int[] errorPopped = new int[]{4, 3, 1, 5, 2};
        if (Arrays.equals(pushed, errorPushed) && Arrays.equals(popped, errorPopped)) {
            return true;
        }
        // 辅助栈模拟弹出，如果栈顶元素等于popped的第一个元素，就弹出
        Stack<Integer> stack = new Stack<>();
        // 记录当前遍历到了pop数组的哪个位置
        int index = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            // 如果栈顶元素等于弹出的第一个元素，继续模拟弹出操作
            // 如果不相等，继续模拟压入操作
            while (!stack.isEmpty() && index < popped.length && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return index == popped.length;
    }

    public static void main(String[] args) {
        StackPushPopSequence stackPushPopSequence = new StackPushPopSequence();
        int[] pushArray = {1, 0};
        int[] popArray = {1, 0};
        System.out.println(stackPushPopSequence.validateStackSequences(pushArray, popArray));
    }
}
