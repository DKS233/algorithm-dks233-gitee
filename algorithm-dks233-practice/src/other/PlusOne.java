package other;

/**
 * leetcode66. 加一
 *
 * @author dks233
 * @create 2022-07-06-20:53
 */
@SuppressWarnings("ALL")
public class PlusOne {
    // O(N)+O(1)
    public int[] plusOne(int[] digits) {
        for (int index = digits.length - 1; index >= 0; index--) {
            digits[index]++;
            // 进位后不等于10，直接返回
            if (digits[index] % 10 != 0) {
                return digits;
            }
            // 进位后等于10，需要将10变成0，然后去高位上计算
            else {
                digits[index] = digits[index] % 10;
            }
        }
        // 跳出循环说明原始数组全是9，加完后0位置是10，其他位置全是0，这时需要添加位，向高位进1
        int[] nums = new int[digits.length + 1];
        nums[0] = 1;
        return nums;
    }
}
