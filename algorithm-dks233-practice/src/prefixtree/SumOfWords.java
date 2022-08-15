package prefixtree;

/**
 * 剑指offer专项突击版：剑指 Offer II 066. 单词之和
 *
 * @author dks233
 * @create 2022-08-15-17:54
 */
public class SumOfWords {
    public static class MapSum {
        Node root;

        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int val) {
            Node node = root;
            char[] str = key.toCharArray();
            int path = 0;
            for (int index = 0; index < str.length; index++) {
                path = str[index] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
            }
            if (node.end > 0) {
                node.value = val;
            } else {
                node.value = val;
                node.end++;
            }
        }

        // 先定位到prefix对应的节点处，然后向下累加
        public int sum(String prefix) {
            Node node = root;
            char[] str = prefix.toCharArray();
            int path = 0;
            for (int index = 0; index < str.length; index++) {
                path = str[index] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
            }
            return getSum(node);
        }

        public int getSum(Node node) {
            int sum = node.value;
            for (Node next : node.nexts) {
                if (next != null) {
                    sum = sum + getSum(next);
                }
            }
            return sum;
        }

        public static class Node {
            int end; // 有多少字符串以node为尾
            int value; // 键值
            Node[] nexts;

            public Node() {
                this.end = 0;
                this.value = 0;
                this.nexts = new Node[26];
            }
        }
    }

}
