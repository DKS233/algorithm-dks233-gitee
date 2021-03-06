package other;

/**
 * leetcode28. 实现 strStr()
 *
 * @author dks233
 * @create 2022-07-15-22:06
 */
public class FirstStrStr {

    // 返回s1中s2第一次出现的位置
    public static int strStr(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = getNextArray(str2);
        // 当前需要比对的位置：str1中的x位置和str2中的y位置
        int x = 0;
        int y = 0;
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            }
            // 向右移动可以看成y左移
            // y已经无法左移了，以++x位置的元素做头继续检查
            else if (next[y] == -1) {
                x++;
            }
            // 遇到不匹配的位置，y左移
            else {
                y = next[y];
            }
        }
        // x越界说明s2不断向右移动的过程中s1找不到匹配的头
        // y越界说明s1中找到了符合的头
        // 如：s1[x-y,x] 和 s2[0,y]匹配，说明s1中第一次出现s2的索引位置是x-y
        return y == str2.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        // 当前需要求最大匹配长度的位置
        int index = 2;
        // 当前和index-1位置比较的位置，初始情况下，index-1=1，令cn=0，cn需要和1位置的数比较，如果匹配，说明next[2]=1
        int cn = 0;
        while (index < str2.length) {
            if (str2[index - 1] == str2[cn]) {
                next[index++] = ++cn;
            }
            // cn还能继续往左跳
            else if (cn > 0) {
                cn = next[cn];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }
}
