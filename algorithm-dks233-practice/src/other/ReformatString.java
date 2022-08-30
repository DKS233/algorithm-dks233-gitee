package other;

/**
 * leetcode1417. 重新格式化字符串
 *
 * @author dks233
 * @create 2022-08-30-17:05
 */
public class ReformatString {
    public String reformat(String s) {
        // 统计字符和数字出现的次数
        StringBuilder charList = new StringBuilder();
        StringBuilder intList = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                intList.append(s.charAt(i));
            } else {
                charList.append(s.charAt(i));
            }
        }
        // 符合条件的情况：两个集合大小相等，相差一个
        if (Math.abs(charList.length() - intList.length()) > 1) {
            return "";
        }
        int n = charList.length();
        int m = intList.length();
        StringBuilder str = new StringBuilder();
        if (n > m) {
            char c = charList.charAt(n - 1);
            str.append(c);
            charList.deleteCharAt(n - 1);
        } else if (n < m) {
            char c = intList.charAt(m - 1);
            str.append(c);
            intList.deleteCharAt(m - 1);
        }
        // 到这儿两个字符串长度相等
        if (str.length() == 0 || (str.charAt(0) >= '0' && str.charAt(0) <= '9')) {
            for (int i = 0; i < charList.length(); i++) {
                str.append(charList.charAt(i));
                str.append(intList.charAt(i));
            }
        } else {
            for (int i = 0; i < charList.length(); i++) {
                str.append(intList.charAt(i));
                str.append(charList.charAt(i));
            }
        }
        return str.toString();
    }
}
