package other;

/**
 * leetcode面试题 01.01. 判定字符是否唯一
 * https://leetcode.cn/problems/is-unique-lcci/
 *
 * @author dks233
 * @create 2022-09-11-20:51
 */
@SuppressWarnings("ALL")
public class WhetherCharactersAreUnique {
    public static class MethodOne {
        public boolean isUnique(String str) {
            int[] count = new int[26];
            for (int i = 0; i < str.length(); i++) {
                if (count[str.charAt(i) - 'a'] != 0) {
                    return false;
                }
                count[str.charAt(i) - 'a']++;
            }
            return true;
        }
    }

    // 每出现一位用一个二进制位标识
    public static class MethodTwo {
        public boolean isUnique(String str) {
            // 32位的二进制整数
            // 遍历字符，如果之前没出现过这个字符，将从右往左第c个二进制标识为1
            // 后续用(1<<c)和cur做与运算，如果之前出现过这个字符，该位置上已经是1，与运算结果不等于0
            // 如果与运算结果是0，说明之前没出现过这个字符，将第c个二进制位标记为1
            int cur = 0;
            for (int i = 0; i < str.length(); i++) {
                int c = str.charAt(i) - 'a';
                if (((1 << c) & cur) != 0) {
                    return false;
                }
                cur |= (1 << c);
            }
            return true;
        }
    }
}
