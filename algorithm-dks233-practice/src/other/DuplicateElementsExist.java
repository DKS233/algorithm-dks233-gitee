package other;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode217. 存在重复元素
 *
 * @author dks233
 * @create 2022-07-09-10:02
 */
@SuppressWarnings("ALL")
public class DuplicateElementsExist {
    // 复杂度：O(N)+O(N)
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int index = 0; index < nums.length; index++) {
            if (!set.contains(nums[index])) {
                set.add(nums[index]);
            } else {
                return true;
            }
        }
        return false;
    }
}
