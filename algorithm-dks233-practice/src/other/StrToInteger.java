package other;

/**
 * 剑指 Offer第二版 67. 把字符串转换成整数
 * 参考文档：https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/solution/mian-shi-ti-67-ba-zi-fu-chuan-zhuan-huan-cheng-z-4/
 *
 * @author dks233
 * @create 2022-06-19-23:53
 */
public class StrToInteger {
    public int strToInt(String str) {
        str = str.trim();
        // 只有空格的情况
        if (str.length() == 0) {
            return 0;
        }
        // 标志位，记录正负号，0表示没有正负号
        int signal = 0;
        // 数字开始的位置，如果有正负号，从1开始，如果没有正负号，从0开始
        int startIndex = 0;
        if (str.charAt(0) == '+') {
            startIndex = 1;
            signal = 1;
        } else if (str.charAt(0) == '-') {
            startIndex = 1;
            signal = -1;
        }
        int number = Integer.MAX_VALUE / 10;
        // 遍历到end位置累加的数
        int ans = 0;
        for (int end = startIndex; end < str.length(); end++) {
            if (str.charAt(end) > '9' || str.charAt(end) < '0') {
                return signal == 0 ? ans : signal * ans;
            }
            char endIndexChar = str.charAt(end);
            // 越界处理
            // 情况1：ans > number     ans * 10 + endIndexNumber肯定越界
            // 情况2：ans = number && endIndexNumber > '7'     ans * 10 + endIndexNumber肯定超过Integer.MAX_VALUE
            // endIndexNumber='8' endIndexNumber>'8' 这两种情况返回的都是 -Integer.MIN_VALUE
            if (ans > number || (ans == number && endIndexChar > '7')) {
                return signal < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + (endIndexChar - '0');
        }
        return signal == 0 ? ans : signal * ans;
    }

    public static void main(String[] args) {
        StrToInteger strToInteger = new StrToInteger();
        strToInteger.strToInt("  -0012a42");
    }
}
