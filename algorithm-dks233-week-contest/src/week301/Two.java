package week301;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * leetcode2336. 无限集中的最小数字
 *
 * @author dks233
 * @create 2022-07-10-11:01
 */
@SuppressWarnings("ALL")
public class Two {
    public static class SmallestInfiniteSet {
        // 小根堆保存被移除的整数
        PriorityQueue<Integer> heap;

        public SmallestInfiniteSet() {
            heap = new PriorityQueue<>();
        }

        public int popSmallest() {
            if (heap.isEmpty()) {
                heap.offer(1);
                return 1;
            } else {
                Object[] arr = heap.toArray();
                Arrays.sort(arr);
                int ans = 1;
                if (arr.length < 2) {
                    if ((int) arr[0] == 1) {
                        ans = 2;
                    } else {
                        ans = 1;
                    }
                } else {
                    int index = 0;
                    int value = 1;
                    for (index = 0; index < arr.length; index++) {
                        if ((int) arr[index] != value) {
                            ans = value;
                            break;
                        } else {
                            value++;
                        }
                    }
                    if (index == arr.length) {
                        ans = value;
                    }
                }
                heap.offer(ans);
                return ans;
            }
        }

        public void addBack(int num) {
            if (heap.isEmpty()) {
                return;
            } else {
                if (heap.contains(num)) {
                    heap.remove(num);
                }
            }
        }
    }
}
