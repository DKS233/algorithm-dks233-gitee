package other;

/**
 * 剑指 Offer第二版 58 - I. 翻转单词顺序
 * 剑指 Offer第二版 58 - II. 左旋转字符串
 *
 * @author dks233
 * @create 2022-06-19-15:49
 */
public class ReverseWord {
    // 题目1
    // 从后往前遍历字符串，提取每个单词，然后拼接新的字符串
    // O(N)+O(N)
    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder builder = new StringBuilder();
        // left、right 单词左右边界
        int right = s.length() - 1, left = s.length() - 1;
        while (left >= 0) {
            // 搜索单词
            while (left >= 0 && s.charAt(left) != ' ') {
                left--;
            }
            // 添加单词
            // 跳出循环时，left<0或s.charAt(left)=' '，所以单词左边界是left+1
            // subString是左闭右开的，所以右边界是right+1，才能取到整个单词
            builder.append(s.substring(left + 1, right + 1)).append(" ");
            // 跳过单词前面空格
            while (left >= 0 && s.charAt(left) == ' ') {
                left--;
            }
            // right跟上left，搜索下一个单词
            right = left;
        }
        // 最后一个单词后面的空格需要去掉
        return String.valueOf(builder).trim();
    }

    // 题目2
    // O(N)+O(N)
    public String reverseLeftWords(String s, int n) {
        StringBuilder builderBefore = new StringBuilder();
        StringBuilder builderBehind = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builderBehind.append(s.charAt(i));
        }
        for (int i = n; i < s.length(); i++) {
            builderBefore.append(s.charAt(i));
        }
        return builderBefore.append(builderBehind).toString();
    }

    // 题目2另一种写法
    // O(N)+O(N)
    public static class AnotherMethod {
        public String reverseLeftWords(String s, int n) {
            return s.substring(n, s.length()) + s.substring(0, n);
        }
    }
}
