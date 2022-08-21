package week307;

/**
 * 6166. 最大回文数字
 *
 * @author dks233
 * @create 2022-08-21-10:11
 */
public class Two {
    // 先填两边（出现偶数次的字符），然后填中间的奇数字符串
    // 考虑第一个数字是0的情况
    public String largestPalindromic(String str) {
        int[] counts = new int[10];
        for (int i = 0; i < str.length(); i++) {
            counts[str.charAt(i) - '0']++;
        }
        // 所有字符都是0，返回"0"
        if (counts[0] == str.length()) {
            return "0";
        }
        // 出现偶数次的字符，填到两边
        // 左边字符串+中间字符串+左边字符串反转
        // 特殊情况：只有0出现了超过两次，第一个字符是0
        boolean flag = true;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= 2 && i != 0) {
                flag = false;
                break;
            }
        }
        // 步骤1：计算左边字符串
        StringBuilder leftStr = new StringBuilder();
        if (!flag) {
            for (int index = counts.length - 1; index >= 0; index--) {
                while (counts[index] >= 2) {
                    leftStr.append(index);
                    counts[index] -= 2;
                }
            }
        }
        // 步骤2：计算中间字符串（只可以填一个字符）
        StringBuilder midStr = new StringBuilder();
        for (int index = counts.length - 1; index >= 0; index--) {
            if (counts[index] > 0) {
                midStr.append(index);
                break;
            }
        }
        // 步骤3：字符串合并
        return leftStr.toString() + midStr + leftStr.reverse();
    }
}
