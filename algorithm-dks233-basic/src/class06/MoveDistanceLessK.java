package class06;

import java.util.PriorityQueue;

/**
 * 几乎有序数组做排序
 * 几乎有序：如果把数组排好序的话，每个元素移动的距离一定不超过k
 * 分析：比如k=5，排好序后，最小值位于0位置，根据规则，排序前的最小值一定位于0到5之间。
 * 把前6个数放到小根堆中，弹出的值就是最小值，把弹出的值放到0位置。
 * 排好序后，倒数第二小的值位于1位置，根据规则，排序前的最小值一定位于0到6之间。
 * 第一轮弹出最小值后，把第7个数放入小根堆中，弹出新的小根堆中的最小值，放到1位置。
 *
 * @author dks233
 * @create 2022-04-05-21:09
 */
public class MoveDistanceLessK {
    public static void moveDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        // 建堆，大小为k+1
        // 默认是小根堆，所以每次弹出的值就是最小值
        PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1);
        int index = 0;
        // 先将前k个数放到小根堆中
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            // 每轮放heap中一个数
            heap.add(arr[index]);
            // 弹出最小的数，放到i位置
            if (!heap.isEmpty()) {
                arr[i] = heap.poll();
            }
        }
        // 将剩余的数放到剩余位置
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}
