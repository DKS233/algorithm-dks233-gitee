package class06;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author dks233
 * @create 2022-04-05-16:47
 */
public class HeapSort {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArrOne = randomArr(maxLen, maxValue);
            int[] randomArrTwo = randomArr(maxLen, maxValue);
            int[] copyArrOne = copyArr(randomArrOne);
            int[] copyArrTwo = copyArr(randomArrTwo);
            heapSortOne(randomArrOne);
            heapSortTwo(randomArrTwo);
            Arrays.sort(copyArrOne);
            Arrays.sort(copyArrTwo);
            if (!isEquals(randomArrOne, copyArrOne)) {
                isSuccess = false;
                break;
            }
            if (!isEquals(randomArrTwo, copyArrTwo)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    /**
     * 从上往下建堆
     * 给一个数组，假设一个一个数给你，一步步heapInsert将整个数组调成大根堆，此时0位置就是最大值，将n-1位置和0位置的数交换，
     * 堆大小减1，0位置做heapify，此时0~n-2是大根堆，将0位置和n-2位置的数交换，堆大小减小1...
     *
     * @param arr 需要排序的数组
     */
    public static void heapSortOne(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 先将整个数组调成大根堆
        // O(NlogN)
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        // O(NlogN)
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    /**
     * 与方法1的区别：heapInsert为O(N)
     * 整个数组转换成大根堆时，从下往上建堆，从最后一层依次往上heapify
     *
     * @param arr 需要排序的数组
     */
    public static void heapSortTwo(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        // O(NlogN)
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // 新添加的数初始位置：index
    private static void heapInsert(int[] heap, int index) {
        // index=0时，跳出循环
        // 父节点值大于当前节点的值，跳出循环
        while (heap[index] > heap[(index - 1) / 2]) {
            swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 需要下沉的数初始索引位置：index
    private static void heapify(int[] heap, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 如果index位置的数比左右子节点的较大值都大，就不交换，否则要和子节点的较大值交换
            int largest = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;
            largest = heap[index] > heap[largest] ? index : largest;
            if (index == largest) {
                break;
            }
            swap(heap, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] randomArr = new int[len];
        for (int element : randomArr) {
            element = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }

    private static int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, arr.length);
        return copyArr;
    }

    private static boolean isEquals(int[] randomArr, int[] copyArr) {
        if (randomArr == null && copyArr != null || randomArr != null && copyArr == null) {
            return false;
        }
        if (randomArr == null) {
            return false;
        }
        if (randomArr.length != copyArr.length) {
            return false;
        }
        for (int i = 0; i < randomArr.length; i++) {
            if (randomArr[i] != copyArr[i]) {
                return false;
            }
        }
        return true;
    }
}
