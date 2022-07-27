package dp;

/**
 * 剑指offer专项突击版：剑指 Offer II 096. 字符串交织
 * TODO:暴力递归改动态规划测试不通过
 *
 * @author dks233
 * @create 2022-07-26-15:49
 */
@SuppressWarnings("ALL")
public class StringInterleaving {
    // 暴力递归
    public static class MethodOne {
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            return process(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0);
        }

        // strOne[indexOne,s1.length()-1] 和 strTwo[indexTwo,s2.length()-1] 能否组成strThree[indexThree,s3.length()-1]
        public boolean process(char[] strOne, char[] strTwo, char[] strThree, int indexOne, int indexTwo, int indexThree) {
            // 情况1：strOne的最后一个字符和strThree的最后一个字符相等，下面第一个递归结束
            // 情况2：strTwo的最后一个字符和strThree的最后一个字符相等，下面第二个递归结束
            if ((indexOne == strOne.length && indexThree == strThree.length) ||
                    (indexTwo == strTwo.length && indexThree == strThree.length)) {
                return true;
            }
            boolean ans = false;
            if (indexOne < strOne.length) {
                if (strOne[indexOne] == strThree[indexThree]) {
                    ans = process(strOne, strTwo, strThree, indexOne + 1, indexTwo, indexThree + 1);
                }
                if (ans) {
                    return true;
                }
            }
            if (indexTwo < strTwo.length) {
                if (strTwo[indexTwo] == strThree[indexThree]) {
                    ans = process(strOne, strTwo, strThree, indexOne, indexTwo + 1, indexThree + 1);
                }
                if (ans) {
                    return true;
                }
            }
            return ans;
        }
    }

    // 暴力递归+记忆化搜索
    public static class MethodTwo {
        Boolean[][] dp;

        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            dp = new Boolean[s1.length() + 1][s2.length() + 1];
            return process(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0);
        }

        // strOne[indexOne,s1.length()-1] 和 strTwo[indexTwo,s2.length()-1] 能否组成strThree[indexThree,s3.length()-1]
        public boolean process(char[] strOne, char[] strTwo, char[] strThree, int indexOne, int indexTwo, int indexThree) {
            if (dp[indexOne][indexTwo] != null) {
                return dp[indexOne][indexTwo];
            }
            // 情况1：strOne的最后一个字符和strThree的最后一个字符相等
            // 情况2：strTwo的最后一个字符和strThree的最后一个字符相等
            if ((indexOne == strOne.length && indexThree == strThree.length) ||
                    (indexTwo == strTwo.length && indexThree == strThree.length)) {
                return true;
            }
            boolean ans = false;
            if (indexOne < strOne.length) {
                if (strOne[indexOne] == strThree[indexThree]) {
                    ans = process(strOne, strTwo, strThree, indexOne + 1, indexTwo, indexThree + 1);
                }
                if (ans) {
                    return true;
                }
            }
            if (indexTwo < strTwo.length) {
                if (strTwo[indexTwo] == strThree[indexThree]) {
                    ans = process(strOne, strTwo, strThree, indexOne, indexTwo + 1, indexThree + 1);
                }
                if (ans) {
                    return true;
                }
            }
            dp[indexOne][indexTwo] = ans;
            return ans;
        }
    }

    // TODO:暴力递归改动态规划测试不通过
    public static class MethodThree {
        // 暴力递归中：indexThree即为indexOne+indexTwo
        // [1,s1.length()-1]->[3,s2.length()-1]->[4,s3.length()-1]
        // dp[indexOne][indexTwo]表示 ：
        // strOne[indexOne,s1.length()-1] 和 strTwo[indexTwo,s2.length()-1] 能否组成strThree[indexThree,s3.length()-1]
        public boolean isInterleave(String s1, String s2, String s3) {
            if (s1.length() + s2.length() != s3.length()) {
                return false;
            }
            boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
            dp[s1.length()][s3.length() - s1.length()] = true;
            dp[s3.length() - s2.length()][s2.length()] = true;
            for (int indexOne = s1.length() - 1; indexOne >= 0; indexOne--) {
                for (int indexTwo = s2.length() - 1; indexTwo >= 0; indexTwo--) {
                    if (s1.charAt(indexOne) == s3.charAt(indexOne + indexTwo)) {
                        dp[indexOne][indexTwo] |= dp[indexOne + 1][indexTwo];
                    }
                    if (s2.charAt(indexTwo) == s3.charAt(indexOne + indexTwo)) {
                        dp[indexOne][indexTwo] |= dp[indexOne][indexTwo + 1];
                    }
                }
            }
            return dp[0][0];
        }
    }

    public static void main(String[] args) {
        MethodThree methodThree = new MethodThree();
        methodThree.isInterleave("aa", "aa", "aaaa");
    }
}
