package other;

/**
 * 剑指 Offer第二版 15. 二进制中1的个数
 *
 * @author dks233
 * @create 2022-06-20-23:44
 */
public class NumberOfOneInBinary {
    // 13->1101
    // 1->0001
    public int hammingWeight(int n) {
        int[] arr = new int[32];
        for (int i = 0; i < 32; i++) {
            arr[i] = (n >> i) & 1;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                ans += 1;
            }
        }
        return ans;
    }
}
