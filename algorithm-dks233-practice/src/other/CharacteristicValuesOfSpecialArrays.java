package other;

/**
 * leetcode1608. 特殊数组的特征值
 *
 * @author dks233
 * @create 2022-09-12-8:00
 */
@SuppressWarnings("ALL")
public class CharacteristicValuesOfSpecialArrays {
    public int specialArray(int[] nums) {
        int[] counts = new int[1001];
        for (int i = 0; i < nums.length; i++) {
            counts[nums[i]]++;
        }
        int count = 0;
        for (int i = counts.length - 1; i >= 0; i--) {
            count += counts[i];
            if (count == i) {
                return i;
            }
        }
        return -1;
    }
}
