package other;

/**
 * leetcode622. 设计循环队列
 *
 * @author dks233
 * @create 2022-08-29-22:24
 */
@SuppressWarnings("ALL")
public class MyCircularQueue {
    // start:下一个元素从哪里拿
    int start;
    // end:下一个元素往哪里放
    int end;
    // 队列中元素数量
    int size;
    // 队列最大能存的元素数量
    int limit;
    // 循环数组
    int[] arr;

    // 分析：用循环数组实现队列
    // 分析：添加元素从队首插入，删除从队首删除
    public MyCircularQueue(int k) {
        this.limit = k;
        this.start = 0;
        this.end = 0;
        this.size = 0;
        this.arr = new int[limit];
    }

    // 插入元素，插入成功返回true
    public boolean enQueue(int value) {
        if (size == limit) {
            return false;
        }
        arr[end] = value;
        end = getPreIndex(end);
        size++;
        return true;
    }

    // 删除元素，删除成功返回true
    public boolean deQueue() {
        if (size == 0) {
            return false;
        }
        start = getPreIndex(start);
        size--;
        return true;
    }

    // 队首获取元素，队列为空，返回-1
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return arr[start];
    }

    // 队尾获取元素，队列为空，返回-1
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return arr[getNextIndex(end)];
    }

    // 循环队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 循环队列是否已满
    public boolean isFull() {
        return size == limit;
    }

    public int getPreIndex(int cur) {
        return cur == 0 ? limit - 1 : cur - 1;
    }

    public int getNextIndex(int cur) {
        return cur == limit - 1 ? 0 : cur + 1;
    }
}