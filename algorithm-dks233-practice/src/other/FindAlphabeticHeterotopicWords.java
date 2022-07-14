package other;

import java.util.*;

/**
 * leetcode438. 找到字符串中所有字母异位词
 *
 * @author dks233
 * @create 2022-07-14-17:55
 */
public class FindAlphabeticHeterotopicWords {
    // 滑动窗口
    // 时间复杂度：O(N)
    public static class MethodOne {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> list = new ArrayList<>();
            // 滑动窗口字符串各字符出现的次数
            int[] countOne = new int[26];
            // p中各字符出现的次数
            int[] countTwo = new int[26];
            for (int index = 0; index < p.length(); index++) {
                countTwo[p.charAt(index) - 'a']++;
            }
            int left = 0;
            int right = p.length() - 1;
            if (right >= s.length()) {
                return list;
            }
            for (int index = left; index <= right; index++) {
                countOne[s.charAt(index) - 'a']++;
            }
            while (true) {
                if (isDiffLocationWord(countOne, countTwo)) {
                    list.add(left);
                }
                // 更新窗口，left右移，right右移
                // countOne数组体现为去左加右
                countOne[s.charAt(left++) - 'a']--;
                if (right + 1 >= s.length()) {
                    break;
                } else {
                    countOne[s.charAt(++right) - 'a']++;
                }
            }
            return list;
        }

        public boolean isDiffLocationWord(int[] countOne, int[] countTwo) {
            for (int index = 0; index < countOne.length; index++) {
                if (countOne[index] != countTwo[index]) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        MethodOne methodOne = new MethodOne();
        methodOne.findAnagrams("cbaebabacd", "abc");
    }
}
