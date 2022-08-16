package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 循环数组实现队列
 * 分析：begin表示元素下次从哪里拿，end表示元素下次往哪里放，size表示当前数组长度，size满了就不允许添加
 * 循环数组实现栈
 * 分析：begin表示元素下次从哪里拿，end表示元素下次往哪里放，size表示当前数组长度，size满了就不允许添加
 *
 * @author dks233
 * @create 2022-03-26-21:30
 */
@SuppressWarnings("ALL")
public class ArrayToStachAndQueue {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxValue = 2333;
        int limit = 233;
        for (int i = 0; i < testTimes; i++) {
            MyStack myStack = new MyStack(limit);
            Stack<Integer> stack = new Stack<>();
            MyQueue myQueue = new MyQueue(limit);
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < limit; j++) {
                if (myQueue.isEmpty()) {
                    int value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
                    myQueue.enQueue(value);
                    queue.offer(value);
                } else {
                    if (Math.random() < 0.5) {
                        int value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
                        myQueue.enQueue(value);
                        queue.offer(value);
                    } else {
                        Integer deQueueForMyQueue = myQueue.deQueue();
                        Integer deQueueForQueue = queue.poll();
                        if (!(deQueueForMyQueue.equals(deQueueForQueue))) {
                            System.out.println("队列测试出错");
                        }
                    }
                }
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
                        Integer popForStack = stack.pop();
                        Integer popForMyStack = myStack.pop();
                        if (!(popForMyStack.equals(popForStack))) {
                            System.out.println("栈测试失败");
                        }
                    }
                }
            }
        }
        System.out.println("栈和队列全部测试成功");
    }

    public static class MyQueue {
        private final int[] arr;
        private final int limit;
        private int size;
        private int begin; // 元素下次从哪里拿
        private int end; // 元素下次往哪里放

        public MyQueue(int limit) {
            this.limit = limit;
            this.arr = new int[limit];
            this.size = 0;
            this.begin = 0;
            this.end = 0;
        }

        // 给队列添加元素
        public void enQueue(int value) {
            if (size == limit) {
                throw new RuntimeException("当前队列已满，无法添加元素");
            }
            arr[end] = value;
            end = nextIndex(end);
            size++;
        }

        // 从队列删除元素
        public int deQueue() {
            if (size == 0) {
                throw new RuntimeException("当前队列为空，无法删除元素");
            }
            int value = arr[begin];
            begin = nextIndex(begin);
            size--;
            return value;
        }

        // 当前位置是cur，返回下一个位置
        public int nextIndex(int cur) {
            return cur < arr.length - 1 ? cur + 1 : 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static class MyStack {
        private int[] arr;
        private int limit;
        private int size;
        private int begin; // 元素下次从哪里拿
        private int end; // 元素下次往哪里放

        public MyStack(int limit) {
            this.limit = limit;
            this.arr = new int[limit];
            this.size = 0;
            this.begin = 0;
            this.end = 0;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("当前栈已满，无法添加元素");
            }
            size++;
            arr[end] = value;
            begin = end;
            end = nextIndex(end);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("当前栈为空，无法删除元素");
            }
            size--;
            int value = arr[begin];
            begin = preIndex(begin);
            end = preIndex(end);
            return value;
        }

        public int nextIndex(int cur) {
            return cur < arr.length - 1 ? cur + 1 : 0;
        }

        public int preIndex(int cur) {
            return cur > 0 ? cur - 1 : arr.length - 1;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}
