package class09;

import common.ArrayUtils;

import java.util.ArrayList;

/**
 * 快慢指针求链表中点
 * 图解：快慢指针求链表中点.drawio
 *
 * @author dks233
 * @create 2022-04-17-21:14
 */
public class LinkedListMidPoint {
    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    // 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
    public static Node midOrUpMid(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
    public static Node midOrDownMid(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node upMidUpUpMid(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node upMidUpDownMid(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 对数器：奇数长度返回中点，偶数长度返回上中点
    public static Node comparatorOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        // list.size和中点索引关系
        // 8->3 7->3 2->0 1->0
        if (list.size() % 2 == 0) {
            return list.get(list.size() / 2 - 1);
        } else {
            return list.get(list.size() / 2);
        }
    }

    // 对数器：奇数长度返回中点，偶数长度返回下中点
    public static Node comparatorTwo(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        ArrayList<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        // list.size和中点索引关系
        // 8->4 7->3 2->1 1->0
        return list.get(list.size() / 2);
    }

    // 对数器：奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node comparatorThree(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        // list.size和中点索引关系
        // 8->2 7->2 2->null 1->null
        if (list.size() % 2 == 0) {
            return list.get(list.size() / 2 - 2);
        } else {
            return list.get(list.size() / 2 - 1);
        }
    }

    // 对数器：奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node comparatorFour(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        // list.size和中点索引关系
        // 8->3 7->2 2->0 1->null
        return list.get(list.size() / 2 - 1);
    }

    public static void main(String[] args) {
        int testTimes = 10000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        boolean isSuccessCompareToZuoOne = true;
        boolean isSuccessCompareToZuoTwo = true;
        for (int i = 0; i < testTimes; i++) {
            // 自己的对数器和方法测试
            int len = (int) (Math.random() * (maxLen)) + 1;
            ArrayList<Node> list = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                int value = ArrayUtils.randomNumber(maxValue);
                Node node = new Node(value);
                list.add(node);
            }
            for (int k = 0; k < list.size() - 1; k++) {
                list.get(k).next = list.get(k + 1);
            }
            list.get(list.size() - 1).next = null;
            if (!isEquals(list)) {
                isSuccess = false;
                break;
            }
            // 和左程云的对比测试一
            if (!isEqualsCompareToZuoOne(list)) {
                isSuccessCompareToZuoOne = false;
                break;
            }
            // 和左程云对比测试二
            if (!isEqualsCompareToZuoTwo()) {
                isSuccessCompareToZuoTwo = false;
                break;
            }
        }
        System.out.println(isSuccess ? "小段测试成功" : "小段测试失败");
        System.out.println(isSuccessCompareToZuoOne ? "和左程云对比测试一成功" : "和左程云对比测试一失败");
        System.out.println(isSuccessCompareToZuoTwo ? "和左程云对比测试二成功" : "和左程云对比测试二失败");
    }

    public static boolean isEquals(ArrayList<Node> list) {
        Node nodeOne = midOrUpMid(list.get(0));
        Node nodeTwo = midOrDownMid(list.get(0));
        Node nodeThree = upMidUpUpMid(list.get(0));
        Node nodeFour = upMidUpDownMid(list.get(0));
        Node comparatorOne = comparatorOne(list.get(0));
        Node comparatorTwo = comparatorTwo(list.get(0));
        Node comparatorThree = comparatorThree(list.get(0));
        Node comparatorFour = comparatorFour(list.get(0));
        return nodeOne == comparatorOne && nodeTwo == comparatorTwo
                && nodeThree == comparatorThree && nodeFour == comparatorFour;
    }

    public static boolean isEqualsCompareToZuoOne(ArrayList<Node> list) {
        Node nodeOne = ZuoLinkedListMidPoint.midOrUpMidNode(list.get(0));
        Node nodeTwo = ZuoLinkedListMidPoint.midOrDownMidNode(list.get(0));
        Node nodeThree = ZuoLinkedListMidPoint.midOrUpMidPreNode(list.get(0));
        Node nodeFour = ZuoLinkedListMidPoint.midOrDownMidPreNode(list.get(0));
        Node comparatorOne = comparatorOne(list.get(0));
        Node comparatorTwo = comparatorTwo(list.get(0));
        Node comparatorThree = comparatorThree(list.get(0));
        Node comparatorFour = comparatorFour(list.get(0));
        return nodeOne == comparatorOne && nodeTwo == comparatorTwo
                && nodeThree == comparatorThree && nodeFour == comparatorFour;
    }

    public static boolean isEqualsCompareToZuoTwo() {
        int value = (int) (Math.random() * 2333 + 1);
        Node test = null;
        test = new Node(value);
        test.next = new Node(value + 1);
        test.next.next = new Node(value + 2);
        test.next.next.next = new Node(value + 3);
        test.next.next.next.next = new Node(value + 4);
        test.next.next.next.next.next = new Node(value + 5);
        test.next.next.next.next.next.next = new Node(value + 6);
        test.next.next.next.next.next.next.next = new Node(value + 7);
        test.next.next.next.next.next.next.next.next = new Node(value + 8);
        Node nodeOne = ZuoLinkedListMidPoint.midOrUpMidNode(test);
        Node nodeTwo = ZuoLinkedListMidPoint.midOrDownMidNode(test);
        Node nodeThree = ZuoLinkedListMidPoint.midOrUpMidPreNode(test);
        Node nodeFour = ZuoLinkedListMidPoint.midOrDownMidPreNode(test);
        Node myNodeOne = midOrUpMid(test);
        Node myNodeTwo = midOrDownMid(test);
        Node myNodeThree = upMidUpUpMid(test);
        Node myNodeFour = upMidUpDownMid(test);
        return nodeOne == myNodeOne && nodeTwo == myNodeTwo
                && nodeThree == myNodeThree && nodeFour == myNodeFour;
    }

}















