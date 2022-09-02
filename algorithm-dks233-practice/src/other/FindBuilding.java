package other;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.concurrent.locks.StampedLock;

/**
 * 题目：每个位置能看到多少楼
 * 题目地址：https://www.nowcoder.com/test/question/done?tid=60510452&qid=1453774#summary
 *
 * @author dks233
 * @create 2022-08-31-12:37
 */
@SuppressWarnings("ALL")
public class FindBuilding {
    // 分析：分析从i位置出发，左边有多少是递增的，右边有多少个位置是递增的
    public int[] findBuilding(int[] heights) {
        int[] result = new int[heights.length];
        // 自己能看到自己
        Arrays.fill(result, 1);
        // 当前位置出发，求每个数右边有多少是递增的
        Deque<Integer> leftStack = new ArrayDeque<>();
        for (int i = heights.length - 1; i > 0; i--) {
            // 当前楼大于等于后面的楼时，将后面的楼丢掉，因为在左边的楼是看不到当前楼右边的楼的
            while (!leftStack.isEmpty() && heights[leftStack.peek()] <= heights[i]) {
                leftStack.pop();
            }
            leftStack.push(i);
            // 当前楼左边的楼可以看到当前楼和比当前楼高的楼
            result[i - 1] += leftStack.size();
        }
        // 当前位置出发，求每个数左边有多少是递增的
        Deque<Integer> rightStack = new ArrayDeque<>();
        for (int i = 0; i < heights.length - 1; i++) {
            // 当前楼大于等于前面的楼时，将前面的楼丢掉，因为在右边的楼是看不到当前楼左边的楼的
            while (!rightStack.isEmpty() && heights[rightStack.peek()] <= heights[i]) {
                rightStack.pop();
            }
            rightStack.push(i);
            // 当前楼右边的楼可以看到当前楼和比当前楼高的楼
            result[i + 1] += rightStack.size();
        }
        return result;
    }

    public static void main(String[] args) {
        FindBuilding findBuilding = new FindBuilding();
        for (int value : findBuilding.findBuilding(new int[]{5, 3, 8, 3, 2, 5})) {
            System.out.print(value + "\t");
        }
    }
}
