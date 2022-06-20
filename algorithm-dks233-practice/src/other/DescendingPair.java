package other;

/**
 * 剑指 Offer第二版 51. 数组中的逆序对
 *
 * @author dks233
 * @create 2022-06-20-16:59
 */
public class DescendingPair {
    public int reversePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return processOne(arr, 0, arr.length - 1);
    }

    public int processOne(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + ((right - left) >> 1);
        return processOne(arr, left, mid) + processOne(arr, mid + 1, right) + mergeOne(arr, left, mid, right);
    }

    // 左右组都是从右往左merge，对每一个左组元素，看右组有多少个元素比它小
    // 左组数大，拷贝左组，逆序对累加
    // 左组数小，拷贝右组，逆序对不累加
    // 左组右组数相等，拷贝右组，逆序对不累加
    public int mergeOne(int[] arr, int left, int mid, int right) {
        int p1 = mid; // 左组最后一个数
        int p2 = right; // 右组最后一个数
        int[] help = new int[right - left + 1];
        int i = help.length - 1;
        int ans = 0; // 当前merge的逆序对数量
        while (p1 >= left && p2 >= mid + 1) {
            ans += arr[p1] > arr[p2] ? p2 - mid : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        // p1越界
        while (p2 >= mid + 1) {
            help[i--] = arr[p2--];
        }
        // p2越界
        while (p1 >= left) {
            help[i--] = arr[p1--];
        }
        for (i = 0; i < help.length; i++) {
            arr[i + left] = help[i];
        }
        return ans;
    }
}
