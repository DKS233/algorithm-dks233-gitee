package other;

import java.util.HashMap;

/**
 * leetcode12. 整数转罗马数字
 * 参考文档：https://leetcode.cn/problems/integer-to-roman/solution/tan-xin-suan-fa-by-liweiwei1419/
 * leetcode13. 罗马数字转整数
 * 参考文档：https://leetcode.cn/problems/roman-to-integer/solution/qing-xi-tu-jie-python3-by-ml-zimingmeng/
 *
 * @author dks233
 * @create 2022-06-30-19:21
 */
public class IntegerRomanConvert {
    // 题目1：整数转罗马数字
    public String intToRoman(int number) {
        int[] nums = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (index < nums.length) {
            while (number >= nums[index]) {
                builder.append(romans[index]);
                number -= nums[index];
            }
            index++;
        }
        return builder.toString();
    }

    // 题目2：罗马数字转整数
    // 思路：小的数在大的数后面就相减，比如包含4,9,40,90,400,900的这些数
    // 小的数在大的数前面就相加，比如1,5这些数
    public int romanToInt(String s) {
        char[] romans = new char[]{'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int[] nums = new int[]{1000, 500, 100, 50, 10, 5, 1};
        HashMap<Character, Integer> map = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            map.put(romans[index], nums[index]);
        }
        int ans = 0;
        for (int index = 0; index < s.length(); index++) {
            if (index + 1 < s.length() && map.get(s.charAt(index)) < map.get(s.charAt(index + 1))) {
                ans -= map.get(s.charAt(index));
            } else {
                ans += map.get(s.charAt(index));
            }
        }
        return ans;
    }
}
