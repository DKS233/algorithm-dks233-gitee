package slidingwindow;

import java.util.*;

/**
 * leetcode239. 滑动窗口最大值
 * 参考文档：https://leetcode.cn/problems/sliding-window-maximum/solution/dong-hua-yan-shi-dan-diao-dui-lie-239hua-hc5u/
 *
 * @author dks233
 * @create 2022-09-08-16:43
 */
@SuppressWarnings("ALL")
public class SlidingWindowMaximum {
    // 单调队列
    // 队首-队尾 队列中元素从队首到队尾依次减小
    // 从左往右遍历，如果当前元素大于队尾元素，将队尾元素弹出，然后将当前元素加入队列中
    // 队首元素下标小于窗口左边界下标时，队首元素需要弹出
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        // 先将第一个窗口构建好
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int curIndex = 0;
        result[curIndex++] = nums[deque.peekFirst()];
        // 构建剩余窗口
        for (int i = k; i < nums.length; i++) {
            // 队首元素下标小于窗口左边界下标使，队首元素需要弹出
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // 处理完队首元素后，将后续元素添加到队列中
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            result[curIndex++] = nums[deque.peekFirst()];
        }
        return result;
    }
}
