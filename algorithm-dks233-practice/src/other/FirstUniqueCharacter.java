package other;

/**
 * leetcode387. 字符串中的第一个唯一字符
 *
 * @author dks233
 * @create 2022-08-28-22:56
 */
@SuppressWarnings("ALL")
public class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        char[] str = s.toCharArray();
        int[] count = new int[26];
        // 先统计每个字符出现的次数
        for (int i = 0; i < str.length; i++) {
            count[str[i] - 'a']++;
        }
        // 如果是第一个不重复的字符，出现次数应该是1
        // 再次遍历字符串，找到第一个出现次数为1的字符
        for (int i = 0; i < str.length; i++) {
            if (count[str[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
