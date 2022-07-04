package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * leetcode49. 字母异位词分组
 *
 * @author dks233
 * @create 2022-07-04-19:30
 */
@SuppressWarnings("ALL")
public class AlphabeticHeterotopicWordGrouping {
    // 对每个字符串进行排序，异位词和原单词排序后是相同的
    // 用HashMap存字母异位词的组合，key:排序后的字符串，value：对应的异位词
    // 时间复杂度：O(NKlogK)  n是字符串数组长度，k是数组中最大字符串长度
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            hashMap.putIfAbsent(String.valueOf(chars), new ArrayList<String>());
            hashMap.get(String.valueOf(chars)).add(str);
        }
        List<List<String>> list = new ArrayList<>();
        hashMap.forEach((str, strList) -> {
            list.add(strList);
        });
        return list;
    }

    // 方法2 不用排序
    // 用字符和词频拼接成的字符串当做key
    // 时间复杂度：O(NK) N是字符串数组长度 K是数组中字符串的最大长度
    public static class MethodTwo {
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, List<String>> hashMap = new HashMap<>();
            for (String str : strs) {
                // 统计str的字符和字符出现的次数
                int[] counts = new int[26];
                char[] chars = str.toCharArray();
                for (int index = 0; index < chars.length; index++) {
                    counts[chars[index] - 'a']++;
                }
                // 拼接字符和字符出现的次数作为哈希表的key
                StringBuilder builder = new StringBuilder();
                for (int index = 0; index < counts.length; index++) {
                    if (counts[index] != 0) {
                        builder.append((char) ('a' + index)).append(counts[index]);
                    }
                }
                // 更新/新增key对应的字符串列表
                hashMap.putIfAbsent(builder.toString(), new ArrayList<String>());
                hashMap.get(builder.toString()).add(str);
            }
            List<List<String>> list = new ArrayList<>();
            for (List<String> value : hashMap.values()) {
                list.add(value);
            }
            return list;
        }
    }
}
