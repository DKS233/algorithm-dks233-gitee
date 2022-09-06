package other;

import java.util.*;

/**
 * leetcode828. 统计子串中的唯一字符
 * 参考文档：https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string/solution/by-ac_oier-922k/
 *
 * @author dks233
 * @create 2022-09-06-8:35
 */
@SuppressWarnings("ALL")
public class CountUniqueCharInSubstring {
    // 题目要求：求每个子串中只出现一次的字符的总和
    // 分析：求每个字符在多少个子串中只出现一次，每有一个子串，累加一次
    // 分析：假设在某个区间内，某个字符只出现一次，那这个字符贡献的子串数量是：
    // 这个字符到区间左边界的长度*这个字符到区间右边界的长度（左右边界长度都包括该字符本身）
    public int uniqueLetterString(String s) {
        char[] str = s.toCharArray();
        // 记录字符上次出现的位置
        int[] lastIndexs = new int[26];
        // str[i]作为子区间内唯一字符能到达的最远左边界和右边界
        // 左右边界表示不能到达
        int[] left = new int[str.length];
        int[] right = new int[str.length];
        Arrays.fill(lastIndexs, -1);
        for (int i = 0; i < str.length; i++) {
            left[i] = lastIndexs[str[i] - 'A'];
            lastIndexs[str[i] - 'A'] = i;
        }
        Arrays.fill(lastIndexs, str.length);
        for (int i = str.length - 1; i >= 0; i--) {
            right[i] = lastIndexs[str[i] - 'A'];
            lastIndexs[str[i] - 'A'] = i;
        }
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            count += (i - left[i]) * (right[i] - i);
        }
        return count;
    }
}
