package other;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode2395. 和相等的子数组
 *
 * @author dks233
 * @create 2022-09-05-15:00
 */
@SuppressWarnings("ALL")
public class AndEqualSubarray {
    // 分析：set存和，重复就返回true
    public boolean findSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int cur = nums[i] + nums[i + 1];
            if (set.contains(cur)) {
                return true;
            } else {
                set.add(cur);
            }
        }
        return false;
    }
}
