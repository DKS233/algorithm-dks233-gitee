package class09;

import common.LinkedListUtils;
import common.LinkedListUtils.Node;

import java.util.ArrayList;

/**
 * leetcode234：回文链表
 * 给定一个单链表的头节点head，请判断该链表是否是回文结构
 * 方法1：复制链表到list中，双指针判断是否为回文结构
 * 方法2：快慢指针法，将链表后半部分翻转，然后前半部分和后半部分进行比较，比较完成后链表恢复原样
 * 分析：如果是奇数长度（包括头节点），前半部分为[0,mid]，也可以看成[0,mid-1]，如果是偶数长度，前半部分为[0,上中点]
 *
 * @author dks233
 * @create 2022-04-18-11:09
 */
public class IsPalindromeLinkedList {

    // 方法1：复制链表到list中，双指针判断是否为回文结构
    public static boolean isPalindromeOne(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (list.get(left).data != list.get(right).data) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // 方法2：快慢指针法，本题中奇数长度选择前半部分为[0,mid]，偶数长度选择前半部分为[0,上中点]
    // 注：对应快慢指针寻找链表中点的第二种情况：奇数长度返回中点，偶数长度返回上中点
    public static boolean isPalindromeTwo(Node head) {
        // 先找到前半部分的结尾
        if (head == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        // 循环结束时slow的位置就是前半部分的结尾
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 反转后半部分链表，返回新的后半部分链表的头节点
        Node secondHalfPartFirst = reverseSecondHalfPart(slow.next);
        // 还原链表并返回结果
        boolean result = isEquals(head, secondHalfPartFirst);
        slow.next = reverseSecondHalfPart(secondHalfPartFirst);
        return result;
    }

    // headOne：前半部分头节点
    // headTwo：后半部分头节点
    public static boolean isEquals(Node headOne, Node headTwo) {
        // 偶数长度：前后两部分长度相等
        // 奇数长度：前半部分多了个中点
        while (headTwo != null) {
            if (headOne.data != headTwo.data) {
                return false;
            }
            headOne = headOne.next;
            headTwo = headTwo.next;
        }
        return true;
    }

    public static Node reverseSecondHalfPart(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int maxValue = 4;
        int maxLen = 2333;
        boolean isSuccess = true;
        for (int i = 0; i < testTimes; i++) {
            ArrayList<Node> nodeList = LinkedListUtils.randomNodeList(maxLen, maxValue);
            boolean palindromeOne = isPalindromeOne(nodeList.get(0));
            boolean palindromeTwo = isPalindromeTwo(nodeList.get(0));
            if (palindromeOne != palindromeTwo) {
                isSuccess = false;
                break;
            }
        }
        System.out.println(isSuccess ? "测试成功" : "测试失败");
    }
}
