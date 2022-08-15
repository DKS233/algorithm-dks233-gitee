package prefixtree;

/**
 * 剑指offer专项突击版：剑指 Offer II 062. 实现前缀树
 *
 * @author dks233
 * @create 2022-08-15-9:55
 */
@SuppressWarnings("ALL")
public class ImplementPrefixTree {
    public static class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            int path = 0;
            Node node = root;
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

        public boolean search(String word) {
            if (word == null) {
                return true;
            }
            char[] str = word.toCharArray();
            int path = 0;
            Node node = root;
            for (int index = 0; index < str.length; index++) {
                path = str[index] - 'a';
                if (node.nexts[path] == null) {
                    return false;
                }
                node = node.nexts[path];
            }
            return node.end > 0;
        }

        public boolean startsWith(String prefix) {
            if (prefix == null) {
                return true;
            }
            char[] str = prefix.toCharArray();
            int path = 0;
            Node node = root;
            for (int index = 0; index < str.length; index++) {
                path = str[index] - 'a';
                if (node.nexts[path] == null) {
                    return false;
                }
                node = node.nexts[path];
            }
            return node.pass > 0;
        }

        public static class Node {
            Node[] nexts;
            int end;
            int pass;

            public Node() {
                nexts = new Node[26];
            }
        }
    }
}
