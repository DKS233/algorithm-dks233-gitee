package other;

import java.util.HashSet;

/**
 * 剑指 Offer第二版  38. 字符串的排列
 *
 * @author dks233
 * @create 2022-06-18-23:32
 */
public class ArrangementOfString {
    public static class MethodOne {
        public String[] permutation(String s) {
            if (s == null || s.length() == 0) {
                return new String[0];
            }
            if (s.length() == 1) {
                return new String[]{s};
            }
            char[] chars = s.toCharArray();
            return null;
        }

        public void process(char[] chars, int index, HashSet<String> hashSet) {
            if (index == chars.length) {

            }

        }
    }
}
