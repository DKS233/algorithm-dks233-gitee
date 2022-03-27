package class03;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能。
 * (1) push/pop/getMin操作的时间复杂度都是O(1)
 * (2) 设计的栈类型可以使用现成的栈结构
 * 分析：准备两个栈，数据栈和最小栈
 * 当前数比最小栈栈顶小，当前数放入最小栈
 * 当前数不比最小栈栈顶小，重复压入最小栈栈顶（也可以选择不放，减小空间）
 * 同步压入，同步弹出，最小值就是此时最小栈的栈顶
 *
 * @author dks233
 * @create 2022-03-27-21:56
 */
public class GetMinStack {
    public static class MyStackOne {
        private final Stack<Integer> stack;
        private final Stack<Integer> minStack;

        public MyStackOne() {
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(Integer value) {
            if (minStack.isEmpty()) {
                minStack.push(value);
            } else if (value < getMin()) {
                minStack.push(value);
            }
            stack.push(value);
        }

        public Integer pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("栈为空，无法弹出元素");
            }
            Integer pop = stack.pop();
            if (pop.equals(getMin())) {
                minStack.pop();
            }
            return pop;
        }

        public Integer getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("当前栈为空，无法获取最小值");
            }
            return minStack.peek();
        }
    }

    public static class MyStackTwo {
        private final Stack<Integer> stack;
        private final Stack<Integer> minStack;

        public MyStackTwo() {
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(Integer value) {
            if (minStack.isEmpty()) {
                minStack.push(value);
            } else if (value < getMin()) {
                minStack.push(value);
            } else {
                Integer min = getMin();
                minStack.push(min);
            }
            stack.push(value);
        }

        public Integer pop() {
            if (stack.isEmpty()) {
                throw new RuntimeException("栈为空，无法弹出元素");
            }
            minStack.pop();
            return stack.pop();
        }

        public Integer getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("当前栈元素为空，无法获取最小值");
            }
            return minStack.peek();
        }
    }
}
