package prefixtree;

import java.util.Arrays;

/**
 * 剑指offer专项突击版：剑指 Offer II 065. 最短的单词编码
 *
 * @author dks233
 * @create 2022-08-15-16:34
 */
@SuppressWarnings("ALL")
public class ShortestWordEncoding {

    public int minimumLengthEncoding(String[] words) {
        MyPrefixTree myPrefixTree = new MyPrefixTree();
        // 字符串按照长度排序：大->小
        Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
        for (String word : words) {
            if (!myPrefixTree.isPrefix(word)) {
                myPrefixTree.add(word);
            }
        }
        return myPrefixTree.count;
    }

    public static class MyPrefixTree {
        Node root;
        int count; // 统计最终字符串长度

        public MyPrefixTree() {
            count = 0;
            this.root = new Node();
        }

        // 注：字符串从右往左添加
        public void add(String word) {
            char[] str = word.toCharArray();
            Node node = root;
            node.pass++;
            int path = 0;
            for (int index = str.length - 1; index >= 0; index--) {
                path = str[index] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
            count += word.length() + 1;
        }

        // 是否已经包含word前缀
        public boolean isPrefix(String word) {
            int path = 0;
            Node node = root;
            char[] str = word.toCharArray();
            for (int index = str.length - 1; index >= 0; index--) {
                path = str[index] - 'a';
                if (node.nexts[path] == null) {
                    return false;
                }
                node = node.nexts[path];
            }
            return node.pass > 0;
        }

    }

    public static class Node {
        int end;
        int pass;
        Node[] nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new Node[26];
        }
    }
}
