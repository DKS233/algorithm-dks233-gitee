package other;

import java.util.PriorityQueue;

/**
 * leetcode剑指 Offer 41. 数据流中的中位数
 * 参考文档：https://leetcode.cn/problems/find-median-from-data-stream/solution/gong-shui-san-xie-jing-dian-shu-ju-jie-g-pqy8/
 * 进阶：如果数据流中所有整数都在0到100范围内，你将如何优化你的算法？
 * 可以使用建立长度为101的桶，每个桶分别统计每个数的出现次数，同时记录数据流中总的元素数量，
 * 每次查找中位数时，先计算出中位数是第几位，从前往后扫描所有的桶得到答案
 *
 * @author dks233
 * @create 2022-09-05-17:00
 */
@SuppressWarnings("ALL")
public class MedianInDataStream {
    public static class MedianFinder {
        // 大根堆，存排序后的前半部分，当数据流中数的数量为奇数时，大根堆中多存一个
        PriorityQueue<Integer> leftHeap;
        // 小根堆，存排序后的后半部分
        PriorityQueue<Integer> rightHeap;

        public MedianFinder() {
            leftHeap = new PriorityQueue<>((i, j) -> j - i);
            rightHeap = new PriorityQueue<>((i, j) -> i - j);
        }

        // 情况1：插入前两个堆中元素数量相同，说明之前的数量为偶数
        // rightHeap为空，直接插入leftHeap中
        // rightHeap不为空，rightHeap堆顶元素小于num，将rightHeap堆顶弹出，插入leftHeap，然后num插入到rightHeap中
        // rightHeap不为空，rightHeap堆顶元素大于num，说明当前数属于前半部分，直接插入leftHeap中
        // 情况2：插入前两个堆中元素数量不同，说明之前的数量为奇数，leftHeap中元素大于rightHeap中元素
        // leftHeap堆顶元素小于num，直接将num插入rightHeap中
        // leftHeap堆顶元素大于num，将leftHeap堆顶元素弹出，插入rightHeap，然后num插入到leftHeap中
        public void addNum(int num) {
            if (leftHeap.size() == rightHeap.size()) {
                if (rightHeap.isEmpty()) {
                    leftHeap.offer(num);
                } else if (rightHeap.peek() <= num) {
                    leftHeap.offer(rightHeap.poll());
                    rightHeap.offer(num);
                } else {
                    leftHeap.offer(num);
                }
            } else {
                if (leftHeap.peek() <= num) {
                    rightHeap.offer(num);
                } else {
                    rightHeap.offer(leftHeap.poll());
                    leftHeap.offer(num);
                }
            }
        }

        // 如果当前数的数量为奇数，即leftHeap中元素多于rightHeap中元素，直接取leftHeap中堆顶元素
        // 如果当前数的数量为偶数，即leftHeap中元素等于rightHeap中元素，取两个堆顶元素除以2
        public double findMedian() {
            if (leftHeap.size() > rightHeap.size()) {
                return leftHeap.peek();
            } else {
                return ((double) leftHeap.peek() + rightHeap.peek()) / 2;
            }
        }
    }
}
