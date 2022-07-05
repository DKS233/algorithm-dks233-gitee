package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * leetcode17. 电话号码的字母组合
 *
 * @author dks233
 * @create 2022-07-05-11:01
 */
@SuppressWarnings("ALL")
public class LetterCombinationOfTelephoneNumber {
    // 回溯算法
    // 时间复杂度：O(3^m*4^n) 需要遍历到每一种组合
    // m是输入中对应3个字母的数字个数，n是输入中对应4个字母的数字个数
    // 空间复杂度：O(m+n)，取决于回溯过程中的递归调用层数
    public static class MethodOne {
        List<String> list = new ArrayList<>();

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.length() == 0) {
                return new ArrayList<String>();
            }
            // 各个英文字母和数字的对应关系
            // 2->abc 3->def 4->ghi 5->jkl 6->mno 7->pqrs 8->tuv 9->wxyz
            HashMap<Character, String> map = new HashMap<>();
            map.put('2', "abc");
            map.put('3', "def");
            map.put('4', "ghi");
            map.put('5', "jkl");
            map.put('6', "mno");
            map.put('7', "pqrs");
            map.put('8', "tuv");
            map.put('9', "wxyz");
            // 各个位置字符串进行拼接
            process(map, new StringBuilder(), digits, 0);
            return list;
        }

        /**
         * @param map    key为数字字符，value为数字对应的字符串
         * @param curStr 当前拼接的字符串
         * @param digits 包含数字的字符串数组
         * @param index  当前遍历到的字符串索引
         */
        public void process(HashMap<Character, String> map, StringBuilder curStr, String digits, int index) {
            if (index == digits.length()) {
                list.add(curStr.toString());
                return;
            }
            // 得到当前遍历到的数字对应的字符串，然后遍历，和其他位置数字对应的字符串进行拼接
            char c = digits.charAt(index);
            char[] chars = map.get(c).toCharArray();
            for (int i = 0; i < chars.length; i++) {
                // i位置和其他数字对应的字符串进行拼接，然后回溯，其他位置再和其他数字对应的字符串进行拼接
                curStr.append(chars[i]);
                process(map, curStr, digits, index + 1);
                curStr.deleteCharAt(curStr.length() - 1);
            }
        }
    }
}
