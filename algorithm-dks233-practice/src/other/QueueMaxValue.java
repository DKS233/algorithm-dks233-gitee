package other;

import java.util.LinkedList;

/**
 * 剑指 Offer第二版 59 - II. 队列的最大值
 * 参考文档：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/solution/jian-zhi-offer-59-ii-dui-lie-de-zui-da-z-0pap/
 *
 * @author dks233
 * @create 2022-06-19-16:28
 */
public class QueueMaxValue {
    public static class MaxQueue {
        // 构建一个非单调递减的辅助双端队列，辅助队列队首元素就是队列最大值
        LinkedList<Integer> helpQueue;
        LinkedList<Integer> queue;


        public MaxQueue() {
            this.helpQueue = new LinkedList<>();
            this.queue = new LinkedList<>();
        }

        public int max_value() {
            if (helpQueue.isEmpty()) {
                return -1;
            }
            return helpQueue.peekFirst();
        }

        public void push_back(int value) {
            queue.offer(value);
            if (helpQueue.isEmpty()) {
                helpQueue.offer(value);
            }
            // 维护辅助队列的非单调递减性质
            // 如果value大于辅助队列里的某些元素，将这些元素从队尾弹出（这里需要双端队列）
            // 弹出后将value加入到队列中
            // 注意：value如果等于最大值，辅助队列里会出现一个最大值，如果queue里有两个最大值，即使删除一个，队列的最大值也不受影响
            else {
                while (!helpQueue.isEmpty() && value > helpQueue.peekLast()) {
                    helpQueue.pollLast();
                }
                helpQueue.offer(value);
            }
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            Integer poll = queue.poll();
            // 如果对列中弹出的就是最大值，辅助队列里需要同步弹出，保持两个队列元素一致
            if (poll.equals(helpQueue.peekFirst())) {
                helpQueue.poll();
            }
            return poll;
        }
    }
}
