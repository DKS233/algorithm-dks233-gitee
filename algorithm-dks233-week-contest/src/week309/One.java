package week309;

import java.util.Arrays;

/**
 * 6167. 检查相同字母间的距离
 *
 * @author dks233
 * @create 2022-09-04-9:48
 */
@SuppressWarnings("ALL")
public class One {
    // 方法1：通过调用api，计算每个字符两次出现的间隔
    public static class MethodOne {
        public boolean checkDistances(String s, int[] distance) {
            for (int i = 0; i < distance.length; i++) {
                int lastIndex = s.lastIndexOf(i + 'a');
                int index = s.indexOf(i + 'a');
                // 排序i+'a'不存在于s中的情况
                if (s.indexOf(i + 'a') == -1) {
                    continue;
                }
                if (lastIndex - index != distance[i] + 1) {
                    return false;
                }
            }
            return true;
        }
    }

    // 方法2：记录字符上次出现的位置，一次遍历
    public static class MethodTwo {
        public boolean checkDistances(String s, int[] distance) {
            // 记录字符上一次出现的位置
            int[] last = new int[26];
            Arrays.fill(last, -1);
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (last[c - 'a'] == -1) {
                    last[c - 'a'] = i;
                }
                // 第二次出现的时候比较distance[i]+1和两次出现的距离
                else {
                    if (distance[c - 'a'] + 1 != i - last[c - 'a']) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

}
