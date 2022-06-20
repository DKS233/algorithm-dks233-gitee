package other;

/**
 * 剑指 Offer第二版66. 构建乘积数组
 * 参考文档：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/mian-shi-ti-66-gou-jian-cheng-ji-shu-zu-biao-ge-fe/
 *
 * @author dks233
 * @create 2022-06-19-23:38
 */
public class CreateMultiplyArray {
    // 时间复杂度：O(N)
    // 空间复杂度：O(N)
    public int[] constructArr(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int length = nums.length;
        int[] left = new int[length];
        int[] right = new int[length];
        left[0] = 1;
        right[length - 1] = 1;
        // 先求上三角的乘积（每一行）
        for (int i = 1; i < length; i++) {
            left[i] = nums[i - 1] * left[i - 1];
        }
        // 再求下三角的乘积（每一行）
        for (int i = length - 2; i >= 0; i--) {
            right[i] = nums[i + 1] * right[i + 1];
        }
        // 构建乘积数组，每行的成绩=每行上三角的乘积*每行下三角的乘积
        int[] ans = new int[length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }
}
