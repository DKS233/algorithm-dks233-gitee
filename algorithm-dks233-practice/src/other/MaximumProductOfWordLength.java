package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 005. 单词长度的最大乘积
 *
 * @author dks233
 * @create 2022-07-18-10:07
 */
@SuppressWarnings("ALL")
public class MaximumProductOfWordLength {
    // 时间复杂度：O(N*N)
    public int maxProduct(String[] words) {
        // 二进制位表示每个字符是否出现过，a表示从右往左第0位
        // 0111 表示abc
        // 0011 表示ab
        int[] nums = new int[words.length];
        for (int index = 0; index < words.length; index++) {
            for (int digit = 0; digit < words[index].length(); digit++) {
                nums[index] |= (1 << (words[index].charAt(digit) - 'a'));
            }
        }
        // 如果两个字符不包含相同字符，二进制与运算结果为0
        int maxLen = 0;
        for (int first = 0; first < nums.length; first++) {
            for (int second = first + 1; second < nums.length; second++) {
                if ((nums[first] & nums[second]) == 0) {
                    maxLen = Math.max(maxLen, words[first].length() * words[second].length());
                }
            }
        }
        return maxLen;
    }
}
