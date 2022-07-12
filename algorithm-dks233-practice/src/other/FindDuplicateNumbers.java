package other;

/**
 * leetcode287. 寻找重复数
 *
 * @author dks233
 * @create 2022-07-12-17:14
 */
@SuppressWarnings("ALL")
public class FindDuplicateNumbers {
    // 参考leetcode142：寻找环的入口节点
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
