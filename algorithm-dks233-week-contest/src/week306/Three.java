package week306;

/**
 * 6150. 根据模式串构造最小数字
 * 参考文档：https://leetcode.cn/problems/construct-smallest-number-from-di-string/solution/c-by-endless_developy-hu0b/
 *
 * @author dks233
 * @create 2022-08-14-10:29
 */
@SuppressWarnings("ALL")
public class Three {
    // 构造一个升序字符串
    // 遇到I，不变
    // 遇到D，反转[当前字符,下一个不为D的字符位置]
    public String smallestNumber(String pattern) {
        int len = pattern.length();
        char[] str = new char[len + 1];
        for (int index = 0; index < str.length; index++) {
            str[index] = (char) (index + 1 + '0');
        }
        int cur = 0;
        while (cur < len) {
            if (pattern.charAt(cur) == 'I') {
                cur++;
            } else {
                int temp = cur;
                while (cur < len && pattern.charAt(cur) == 'D') {
                    cur++;
                }
                swap(str, temp, cur);
            }
        }
        return String.valueOf(str);
    }

    // str[left,right]范围内字符串反转
    // 0 1 2 3 4 5
    // 0 1 2 3 4
    public void swap(char[] str, int left, int right) {
        while (left < right) {
            char temp = str[left];
            str[left] = str[right];
            str[right] = temp;
            left++;
            right--;
        }
    }
}
