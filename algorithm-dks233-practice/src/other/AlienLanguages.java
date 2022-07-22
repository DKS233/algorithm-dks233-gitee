package other;

import java.util.Arrays;

/**
 * 剑指offer专项突击版：剑指 Offer II 034. 外星语言是否排序
 *
 * @author dks233
 * @create 2022-07-21-15:11
 */
@SuppressWarnings("ALL")
public class AlienLanguages {
    // 相当于自定义比较器
    public boolean isAlienSorted(String[] words, String order) {
        for (int index = 0; index < words.length - 1; index++) {
            if (compare(words[index], words[index + 1], order) > 0) {
                return false;
            }
        }
        return true;
    }

    // 返回负数 说明strOne字典序小
    // 返回正数 说明strTwo字典序小
    // 返回0 说明两个字符串字典序相等
    public int compare(String strOne, String strTwo, String order) {
        int indexOne = 0;
        int indexTwo = 0;
        while (indexOne < strOne.length() && indexTwo < strTwo.length()) {
            if (order.indexOf(strOne.charAt(indexOne)) > order.indexOf(strTwo.charAt(indexTwo))) {
                return 1;
            } else if (order.indexOf(strOne.charAt(indexOne)) < order.indexOf(strTwo.charAt(indexTwo))) {
                return -1;
            } else {
                indexOne++;
                indexTwo++;
            }
        }
        // 到这儿说明之前的字符都相等
        // 如果只有indexOne越界，strOne的字典序肯定小
        if (indexOne < strOne.length()) {
            return 1;
        }
        // 如果只有indexTwo越界，strTwo的字典序肯定大
        if (indexTwo < strOne.length()) {
            return -1;
        }
        // 如果都越界，说明两个字符串长度相等，且对应的字符也相等
        return 0;
    }
}
