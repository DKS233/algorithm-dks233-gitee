package other;

/**
 * leetcode344. 反转字符串
 *
 * @author dks233
 * @create 2022-07-09-16:15
 */
public class ReverseString {
    public void reverseString(char[] s) {
        int leftIndex = 0;
        int rightIndex = s.length - 1;
        while (leftIndex < rightIndex) {
            char temp = s[leftIndex];
            s[leftIndex] = s[rightIndex];
            s[rightIndex] = temp;
            leftIndex++;
            rightIndex--;
        }
    }
}
