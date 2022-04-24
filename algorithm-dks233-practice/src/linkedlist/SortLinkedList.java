package linkedlist;

/**
 * leetcode148：排序链表
 * 给链表头节点，将链表升序排列并返回新的头节点
 * TODO：方法1是自顶向下的方法，空间复杂度为O(logN)
 * TODO：自底向上空间复杂度可以达到O(1)
 *
 * @author dks233
 * @create 2022-04-24-16:55
 */
public class SortLinkedList {
    // 方法1：归并排序：快慢指针
    // 时间复杂度：O(N*logN)
    // 空间复杂度：O(logN)
    public static ListNode sortLinkedListOne(ListNode head) {
        // 如果当前链表有0个节点或者1个节点就不需要排序
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针，奇数返回中点，偶数返回上中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 当前链表切分出两个链表，递归排序
        // 两部分的排序结果进行merge
        ListNode next = slow.next;
        slow.next = null;
        ListNode headOne = sortLinkedListOne(head);
        ListNode headTwo = sortLinkedListOne(next);
        return merge(headOne, headTwo);
    }

    // 对两个分链表进行merge
    // 注意null的边界条件
    private static ListNode merge(ListNode headOne, ListNode headTwo) {
        // 两链表头节点不确定，用虚拟头节点
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        // headOne和headTwo都没有越界
        while (headOne != null && headTwo != null) {
            if (headOne.val < headTwo.val) {
                cur.next = headOne;
                headOne = headOne.next;
            } else {
                cur.next = headTwo;
                headTwo = headTwo.next;
            }
            cur = cur.next;
        }
        // headTwo越界
        if (headOne != null) {
            cur.next = headOne;
        }
        // headOne越界
        if (headTwo != null) {
            cur.next = headTwo;
        }
        return dummyHead.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

}
