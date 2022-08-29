package other;

/**
 * leetcode1470. 重新排列数组
 *
 * @author dks233
 * @create 2022-08-29-0:03
 */
@SuppressWarnings("ALL")
public class RearrangeArray {
    // 0 n 1 n+1 2 n+2 ...
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[n * 2];
        int cur = 0;
        for (int i = 0; i < result.length; i++) {
            if (i % 2 == 0) {
                result[i] = nums[cur];
            } else {
                result[i] = nums[cur + n];
                cur++;
            }
        }
        return result;
    }
}
