package class14;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 金条切割问题
 * 牛客网：https://www.nowcoder.com/questionTerminal/418d2fcdf7f24d6f8f4202e23951c0da
 *
 * @author dks233
 * @create 2022-05-03-9:25
 */
@SuppressWarnings("ALL")
public class GoldCut {
    // 体系班代码
    public static class OnClass {
        // arr:需要将金条切割成数组中对应的长度
        // return:金条切割的最小代价
        public static int getMinCutCost(int[] arr) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int value : arr) {
                queue.offer(value);
            }
            // 每次弹出两个数，合成一个数，把合成的数再放回去，直到小根堆中只剩一个树
            // 最终建成的大数的非叶子节点的和即为总代价
            // 非叶子节点即每次合成的数
            int minCount = 0;
            int add = 0;
            while (queue.size() > 1) {
                add = queue.poll() + queue.poll();
                queue.offer(add);
                minCount += add;
            }
            return minCount;
        }

        public static int comparator(int[] arr) {
            if (arr == null || arr.length == 0) {
                return 0;
            }
            return process(arr, 0);
        }

        // arr:待合并的数
        // preCount:当前的合并产生了多少代价
        // arr只剩一个数的时候，停止合并，返回最小代价
        private static int process(int[] arr, int preCount) {
            if (arr.length == 1) {
                return preCount;
            }
            int ans = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    ans = Math.min(ans, process(getNotMerge(arr, i, j), preCount + arr[i] + arr[j]));
                }
            }
            return ans;
        }

        private static int[] getNotMerge(int[] arr, int i, int j) {
            int[] ans = new int[arr.length - 1];
            int ansi = 0;
            for (int arri = 0; arri < arr.length; arri++) {
                if (arri != i && arri != j) {
                    ans[ansi++] = arr[arri];
                }
            }
            ans[ansi] = arr[i] + arr[j];
            return ans;
        }

        public static int[] generateRandomArray(int maxSize, int maxValue) {
            int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * (maxValue + 1));
            }
            return arr;
        }

        public static void main(String[] args) {
            int testTime = 100000;
            int maxSize = 6;
            int maxValue = 1000;
            boolean isSuccess = true;
            for (int i = 0; i < testTime; i++) {
                int[] arr = generateRandomArray(maxSize, maxValue);
                if (comparator(arr) != getMinCutCost(arr)) {
                    isSuccess = false;
                    break;
                }
            }
            System.out.println(isSuccess ? "测试成功" : "测试失败");
        }
    }

    // 牛客网原题
    public static class GoldCutOne {
        public static class Main {
            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                int n = scanner.nextInt();
                long[] arr = new long[n];
                for (int i = 0; i < n; i++) {
                    int value = scanner.nextInt();
                    arr[i] = value;
                }
                // 小根堆，将所有数压入
                PriorityQueue<Long> heap = new PriorityQueue<>();
                for (int i = 0; i < arr.length; i++) {
                    heap.add(arr[i]);
                }
                // 每次弹出两个，相加，然后金条累加
                long result = 0;
                while (heap.size() > 1) {
                    long add = heap.poll() + heap.poll();
                    heap.add(add);
                    result += add;
                }
                System.out.println(result);
            }
        }
    }

}
