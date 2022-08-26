package other;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode179. 最大数
 *
 * @author dks233
 * @create 2022-08-26-16:38
 */
@SuppressWarnings("ALL")
public class MaxNumber {
    public String largestNumber(int[] nums) {
        boolean isAllZero = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                isAllZero = false;
                break;
            }
        }
        if (isAllZero) {
            return "0";
        }
        String[] copy = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i] + "";
        }
        Arrays.sort(copy, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < copy.length; i++) {
            builder.append(copy[i]);
        }
        return builder.toString();
    }


}
