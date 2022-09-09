package slidingwindow;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode438. 找到字符串中所有字母异位词
 *
 * @author dks233
 * @create 2022-09-08-19:26
 */
@SuppressWarnings("ALL")
public class LetterEctopicWords {
    // 字符串中每个字符出现次数都相等即字母异位词
    // 统计p中每个字符出现的次数，维持一个滑动窗口，长度为p.length()
    // 滑动窗口一直往右滑，遇到字母异位词就将左窗口索引添加到列表中
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (p.length() > s.length()) {
            return list;
        }
        int[] pCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
        }
        // 构建第一个滑动窗口
        int[] curCount = new int[26];
        for (int i = 0; i < p.length(); i++) {
            curCount[s.charAt(i) - 'a']++;
        }
        int left = 0;
        int right = p.length() - 1;
        while (right < s.length()) {
            if (isEquals(curCount, pCount)) {
                list.add(left);
            }
            if (right + 1 < s.length()) {
                curCount[s.charAt(right) - 'a']++;
                curCount[s.charAt(left) - 'a']--;
            }
            left++;
            right++;
        }
        return list;
    }

    public boolean isEquals(int[] curCount, int[] pCount) {
        for (int i = 0; i < 26; i++) {
            if (curCount[i] != pCount[i]) {
                return false;
            }
        }
        return true;
    }
}
