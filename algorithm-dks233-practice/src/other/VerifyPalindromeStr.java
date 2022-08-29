package other;

/**
 * leetcode125. 验证回文串
 *
 * @author dks233
 * @create 2022-08-29-10:43
 */
@SuppressWarnings("ALL")
public class VerifyPalindromeStr {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if ((s.charAt(i) >= '0' && s.charAt(i) <= '9') || (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')) {
                builder.append(s.charAt(i));
            }
            i++;
        }
        int left = 0;
        int right = builder.length() - 1;
        while (left <= right) {
            if (builder.charAt(left) != builder.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        VerifyPalindromeStr str = new VerifyPalindromeStr();
        str.isPalindrome("0P");
    }
}
