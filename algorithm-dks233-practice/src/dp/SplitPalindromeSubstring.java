package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 086. 分割回文子字符串
 *
 * @author dks233
 * @create 2022-07-27-17:39
 */
@SuppressWarnings("ALL")
public class SplitPalindromeSubstring {
    List<List<String>> list;
    boolean[][] dp;

    public String[][] partition(String s) {
        // dp求出所有区间内是否是回文子串
        init(s);
        list = new ArrayList<>();
        process(s, 0, new ArrayList<>());
        // 将list转换为二维数组
        String[][] result = new String[list.size()][];
        for (int index = 0; index < list.size(); index++) {
            result[index] = list.get(index).toArray(new String[0]);
        }
        return result;
    }

    // 处理[index,...]范围内的字符串
    public void process(String str, int index, List<String> singleList) {
        if (index == str.length()) {
            list.add(new ArrayList<>(singleList));
            return;
        }
        // 从index位置开始依次尝试
        int right = index;
        while (right < str.length()) {
            if (dp[index][right]) {
                singleList.add(str.substring(index, right + 1));
                process(str, right + 1, singleList);
                singleList.remove(singleList.size() - 1);
            }
            right++;
        }
    }


    // dp求出所有范围内是否是回文子串
    public void init(String s) {
        // dp[left][right] [left,right]范围内是否是回文子串
        char[] str = s.toCharArray();
        dp = new boolean[str.length][str.length];
        // 对角线位置肯定是true
        // 如果[left,right]范围内有两个字符,dp[left][right]=str[left]==str[right]
        // 如果[left,right]范围内有三个字符,dp[left][right]=str[left]==str[right]
        // 如果[left,right]范围内有超过三个字符,dp[left][right]=str[left]==str[right]&&dp[left+1][right-1]
        for (int left = 0; left < dp.length; left++) {
            dp[left][left] = true;
        }
        for (int left = dp.length - 1; left >= 0; left--) {
            for (int right = dp[0].length - 1; right >= 0; right--) {
                if (right < left + 3) {
                    dp[left][right] = str[left] == str[right];
                } else {
                    dp[left][right] = str[left] == str[right] && dp[left + 1][right - 1];
                }
            }
        }
    }
}
