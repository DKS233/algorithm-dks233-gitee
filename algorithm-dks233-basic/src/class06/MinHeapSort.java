package class06;

import common.ArrayUtils;

import java.util.Arrays;

/**
 * 从大到小排序
 *
 * @author dks233
 * @create 2022-04-08-11:34
 */
public class MinHeapSort {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArrOne = ArrayUtils.randomArr(maxLen, maxValue);
            int[] copyArrOne = ArrayUtils.copyArr(randomArrOne);
            minHeapSortOne(randomArrOne);
            reverseArr(randomArrOne);
            comparator(copyArrOne);
            int[] randomArrTwo = ArrayUtils.randomArr(maxLen, maxValue);
            int[] copyArrTwo = ArrayUtils.copyArr(randomArrTwo);
            minHeapSortTwo(randomArrTwo);
            reverseArr(randomArrTwo);
            comparator(copyArrTwo);
            if (!ArrayUtils.isEquals(randomArrOne, copyArrOne)) {
                isSuccess = false;
                System.out.println("randomArrOne----->" + Arrays.toString(randomArrOne));
                System.out.println("copyArrOne----->" + Arrays.toString(copyArrOne));
                break;
            }
            if (!ArrayUtils.isEquals(randomArrTwo, copyArrTwo)) {
                isSuccess = false;
                System.out.println("randomArrTwo----->" + Arrays.toString(randomArrTwo));
                System.out.println("copyArrTwo----->" + Arrays.toString(copyArrTwo));
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static void reverseArr(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    public static void minHeapSortOne(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int heapSize = arr.length;
        // 将数组转换成小根堆，然后0位置的数和heapSize-1位置的数交换，然后heapSize--，将heapSize-1位置
        // 的数剔除出小根堆，形成新的数组。然后0位置的数heapify，再将数组整体转换为小根堆，...
        // 数组转换成小根堆：方法1：一个一个heapInsert
        for (int i = 0; i < heapSize; i++) {
            heapInsert(arr, i);
        }
        // 然后进行循环heapify
        while (heapSize > 0) {
            ArrayUtils.swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    public static void minHeapSortTwo(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int heapSize = arr.length;
        // 将数组转换成小根堆，然后0位置的数和heapSize-1位置的数交换，然后heapSize--，将heapSize-1位置
        // 的数剔除出小根堆，形成新的数组。然后0位置的数heapify，再将数组整体转换为小根堆，...
        // 数组转换成小根堆：方法2：整个数组从下往上heapify
        for (int i = heapSize - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }
        // 然后进行循环heapify
        while (heapSize > 0) {
            ArrayUtils.swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }
    }

    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);
    }

    // 先添加进来的元素初始位置为heapSize-1
    public static void heapInsert(int[] heap, int index) {
        // 如果index位置数比父节点数小，就交换
        // 如果index位置数不比父节点数小或者index=0（根节点），跳出循环
        while (heap[index] < heap[(index - 1) / 2]) {
            ArrayUtils.swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 需要下沉的数初始位置为0
    public static void heapify(int[] heap, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 左右子节点中较小的值和index位置的数比较，如果index位置的数比较小的值大，就交换
            int smallest = left + 1 < heapSize && heap[left] > heap[left + 1] ? left + 1 : left;
            smallest = heap[index] > heap[smallest] ? smallest : index;
            // index位置的数比较小的中还要小，就不交换，停止下沉
            if (index == smallest) {
                break;
            }
            // index位置的数下沉
            ArrayUtils.swap(heap, index, smallest);
            index = smallest;
            left = index * 2 + 1;
        }
    }

}
