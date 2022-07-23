package other;

import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 剑指offer专项突击版：剑指 Offer II 059. 数据流的第 K 大数值
 *
 * @author dks233
 * @create 2022-07-23-23:24
 */
public class TheKthLargestValueOfDataFlow {
    public static class KthLargest {
        PriorityQueue<Integer> heap;
        int k;

        public KthLargest(int k, int[] nums) {
            heap = new PriorityQueue<>();
            for (int index = 0; index < nums.length; index++) {
                heap.add(nums[index]);
            }
            this.k = k;
        }

        public int add(int val) {
            heap.offer(val);
            while (heap.size() > k) {
                heap.poll();
            }
            return heap.peek();
        }
    }
}
