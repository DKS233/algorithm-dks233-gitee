package other;

/**
 * leetcode14. 最长公共前缀
 * 参考文档：https://leetcode.cn/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode-solution/
 *
 * @author dks233
 * @create 2022-06-30-21:05
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 最长公共前缀不会大于字符串数组中最小字符串长度
        int minLenIndex = 0;
        for (int index = 0; index < strs.length; index++) {
            minLenIndex = strs[index].length() < strs[minLenIndex].length() ? index : minLenIndex;
        }
        String minLenStr = strs[minLenIndex];
        // 以最小长度字符串为基准，比较字符串各个位置对应字符是否相等
        for (int index = 0; index < minLenStr.length(); index++) {
            for (int i = 0; i < strs.length; i++) {
                if (strs[i].charAt(index) != minLenStr.charAt(index)) {
                    return minLenStr.substring(0, index);
                }
            }
        }
        return minLenStr;
    }
}
