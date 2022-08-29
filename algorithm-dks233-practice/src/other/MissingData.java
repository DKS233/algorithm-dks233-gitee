package other;

/**
 * leetcode268. 丢失的数字
 *
 * @author dks233
 * @create 2022-08-29-21:12
 */
@SuppressWarnings("ALL")
public class MissingData {
    public static class MethodOne {
        // 初次遍历数组，尽量将i位置的值放成i
        // 再次遍历，如果位置的值都对应，返回n，否则返回第一个值和索引不匹配的位置
        public int missingNumber(int[] nums) {
            int n = nums.length;
            int i = 0;
            while (i < n) {
                while (nums[i] != n && nums[i] != i) {
                    swap(nums, nums[i], i);
                }
                i++;
            }
            for (i = 0; i < n; i++) {
                if (nums[i] != i) {
                    return i;
                }
            }
            return n;
        }

        public void swap(int[] arr, int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }

    public static class MethodTwo {
        // 用数组存出现的每个元素
        public int missingNumber(int[] nums) {
            int[] count = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                count[nums[i]]++;
            }
            for (int i = 0; i < count.length; i++) {
                if (count[i] == 0) {
                    return i;
                }
            }
            return -1;
        }
    }
}
