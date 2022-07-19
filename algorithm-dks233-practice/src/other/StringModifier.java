package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 014. 字符串中的变位词
 * 剑指offer专项突击版：剑指 Offer II 015. 字符串中的所有变位词
 *
 * @author dks233
 * @create 2022-07-19-11:37
 */
@SuppressWarnings("ALL")
public class StringModifier {
    // 问题一
    // 滑动窗口优化版，不用每个窗口都新建一个数组
    public static class MethodTwo {
        public boolean checkInclusion(String s1, String s2) {
            char[] strOne = s1.toCharArray();
            char[] strTwo = s2.toCharArray();
            // 统计s1中各字符出现的次数
            int[] charCount = new int[26];
            for (int index = 0; index < strOne.length; index++) {
                charCount[strOne[index] - 'a']++;
            }
            // 根据s1的长度去s2中检索对应长度的子串是否符合
            if (strTwo.length < strOne.length) {
                return false;
            }
            int left = 0;
            int right = strOne.length - 1;
            int[] copy = new int[26];
            for (int index = left; index <= right; index++) {
                copy[strTwo[index] - 'a']++;
            }
            while (true) {
                if (Arrays.equals(copy, charCount)) {
                    return true;
                }
                copy[strTwo[left++] - 'a']--;
                if (right + 1 < strTwo.length) {
                    copy[strTwo[++right] - 'a']++;
                } else {
                    break;
                }
            }
            return false;
        }
    }

    // 问题一：滑动窗口未优化版
    // 统计s1的词频，在s2中滑动区间判断
    public static class MethodOne {
        public boolean checkInclusion(String s1, String s2) {
            char[] strOne = s1.toCharArray();
            char[] strTwo = s2.toCharArray();
            // 统计s1中各字符出现的次数
            int[] charCount = new int[26];
            for (int index = 0; index < strOne.length; index++) {
                charCount[strOne[index] - 'a']++;
            }
            // 根据s1的长度去s2中检索对应长度的子串是否符合
            if (strTwo.length < strOne.length) {
                return false;
            }
            int left = 0;
            int right = strOne.length - 1;
            while (right < strTwo.length) {
                int[] compare = new int[26];
                for (int index = left; index <= right; index++) {
                    compare[strTwo[index] - 'a']++;
                }
                if (Arrays.equals(charCount, compare)) {
                    return true;
                }
                left++;
                right++;
            }
            return false;
        }
    }

    // 问题二：参考问题一方法二
    public static class ProblemTwoMethodOne {
        // 找到s2中所有s1的变位词，输出起始索引
        public List<Integer> findAnagrams(String s2, String s1) {
            List<Integer> list = new ArrayList<>();
            char[] strOne = s1.toCharArray();
            char[] strTwo = s2.toCharArray();
            // 统计s1中各字符出现的次数
            int[] charCount = new int[26];
            for (int index = 0; index < strOne.length; index++) {
                charCount[strOne[index] - 'a']++;
            }
            // 根据s1的长度去s2中检索对应长度的子串是否符合
            if (strTwo.length < strOne.length) {
                return list;
            }
            int left = 0;
            int right = strOne.length - 1;
            int[] copy = new int[26];
            for (int index = left; index <= right; index++) {
                copy[strTwo[index] - 'a']++;
            }
            while (true) {
                if (Arrays.equals(copy, charCount)) {
                    list.add(left);
                }
                copy[strTwo[left++] - 'a']--;
                if (right + 1 < strTwo.length) {
                    copy[strTwo[++right] - 'a']++;
                } else {
                    break;
                }
            }
            return list;
        }
    }
}
