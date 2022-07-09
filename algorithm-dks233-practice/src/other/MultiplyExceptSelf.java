package other;

/**
 * leetcode238. 除自身以外数组的乘积
 * 参考文档：https://leetcode.cn/problems/product-of-array-except-self/solution/product-of-array-except-self-shang-san-jiao-xia-sa/
 *
 * @author dks233
 * @create 2022-07-09-15:32
 */
@SuppressWarnings("ALL")
public class MultiplyExceptSelf {
    // O(N)+O(1)
    // 得到左下角和右上角两个三角的值，然后对应行相乘
    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        // 左下角
        int leftDown = 1;
        for (int index = 0; index < nums.length; index++) {
            ans[index] = leftDown;
            leftDown *= nums[index];
        }
        // 右上角
        int rightUp = 1;
        for (int index = nums.length - 1; index > 0; index--) {
            rightUp *= nums[index];
            ans[index - 1] *= rightUp;
        }
        return ans;
    }
}
