package linkedlist;

import common.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 剑指offer35：复杂链表复制
 * 每个节点:val+next+random(指向链表的任意节点或者null)
 *
 * @author dks233
 * @create 2022-04-21-10:50
 */
public class CopyComplexLinkedList {
    // 方法1：用HashMap存原链表的节点和复制后链表的节点
    // 时间复杂度：O(N)，额外空间复杂度：O(N)
    public static Node copyOne(Node head) {
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node cur = head;
        // hashMap初始化，给val赋值
        while (cur != null) {
            hashMap.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        // hashMap中节点next和random赋值
        while (cur != null) {
            hashMap.get(cur).next = hashMap.get(cur.next);
            hashMap.get(cur).random = hashMap.get(cur.random);
            cur = cur.next;
        }
        return hashMap.get(head);
    }

    // 方法2：用next连接原链表节点和复制后链表的节点
    // 原链表：1->2->3->null
    // 复制后链表：1->1'->2->2'->3->3'->null
    // 时间复杂度：O(N)，额外空间复杂度：O(1)
    public static Node copyTwo(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        // 新旧节点连接
        // 每个节点后面加个copy节点，给节点val赋值
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        // 每个新节点random赋值
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = next;
        }
        // 新旧链表分离(每个节点next赋值)
        // 1->1'->2->2'->3->3' => 1->2->3 + 1'+2'+3'
        cur = head;
        Node copy = null;
        Node copyHead = head.next;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next == null ? null : next.next;
            cur = next;
        }
        return copyHead;
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 23;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            ArrayList<ArrayList<Node>> nodeListList = randomNodeListList(maxLen, maxValue);
            ArrayList<Node> nodeListOne = nodeListList.get(0);
            ArrayList<Node> nodeListTwo = nodeListList.get(1);
            Node copyOne = copyOne(nodeListOne.get(0));
            Node copyTwo = copyTwo(nodeListTwo.get(0));
            for (int j = 0; j < nodeListOne.size(); i++) {
                if (copyOne == null && copyTwo == null) {
                    break;
                }
                if (copyOne == null) {
                    isSuccess = false;
                    break;
                }
                if (copyOne.random.val != copyTwo.random.val) {
                    isSuccess = false;
                    break;
                }
                if (copyOne.val != copyTwo.val) {
                    isSuccess = false;
                    break;
                }
                copyOne = copyOne.next;
                copyTwo = copyTwo.next;
            }
            if (!isSuccess) {
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }

    public static ArrayList<ArrayList<Node>> randomNodeListList(int maxLen, int maxValue) {
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
            int randomIndex = (int) (Math.random() * listOne.size());
            listOne.get(j).next = listOne.get(j + 1);
            listTwo.get(j).next = listTwo.get(j + 1);
            listOne.get(j).random = listOne.get(randomIndex);
            listTwo.get(j).random = listTwo.get(randomIndex);
        }
        listOne.get(listOne.size() - 1).next = null;
        listOne.get(listOne.size() - 1).random = listOne.get(0);
        listTwo.get(listTwo.size() - 1).next = null;
        listTwo.get(listTwo.size() - 1).random = listTwo.get(0);
        ArrayList<ArrayList<Node>> nodeListList = new ArrayList<>();
        nodeListList.add(listOne);
        nodeListList.add(listTwo);
        return nodeListList;
    }
}
