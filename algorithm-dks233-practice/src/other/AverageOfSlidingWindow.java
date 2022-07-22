package other;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指offer专项突击版：剑指 Offer II 041. 滑动窗口的平均值
 *
 * @author dks233
 * @create 2022-07-21-21:24
 */
public class AverageOfSlidingWindow {
    public static class MovingAverage {
        int sum;
        LinkedList<Integer> list;
        int size;

        public MovingAverage(int size) {
            this.sum = 0;
            this.size = size;
            this.list = new LinkedList<>();
        }

        public double next(int val) {
            if (list.size() < size) {
                sum += val;
                list.addLast(val);
            } else {
                Integer first = list.removeFirst();
                sum -= first;
                list.addLast(val);
                sum += val;
            }
            return (double) sum / list.size();
        }
    }

}
