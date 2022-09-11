package other;

import java.util.Arrays;

/**
 * leetcode451. 根据字符出现频率排序
 *
 * @author dks233
 * @create 2022-09-11-21:20
 */
@SuppressWarnings("ALL")
public class SortByCharacterOccurrenceFrequency {
    public String frequencySort(String s) {
        // 统计每个字符出现的次数
        int[] counts = new int[128];
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            counts[str[i]]++;
        }
        // 需要根据字符出现的次数将对应的字符拼接起来
        // 用一个对象将字符和字符出现的次数联系起来，然后根据出现次数倒序，拼接字符
        Node[] nodes = new Node[128];
        for (int i = 0; i < counts.length; i++) {
            nodes[i] = new Node(counts[i], i);
        }
        Arrays.sort(nodes, (a, b) -> b.count - a.count);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].count; j++) {
                builder.append((char) nodes[i].c);
            }
        }
        return builder.toString();
    }

    public static class Node {
        int count;
        int c;

        public Node(int count, int c) {
            this.count = count;
            this.c = c;
        }
    }
}
