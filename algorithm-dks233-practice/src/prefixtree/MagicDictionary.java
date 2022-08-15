package prefixtree;

import java.util.HashMap;

/**
 * 剑指offer专项突击版：剑指 Offer II 064. 神奇的字典
 *
 * @author dks233
 * @create 2022-08-15-12:12
 */
public class MagicDictionary {
    public static class Node {
        int pass; // 多少字符串经过节点
        int end; // 多少字符串以node为结尾
        HashMap<Integer, Node> map; // 表示该节点接下来指向那个节点，key:字符ASCII码值，value：node

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.map = new HashMap<>();
        }
    }

    Node root;

    public MagicDictionary() {
        root = new Node();
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            add(word);
        }
    }

    public void add(String word) {
        int path = 0;
        Node node = root;
        node.pass++;
        char[] str = word.toCharArray();
        for (int index = 0; index < str.length; index++) {
            path = str[index];
            if (!node.map.containsKey(path)) {
                node.map.put(path, new Node());
            }
            node = node.map.get(path);
            node.pass++;
        }
        node.end++;
    }

    public boolean search(String searchWord) {
        return process(searchWord, root, 0, false);
    }

    /**
     * @param word        需要查找的单词
     * @param node        当前遍历的节点
     * @param index       当前遍历到的索引位置
     * @param isCorrected 某一个位置是否被修改
     * @return node开始修改一个字符能否符合条件
     */
    public boolean process(String word, Node node, int index, boolean isCorrected) {
        if (index == word.length()) {
            return isCorrected && node.end > 0;
        }
        int key = word.charAt(index);
        if (node.map.containsKey(key)) {
            if (process(word, node.map.get(key), index + 1, isCorrected)) {
                return true;
            }
        }
        if (!isCorrected) {
            for (Integer singleKey : node.map.keySet()) {
                if (singleKey != key) {
                    if (process(word, node.map.get(singleKey), index + 1, true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}