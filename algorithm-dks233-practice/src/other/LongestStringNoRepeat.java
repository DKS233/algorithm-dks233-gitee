package other;

import java.util.HashSet;

/**
 * 剑指 Offer第二版 48. 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度
 * 参考文档：https://leetcode.cn/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
 *
 * @author dks233
 * @create 2022-06-20-21:26
 */
public class LongestStringNoRepeat {
    // 滑动窗口
    // O(N) + O(1)
    public static class MethodOne {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            int ans = 1;
            // 滑动窗口左右边界，左闭右闭
            int left = 0;
            int right = 0;
            // HashSet存放窗口内的元素
            HashSet<Character> set = new HashSet<>();
            set.add(s.charAt(0));
            while (left <= s.length() - 1) {
                // 窗口删除最左边元素
                if (left > 0) {
                    set.remove(s.charAt(left - 1));
                }
                // 右边界右移，保持窗口内字符是不重复的
                while (right < s.length() - 1 && !set.contains(s.charAt(right + 1))) {
                    set.add(s.charAt(right + 1));
                    right++;
                }
                // 更新ans，当前窗口内长度是right-left+1
                ans = Math.max(right - left + 1, ans);
                // 左边界右移
                left++;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        MethodOne methodOne = new MethodOne();
        System.out.println(methodOne.lengthOfLongestSubstring("au"));
    }
}
