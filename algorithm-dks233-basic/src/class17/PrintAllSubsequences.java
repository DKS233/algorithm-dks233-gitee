package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 打印一个字符串的全部子序列
 * 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 *
 * @author dks233
 * @create 2022-05-16-10:10
 */
public class PrintAllSubsequences {
    // 打印一个字符串的全部子序列
    public static List<String> printAllSubsequencesOne(String word) {
        char[] chars = word.toCharArray();
        String path = ""; // 子序列记录
        List<String> result = new ArrayList<>(); // 子序列list
        process(chars, 0, result, path);
        return result;
    }

    /**
     * @param chars  字符数组
     * @param index  当前遍历的索引位置
     * @param result 子序列list
     * @param path   遍历到index位置时的子序列（未处理index位置）
     */
    public static void process(char[] chars, int index, List<String> result, String path) {
        // 如果index越界，将当前path添加到result
        if (index == chars.length) {
            result.add(path);
            return;
        }
        // 要index位置字符
        process(chars, index + 1, result, path + chars[index]);
        // 不要index位置字符
        process(chars, index + 1, result, path);
    }

    // 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
    // 注：用hashSet存path，去重
    public static List<String> printAllSubsequencesTwo(String word) {
        char[] chars = word.toCharArray();
        String path = ""; // 子序列记录
        HashSet<String> set = new HashSet<>(); // 子序列set
        process(chars, 0, set, path);
        // 子序列list
        return new ArrayList<>(set);
    }

    /**
     * @param chars 字符数组
     * @param index 当前遍历的索引位置
     * @param set   子序列set
     * @param path  遍历到index位置时的子序列（未处理index位置）
     */
    public static void process(char[] chars, int index, HashSet<String> set, String path) {
        // 如果index越界，将当前path添加到result
        if (index == chars.length) {
            set.add(path);
            return;
        }
        // 要index位置字符
        process(chars, index + 1, set, path + chars[index]);
        // 不要index位置字符
        process(chars, index + 1, set, path);
    }


    public static void main(String[] args) {
        String word = "abcd";
        for (String subsequence : printAllSubsequencesOne(word)) {
            System.out.print(subsequence + "   ");
        }
        System.out.println();
        System.out.println(printAllSubsequencesOne(word).size());
        for (String subsequence : printAllSubsequencesTwo(word)) {
            System.out.print(subsequence + "   ");
        }
        System.out.println();
        System.out.println(printAllSubsequencesOne(word).size());
    }
}
