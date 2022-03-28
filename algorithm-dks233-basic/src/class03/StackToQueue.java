package class03;

import java.util.Stack;

/**
 * 用栈结构实现队列结构
 * 分析：准备两个栈，一个push栈，一个pop栈，push用于往pop里倒数据
 * 两个原则：push往pop栈里倒数据一定要一次性倒完；只要pop栈里空了才能继续倒数据
 * 分析：当前pop栈中有一条数据，要往push中加数据，数据可以正常添加，但是由于第二条原则限制，
 * 添加完数据后无法往pop栈中倒数据，只有pop中为空时才可以继续倒数据
 * 分析：两条原则保证了队列的先进先出的原则（pop栈里弹出的一定是先进的数据）
 *
 * @author dks233
 * @create 2022-03-28-21:35
 */
public class StackToQueue {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.offer(1);
        myQueue.offer(2);
        System.out.println(myQueue.poll()); // 1
        myQueue.offer(3);
        System.out.println(myQueue.poll()); // 2
        System.out.println(myQueue.poll()); // 3
    }

    public static class MyQueue {
        private final Stack<Integer> stackPush;
        private final Stack<Integer> stackPop;

        public MyQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        // 倒数据操作
        private void pushToPop() {
            // 只有pop栈里空了才能继续倒数据
            if (stackPop.isEmpty()) {
                // 倒数据一次性倒完
                while (!(stackPush.isEmpty())) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void offer(Integer value) {
            stackPush.push(value);
            // 每添加一次数据检查能不能倒数据
            pushToPop();
        }

        public Integer peek() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            pushToPop();
            return stackPop.peek();
        }

        public Integer poll() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("队列为空");
            }
            pushToPop();
            return stackPop.pop();
        }
    }

}
