package other;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 剑指 Offer第二版 50. 第一个只出现一次的字符
 *
 * @author dks233
 * @create 2022-06-18-22:58
 */
public class FirstAppearOnceCharacter {
    // hashMap记录每个字母出现的频率
    public char firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (hashMap.containsKey(chars[i])) {
                hashMap.put(chars[i], hashMap.get(chars[i]) + 1);
            } else {
                hashMap.put(chars[i], 1);
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (hashMap.get(chars[i]) == 1) {
                return chars[i];
            }
        }
        return ' ';
    }

    // hashMap记录每个字母是否只出现了一次
    public static class MethodTwo {
        public char firstUniqChar(String s) {
            char[] chars = s.toCharArray();
            HashMap<Character, Boolean> hashMap = new HashMap<>();
            // 第一次插入的时候变成true，第二次开始插入就变成false了
            for (int i = 0; i < chars.length; i++) {
                hashMap.put(chars[i], !hashMap.containsKey(chars[i]));
            }
            for (int i = 0; i < chars.length; i++) {
                if (hashMap.get(chars[i])) {
                    return chars[i];
                }
            }
            return ' ';
        }
    }

    // 用LinkedHashMap，按照插入顺序排序，免去第二轮检索chars数组
    // LinkedHashMap记录每个字母是否只出现了一次
    public static class MethodThree {
        public char firstUniqChar(String s) {
            char[] chars = s.toCharArray();
            LinkedHashMap<Character, Boolean> hashMap = new LinkedHashMap<>();
            // 第一次插入的时候变成true，第二次开始插入就变成false了
            for (int i = 0; i < chars.length; i++) {
                hashMap.put(chars[i], !hashMap.containsKey(chars[i]));
            }
            for (Map.Entry<Character, Boolean> entry : hashMap.entrySet()) {
                if (entry.getValue()) {
                    return entry.getKey();
                }
            }
            return ' ';
        }
    }
}
