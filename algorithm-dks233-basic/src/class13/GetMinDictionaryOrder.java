package class13;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个由字符串组成的数组strs，必须把所有的字符串拼接起来，返回所有可能的拼接结果中字典序最小的结果
 *
 * @author dks233
 * @create 2022-05-01-10:12
 */
public class GetMinDictionaryOrder {
    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String getMinDictionaryOrder(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        StringBuilder ans = new StringBuilder();
        for (String str : strs) {
            ans.append(str);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"ab", "b"};
        System.out.println(getMinDictionaryOrder(strs));
    }
}
