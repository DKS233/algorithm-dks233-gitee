package class05;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 快速排序
 * 递归版本：
 * 非递归版本：栈实现，队列实现
 * 注：非递归的每个子任务和子任务的子任务互不影响，先执行谁都可以，所以没有先后顺序之分
 *
 * @author dks233
 * @create 2022-04-03-17:45
 */
public class QuickSort {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = randomArr(maxLen, maxValue);
            int[] copyArrForComparator = copyArr(randomArr);
            int[] copyArrForStack = copyArr(randomArr);
            int[] copyArrForQueue = copyArr(randomArr);
            quickSortOne(randomArr);
            comparator(copyArrForComparator);
            quickSortStack(copyArrForStack);
            quickSortQueue(copyArrForQueue);
            if (!isEquals(randomArr, copyArrForComparator)) {
                isSuccess = false;
                break;
            }
            if (!isEquals(randomArr, copyArrForStack)) {
                isSuccess = false;
                break;
            }
            if (!isEquals(randomArr, copyArrForQueue)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    // 快速排序递归实现
    private static void quickSortOne(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // 快速排序非递归实现：栈实现
    private static void quickSortStack(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 随机一个数作为划分值
        swap(arr, arr.length - 1, (int) (Math.random() * arr.length));
        // 第一轮等于区的左右边界
        int[] equalsArea = dutchNationalFlag(arr, 0, arr.length - 1);
        Op opLeft = new Op(0, equalsArea[0] - 1);
        Op opRight = new Op(equalsArea[1] + 1, arr.length - 1);
        Stack<Op> stack = new Stack<>();
        // 栈中分发两个任务，每个任务中实现大于小于等于区域的划分
        stack.push(opLeft);
        stack.push(opRight);
        while (!stack.isEmpty()) {
            // 弹出子任务
            Op pop = stack.pop();
            if (pop.left < pop.right) {
                swap(arr, pop.left + (int) (Math.random() * (pop.right - pop.left + 1)), pop.right);
                // 子任务再分出两个子任务，直到所有的子任务都被执行完
                equalsArea = dutchNationalFlag(arr, pop.left, pop.right);
                stack.push(new Op(pop.left, equalsArea[0] - 1));
                stack.push(new Op(equalsArea[1] + 1, pop.right));
            }
        }
    }

    // 快速排序非递归实现：对列实现
    private static void quickSortQueue(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 选定划分值
        swap(arr, arr.length - 1, (int) (Math.random() * arr.length));
        // 得到等于区的左右边界
        int[] equalsArea = dutchNationalFlag(arr, 0, arr.length - 1);
        // 队列添加两个子任务
        Queue<Op> queue = new LinkedList<>();
        queue.offer(new Op(0, equalsArea[0] - 1));
        queue.offer(new Op(equalsArea[1] + 1, arr.length - 1));
        while (!queue.isEmpty()) {
            // 子任务出队列
            Op poll = queue.poll();
            if (poll.left < poll.right) {
                swap(arr, poll.right, poll.left + (int) (Math.random() * (poll.right - poll.left + 1)));
                // 子任务再分出两个子任务
                equalsArea = dutchNationalFlag(arr, poll.left, poll.right);
                queue.offer(new Op(poll.left, equalsArea[0] - 1));
                queue.offer(new Op(equalsArea[1] + 1, poll.right));
            }
        }
    }

    // 非递归实现需要的辅助类：需要处理什么范围上的排序
    public static class Op {
        int left;
        int right;

        public Op(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    // 每一轮：将一个数的大于小于等于区划分出来，然后等于区不动，对左右两边的大于和小于区再进行划分
    private static void process(int[] arr, int left, int right) {
        // left >= right 边界条件
        // 等于区左边界是left，left>left-1
        // 等于区右边界是right，right+1>right
        if (left >= right) {
            return;
        }
        // 将数组中[left,right]随机一个值放到right位置处
        swap(arr, left + (int) (Math.random() * (right - left + 1)), right);
        // 得到等于区域的左右边界
        int[] equalArea = dutchNationalFlag(arr, left, right);
        process(arr, left, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, right);
    }

    // 得到等于区的左右边界
    private static int[] dutchNationalFlag(int[] arr, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, right};
        }
        // 小于区的左边界
        int less = left - 1;
        // 大于区的右边界，先将指定的值（需要比较的值）放到大于区
        // 本轮大于小于区完毕后再和大于区的第一个数进行交换
        int more = right;
        int index = left;
        while (index < more) {
            // 当前数等于指定值，index右移
            if (arr[index] == arr[right]) {
                index++;
            }
            // 当前数大于指定值，当前数和大于区前一个数做交换，index不动
            else if (arr[index] > arr[right]) {
                swap(arr, index, more - 1);
                more--;
            }
            // 当前数小于指定值，当前数和小于区后一个数做交换，index右移
            else if (arr[index] < arr[right]) {
                swap(arr, index, less + 1);
                less++;
                index++;
            }
        }
        // 把指定的值和大于区第一个数做交换
        swap(arr, right, more);
        // 返回等于区的左右边界
        // 注：如果到这里less指针还是left-1，由于等于区至少有一个数，所以等于区左边界是left，即left-1+1
        return new int[]{less + 1, more};
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] randomArr = new int[len];
        for (int element : randomArr) {
            element = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return randomArr;
    }

    private static int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, copyArr.length);
        return copyArr;
    }

    private static boolean isEquals(int[] randomArr, int[] copyArr) {
        if (randomArr != null && copyArr == null || randomArr == null && copyArr != null) {
            return false;
        }
        if (randomArr == null) {
            return false;
        }
        if (randomArr.length != copyArr.length) {
            return false;
        }
        for (int i = 0; i < randomArr.length; i++) {
            if (randomArr[i] != copyArr[i]) {
                return false;
            }
        }
        return true;
    }

    private static void comparator(int[] arr) {
        Arrays.sort(arr);
    }
}
