package other;

import java.util.HashMap;

/**
 * leetcode454. 四数相加 II
 *
 * @author dks233
 * @create 2022-08-28-20:17
 */
@SuppressWarnings("ALL")
public class FourNumberSum {
    // 两个数组分组，然后看是否能合并成0
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                hashMap.put(sum, hashMap.getOrDefault(sum, 0) + 1);
            }
        }
        int count = 0;
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int sum = nums3[i] + nums4[j];
                count += hashMap.getOrDefault(-sum, 0);
            }
        }
        return count;
    }
}
