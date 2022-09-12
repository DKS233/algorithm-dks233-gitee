package other;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode13. 罗马数字转整数
 * leetcode12. 整数转罗马数字
 *
 * @author dks233
 * @create 2022-09-12-8:13
 */
@SuppressWarnings("ALL")
public class RomanIntegerReverse {
    // 分析：小的出现在后面就是加，小的出现在后面就是减
    public int romanToInt(String s) {
        char[] str = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int ans = 0;
        for (int i = 0; i < str.length - 1; i++) {
            if (map.get(str[i]) < map.get(str[i + 1])) {
                ans -= map.get(str[i]);
            } else {
                ans += map.get(str[i]);
            }
        }
        ans += map.get(str[str.length - 1]);
        return ans;
    }

    public String intToRoman(int number) {
        int[] ints = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] chars = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int index = 0;
        StringBuilder builder = new StringBuilder();
        while (index < ints.length) {
            while (number >= ints[index]) {
                builder.append(chars[index]);
                number -= ints[index];
            }
            index++;
        }
        return builder.toString();
    }
}
