package sort_array;

import java.util.Arrays;

/**
 * 剑指 Offer第二版 45. 把数组排成最小的数
 * 参考文档：https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/solution/mian-shi-ti-45-ba-shu-zu-pai-cheng-zui-xiao-de-s-4/
 *
 * @author dks233
 * @create 2022-06-18-18:08
 */
public class ArrangeArrayToSmallNumber {
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (str1, str2) -> (str1 + str2).compareTo(str2 + str1));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            builder.append(strs[i]);
        }
        return builder.toString();
    }
}
