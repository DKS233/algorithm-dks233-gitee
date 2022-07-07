package other;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * leetcode347. 前K个高频元素
 *
 * @author dks233
 * @create 2022-07-07-11:06
 */
@SuppressWarnings("ALL")
public class TopKHighFrequencyElements {
    // 利用小根堆
    // 时间复杂度：O(N*logk)
    public int[] topKFrequent(int[] nums, int k) {
        // 先用map统计每个数字出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            if (map.containsKey(nums[index])) {
                map.put(nums[index], map.get(nums[index]) + 1);
            } else {
                map.put(nums[index], 1);
            }
        }
        // 维护一个小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
        // 始终保持小根堆里的元素是k个
        // 小根堆元素达到k个后，如果添加元素的频率大于堆顶元素，弹出堆顶元素，新元素添加到小根堆里
        map.forEach((key, value) -> {
            if (minHeap.size() < k) {
                minHeap.add(key);
            } else {
                if (value > map.get(minHeap.peek())) {
                    minHeap.poll();
                    minHeap.add(key);
                }
            }
        });
        // 遍历完毕，将小根堆元素转换成数组
        int[] ans = new int[k];
        for (int index = 0; index < k; index++) {
            ans[index] = minHeap.poll();
        }
        return ans;
    }
}
