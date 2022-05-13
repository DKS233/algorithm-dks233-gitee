package class07;

import java.util.Comparator;
import java.util.HashMap;

/**
 * 加强堆数组实现
 *
 * @author dks233
 * @create 2022-05-13-15:21
 */
public class ReinforcedHeap {
    public static class MyReinforcedHeap<T> {
        public T[] heap;
        public int heapSize;
        public int limit;
        public HashMap<T, Integer> indexMap;
        public Comparator<T> comparator;

        public void push(T value) {
            if (isFull()) {
                throw new RuntimeException("your heap is full");
            }
            indexMap.put(value, heapSize);
            heap[heapSize] = value;
            heapInsert(heapSize++);
        }

        public T pop() {
            if (isEmpty()) {
                throw new RuntimeException("your heap is empty");
            }
            T result = heap[0];
            swap(heap, 0, --heapSize);
            indexMap.remove(result);
            heapify(0);
            return result;
        }

        public void heapInsert(int index) {
            while (comparator.compare(heap[index], heap[(index - 1) / 2]) < 0) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void remove(T value) {
            Integer index = indexMap.get(value);
            T replace = heap[heapSize - 1];
            heapSize--;
            indexMap.remove(value);
            if (value != replace) {
                swap(heap, heapSize - 1, index);
                resign(replace);
            }
        }

        public void resign(T value) {
            heapInsert(indexMap.get(value));
            heapify(indexMap.get(value));
        }

        public void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int smallest = left + 1 < heapSize &&
                        comparator.compare(heap[left + 1], heap[left]) < 0 ? left + 1 : left;
                smallest = comparator.compare(heap[index], heap[smallest]) < 0 ? index : smallest;
                if (smallest == index) {
                    break;
                }
                swap(heap, index, smallest);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        public void swap(T[] heap, int a, int b) {
            Integer indexA = indexMap.get(heap[a]);
            Integer indexB = indexMap.get(heap[b]);
            indexMap.put(heap[a], indexB);
            indexMap.put(heap[b], indexA);
            T temp = heap[a];
            heap[a] = heap[b];
            heap[b] = temp;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }
    }
}
