package class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * leetcode1856. 子数组最小乘积的最大值
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，一定都可以算出(sub累加和 )* (sub中的最小值)是什么，那么所有子数组中，这个值最大是多少
 * 分析：以i位置值为最小值的子数组乘积最大值=arr[i]*(i左边离i最近的比arr[i]小的位置，i右边离i最近的比arr[i]小的位置)区间内的累加和
 * 分析：求离i最近的比arr[i]小的元素位置见MonotoneStack.java
 * 分析：区间累加和通过前缀和数组进行计算
 *
 * @author dks233
 * @create 2022-08-31-23:32
 */
@SuppressWarnings("ALL")
public class SumMultiplyMinvalue {
    // 区间复杂度：O(N)
    public int maxSumMinProduct(int[] nums) {
        // 求i位置左右离i最近的比nums[i]小的位置
        // small[i][0] 左 small[i][1] 右
        int[][] small = getSmall(nums);
        // 前缀和数组
        long[] preSums = new long[nums.length + 1];
        long preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            preSums[i + 1] = preSum;
        }
        // 遍历nums数组，求子数组最大乘积
        long result = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = small[i][0];
            int right = small[i][1];
            // 情况1：left=-1 左边元素都比当前元素大，左边界应该是包含的
            // 情况2：right=-1 右边元素都比当前元素大，右边界应该是包含的
            // 情况3：left!=-1&&right!=-1 左右边界都不包含
            // 情况3中，区间长度应该是preSums[right]-preSums[left+1] 情况1中，应该减去preSums[0]，也即preSums[left+1]
            // 所以left是否等于-1,对结果没影响，情况2中，被减数应该是preSums[nums.length]，所以right=-1时，将right变成nums.length
            if (right == -1) right = nums.length;
            result = Math.max(result, nums[i] * (preSums[right] - preSums[left + 1]));
        }
        return (int) (result % ((int) Math.pow(10, 9) + 7));
    }

    public int[][] getSmall(int[] nums) {
        int[][] result = new int[nums.length][2];
        // 栈中每个列表代表相同值对应的索引
        // 栈存放元素的原则，从栈底到栈顶元素依次增大
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            // 栈顶元素大于当前元素，处理栈顶元素，弹出栈顶元素
            while (!stack.isEmpty() && nums[stack.peek().get(0)] > nums[i]) {
                List<Integer> popList = stack.pop();
                for (Integer pop : popList) {
                    result[pop][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    result[pop][1] = i;
                }
            }
            // 栈为空，或栈顶元素小于等于当前元素
            // 如果栈为空，或栈顶元素小于当前元素，新建列表，将当前元素对应列表入栈
            if (stack.isEmpty() || nums[stack.peek().get(0)] < nums[i]) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
            // 栈顶元素等于当前元素，将当前元素加到栈顶列表中
            else {
                stack.peek().add(i);
            }
        }
        // 遍历完后，栈还不为空，将栈顶元素依次弹出
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
