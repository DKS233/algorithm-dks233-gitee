package tree;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 排队的最大快乐值
 * https://www.nowcoder.com/practice/a5f542742fe24181b28f7d5b82e2e49a?tpId=101&&tqId=33255&rp=1&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 *
 * @author dks233
 * @create 2022-08-20-22:04
 */
public class MaximumHappinessOfParty {
    public static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int root = scanner.nextInt();
            Node[] nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                int value = scanner.nextInt();
                nodes[i] = new Node(value);
            }
            // 设置每个节点的直接父节点
            for (int i = 0; i < n - 1; i++) {
                int father = scanner.nextInt();
                int sub = scanner.nextInt();
                nodes[father - 1].subs.add(nodes[sub - 1]);
            }
            // 计算头节点来与不来的最大快乐值
            System.out.println(getMaxHappyValue(nodes[root - 1]));
        }

        public static int getMaxHappyValue(Node head) {
            if (head == null) {
                return 0;
            }
            Info info = process(head);
            return Math.max(info.come, info.notCome);
        }

        // 以head为头的子树可以获得的最大快乐值
        public static Info process(Node head) {
            if (head == null) {
                return new Info(0, 0);
            }
            int come = head.happy;
            int notCome = 0;
            // head不来，子节点可以来可以不来
            // head来，子节点不能来
            for (Node sub : head.subs) {
                Info subInfo = process(sub);
                notCome += Math.max(subInfo.come, subInfo.notCome);
                come += subInfo.notCome;
            }
            return new Info(come, notCome);
        }

        public static class Info {
            int come;
            int notCome;

            public Info(int come, int notCome) {
                this.come = come;
                this.notCome = notCome;
            }
        }

        public static class Node {
            int happy;
            ArrayList<Node> subs;

            public Node(int happy) {
                this.happy = happy;
                this.subs = new ArrayList<>();
            }
        }
    }
}
