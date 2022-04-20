package class09;

import common.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * 复制链表
 * 要求：时间复杂度O(N)，空间复杂度O(1)
 * 题目见onenote
 *
 * @author dks233
 * @create 2022-04-19-19:25
 */
public class CopyLinkedList {
    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 233;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            ArrayList<ArrayList<Node>> nodeListList = generateTwoNodeList(maxLen, maxValue);
            ArrayList<Node> listOne = nodeListList.get(0);
            ArrayList<Node> listTwo = nodeListList.get(1);
            Node nodeOne = copyLinkedListOne(listOne.get(0));
            Node nodeTwo = copyLinkedListTwo(listTwo.get(0));
            while (nodeOne != null) {
                if (nodeOne.data != nodeTwo.data) {
                    isSuccess = false;
                    break;
                }
                nodeTwo = nodeTwo.next;
                nodeOne = nodeOne.next;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    // 方法1：准备hashmap，key是原节点，value是复制后的节点
    public static Node copyLinkedListOne(Node head) {
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            hashMap.put(cur, new Node(cur.data));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            hashMap.get(cur).next = hashMap.get(cur.next);
            hashMap.get(cur).rand = hashMap.get(cur.rand);
            cur = cur.next;
        }
        return hashMap.get(head);
    }

    // 方法2：将复制后的节点放在next上，最后再将整体调整回来
    public static Node copyLinkedListTwo(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 先设置next
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.data);
            cur.next.next = next;
            cur = next;
        }
        // 再设置rand
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            cur.next.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        // 将新旧链表还原
        cur = head;
        Node copy = null;
        Node copyHead = head.next;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return copyHead;
    }

    public static class Node {
        public int data;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return data == node.data &&
                    Objects.equals(next, node.next) &&
                    Objects.equals(rand, node.rand);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, next, rand);
        }
    }

    // 生成两个节点data值相同的list
    public static ArrayList<ArrayList<Node>> generateTwoNodeList(int maxLen, int maxValue) {
        ArrayList<Node> listOne = new ArrayList<>();
        ArrayList<Node> listTwo = new ArrayList<>();
        int len = (int) (Math.random() * (maxLen)) + 1;
        int j = 0;
        for (j = 0; j < len; j++) {
            int value = ArrayUtils.randomNumber(maxValue);
            Node nodeOne = new Node(value);
            Node nodeTwo = new Node(value);
            listOne.add(nodeOne);
            listTwo.add(nodeTwo);
        }
        for (j = 0; j < listOne.size() - 1; j++) {
            listOne.get(j).next = listOne.get(j + 1);
            listTwo.get(j).next = listTwo.get(j + 1);
        }
        listOne.get(listOne.size() - 1).next = null;
        listTwo.get(listTwo.size() - 1).next = null;
        ArrayList<ArrayList<Node>> nodeListList = new ArrayList<>();
        nodeListList.add(listOne);
        nodeListList.add(listTwo);
        return nodeListList;
    }
}
