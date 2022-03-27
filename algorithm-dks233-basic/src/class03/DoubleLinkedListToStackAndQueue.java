package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 双链表实现栈和队列
 * 思路：双链表实现双端队列，在这基础上实现栈和队列
 *
 * @author dks233
 * @create 2022-03-26-21:13
 */
public class DoubleLinkedListToStackAndQueue {
    public static void main(String[] args) {
        int maxValue = 2333;
        int testTimes = 100000;
        int eachTestOperation = 100;
        for (int i = 0; i < testTimes; i++) {
            MyQueue<Integer> myQueue = new MyQueue<>();
            Queue<Integer> queue = new LinkedList<>();
            Stack<Integer> stack = new Stack<>();
            MyStack<Integer> myStack = new MyStack<>();
            for (int j = 0; j < eachTestOperation; j++) {
                if (myQueue.isEmpty()) {
                    Integer value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
                    myQueue.enQueue(value);
                    queue.offer(value);
                } else {
                    if (Math.random() < 0.5) {
                        Integer value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
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
            }
            if (myStack.isEmpty()) {
                Integer value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
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
                        System.out.println("栈测试出错");
                    }
                }
            }
        }
        System.out.println("队列和栈都测试成功了");
    }

    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public static class DoubleEndsQueue<T> {
        // 队列头结点
        public Node<T> head;
        // 队列尾结点
        public Node<T> tail;

        // 从队列头部添加结点
        public void addFromHead(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                head.last = node;
                node.next = head;
                head = node;
            }

        }

        // 从队列尾部添加结点
        public void addFromTail(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        // 从队列头部删除结点
        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> node = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                node.next = null;
            }
            return node.value;
        }

        // 从队列尾部删除结点
        public T popFromTail() {
            if (head == null) {
                return null;
            }
            Node<T> node = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                node.last = null;
            }
            return node.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    // 双端队列实现栈结构
    public static class MyStack<T> {
        private final DoubleEndsQueue<T> doubleEndsQueue;

        public MyStack() {
            doubleEndsQueue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            doubleEndsQueue.addFromHead(value);
        }

        public T pop() {
            return doubleEndsQueue.popFromHead();
        }

        public boolean isEmpty() {
            return doubleEndsQueue.isEmpty();
        }
    }

    // 双端队列实现队列结构（先进先出）
    public static class MyQueue<T> {
        private final DoubleEndsQueue<T> doubleEndsQueue;

        public MyQueue() {
            doubleEndsQueue = new DoubleEndsQueue<>();
        }

        public void enQueue(T value) {
            doubleEndsQueue.addFromHead(value);
        }

        public T deQueue() {
            return doubleEndsQueue.popFromTail();
        }

        public boolean isEmpty() {
            return doubleEndsQueue.isEmpty();
        }
    }


}
