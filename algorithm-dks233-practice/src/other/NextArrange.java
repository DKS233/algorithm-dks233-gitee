package other;

/**
 * leetcode31. 下一个排列
 *
 * @author dks233
 * @create 2022-07-05-17:18
 */
@SuppressWarnings("ALL")
public class NextArrange {
    // 思路
    // 思考1：下一个排列字典序更大，将右边的某个大数和左边的某个小数互换
    // 思考2：下一个排列增加幅度尽可能小，所以尽可能在低位进行交换，将尽可能小的大数和小数进行交换，然后大数右边的数改成升序
    // 过程
    // 从右往左遍历，找到第一个升序排列，记为[left,firstRight]
    // 此时right右边的数肯定是降序排列（因为是从右往左遍历的）
    // 从右往左遍历，找到第一个大于num[left]的数，此时找到了左右需要互换的数，记为[left,right]
    // 左右两个位置的数互换，然后将大数右边改成升序
    // 如果找不到升序排列，说明当前数组排列就是字典序最大，整个数组改为升序（字典序最小）
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        int left = nums.length - 2;
        int firstRight = nums.length - 1;
        int right = nums.length - 1;
        // 找到第一个升序排列
        while (left >= 0 && nums[left] >= nums[firstRight]) {
            left--;
            firstRight--;
        }
        // left<0说明找不到升序排列，整个数组直接逆序
        // 此时firstRight=0
        if (left >= 0) {
            // 此时[firstRight,nums.length-1]范围内的数肯定是降序排列
            // 从右往左遍历，找到第一个大于nums[left]的数所在位置，即right位置
            while (right >= firstRight) {
                if (nums[right] > nums[left]) {
                    break;
                }
                right--;
            }
            // 将left和right位置的数交换，然后将left后面的数改成升序，即把[firstRight,nums.length-1]范围内的数改成升序（现在是降序）
            swap(nums, left, right);
        }
        int leftIndex = firstRight;
        int rightIndex = nums.length - 1;
        while (leftIndex < rightIndex) {
            swap(nums, leftIndex, rightIndex);
            leftIndex++;
            rightIndex--;
        }
    }

    public void swap(int[] nums, int leftIndex, int rightIndex) {
        int temp = nums[leftIndex];
        nums[leftIndex] = nums[rightIndex];
        nums[rightIndex] = temp;
    }
}
