package linkedlist;

/**
 * leetcode160. 相交链表
 *
 * @author dks233
 * @create 2022-05-26-10:46
 */
public class IntersectingLinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 设链表1长度为a，公共区域长度为c，公共区域前长度为a-c
    // 设链表2长度为b，公共区域长度为c，公共区域前长度为b-c
    // a+(b-c)=b+(a-c)，让一个节点走a，走完a走b，一个节点走b，走完b走a，如果有相交节点一定会相遇
    // 如果没有相交节点，节点a走完a+b，节点b走完a+b，最终都变null，说明没有相交节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curOne = headA;
        ListNode curTwo = headB;
        while (curOne != curTwo) {
            if (curOne != null) {
                curOne = curOne.next;
            } else {
                curOne = headB;
            }
            if (curTwo != null) {
                curTwo = curTwo.next;
            } else {
                curTwo = headA;
            }
            if (curOne == curTwo) {
                return curOne;
            }
        }
        return curOne;
    }
}
