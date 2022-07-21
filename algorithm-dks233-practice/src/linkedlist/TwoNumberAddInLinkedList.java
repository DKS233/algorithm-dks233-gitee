package linkedlist;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;

/**
 * 剑指offer专项突击版：剑指 Offer II 025. 链表中的两数相加
 *
 * @author dks233
 * @create 2022-07-20-18:11
 */
public class TwoNumberAddInLinkedList {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode addTwoNumbers(ListNode headOne, ListNode headTwo) {
        ListNode dummyHead = new ListNode(-1);
        // 遍历两个链表，统计两个链表中的数字
        ListNode curOne = headOne;
        ListNode curTwo = headTwo;
        List<Integer> listOne = new ArrayList<>();
        List<Integer> listTwo = new ArrayList<>();
        while (curOne != null) {
            listOne.add(curOne.val);
            curOne = curOne.next;
        }
        while (curTwo != null) {
            listTwo.add(curTwo.val);
            curTwo = curTwo.next;
        }
        // 计算两个链表中的数字和
        StringBuilder builder = new StringBuilder();
        int indexOne = listOne.size() - 1;
        int indexTwo = listTwo.size() - 1;
        int carry = 0;
        while (indexOne >= 0 || indexTwo >= 0) {
            int one = 0;
            int two = 0;
            int sum = 0;
            if (indexOne >= 0) {
                one = listOne.get(indexOne);
            }
            if (indexTwo >= 0) {
                two = listTwo.get(indexTwo);
            }
            sum += one + two + carry;
            builder.append(sum % 10);
            carry = sum / 10;
            indexOne--;
            indexTwo--;
        }
        // 根据链表和将字符串内的值传到新链表中
        if (carry == 1) {
            builder.append(1);
        }
        builder.reverse();
        String str = builder.toString();
        ListNode cur = dummyHead;
        for (int index = 0; index < str.length(); index++) {
            cur.next = new ListNode(str.charAt(index) - '0');
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
