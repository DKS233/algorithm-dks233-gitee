package class25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个可能含有重复值的数组arr，i位置的数一定存在如下两个信息
 * arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪
 * arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪
 *
 * @author dks233
 * @create 2022-08-31-17:56
 */
@SuppressWarnings("ALL")
public class MonotoneStack {
    // 情况1：没重复值
    // 返回二维数组
    // matrix[i][0] 左侧离i最近并且小于arr[i]的数对应索引
    // matrix[i][1] 右侧离i最近并且小于arr[i]的数对应索引
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] result = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 不符合从小到大的原则，处理栈顶元素并将栈顶元素弹出
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                result[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
                result[popIndex][1] = i;
            }
            // 符合从小到大的原则或者栈为空
            stack.push(i);
        }
        // 遍历完后栈中还有元素，依次计算结果，然后弹出
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            result[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
            result[popIndex][1] = -1;
        }
        return result;
    }

    // 情况2：可能有重复值（支持重复值，可以替代情况1的方法）
    // 返回二维数组
    // matrix[i][0] 左侧离i最近并且小于arr[i]的数对应索引
    // matrix[i][1] 右侧离i最近并且小于arr[i]的数对应索引
    public static int[][] getNearLess(int[] arr) {
        int[][] result = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 栈顶元素大于当前元素，处理栈顶列表中的元素，然后弹出栈顶列表中的元素
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                // 栈顶列表弹出，然后计算左右比列表中对应元素小的结果
                List<Integer> popList = stack.pop();
                for (Integer pop : popList) {
                    // 如果栈为空，左索引是-1
                    // 如果栈不为空，左索引是新栈顶（原栈顶下面）的列表的最后一个元素
                    result[pop][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                    // 右索引是当前元素
                    result[pop][1] = i;
                }
            }
            // 栈为空，或者栈顶列表对应的值都小于等于当前值，将当前值压入栈中
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.add(list);
            }
        }
        // 栈中还有剩余的元素，依次计算结果，然后弹出
        while (!stack.isEmpty()) {
            List<Integer> popList = stack.pop();
            for (Integer pop : popList) {
                result[pop][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                result[pop][1] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int size = 10;
        int max = 20;
        int testTimes = 2000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = getRandomArrayNoRepeat(size);
            int[] arr2 = getRandomArray(size, max);
            if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
                System.out.println("Oops!");
                printArray(arr1);
                break;
            }
            if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
                System.out.println("Oops!");
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

    // for test
    public static int[] getRandomArrayNoRepeat(int size) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    // for test
    public static int[] getRandomArray(int size, int max) {
        int[] arr = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return arr;
    }

    // for test
    public static int[][] rightWay(int[] arr) {
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;
            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }
            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            res[i][0] = leftLessIndex;
            res[i][1] = rightLessIndex;
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[][] res1, int[][] res2) {
        if (res1.length != res2.length) {
            return false;
        }
        for (int i = 0; i < res1.length; i++) {
            if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
                return false;
            }
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}























