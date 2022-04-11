package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题
 *
 * @author dks233
 * @create 2022-04-08-20:56
 */
public class MaxCover {
    public static void main(String[] args) {
        int testTimes = 10000;
        int min = 0;
        int max = 2333;
        int count = 233;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[][] lineArray = getLineArray(min, max, count);
            int maxCover = maxCover(lineArray);
            int maxCoverOne = maxCoverOne(lineArray);
            int maxCoverTwo = maxCoverTwo(lineArray);
            if (maxCover != maxCoverOne || maxCover != maxCoverTwo) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    // 不借助小根堆，找到线段的最小左边界和最大右边界，然后看每个.5位置有几条线段
    // 复杂度 O((max-min)*n)
    public static int maxCover(int[][] m) {
        // 找到最小左边界和最大右边界
        int min = m[0][0];
        int max = m[0][1];
        for (int i = 0; i < m.length; i++) {
            min = Math.min(min, m[i][0]);
            max = Math.max(max, m[i][1]);
        }
        // 最大重合线段数
        int result = 0;
        // 遍历每个.5位置，看此位置有多少条线段穿过
        for (double i = min + 0.5; i < max; i++) {
            int currentResult = 0;
            for (int j = 0; j < m.length; j++) {
                if (m[j][0] < i && m[j][1] > i) {
                    currentResult++;
                }
            }
            result = Math.max(result, currentResult);
        }
        return result;
    }

    // 借助小根堆：实现1
    // 复杂度：所有线段结尾位置，最多进一次小根堆，出一次小根堆，一共进出2N，复杂度为O(N*logN)
    public static int maxCoverOne(int[][] m) {
        Line[] lines = new Line[m.length];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        // 将线段开始位置从小到大排序
        Arrays.sort(lines, new StartComparator());
        // 创建一个小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 最大线段重合数
        int max = 0;
        for (Line line : lines) {
            // 判断小根堆里有没有小于等于当前线段左边界的，如果有，说明不和当前线段重合，弹出
            while (!minHeap.isEmpty() && minHeap.peek() <= line.start) {
                minHeap.poll();
            }
            minHeap.add(line.end);
            max = Math.max(minHeap.size(), max);
        }
        return max;
    }

    // 借助小根堆，实现2
    public static int maxCoverTwo(int[][] m) {
        // 将每条线段开始位置从小到大排序
        Arrays.sort(m, (a, b) -> a[0] - b[0]);
        // 创建小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 最大线段重合数
        int max = 0;
        for (int[] ints : m) {
            while (!minHeap.isEmpty() && minHeap.peek() <= ints[0]) {
                minHeap.poll();
            }
            minHeap.add(ints[1]);
            max = Math.max(minHeap.size(), max);
        }
        return max;
    }

    public static class StartComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int[][] getLineArray(int min, int max, int count) {
        int[][] array = new int[count][2];
        for (int i = 0; i < array.length; i++) {
            int a = min + (int) (Math.random() * (max - min + 1));
            int b = min + (int) (Math.random() * (max - min + 1));
            if (a == b) {
                b = a + 1;
            }
            array[i][0] = Math.min(a, b);
            array[i][1] = Math.max(a, b);
        }
        return array;
    }

}
