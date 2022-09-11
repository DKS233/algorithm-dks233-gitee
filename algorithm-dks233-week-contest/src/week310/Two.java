package week310;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 6177. 子字符串的最优划分
 *
 * @author dks233
 * @create 2022-09-11-10:25
 */
@SuppressWarnings("ALL")
public class Two {
    // 贪心：每一步尽可能包括多的字符，直到有重复
    public int partitionString(String s) {
        HashSet<Character> set = new HashSet<>();
        char[] str = s.toCharArray();
        int minCount = 0;
        int right = 0;
        while (right < str.length) {
            // 右边界一直扩，直到出现重复的字符，切割，计数
            // 此时的right就是下一个字符串的起始位置，需要重新计数，所以set清空
            while (right < str.length && !set.contains(str[right])) {
                set.add(str[right]);
                right++;
            }
            minCount = minCount + 1;
            set.clear();
        }
        return minCount;
    }

    // 数组写法
    public int partitionString2(String s) {
        int[] counts = new int[26];
        char[] str = s.toCharArray();
        int minCount = 0;
        int right = 0;
        while (right < str.length) {
            // 右边界一直扩，直到出现重复的字符，切割，计数
            // 此时的right就是下一个字符串的起始位置，需要重新计数，所以set清空
            while (right < str.length && counts[str[right] - 'a'] == 0) {
                counts[str[right] - 'a']++;
                right++;
            }
            minCount = minCount + 1;
            Arrays.fill(counts, 0);
        }
        return minCount;
    }
}
