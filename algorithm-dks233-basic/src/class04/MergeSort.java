package class04;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author dks233
 * @create 2022-03-29-20:37
 */
public class MergeSort {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = randomArr(maxLen, maxValue);
            int[] copyArr = copyArr(randomArr);
            mergeSortOne(randomArr);
            comparator(copyArr);
            if (!isEquals(randomArr, copyArr)) {
                System.out.println("递归测试失败");
            }
        }
        for (int i = 0; i < testTimes; i++) {
            int[] randomArr = randomArr(maxLen, maxValue);
            int[] copyArr = copyArr(randomArr);
            mergeSortTwo(randomArr);
            comparator(copyArr);
            if (!isEquals(randomArr, copyArr)) {
                System.out.println("非递归测试失败");
            }
        }
        System.out.println("测试成功");
    }

    /**
     * 归并排序递归实现
     * 复杂度分析：master公式（分析递归函数的时间复杂度）
     * T(N)=2*T(N/2)+O(N)，其中2表示递归的次数，N/2表示子问题的规模，O(N)表示除递归操作外的操作的时间复杂度
     * 该题中其他操作的时间复杂度为O(N)，即merge的时间复杂度
     * 由master公式得到T(N)=O(N*logN)
     *
     * @param arr 数组
     */
    public static void mergeSortOne(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    /**
     * 归并排序非递归实现
     * 复杂度分析：每轮设置一个步长，第一轮mergeSize=1，两两merge，然后mergeSize乘2，直到全部merge
     * 步长变化次数为logN次，每次merge的时间复杂度都是O(N)次，所以总的时间复杂度是O(N*logN)
     *
     * @param arr 数组
     */
    public static void mergeSortTwo(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;
        // 步长：初始值为1
        int mergeSize = 1;
        while (mergeSize < n) {
            // 左区域第一个值的索引
            int left = 0;
            while (left < n) {
                // 左组最后一个位置：mid
                int mid = left + mergeSize - 1;
                if (mid >= n) {
                    break;
                }
                // 右组最后一个位置：right
                int right = Math.min(mid + mergeSize, n - 1);
                merge(arr, left, mid, right);
                // 下一组左组第一个位置
                left = right + 1;
            }
            // 防止溢出
            if (mergeSize > n / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 到这儿：p1越界或者p2越界
        // p2越界
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        // p1越界
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        System.arraycopy(help, 0, arr, left, help.length);
    }

    public static void comparator(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Arrays.sort(arr);
    }

    public static int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copyArr = new int[arr.length];
        System.arraycopy(arr, 0, copyArr, 0, arr.length);
        return copyArr;
    }

    public static int[] randomArr(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int element : arr) {
            element = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static boolean isEquals(int[] a, int[] b) {
        if ((a == null && b != null) || (a != null && b == null) || (a == null && b == null)) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
