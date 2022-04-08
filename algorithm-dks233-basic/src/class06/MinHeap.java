package class06;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 小根堆结构
 *
 * @author dks233
 * @create 2022-04-08-10:27
 */
public class MinHeap {

    public static void main(String[] args) {
        int testTimes = 100000;
        int limit = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            MyMinHeap myMinHeap = new MyMinHeap(limit);
            ComparatorMinHeap comparatorMinHeap = new ComparatorMinHeap(limit);
            for (int j = 0; j < limit; j++) {
                if (myMinHeap.isEmpty()) {
                    int number = ArrayUtils.randomNumber(maxValue);
                    myMinHeap.push(number);
                    comparatorMinHeap.push(number);
                } else {
                    if (Math.random() < 0.5) {
                        int number = ArrayUtils.randomNumber(maxValue);
                        myMinHeap.push(number);
                        comparatorMinHeap.push(number);
                    } else {
                        int popForMyMinHeap = myMinHeap.pop();
                        int popForComparatorMinHeap = comparatorMinHeap.pop();
                        if (popForMyMinHeap != popForComparatorMinHeap) {
                            isSuccess = false;
                            System.out.println(Arrays.toString(Arrays.copyOf(myMinHeap.heap,
                                    myMinHeap.heap.length)) + "---------->" + popForMyMinHeap);
                            System.out.println(Arrays.toString(Arrays.copyOf(comparatorMinHeap.heap,
                                    comparatorMinHeap.heap.length)) + "---------->" + popForComparatorMinHeap);
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

    public static class MyMinHeap {
        private int[] heap;
        private int limit;
        private int heapSize;

        public MyMinHeap(int limit) {
            this.limit = limit;
            this.heap = new int[limit];
            this.heapSize = 0;
        }

        private boolean isEmpty() {
            return this.heapSize == 0;
        }

        private boolean isFull() {
            return this.heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("your heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize);
            heapSize++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("your heap is empty");
            }
            int result = heap[0];
            // 将最小值和heapSize-1位置的数交换，然后heapSize--，将heapSize-1位置的数剔除出小根堆
            ArrayUtils.swap(heap, 0, --heapSize);
            // 重新调整小根堆
            heapify(heap, 0, heapSize);
            return result;
        }

        // index初始值为0，然后一步步下沉
        private void heapify(int[] heap, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                // 求出左右子节点的较小值，和index位置比较，较小值比index位置小就交换
                int smallest = left + 1 < heapSize && heap[left + 1] < heap[left] ? left + 1 : left;
                smallest = heap[index] < heap[smallest] ? index : smallest;
                // 如果index位置的值就是最小值，说明不需要下沉，跳出循环
                if (index == smallest) {
                    break;
                }
                // 到这儿说明还需要继续下沉
                ArrayUtils.swap(heap, index, smallest);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        // 新添加的元素初始位置为heapSize
        private void heapInsert(int[] heap, int index) {
            // index位置的值比父节点的值小，就交换
            // index位置的值不比父节点小或者index=0（根节点位置）跳出循环
            while (heap[index] < heap[(index - 1) / 2]) {
                ArrayUtils.swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }
    }

    public static class ComparatorMinHeap {
        private int[] heap;
        private int limit;
        private int heapSize;

        public ComparatorMinHeap(int limit) {
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
                throw new RuntimeException("your heap if full");
            }
            heap[heapSize++] = value;
        }

        public int pop() {
            int minIndex = 0;
            for (int i = 0; i < heapSize; i++) {
                if (heap[i] < heap[minIndex]) {
                    minIndex = i;
                }
            }
            int minValue = heap[minIndex];
            // 将最小值位置和heapSize-1位置交换，heapSize-1，将当前最小值的数（heapSize-1位置）剔除出小根堆
            ArrayUtils.swap(heap, minIndex, --heapSize);
            return minValue;
        }
    }
}
