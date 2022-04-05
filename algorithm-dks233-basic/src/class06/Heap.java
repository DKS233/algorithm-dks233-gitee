package class06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 堆结构
 * 是完全二叉树
 * 小根堆：父节点的值小于等于子节点的值（根节点的值最小）
 * 大根堆：父节点的值大于等于子节点的值（根节点的值最小）
 * 节点位置为i，左孩子节点位置为2i+1，右孩子节点位置为2i+2，父节点位置为(i-1)/2
 * 操作：heapInsert和heapify
 *
 * @author dks233
 * @create 2022-04-04-22:31
 */
public class Heap {
    public static void main(String[] args) {
        // 默认是小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 加入比较器，变成大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MyComparator());
        minHeap.add(1);
        minHeap.add(7);
        System.out.println(minHeap.peek()); // 1
        maxHeap.add(1);
        maxHeap.add(7);
        System.out.println(maxHeap.peek()); // 7
        int testTimes = 100000;
        int limit = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            MyMaxHeap myMaxHeap = new MyMaxHeap(limit);
            ComparatorHeap comparatorHeap = new ComparatorHeap(limit);
            for (int j = 0; j < limit; j++) {
                if (myMaxHeap.isEmpty()) {
                    int value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
                    myMaxHeap.push(value);
                    comparatorHeap.push(value);
                } else {
                    if (Math.random() < 0.5) {
                        int value = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
                        myMaxHeap.push(value);
                        comparatorHeap.push(value);
                    } else {
                        int popMyHeap = myMaxHeap.pop();
                        int popComparator = comparatorHeap.pop();
                        if (popComparator != popMyHeap) {
                            isSuccess = false;
                            System.out.println(Arrays.toString(myMaxHeap.heap) + "--->" + popMyHeap);
                            System.out.println(Arrays.toString(comparatorHeap.heap) + "--->" + popComparator);
                            break;
                        }
                    }
                }
            }
            if (!isSuccess) {
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static class MyMaxHeap {
        private final int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            this.limit = limit;
            this.heap = new int[limit];
            this.heapSize = 0;
        }

        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            // 新添加进来的数上移过程
            heapInsert(heap, heapSize);
            heapSize++;
        }

        // 返回大根堆中最大的数，然后调整大根堆
        public int pop() {
            if (heapSize == 0) {
                throw new RuntimeException("heap is empty");
            }
            int result = heap[0];
            // 将0位置的数和heapSize-1位置的数做交换，然后将heapSize-1位置的数剔除出大根堆
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return result;
        }

        // 需要下沉的数初始位置为0
        private void heapify(int[] heap, int index, int heapSize) {
            // 左子节点的位置
            int left = 2 * index + 1;
            // 求出左右子节点的最大值，和index位置做比较，如果index位置的数小，下沉
            while (left < heapSize) {
                int largest = left + 1 < heapSize && heap[left] < heap[left + 1] ? left + 1 : left;
                largest = heap[index] > heap[largest] ? index : largest;
                // 当前index位置比子节点的较大值都大
                if (index == largest) {
                    break;
                }
                swap(heap, index, largest);
                index = largest;
                left = 2 * index + 1;
            }
        }

        // 新添加进来的数初始位置为heapSize
        private void heapInsert(int[] heap, int index) {
            // 当前index位置处的数比父节点处的数大，需要上移
            // 当前index位置为0，没有父节点，heap[0]=heap[0]，跳出循环
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private static void swap(int[] arr, int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }

        public boolean isEmpty() {
            return this.heapSize == 0;
        }

        public boolean isFull() {
            return this.heapSize == this.limit;
        }
    }

    public static class ComparatorHeap {
        private final int[] heap;
        private final int limit;
        private int heapSize;

        public ComparatorHeap(int limit) {
            this.limit = limit;
            this.heap = new int[limit];
            this.heapSize = 0;
        }

        public boolean isEmpty() {
            return this.heapSize == 0;
        }

        public boolean isFull() {
            return this.heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("heap is full");
            }
            heap[heapSize++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 0; i < heapSize; i++) {
                if (heap[i] > heap[maxIndex]) {
                    maxIndex = i;
                }
            }
            int result = heap[maxIndex];
            // 将最大值位置的数用heapSize-1位置的数替代，然后将heap-1位置的数剔除出大根堆
            heap[maxIndex] = heap[--heapSize];
            return result;
        }
    }
}
