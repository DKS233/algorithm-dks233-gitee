package structure;

import java.util.HashMap;

/**
 * 前缀树：数组实现和map实现
 *
 * @author dks233
 * @create 2022-08-18-21:36
 */
@SuppressWarnings("ALL")
public class PrefixTree {
    // 数组实现：适用于只有小写字母的情况
    public static class MyPrefixTreeOne {
        Node root;

        public MyPrefixTreeOne() {
            this.root = new Node();
        }

        // 向前缀树中插入word
        public void insert(String word) {

        }

        // 多少个单词以word作前缀
        public int prefixCount(String word) {
            return -1;
        }

        // 前缀树中存在多少个word
        public int wordCount(String word) {
            return -1;
        }

        // 从前缀树中删除word
        // 一次删除一个
        public void delete(String word) {

        }

        public static class Node {
            int pass;
            int end;
            Node[] nexts;

            public Node() {
                this.nexts = new Node[26];
                this.pass = 0;
                this.end = 0;
            }
        }
    }

    // 数组实现：适用于只有小写字母的情况
    public static class MyPrefixTreeTwo {
        Node root;

        public MyPrefixTreeTwo() {
            this.root = new Node();
        }

        // 向前缀树中插入word
        public void insert(String word) {

        }

        // 多少个单词以word作前缀
        public int prefixCount(String word) {
            return -1;
        }

        // 前缀树中存在多少个word
        public int wordCount(String word) {
            return -1;
        }

        // 从前缀树中删除word
        // 一次删除一个
        public void delete(String word) {

        }

        public static class Node {
            int pass;
            int end;
            HashMap<Integer, Node> nexts;

            public Node() {
                this.nexts = new HashMap<>();
                this.pass = 0;
                this.end = 0;
            }
        }
    }
}
