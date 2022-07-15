package other;

import java.util.Stack;

/**
 * leetcode739. 每日温度
 *
 * @author dks233
 * @create 2022-07-15-15:33
 */
@SuppressWarnings("ALL")
public class DailyTemperature {
    // 暴力解法：O(N*N)
    public static class MethodOne {
        public int[] dailyTemperatures(int[] nums) {
            int[] arr = new int[nums.length];
            for (int index = 0; index < nums.length; index++) {
                for (int right = index + 1; right < nums.length; right++) {
                    if (nums[right] > nums[index]) {
                        arr[index] = right - index;
                        break;
                    }
                }
            }
            return arr;
        }
    }

    // 单调栈：递减栈
    // 如果栈为空，直接加入元素
    // 如果栈不为空且当前元素大于栈顶元素，弹出栈顶元素，索引差就是栈顶元素的结果，然后当前元素入栈
    // 时间复杂度：O(N)
    public static class MethodTwo {
        public int[] dailyTemperatures(int[] nums) {
            int[] arr = new int[nums.length];
            Stack<Integer> stack = new Stack<>();
            for (int index = 0; index < nums.length; index++) {
                while (!stack.isEmpty() && nums[index] > nums[stack.peek()]) {
                    Integer pop = stack.pop();
                    arr[pop] = index - pop;
                }
                stack.push(index);
            }
            return arr;
        }
    }
}
