package class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 加强堆（小根堆）
 * 关键：同时处理indexMap和heap
 *
 * @author dks233
 * @create 2022-04-09-15:36
 */
public class ReinforcedMinHeap<T> {
    private ArrayList<T> heap;
    private int heapSize;
    private HashMap<T, Integer> indexMap;
    private Comparator<T> comparator;

    public ReinforcedMinHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heapSize = 0;
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
    }

    public void push(T value) {
        heap.add(value);
        indexMap.put(value, heapSize++);
        heapInsert(heapSize - 1);
    }

    public T pop() {
        T ans = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(ans);
        heap.remove(--heapSize);
        heapify(0);
        return ans;
    }

    public void remove(T value) {
        T replace = heap.get(heapSize - 1);
        Integer index = indexMap.get(value);
        indexMap.remove(value);
        heap.remove(--heapSize);
        // index=replace说明最后一个位置上就是需要删除的元素
        if (value != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public void resign(T value) {
        heapInsert(indexMap.get(value));
        heapify(indexMap.get(value));
    }

    private void heapInsert(int index) {
        // index位置的值比父节点位置小，交换
        // index位于根节点处，交换
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int smallest = left + 1 < heapSize && comparator.compare(heap.get(left + 1),
                    heap.get(left)) < 0 ? left + 1 : left;
            smallest = comparator.compare(heap.get(index), heap.get(smallest)) < 0 ? index : smallest;
            if (smallest == index) {
                break;
            }
            swap(smallest, index);
            index = smallest;
            left = index * 2 + 1;
        }
    }

    private void swap(int a, int b) {
        T first = heap.get(a);
        T second = heap.get(b);
        heap.set(a, second);
        heap.set(b, first);
        indexMap.put(first, b);
        indexMap.put(second, a);
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean contains(T value) {
        return indexMap.containsKey(value);
    }

    public T peek() {
        return heap.get(0);
    }

    public ArrayList<T> getAllElements() {
        return new ArrayList<>(heap);
    }
}
