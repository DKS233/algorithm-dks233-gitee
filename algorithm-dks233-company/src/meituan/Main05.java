package meituan;

import java.util.HashMap;
import java.util.Map;

/**
 * 美团9月10号笔试题目5
 * 题目描述：
 * 小美有一串项链，项链共由n颗宝石组成，每个宝石上都有一个数字。但是有一天项链不小心被弄断了，变成了一长串，
 * 此时可以看成一个只包含数字的字符串，于是她准备将项链从某些地方切开后再重新分成多段（每段都是原来字符串的连续子串，且不能再重新组合），
 * 因为小美特别喜欢7这个数字，所以她希望切开后每段的数字和都尽可能被7整除。
 * 例如，假设项链表示为12457，可以切分为124|5|7，第一段和第三段的和能被7整除。她想请你帮她算一算 ，切开后最多能有多少段的数字和能被7整除。
 * <p>
 * 输入描述
 * 一行，一个字符串s，s的每位都是数字。
 * 1 ≤ |s| ≤ 100000，|s| 表示s的长度。
 * 输出描述
 * 输出一个整数，表示切开后最多能有多少段的数字和是7的倍数。
 * 注：为了方便测试，将原题ACM模式改成核心代码模式
 *
 * @author dks233
 * @create 2022-09-10-15:57
 */
@SuppressWarnings("ALL")
public class Main05 {
    // 暴力递归
    public static class MethodOne {
        public static int getMaxPart(String s) {
            char[] str = s.toCharArray();
            return process(str, 0);
        }

        // [index,...]最多能切割成多少段能被7整除的字符串
        public static int process(char[] str, int index) {
            if (index == str.length) {
                return 0;
            }
            int result = 0;
            // 情况1：选择当前字符
            // 从当前位置出发，只要出现7的倍数，马上计数，跳到下一个位置
            int curSum = 0;
            for (int i = index; i < str.length; i++) {
                curSum += str[i] - '0';
                if (curSum % 7 == 0) {
                    curSum %= 7;
                    result = Math.max(1 + process(str, i + 1), result);
                    break;
                }
            }
            // 情况2：跳过当前字符，直接去下一个位置做决定
            // 注：当前单个字符如果可以被7整除（0或者7），也不计数，因为情况1已经考虑了这种情况
            result = Math.max(process(str, index + 1), result);
            return result;
        }
    }

    // 暴力递归改动态规划
    // dp[index]  [index,...]最多能切割成多少段能被7整除的字符串
    public static class MethodTwo {
        public static int getMaxPart(String s) {
            char[] str = s.toCharArray();
            int[] dp = new int[str.length + 1];
            dp[str.length] = 0;
            for (int index = str.length - 1; index >= 0; index--) {
                int result = 0;
                int curSum = 0;
                for (int i = index; i < str.length; i++) {
                    curSum += str[i] - '0';
                    if (curSum % 7 == 0) {
                        curSum %= 7;
                        result = Math.max(1 + dp[i + 1], result);
                        break;
                    }
                    result = Math.max(dp[index + 1], result);
                }
                dp[index] = result;
            }
            return dp[0];
        }
    }

    // 用于测试的方法：来自AC的牛客评论区 牛客用户：Zzx777
    // 牛客评论区（https://www.nowcoder.com/discuss/1047546?type=post&order=hot&pos=&page=8&ncTraceId=&channel=-1&source_id=search_post_nctrack&gio_id=9276FB84C582C087270418350274EF94-1662815143916）
    public static class TestMethod {
        public static int getMaxPart(String s) {
            int n = s.length();
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, -1);
            int[] dp = new int[n + 1];
            int ans = 0;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                dp[i + 1] = dp[i];
                int cur = s.charAt(i) - '0';
                sum += cur;
                sum %= 7;
                if (map.containsKey(sum)) {
                    dp[i + 1] = Math.max(dp[map.get(sum) + 1] + 1, dp[i + 1]);
                }
                map.put(sum, i);
                ans = Math.max(ans, dp[i + 1]);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        int maxLen = 100000;
        int testTimes = 100000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            String randomStr = randomStr(maxLen);
            int two = MethodTwo.getMaxPart(randomStr);
            int three = TestMethod.getMaxPart(randomStr);
            if (two != three) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static String randomStr(int maxLen) {
        StringBuilder builder = new StringBuilder();
        int len = (int) (Math.random() * (maxLen)) + 1;
        for (int i = 0; i < len; i++) {
            int randomChar = (int) (Math.random() * 10);
            builder.append(randomChar);
        }
        return builder.toString();
    }
}




















