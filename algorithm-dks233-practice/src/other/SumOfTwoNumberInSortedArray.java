package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 006. 排序数组中两个数字之和
 *
 * @author dks233
 * @create 2022-07-18-10:31
 */
public class SumOfTwoNumberInSortedArray {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] > target) {
                right--;
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                break;
            }
        }
        return new int[]{left, right};
    }
}
