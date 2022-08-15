package prefixtree;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 063. 替换单词
 * 参考文档：https://leetcode.cn/problems/replace-words/solution/by-ac_oier-jecf/
 *
 * @author dks233
 * @create 2022-08-15-10:11
 */
public class ReplaceWords {
    public String replaceWords(List<String> dictionary, String sentence) {
        StringBuilder builder = new StringBuilder();
        MyPrefixTree myPrefixTree = new MyPrefixTree();
        for (String word : dictionary) {
            myPrefixTree.insert(word);
        }
        for (String subStr : sentence.split(" ")) {
            builder.append(myPrefixTree.search(subStr)).append(" ");
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    public static class MyPrefixTree {
        Node root;

        public MyPrefixTree() {
            this.root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            Node node = root;
            int path = 0;
            char[] str = word.toCharArray();
            node.pass++;
            for (int index = 0; index < str.length; index++) {
                path = str[index] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        // 判断是否包含word的前缀
        // 如果有，返回前缀字符串，没有则返回原字符串
        public String search(String word) {
            char[] str = word.toCharArray();
            int path = 0;
            Node node = root;
            for (int index = 0; index < str.length; index++) {
                path = str[index] - 'a';
                if (node.nexts[path] == null) {
                    return word;
                }
                if (node.nexts[path].end > 0) {
                    return word.substring(0, index + 1);
                }
                node = node.nexts[path];
            }
            return word;
        }
    }

    public static class Node {
        Node[] nexts;
        int pass;
        int end;

        public Node() {
            nexts = new Node[26];
            pass = 0;
            end = 0;
        }
    }

    public static void main(String[] args) {
        ReplaceWords replaceWords = new ReplaceWords();
        List<String> list = new ArrayList<>();
        list.add("cat");
        list.add("bat");
        list.add("rat");
        System.out.println(replaceWords.replaceWords(list, "the cattle was rattled by the battery"));
    }
}
