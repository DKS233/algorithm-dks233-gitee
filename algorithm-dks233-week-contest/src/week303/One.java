package week303;

import java.util.HashSet;

/**
 * @author dks233
 * @create 2022-07-24-10:18
 */
public class One {
    public char repeatedCharacter(String s) {
        char[] str = s.toCharArray();
        HashSet<Character> hashSet = new HashSet<>();
        for (int index = 0; index < str.length; index++) {
            if (hashSet.contains(str[index])) {
                return str[index];
            } else {
                hashSet.add(str[index]);
            }
        }
        return ' ';
    }
}
