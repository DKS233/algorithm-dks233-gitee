package class01;

/**
 * 无序数组中，任意相邻的数一定不相等，找出一个局部最小值
 * 返回：局部最小值的索引位置
 *
 * @author dks233
 * @create 2022-03-20-19:53
 */
public class LocalMinimum {
    public static int getLocalMininum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        // 对应情况1
        if (arr[0] < arr[1]) {
            return 0;
        }
        // 对应情况2
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int left = 1;
        int right = arr.length - 2;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            // 对应情况3
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            }
            // 对应情况4的1
            else if (arr[mid] < arr[mid + 1]) {
                return mid;
            }
            // 对应情况4的2
            else {
                left = mid + 1;
            }
        }
        // 左右区域相交说明找到局部最小值
        return left;
    }
}
