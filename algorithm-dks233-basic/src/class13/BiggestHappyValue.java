package class13;

import java.util.ArrayList;
import java.util.List;

/**
 * 员工的最大快乐值
 *
 * @author dks233
 * @create 2022-04-30-20:26
 */
public class BiggestHappyValue {
    public static class Employee {
        public int happy;
        public List<Employee> subordinates;

        public Employee(int happy, List<Employee> subordinates) {
            this.happy = happy;
            this.subordinates = subordinates;
        }

        public Employee(int happy) {
            this.happy = happy;
            this.subordinates = new ArrayList<>();
        }
    }

    /**
     * 设每轮头节点为head，子节点为1 2 3
     * 情况1：head来
     * 最大收益=1不来的最大收益+2不来的最大收益+3不来的最大收益
     * 情况2：head不来
     * 最大收益=max(1不来的收益，1来的收益)+max(2不来的收益，2来的收益)+max(3不来的收益，3来的收益)
     *
     * @param head 大老板
     * @return 最大快乐值
     */
    public static int getBiggestHappyValue(Employee head) {
        if (head == null) {
            return 0;
        }
        return Math.max(process(head).come, process(head).notCome);
    }

    public static Info process(Employee head) {
        if (head == null) {
            return new Info(0, 0);
        }
        int notCome = 0;
        int come = head.happy;
        for (Employee subordinate : head.subordinates) {
            Info info = process(subordinate);
            // head不来，直属下级可来可不来
            notCome += Math.max(info.notCome, info.come);
            // head来，直属员工不来
            come += info.notCome;
        }
        return new Info(notCome, come);
    }

    public static class Info {
        int notCome; // 不来的收益
        int come; // 来的收益

        public Info(int notCome, int come) {
            this.notCome = notCome;
            this.come = come;
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (getBiggestHappyValue(boss) != comparator(boss)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.subordinates.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    // 对数器
    public static int comparator(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process1(Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (Employee next : cur.subordinates) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.subordinates) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }
}
