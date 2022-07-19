package other;

import java.util.HashSet;

/**
 * 剑指offer专项突击版：剑指 Offer II 016. 不含重复字符的最长子字符串
 *
 * @author dks233
 * @create 2022-07-19-16:35
 */
@SuppressWarnings("ALL")
public class LengthOfLongestSubstringNoRepeat {
    // 时间复杂度：O(N)
    public int lengthOfLongestSubstring(String s) {
        char[] str = s.toCharArray();
        int left = 0;
        int right = 0;
        HashSet<Character> set = new HashSet<>();
        int maxLen = 0;
        while (left < str.length) {
            // left=0时，计算不重复元素
            // left>0时，左窗口元素删除（当前左窗口已经计算过了）
            if (left > 0) {
                set.remove(str[left - 1]);
            }
            // 更新右边界，保持区间内元素不重复
            while (right < str.length && !set.contains(str[right])) {
                set.add(str[right]);
                right++;
            }
            // 跳出循环时右边界到数组最后或出现了重复元素，更新最长连续子字符串长度
            maxLen = Math.max(maxLen, right - left);
            // 更新左边界
            left++;
        }
        return maxLen;
    }
}
