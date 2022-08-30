package other;

/**
 * leetcode641. 设计循环双端队列
 *
 * @author dks233
 * @create 2022-08-30-18:08
 */
@SuppressWarnings("ALL")
public class MyCircularDeque {
    int[] arr;
    int limit;
    int size;
    // [0......] [head,tail]
    int head;
    int tail;

    public MyCircularDeque(int k) {
        this.arr = new int[k];
        this.limit = k;
        this.size = 0;
        this.head = -1;
        this.tail = -1;
    }

    // 从队首添加元素
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        if (head == -1) {
            arr[0] = value;
            head = 0;
            tail = 0;
            size++;
        } else {
            int newHead = getPreIndex(head);
            arr[newHead] = value;
            head = newHead;
            size++;
        }
        return true;
    }

    // 从队尾添加元素
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        if (head == -1) {
            arr[0] = value;
            head = 0;
            tail = 0;
            size++;
        } else {
            int newTail = getNextIndex(tail);
            arr[newTail] = value;
            tail = newTail;
            size++;
        }
        return true;
    }

    // 从队首删除元素
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head = getNextIndex(head);
        size--;
        return true;
    }

    // 从队尾删除元素
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tail = getPreIndex(tail);
        size--;
        return true;
    }

    // 获取队首元素
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return arr[head];
    }

    // 获取队尾元素
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return arr[tail];
    }

    public int getNextIndex(int cur) {
        return cur == limit - 1 ? 0 : cur + 1;
    }

    public int getPreIndex(int cur) {
        return cur == 0 ? limit - 1 : cur - 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == limit;
    }
}
