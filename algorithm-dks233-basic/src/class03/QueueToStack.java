package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用队列结构实现栈结构
 *
 * @author dks233
 * @create 2022-03-28-21:36
 */
public class QueueToStack {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxValue = 2333;
        int limit = 233;
        for (int i = 0; i < testTimes; i++) {
            MyStack myStack = new MyStack();
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j < limit; j++) {
                if (myStack.isEmpty()) {
                    int value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
                    myStack.push(value);
                    stack.push(value);
                } else {
                    if (Math.random() < 0.5) {
                        int value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
                        myStack.push(value);
                        stack.push(value);
                    } else {
                        Integer popForMyStack = myStack.pop();
                        Integer popForStack = stack.pop();
                        if (!(popForMyStack.equals(popForStack))) {
                            System.out.println("测试出错");
                        }
                    }
                }
            }
        }
        System.out.println("测试成功");
    }

    public static class MyStack {
        private Queue<Integer> queue;
        private Queue<Integer> help;

        public MyStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(Integer value) {
            queue.offer(value);
        }

        public Integer pop() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            Integer poll = queue.poll();
            Queue<Integer> temp = new LinkedList<>();
            temp = queue;
            queue = help;
            help = temp;
            return poll;
        }

        public Integer peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            Integer peek = queue.peek();
            Queue<Integer> temp = new LinkedList<>();
            temp = queue;
            queue = help;
            help = temp;
            return peek;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }
}
