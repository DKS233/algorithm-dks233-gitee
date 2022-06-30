package other;

/**
 * leetcode9. 回文数
 * 参考文档：https://leetcode.cn/problems/palindrome-number/solution/hui-wen-shu-by-leetcode-solution/
 *
 * @author dks233
 * @create 2022-06-30-10:58
 */
public class PalindromeInteger {
    // 将一半的数字反转，然后比较
    public boolean isPalindrome(int number) {
        // 特殊情况：最后一位是0
        if (number < 0 || (number % 10 == 0 && number != 0)) {
            return false;
        }
        int reverseNumber = 0;
        while (number > reverseNumber) {
            // 每轮取末尾数字，然后更新反转后的数字
            int end = number % 10;
            reverseNumber = reverseNumber * 10 + end;
            // 更新number
            number = number / 10;
        }
        // 1221 12321
        // 反转后结果分别是12+12,12+123
        return number == reverseNumber || number == reverseNumber / 10;
    }
}
