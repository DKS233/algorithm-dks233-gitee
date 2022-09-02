package class25;

import java.util.*;

/**
 * leetcode84. 柱状图中最大的矩形
 * 给定一个非负数组arr，代表直方图，返回直方图的最大长方形面积
 * 分析：以i位置为高的最大长方形面积：以arr[i]为最小值的子区间长度*arr[i]
 * 分析：从i位置出发，找左右第一个比它小的位置，左右边界不包括，求面积
 * 分析：利用单调栈，找左右位置见MonotoneStack.java
 *
 * @author dks233
 * @create 2022-09-01-16:18
 */
@SuppressWarnings("ALL")
public class MaximumHistogramArea {
    // 普通版：先求区间，再遍历求面积
    public static class MethodOne {
        public int largestRectangleArea(int[] heights) {
            // 求i位置左右离i最近的比heights[i]小的位置
            int[][] leftRight = getLeftRight(heights);
            int result = 0;
            // 情况1：left=-1 左边元素都比当前元素大，左边界应该是包含的
            // 情况2：right=-1 右边元素都比当前元素大，右边界应该是包含的
            // 情况3：left!=-1&&right!=-1 左右边界都不包含
            // 情况3中，区间长度应该是右边界-左边界+1-2 即 right-left+1-2  左右边界都不算
            // 情况1中，左边界设置为-1，相当于比起0多算了一个元素，无影响
            // 情况2中，右边界实际为nums.length-1，因为最后还得多算一个元素，所以将右边界设置为nums.length
            for (int i = 0; i < leftRight.length; i++) {
                int left = leftRight[i][0];
                int right = leftRight[i][1];
                if (right == -1) right = heights.length;
                result = Math.max(result, heights[i] * (right - left - 1));
            }
            return result;
        }

        public int[][] getLeftRight(int[] heights) {
            int[][] result = new int[heights.length][2];
            // 原则：从栈底到栈顶列表对应的元素依次变大
            Stack<List<Integer>> stack = new Stack<>();
            for (int i = 0; i < heights.length; i++) {
                // 栈顶元素大于当前元素，处理栈顶元素，然后弹出栈顶元素
                while (!stack.isEmpty() && heights[stack.peek().get(0)] > heights[i]) {
                    List<Integer> popList = stack.pop();
                    for (Integer pop : popList) {
                        result[pop][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                        result[pop][1] = i;
                    }
                }
                // 栈为空或者栈顶元素小于等于当前元素
                // 情况1：栈为空或栈顶元素小于当前元素，新建列表，入栈
                // 情况2：栈顶元素等于当前元素，当前元素加到栈顶列表的最后一个位置
                if (stack.isEmpty() || heights[stack.peek().get(0)] < heights[i]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    stack.push(list);
                } else {
                    stack.peek().add(i);
                }
            }
            // 遍历完数组后，栈不为空，依次处理栈顶元素，然后弹出
            while (!stack.isEmpty()) {
                List<Integer> popList = stack.pop();
                for (Integer pop : popList) {
                    result[pop][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    result[pop][1] = -1;
                }
            }
            return result;
        }
    }

    // 优化版：求区间的时候直接更新最大面积
    // 优化版：用ArrayDeque代替Stack
    public static class MethodTwo {
        public int largestRectangleArea(int[] heights) {
            return getLeftRight(heights);
        }

        public int getLeftRight(int[] heights) {
            int result = 0;
            // 原则：从栈底到栈顶列表对应的元素依次变大
            Deque<List<Integer>> stack = new ArrayDeque<>();
            for (int i = 0; i < heights.length; i++) {
                // 栈顶元素大于当前元素，处理栈顶元素，然后弹出栈顶元素
                while (!stack.isEmpty() && heights[stack.peek().get(0)] > heights[i]) {
                    List<Integer> popList = stack.pop();
                    for (Integer pop : popList) {
                        int left = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                        int right = i;
                        if (right == -1) right = heights.length;
                        result = Math.max(result, heights[pop] * (right - left - 1));
                    }
                }
                // 栈为空或者栈顶元素小于等于当前元素
                // 情况1：栈为空或栈顶元素小于当前元素，新建列表，入栈
                // 情况2：栈顶元素等于当前元素，当前元素加到栈顶列表的最后一个位置
                if (stack.isEmpty() || heights[stack.peek().get(0)] < heights[i]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    stack.push(list);
                } else {
                    stack.peek().add(i);
                }
            }
            // 遍历完数组后，栈不为空，依次处理栈顶元素，然后弹出
            while (!stack.isEmpty()) {
                List<Integer> popList = stack.pop();
                for (Integer pop : popList) {
                    int left = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    int right = -1;
                    if (right == -1) right = heights.length;
                    result = Math.max(result, heights[pop] * (right - left - 1));
                }
            }
            return result;
        }
    }
}
