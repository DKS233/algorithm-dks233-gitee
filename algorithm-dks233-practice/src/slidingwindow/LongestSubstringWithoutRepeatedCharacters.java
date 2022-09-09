package slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * leetcode3. 无重复字符的最长子串
 *
 * @author dks233
 * @create 2022-09-08-16:30
 */
@SuppressWarnings("ALL")
public class LongestSubstringWithoutRepeatedCharacters {
    public static class MethodOne {
        // 先一直扩充右边界，直到到达包含重复字符的边界（计算的时候不包括边界）
        // 然后更新最长字串长度，然后左边界向右，直到范围内的字符再次符合不重复的条件
        public int lengthOfLongestSubstring(String s) {
            char[] str = s.toCharArray();
            Set<Character> set = new HashSet<>();
            int left = 0;
            int right = 0;
            int maxLen = 1;
            while (right < str.length) {
                // right到达第一次不符合条件的位置，统计长度（不包含right位置）
                while (right < str.length && !set.contains(str[right])) {
                    set.add(str[right]);
                    right++;
                }
                // 更新最大长度
                maxLen = Math.max(right - left, maxLen);
                // 收缩左边界，直到再次符合条件
                if (right < str.length) {
                    char c = str[right];
                    while (set.contains(c) && left < str.length) {
                        set.remove(str[left]);
                        left++;
                    }
                }
            }
            return maxLen;
        }
    }
}
