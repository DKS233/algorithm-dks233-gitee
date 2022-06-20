package other;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 剑指 Offer第二版  38. 字符串的排列
 *
 * @author dks233
 * @create 2022-06-18-23:32
 */
public class ArrangementOfString {
    // HashSet实现去重，visit标记路径中使用过的字符
    // 时间复杂度：O(N*N!)
    // 空间复杂度：O(N)
    public static class MethodOne {
        HashSet<String> hashSet = new HashSet<>(); // 存储拼接完的字符串
        boolean[] visit = new boolean[8]; // 标记当前路径中哪些字符已被使用（8：题目中字符串最多8个字符）

        public String[] permutation(String s) {
            char[] str = s.toCharArray();
            dfs(str, 0, "");
            String[] ans = new String[hashSet.size()];
            List<String> list = new ArrayList<>(hashSet);
            for (int i = 0; i < list.size(); i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }

        // str：原字符串
        // index：当前处理到的索引位置，范围为[0,str.length-1]
        // cur：当前拼接的字符串
        public void dfs(char[] str, int index, String cur) {
            if (index == str.length) {
                hashSet.add(cur);
                return;
            }
            for (int i = 0; i < str.length; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    dfs(str, index + 1, cur + str[i]);
                    visit[i] = false;
                }
            }
        }
    }

    // 排序去重，先对字符串排序
    // 如果字符串中有相同字符，保证相同字符在index位置只使用一次
    // 加上上面约束后，不用hashSet去重，list直接添加后就是去重后的
    // 时间复杂度：O(N*N!)
    // 空间复杂度：O(N)
    public static class MethodTwo {
        boolean[] visit = new boolean[8]; // 标记当前路径中哪些字符被使用
        List<String> list = new ArrayList<>(); // 去重后的字符串列表

        public String[] permutation(String s) {
            char[] str = s.toCharArray();
            Arrays.sort(str);
            dfs(str, 0, "");
            String[] ans = new String[list.size()];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }

        // str：原字符串
        // index：当前处理到了哪个位置
        // cur：当前路径中拼接的字符串
        public void dfs(char[] str, int index, String cur) {
            if (index == str.length) {
                list.add(cur);
                return;
            }
            for (int i = 0; i < str.length; i++) {
                // 有相同字符时，跳过的两种方式
                // 方式1：i >= 1 && str[i] == str[i - 1] && visit[i - 1]
                // 设str[i-1]和str[i]是a，当前路径中，在index位置做决策，选中a有两种情况，选str[i-1]或选str[i]
                // 对于方式1，visit[i-1]没被访问过才能选visit[i]，即str[i]被选中到index位置，该条路径中str[i]到了str[i-1]前面
                // 该条路径index之后的决策中只能访问visit[i-1]，这样保证了str[i]和str[i-1]的相对位置
                // 方式2：i >= 1 && str[i] == str[i - 1] && !visit[i - 1]
                // 设str[i-1]和str[i]是a，当前路径中，在index位置做决策，选中a有两种情况，选str[i-1]和选str[i]
                // 对于方式2，visit[i-1]被访问过才能选中visit[i]，即str[i]被选中到index位置，该条路径中str[i]到了str[i-1]后面
                // 该条路径str[i-1]和str[i]都被访问了，这样保证了str[i-1]和str[i]的相对位置
                if (i >= 1 && str[i] == str[i - 1] && visit[i - 1]) {
                    continue;
                }
                if (!visit[i]) {
                    visit[i] = true;
                    dfs(str, index + 1, cur + str[i]);
                    visit[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        MethodOne methodOne = new MethodOne();
        methodOne.permutation("abc");
    }
}
