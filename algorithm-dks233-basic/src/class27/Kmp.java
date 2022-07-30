package class27;

/**
 * 图解：KMP.drawio
 *
 * @author dks233
 * @create 2022-07-30-18:56
 */
@SuppressWarnings("ALL")
public class Kmp {
    // 返回s1中s2第一次出现的位置
    // N:s1的长度
    // M:s2的长度
    public static int getFirstIndex(String s1, String s2) {
        if (s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 时间复杂度：O(M)
        int[] next = getNextArray(str2);
        // 当前需要比对的位置：str1中的x位置和str2中的y位置
        int x = 0;
        int y = 0;
        // x∈[0,N] y∈[0,M]
        // 时间复杂度：x∈[0,N] x-y∈[N-M,N]
        // 循环第一个分支：x↑ y↑ x-y不变
        // 循环第一个分支：x↑ y不变 x-y↑
        // 循环第三个分支：x不变 y↓ x-y↑
        // x和x-y两个量在区间内只会上升或不变，且次数小于等于N，加起来小于两倍的N
        // 所以时间复杂度是O(N)
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

    // 时间复杂度：分析index和index-cn
    // index∈[0,str2.length-1] cn>0 所以index-cn∈[-cn,str2.length-1-cn]
    // index和index-cn都属于O(M)级别且在while循环中不会减小，所以总次数小于2M
    // 所以时间复杂度是O(M)
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

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getFirstIndex(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
