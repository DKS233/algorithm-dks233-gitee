package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 *
 * @author dks233
 * @create 2022-05-16-10:52
 */
@SuppressWarnings("all")
public class ArrangementOfSingleString {
    public static class ProblemOne {
        public static List<String> geAllArrangementOne(String word) {
            List<String> result = new ArrayList<>();
            char[] str = word.toCharArray();
            if (str == null || str.length == 0) {
                return result;
            }
            // 走完一条全路径过程中未选中的字符
            List<Character> rest = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                rest.add(str[i]);
            }
            // 记录走完一条全路径过程中的路径
            String path = "";
            process(result, rest, path);
            return result;
        }

        public static void process(List<String> result, List<Character> rest, String path) {
            // 所有字符都被选中，说明走完一条全路径，将该路径添加到result中
            if (rest.size() == 0) {
                result.add(path);
            } else {
                // 遍历所有未选中的字符，作为下一个拼接的字符
                for (int i = 0; i < rest.size(); i++) {
                    Character cur = rest.remove(i); // 当前选中cur，将cur从未选中字符中剔除
                    process(result, rest, path + cur); // 继续走，直到rest.size==0，即走完一条全路径
                    rest.add(i, cur); // 路径恢复（恢复现场）
                }
            }
        }

        // 路径就记录在str，设word="abc"
        // swap(0,0) swap(1,1) swap(2,2)  abc
        // swap(0,0) swap(1,2) swap(2,2)  acb
        // swap(0,1) swap(1,1) swap(2,2)  bac
        // swap(0,1) swap(1,2) swap(2,2)  bca
        // swap(0,2) swap(1,1) swap(2,2)  cba
        // swap(0,2) swap(1,2) swap(2,2)  cab
        public static List<String> geAllArrangementTwo(String word) {
            List<String> result = new ArrayList<>();
            char[] str = word.toCharArray();
            if (str == null || str.length == 0) {
                return result;
            }
            process(0, str, result);
            return result;
        }

        public static void process(int index, char[] str, List<String> result) {
            if (index == str.length) {
                result.add(String.valueOf(str));
            } else {
                // index=0 00交换 01交换 02交换
                // index=1 11交换 12交换
                // index=2 22交换
                for (int i = index; i < str.length; i++) {
                    swap(str, index, i);
                    process(index + 1, str, result);
                    // 恢复现场
                    swap(str, index, i);
                }
            }
        }

        public static void swap(char[] str, int a, int b) {
            char temp = str[a];
            str[a] = str[b];
            str[b] = temp;
        }
    }

    public static class ProblemTwo {
        // Problem中geAllArrangementTwo改造，实现去重
        public static List<String> geAllArrangementOne(String word) {
            List<String> result = new ArrayList<>();
            char[] str = word.toCharArray();
            if (str == null || str.length == 0) {
                return result;
            }
            process(result, str, 0);
            return result;
        }

        // 控制每个index位置的字符不要走重复的
        // a b a
        // 00交换(a做开头),01交换(b做开头),02交换跳过(a已经做过开头)，a的分支只走一次
        public static void process(List<String> result, char[] str, int index) {
            if (index == str.length) {
                result.add(String.valueOf(str));
            } else {
                // 记录做过index位置字符的str[i]，index位置别再出现同样的字符
                boolean[] visits = new boolean[256];
                for (int i = index; i < str.length; i++) {
                    // visits[str[i]] = false str[i]没做过index位置的字符
                    // visits[str[i]] = true  str[i]做过index位置的字符
                    if (!visits[str[i]]) {
                        // 走一次就记录成true，下次不走这条分支
                        visits[str[i]] = true;
                        swap(str, index, i);
                        process(result, str, index + 1);
                        swap(str, index, i);
                    }
                }
            }
        }

        // HashSet实现去重
        public static List<String> geAllArrangementTwo(String word) {
            List<String> result = new ArrayList<>();
            char[] str = word.toCharArray();
            if (str == null || str.length == 0) {
                return result;
            }
            HashSet<String> set = new HashSet<>();
            process(set, str, 0);
            for (String s : set) {
                result.add(s);
            }
            return result;
        }

        public static void process(HashSet<String> set, char[] str, int index) {
            if (index == str.length) {
                set.add(String.valueOf(str));
            } else {
                for (int i = index; i < str.length; i++) {
                    swap(str, index, i);
                    process(set, str, index + 1);
                    swap(str, index, i);
                }
            }
        }

        public static void swap(char[] str, int a, int b) {
            char temp = str[a];
            str[a] = str[b];
            str[b] = temp;
        }

    }

    public static void main(String[] args) {
        ProblemOne problemOne = new ProblemOne();
        String word = "aaa";
        for (String s : problemOne.geAllArrangementOne(word)) {
            System.out.print(s + "   ");
        }
        System.out.println();
        for (String s : problemOne.geAllArrangementTwo(word)) {
            System.out.print(s + "   ");
        }
        System.out.println();
        for (String s : ProblemTwo.geAllArrangementOne(word)) {
            System.out.print(s + "   ");
        }
        System.out.println();
        for (String s : ProblemTwo.geAllArrangementTwo(word)) {
            System.out.print(s + "   ");
        }
        System.out.println();
    }
}
