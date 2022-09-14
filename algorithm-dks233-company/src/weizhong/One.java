package weizhong;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 微众银行9月13号题目1：给定一个数组，选其中的三个数，组成最大的数
 *
 * @author dks233
 * @create 2022-09-14-17:01
 */
@SuppressWarnings("ALL")
public class One {
    // 分析：按照字符串长度和字符串的字典序排序，找到排序后最大的三个字符串
    // 然后对这三个字符串再进行排序
    // 排序方式：重写比较器，重新排序（(a,b)->(b+a).compare(a+b)），将排序后的三个字符串拼接即为所求
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = String.valueOf(scanner.nextInt());
        }
        Arrays.sort(strs, (a, b) -> {
            if (a.length() == b.length()) {
                return b.compareTo(a);
            } else {
                return b.length() - a.length();
            }
        });
        String[] newStr = new String[3];
        for (int i = 0; i < 3; i++) {
            newStr[i] = strs[i];
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < newStr.length; i++) {
            builder.append(newStr[i]);
        }
        System.out.println(builder.toString());
    }
}
