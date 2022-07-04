package other;

/**
 * leetcode136. 只出现一次的数字
 *
 * @author dks233
 * @create 2022-07-04-16:39
 */
@SuppressWarnings("ALL")
public class ElementAppearOnce {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int index = 0; index < nums.length; index++) {
            ans ^= nums[index];
        }
        return ans;
    }
}
