package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 002. 二进制加法
 *
 * @author dks233
 * @create 2022-07-16-23:36
 */
public class BinaryAdd {
    // 分析：从后往前遍历，依次相加，依次进位
    // 如果两个字符串长度不一致，前面按照0处理
    // 复杂度：O(N)+O(1)
    public String addBinary(String a, String b) {
        char[] strOne = a.toCharArray();
        char[] strTwo = b.toCharArray();
        int indexOne = strOne.length - 1;
        int indexTwo = strTwo.length - 1;
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        while (indexOne >= 0 || indexTwo >= 0) {
            int curOne = indexOne >= 0 ? strOne[indexOne] - '0' : 0;
            int curTwo = indexTwo >= 0 ? strTwo[indexTwo] - '0' : 0;
            // 当前数位的和+进位
            int curNum = curOne + curTwo + carry;
            // 拼接当前位二进制计算后的值
            builder.append(curNum >= 2 ? curNum - 2 : curNum);
            // 更新当前位向前一位的进位
            carry = curNum >= 2 ? 1 : 0;
            // 更新indexOne和indexTwo
            indexOne--;
            indexTwo--;
        }
        // 跳出循环，如果进位是1，需要拼接拼接一位
        if (carry == 1) {
            builder.append(carry);
        }
        return builder.reverse().toString();
    }
}
