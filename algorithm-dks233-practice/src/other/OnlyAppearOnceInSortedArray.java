package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 070. 排序数组中只出现一次的数字
 *
 * @author dks233
 * @create 2022-07-25-23:13
 */
@SuppressWarnings("ALL")
public class OnlyAppearOnceInSortedArray {
    // O(logN)的解法
    public static class MethodTwo {
        // 二分法
        // mid位置三种情况：和前面一个数相等，和后面一个数相等，mid情况即为只出现一次的数字
        public int singleNonDuplicate(int[] nums) {
            int len = nums.length;
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                // 情况1：和前面一个数相等
                // 如果mid是奇数，说明[0,mid]为偶数长度，出现一次的数字肯定在mid右边
                // 如果mid是偶数，说明出现一次的数字肯定在mid-1左边
                if (mid > 0 && nums[mid] == nums[mid - 1]) {
                    if (mid % 2 != 0) {
                        left = mid + 1;
                    } else {
                        right = mid - 2;
                    }
                }
                // 情况2：和后面一个数相等
                // 如果mid是奇数，说明mid+1是偶数，[0,mid+1]为奇数，所求数字在mid左边
                // 如果mid是偶数，说明mid+1是奇数，[0,mid+1]为偶数，所求数字在mid+1右边
                else if (mid < len - 1 && nums[mid] == nums[mid + 1]) {
                    if (mid % 2 != 0) {
                        right = mid - 1;
                    } else {
                        left = mid + 2;
                    }
                }
                // 情况3：mid不和前后数字相等，mid位置数即为所求
                else {
                    return nums[mid];
                }
            }
            return -1;
        }
    }

    // O(N)的解法
    public static class MethodOne {
        public int singleNonDuplicate(int[] nums) {
            int ans = 0;
            for (int index = 0; index < nums.length; index++) {
                ans ^= nums[index];
            }
            return ans;
        }
    }
}
