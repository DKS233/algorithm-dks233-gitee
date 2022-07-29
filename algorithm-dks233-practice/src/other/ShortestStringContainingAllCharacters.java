package other;

/**
 * 剑指offer专项突击版：剑指 Offer II 017. 含有所有字符的最短字符串
 * 参考文档：https://leetcode.cn/problems/minimum-window-substring/solution/tong-su-qie-xiang-xi-de-miao-shu-hua-dong-chuang-k/
 *
 * @author dks233
 * @create 2022-07-29-20:07
 */
@SuppressWarnings("ALL")
public class ShortestStringContainingAllCharacters {
    // 思路：滑动窗口
    // left不动，right一直右移，直到包含t中所有字符
    // left右移，直到包含的临界点，记录当前长度
    // left继续右移，滑动窗口不满足条件，right右移，寻找新的滑动窗口
    public String minWindow(String s, String t) {
        int leftAns = 1;
        int rightAns = Integer.MAX_VALUE;
        // t的字符统计
        int[] targetCount = new int[128];
        for (int index = 0; index < t.length(); index++) {
            targetCount[t.charAt(index)]++;
        }
        // 当前窗口内的字符统计
        int[] curCount = new int[128];
        int left = 0, right = 0;
        while (right < s.length()) {
            // left不动，right一直右移，直到包含t中所有字符
            while (!compare(targetCount, curCount) && right < s.length()) {
                curCount[s.charAt(right)]++;
                right++;
            }
            if (right == s.length()) {
                break;
            }
            // left右移，直到包含的临界点
            while (left < right && (targetCount[s.charAt(left)] == 0 ||
                    (targetCount[s.charAt(left)] > 0 && curCount[s.charAt(left)] > targetCount[s.charAt(left)]))) {
                curCount[s.charAt(left)]--;
                left++;
            }
            // 记录当前滑动窗口的字符，更新最短字符串
            if (right - left + 1 < rightAns - leftAns + 1) {
                leftAns = left;
                rightAns = right;
            }
            curCount[s.charAt(left)]--;
            left++;
        }
        return rightAns == Integer.MAX_VALUE ? "" : s.substring(leftAns, rightAns + 1);
    }

    // 判断curCount是否包含targetCount的所有字符
    public boolean compare(int[] targetCount, int[] curCount) {
        for (int index = 0; index < targetCount.length; index++) {
            if (targetCount[index] > 0 && targetCount[index] > curCount[index]) {
                return false;
            }
        }
        return true;
    }
}
