package class08;

import java.util.HashMap;

/**
 * 前缀树
 * 对字符串进行增删改查
 *
 * @author dks233
 * @create 2022-04-11-20:52
 */
@SuppressWarnings("ALL")
public class PrefixTree {
    // 使用于字符串由小写字母a~z构成的情况
    public static class NodeOne {
        public int pass; // 有多少条路径到达此节点
        public int end; // 有多少条路径以此节点为结尾
        public NodeOne[] nexts;

        public NodeOne() {
            pass = 0;
            end = 0;
            // 一个节点最多有26条路
            // 0->25 对应 a->z
            // nexts[path]==null 说明path方向的路不存在
            // nexts[path]!=null 说明path方向的路存在
            // 方向path可以表示为char[i]-'a'，a->z的path对应0->25
            nexts = new NodeOne[26];
        }
    }

    public static class PrefixTreeOne {
        private NodeOne root;

        public PrefixTreeOne() {
            root = new NodeOne();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            NodeOne node = root;
            // 头节点pass++
            node.pass++;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                // 根据字符决定走哪条路(0~25)
                path = chars[i] - 'a';
                // path方向的路不存在，新建一条
                if (node.nexts[path] == null) {
                    node.nexts[path] = new NodeOne();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        // 查看word插入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            NodeOne node = root;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        // 有几个字符串以word做前缀
        public int prefixNumber(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            NodeOne node = root;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }

        // 从树中删除word（执行一次delete方法删除一个word）
        public void delete(String word) {
            if (word == null || search(word) == 0) {
                return;
            }
            char[] chars = word.toCharArray();
            NodeOne node = root;
            node.pass--;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                // 防止内存泄漏
                // node下一个节点pass--后如果pass为0
                // 说明字符串删除完后已经没有路径经过此节点
                // 直接删除节点，后续节点断开连接
                if (--node.nexts[path].pass == 0) {
                    node.nexts[path] = null;
                    return;
                }
                // node跳到下个节点
                node = node.nexts[path];
            }
            node.end--;
        }
    }

    // 字符串种类很多时，用HashMap的形式表示下级的路径
    // HashMap<Integer,NodeTwo> Integer为ASCII码值
    public static class NodeTwo {
        public int pass;
        public int end;
        public HashMap<Integer, NodeTwo> nexts;

        public NodeTwo() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class PrefixTreeTwo {
        private NodeTwo root;

        public PrefixTreeTwo() {
            root = new NodeTwo();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            NodeTwo node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                // char的ASCII值作为路径方向
                path = chars[i];
                // path方向没有节点，就新建一个节点
                if (!node.nexts.containsKey(path)) {
                    node.nexts.put(path, new NodeTwo());
                }
                node = node.nexts.get(path);
                node.pass++;
            }
            node.end++;
        }

        // 树中word插入了多少次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            NodeTwo node = root;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i];
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.end;
        }

        // 树中有多少个字符串以word为前缀
        public int prefixNumber(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            NodeTwo node = root;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i];
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.pass;
        }

        // 从树中删除一个word
        public void delete(String word) {
            if (word == null || search(word) == 0) {
                return;
            }
            char[] chars = word.toCharArray();
            NodeTwo node = root;
            node.pass--;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i];
                if (--node.nexts.get(path).pass == 0) {
                    node.nexts.remove(path);
                    return;
                }
                node = node.nexts.get(path);
            }
            node.end--;
        }
    }

    public static class ComparatorPrefixTree {
        // key->字符串 value->字符串数量
        private final HashMap<String, Integer> hashMap;

        public ComparatorPrefixTree() {
            hashMap = new HashMap<>();
        }

        public void insert(String word) {
            if (!hashMap.containsKey(word)) {
                hashMap.put(word, 1);
            } else {
                hashMap.put(word, hashMap.get(word) + 1);
            }
        }

        public int search(String word) {
            if (!hashMap.containsKey(word)) {
                return 0;
            } else {
                return hashMap.get(word);
            }
        }

        public int prefixNumber(String word) {
            int ans = 0;
            for (String s : hashMap.keySet()) {
                if (s.startsWith(word)) {
                    ans += hashMap.get(s);
                }
            }
            return ans;
        }

        public void delete(String word) {
            if (hashMap.containsKey(word)) {
                hashMap.put(word, hashMap.get(word) - 1);
                if (hashMap.get(word) == 0) {
                    hashMap.remove(word);
                }
            }
        }
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLen = 30; // 字符串最大长度
        int limit = 2333; // 字符串数组最大长度
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            String[] strings = genereteStringArray(limit, maxLen);
            PrefixTreeOne prefixTreeOne = new PrefixTreeOne();
            PrefixTreeTwo prefixTreeTwo = new PrefixTreeTwo();
            ComparatorPrefixTree comparatorPrefixTree = new ComparatorPrefixTree();
            for (int j = 0; j < strings.length; j++) {
                String word = strings[j];
                double random = Math.random();
                if (random < 0.25) {
                    prefixTreeOne.insert(word);
                    prefixTreeTwo.insert(word);
                    comparatorPrefixTree.insert(word);
                } else if (random < 0.5) {
                    int searchOne = prefixTreeOne.search(word);
                    int searchTwo = prefixTreeTwo.search(word);
                    int searchThree = comparatorPrefixTree.search(word);
                    if (!isEquals(searchOne, searchTwo, searchThree)) {
                        isSuccess = false;
                        break;
                    }
                } else if (random < 0.75) {
                    int searchOne = prefixTreeOne.prefixNumber(word);
                    int searchTwo = prefixTreeTwo.prefixNumber(word);
                    int searchThree = comparatorPrefixTree.prefixNumber(word);
                    if (!isEquals(searchOne, searchTwo, searchThree)) {
                        isSuccess = false;
                        break;
                    }
                } else {
                    prefixTreeOne.delete(word);
                    prefixTreeTwo.delete(word);
                    comparatorPrefixTree.delete(word);
                }
            }
            if (!isSuccess) {
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    // 单字符串最大长度
    public static String generateString(int maxLen) {
        int len = (int) (Math.random() * (maxLen)) + 1;
        char[] chars = new char[len];
        for (int i = 0; i < chars.length; i++) {
            // 测试数据太大了，生成的字符串几乎不可能重复
            // chars[i] = (char) (97 + (int) (Math.random() * 6));
            chars[i] = (char) (97 + (int) (Math.random() * 6));
        }
        return String.valueOf(chars);
    }

    // 字符串数组最大长度：limit
    // 单字符串最大长度：maxLen
    public static String[] genereteStringArray(int limit, int maxLen) {
        int length = (int) (Math.random() * limit) + 1;
        String[] strings = new String[length];
        for (int i = 0; i < length; i++) {
            strings[i] = generateString(maxLen);
        }
        return strings;
    }

    public static boolean isEquals(int a, int b, int c) {
        return a == b && b == c;
    }
}
