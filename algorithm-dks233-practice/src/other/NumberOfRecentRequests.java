package other;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指offer专项突击版：剑指 Offer II 042. 最近请求次数
 *
 * @author dks233
 * @create 2022-07-21-21:50
 */
public class NumberOfRecentRequests {
    public static class RecentCounter {
        Deque<Integer> list;

        public RecentCounter() {
            list = new ArrayDeque<>();
        }

        public int ping(int t) {
            if (t <= 3000) {
                list.addLast(t);
            } else {
                while (!list.isEmpty() && list.peekFirst() < t - 3000) {
                    list.removeFirst();
                }
                list.addLast(t);
            }
            return list.size();
        }
    }
}
