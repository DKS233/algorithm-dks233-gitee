package class25;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * leetcode85. 最大矩形
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的最大子矩形内部有多少个1（面积）
 * 分析：暴力解法时间复杂度是O(N^6) 选取矩阵左上角和右下角都是O(N*N) 所以选到矩阵是O(N^4) 再遍历矩阵 所以复杂度是O(N^6)
 * 分析：压缩数组+单调栈
 * 分析：求直方图最大矩形的面积见MaximumHistogramArea.java
 *
 * @author dks233
 * @create 2022-09-01-17:15
 */
@SuppressWarnings("ALL")
public class MaxRectangle {
    // 分析：求以每行为底的直方图中最大矩形的面积
    // 原则：遇到1就加1，遇到0就置0
    // 如 [1 1 1 1 1] [1 0 1 1 1] [1 1 1 0 1] [1 1 1 1 1]
    // 以0到3行为底的直方图分别为 [1 1 1 1 1] [2 0 2 2 2] [3 1 3 0 3] [4 2 4 1 4]
    // 求每个直方图中最大矩形的面积
    public int maximalRectangle(char[][] matrix) {
        // 当前行的直方图
        int[] curLine = new int[matrix[0].length];
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0) {
                    curLine[j] = matrix[i][j] - '0';
                } else {
                    curLine[j] = matrix[i][j] == '0' ? 0 : matrix[i][j] - '0' + curLine[j];
                }
            }
            result = Math.max(curMax(curLine), result);
        }
        return result;
    }

    // 求直方图中最大矩阵面积
    public int curMax(int[] nums) {
        Deque<List<Integer>> stack = new ArrayDeque<>();
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek().get(0)] > nums[i]) {
                List<Integer> popList = stack.pop();
                for (Integer pop : popList) {
                    int left = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    int right = i;
                    if (right == -1) right = nums.length;
                    result = Math.max(nums[pop] * (right - left - 1), result);
                }
            }
            if (stack.isEmpty() || nums[stack.peek().get(0)] < nums[i]) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            } else {
                stack.peek().add(i);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            for (Integer pop : popList) {
                int left = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                int right = -1;
                if (right == -1) right = nums.length;
                result = Math.max(nums[pop] * (right - left - 1), result);
            }
        }
        return result;
    }
}
