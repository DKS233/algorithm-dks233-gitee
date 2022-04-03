package class05;

/**
 * 荷兰国旗问题
 * 一个数组中，大于x的放右边，等于x的放中间，小于x的放左边
 * 分析：大于区边界为arr.length，小于区边界为-1
 * 分析：当前数大于给定的值，和大于区的前一个数交换，大于区向左扩，当前数停在原地
 * 当前数小于给定的值，和小于区的下一个数交换，小于区向右扩，当前数跳到下一个数
 * 当前数等于给定的值，当前数直接跳下一个
 * 分析：小于区的下一个只有可能是当前数或者等于给定值的数
 *
 * @author dks233
 * @create 2022-04-03-12:09
 */
public class DutchNationalFlag {
    public static class NationalFlag {
        /**
         * 荷兰国旗问题，以arr[right]做划分
         *
         * @param arr   数组
         * @param left  数组左边界
         * @param right 数组右边界
         * @return 等于区的左右边界
         */
        private static int[] dutchNationalFlag(int[] arr, int left, int right) {
            if (left > right) {
                return new int[]{-1, -1};
            }
            if (left == right) {
                return new int[]{left, right};
            }
            int less = left - 1; // 小于区右边界
            int more = right; // 大于区左边界
            int index = left;
            // 当前位置，不能和大于区的左边界撞上
            while (index < more) {
                if (arr[index] == arr[right]) {
                    index++;
                } else if (arr[index] > arr[right]) {
                    swap(arr, index, more - 1);
                    more--;
                } else if (arr[index] < arr[right]) {
                    swap(arr, less + 1, index);
                    less++;
                    index++;
                }
            }
            swap(arr, more, right);
            return new int[]{less + 1, more};
        }

        private static void swap(int[] arr, int a, int b) {
            int temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }
    }

    // 荷兰国旗问题，以指定值做划分
    // leetcode75：数组中有三个数，012
    // 把0放左边，1放中间，2放右边
    public static class Solution {
        public void sortColors(int[] nums) {
            int less = -1;
            int more = nums.length;
            int index = 0;
            while (index < more) {
                if (nums[index] == 1) {
                    index++;
                } else if (nums[index] < 1) {
                    swap(nums, index++, ++less);
                } else if (nums[index] > 1) {
                    swap(nums, index, --more);
                }
            }
        }

        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }
}

