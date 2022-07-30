package other;

import java.util.HashMap;

/**
 * leetcode2325. 解密消息
 *
 * @author dks233
 * @create 2022-07-29-23:39
 */
@SuppressWarnings("ALL")
public class DecryptMessage {
    // 用map
    public String decodeMessage(String key, String message) {
        // 记录key中字符对应的a-z
        HashMap<Character, Character> hashMap = new HashMap<>();
        char[] str = key.toCharArray();
        char cur = 'a';
        for (int index = 0; index < str.length; index++) {
            if (str[index] >= 'a' && str[index] <= 'z' && !hashMap.containsKey(str[index])) {
                hashMap.put(str[index], cur);
                cur = (char) (cur + 1);
            }
        }
        // 根据对应关系替换message中的字符
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < message.length(); index++) {
            if (message.charAt(index) >= 'a' && message.charAt(index) <= 'z') {
                builder.append(hashMap.get(message.charAt(index)));
            } else {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
