package other;

/**
 * leetcode38. 外观数列
 *
 * @author dks233
 * @create 2022-08-24-23:13
 */
@SuppressWarnings("ALL")
public class LookAndSaySequence {
    public static class MethodOne {
        public String countAndSay(int n) {
            String[] strs = new String[n];
            strs[0] = "1";
            for (int i = 1; i < strs.length; i++) {
                String pre = strs[i - 1];
                StringBuilder builder = new StringBuilder();
                int index = 0;
                int count = 1;
                while (index < pre.length()) {
                    while (index + 1 < pre.length() && pre.charAt(index) == pre.charAt(index + 1)) {
                        index++;
                        count++;
                    }
                    builder.append(count).append(pre.charAt(index));
                    index++;
                    count = 1;
                }
                strs[i] = builder.toString();
            }
            return strs[n - 1];
        }
    }
}
