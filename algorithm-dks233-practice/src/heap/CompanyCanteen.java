package heap;

import java.io.*;
import java.util.PriorityQueue;

/**
 * 美团笔试题：公司食堂
 * 题目链接：https://www.nowcoder.com/questionTerminal/601815bea5544f389bcd20fb5ebca6a8?answerType=1&f=discussion
 *
 * @author dks233
 * @create 2022-08-31-0:08
 */
@SuppressWarnings("ALL")
public class CompanyCanteen {
    public static class Main {
        // 思路：准备存0和1的小根堆，然后男女按照优先级选取小根堆元素
        // 选取后弹出元素，0弹出后加入1，1弹出后无后续
        public static void main(String[] args) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            int t = Integer.parseInt(reader.readLine());
            for (int i = 0; i < t; i++) {
                int n = Integer.parseInt(reader.readLine());
                String str1 = reader.readLine();
                // 准备两个小根堆：存0和存1
                PriorityQueue<Node> zeroHeap = new PriorityQueue<>((node1, node2) -> node1.index - node2.index);
                PriorityQueue<Node> oneHeap = new PriorityQueue<>((node1, node2) -> node1.index - node2.index);
                for (int j = 0; j < str1.length(); j++) {
                    int value = str1.charAt(j) - '0';
                    Node node = new Node(value, j + 1);
                    if (value == 0) {
                        zeroHeap.offer(node);
                    }
                    if (value == 1) {
                        oneHeap.offer(node);
                    }
                }
                int m = Integer.parseInt(reader.readLine());
                String str2 = reader.readLine();
                for (int j = 0; j < m; j++) {
                    // 男性，优先选1，然后选0
                    if (str2.charAt(j) == 'M') {
                        if (!oneHeap.isEmpty()) {
                            writer.write(String.valueOf(oneHeap.poll().index));
                            writer.newLine();
                        } else {
                            Node node = zeroHeap.poll();
                            node.value++;
                            oneHeap.offer(node);
                            writer.write(String.valueOf(node.index));
                            writer.newLine();
                        }
                    }
                    // 女性，优先选0，然后选1
                    else {
                        if (!zeroHeap.isEmpty()) {
                            Node node = zeroHeap.poll();
                            node.value++;
                            oneHeap.offer(node);
                            writer.write(String.valueOf(node.index));
                            writer.newLine();
                        } else {
                            writer.write(String.valueOf(oneHeap.poll().index));
                            writer.newLine();
                        }
                    }
                }
            }
            writer.flush();
        }

        public static class Node {
            int value;
            int index;

            public Node(int value, int index) {
                this.value = value;
                this.index = index;
            }
        }
    }
}
