package linkedlist;

import common.ArrayUtils;
import common.LinkedListUtils.Node;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 剑指offer06：从尾到头打印链表
 * 方法1：链表反转，遍历打印
 * 方法2：借助栈实现
 *
 * @author dks233
 * @create 2022-04-19-14:47
 */
public class PrintLinkedList {
    // 方法1：链表反转，然后遍历打印
    // 注：观察head==null情况
    public static int[] reverseOne(Node head) {
        int length = 0;
        Node next = null;
        Node pre = null;
        // 循环结束pre就是新的头节点
        while (head != null) {
            length++;
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        int[] arr = new int[length];
        int i = 0;
        while (pre != null) {
            arr[i++] = pre.data;
            pre = pre.next;
        }
        return arr;
    }

    // 方法2：借助栈实现
    // 注：观察head==null的情况
    public static int[] reverseTwo(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        int[] arr = new int[stack.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop().data;
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxLen = 233;
        int maxValue = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
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
            int[] one = reverseOne(listOne.get(0));
            int[] two = reverseTwo(listTwo.get(0));
            if (!ArrayUtils.isEquals(one, two)) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
