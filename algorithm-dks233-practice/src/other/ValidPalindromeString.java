package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 018. 有效的回文
 * 剑指offer专项突击版：剑指 Offer II 019. 最多删除一个字符得到回文
 *
 * @author dks233
 * @create 2022-07-19-22:27
 */
@SuppressWarnings("ALL")
public class ValidPalindromeString {
    public static class ProblemOne {
        public boolean isPalindrome(String s) {
            char[] str = s.toCharArray();
            int left = 0;
            int right = str.length - 1;
            while (left < right) {
                while (left < right && !isLegal(str[left])) {
                    left++;
                }
                while (left < right && !isLegal(str[right])) {
                    right--;
                }
                if (left < right && !isEquals(str[left], str[right])) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

        // 判断字符是否是数字或字母
        public boolean isLegal(char c) {
            return c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
        }

        // 判断两个字符忽略大小写是否相等
        public boolean isEquals(char cOne, char cTwo) {
            if (cOne >= 'A' && cOne <= 'Z') {
                cOne += 32;
            }
            if (cTwo >= 'A' && cTwo <= 'Z') {
                cTwo += 32;
            }
            return cOne == cTwo;
        }
    }

    public static class MethodTwo {
        public boolean validPalindrome(String s) {
            char[] str = s.toCharArray();
            int left = 0;
            int right = str.length - 1;
            while (left < right) {
                if (str[left] != str[right]) {
                    return process(str, left + 1, right) || process(str, left, right - 1);
                }
                left++;
                right--;
            }
            return true;
        }

        // 判断str在left到right范围内是否是回文
        // 特殊情况：left=right，一个字符肯定是回文，返回true
        public boolean process(char[] str, int left, int right) {
            while (left < right) {
                if (str[left] != str[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
}
