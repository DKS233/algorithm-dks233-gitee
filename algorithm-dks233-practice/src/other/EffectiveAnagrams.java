package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 032. 有效的变位词
 * 剑指offer专项突击版：剑指 Offer II 033. 变位词组
 *
 * @author dks233
 * @create 2022-07-21-13:04
 */
@SuppressWarnings("ALL")
public class EffectiveAnagrams {
    // 统计字符数
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        if (s.equals(t)) {
            return false;
        }
        int[] charCount = new int[26];
        for (int index = 0; index < s.length(); index++) {
            charCount[s.charAt(index) - 'a']++;
        }
        for (int index = 0; index < t.length(); index++) {
            charCount[t.charAt(index) - 'a']--;
        }
        for (int index = 0; index < charCount.length; index++) {
            if (charCount[index] != 0) {
                return false;
            }
        }
        return true;
    }

    // 问题二：方法1：对所有字符串进行排序，变位词排序后都是相等的
    public List<List<String>> groupAnagrams(String[] strs) {
        // key:排序后的字符串 value:对应的变位词列表
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            hashMap.putIfAbsent(String.valueOf(chars), new ArrayList<>());
            hashMap.get(String.valueOf(chars)).add(str);
        }
        List<List<String>> list = new ArrayList<>();
        hashMap.values().forEach((singleList -> {
            list.add(new ArrayList<>(singleList));
        }));
        return list;
    }

    // 问题二：方法2：每个字符加上其出现的次数拼接作为哈希表的key
    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            int[] charCount = new int[26];
            for (int index = 0; index < str.length(); index++) {
                charCount[str.charAt(index) - 'a']++;
            }
            StringBuilder builder = new StringBuilder();
            for (int index = 0; index < charCount.length; index++) {
                if (charCount[index] != 0) {
                    builder.append(index + 'a').append(charCount[index]);
                }
            }
            hashMap.putIfAbsent(builder.toString(), new ArrayList<>());
            hashMap.get(builder.toString()).add(str);
        }
        List<List<String>> list = new ArrayList<>();
        hashMap.values().forEach(singleList -> {
            list.add(singleList);
        });
        return list;
    }
}












